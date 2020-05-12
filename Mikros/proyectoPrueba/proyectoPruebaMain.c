
//using ADC1, IN10 from PC0 (trimmer) - TRIMMER -> PIN: PC0-ra konektatuta

#include <ctype.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>

#include "ourRccGpio.h"
#include "sysClockandTick.h"
#include "ourExti.h"
#include "ourAdc.h"
#include "ourCom.h"

#define EXTI_HASIERA 0x40013C00
#define EXTI_PR_OFFSET 0x14

#define USED_COM_PORT COM1
#define N_LEDS 4
#define N_DIGITOS_PASSWORD 4


uint32_t ledPins[N_LEDS]={6,7,8,9};

uint32_t password[N_DIGITOS_PASSWORD];
uint32_t pos_password;

enum estado {ESPERA, ENVIO, LECTURA};

void initGPIO(void);
void turnOffAllLeds(void);
uint32_t blockingReadFromUart(COM com, uint8_t *pMsg, uint8_t endChar, uint8_t maxSize);
void resetPassword();

int main(void)
{
  int i , n=0; // n para la lectura
  uint32_t val;
	char str_temp[128];
	char str[128];
	enum estado estado_actual;
	char str_sarrera[] = "mufitDeberiaHaberIdoAlPBLDay\r\n";
	
	pos_password = 0;
	estado_actual = ESPERA;
	
	initSysTick(1000);
	initCom(USED_COM_PORT,9600);	
	enablePA0interruptOnExti0WhenRising();
  initGPIO();
	initAdc01();
	
	writeToUart(USED_COM_PORT, (uint8_t*)str_sarrera , strlen(str_sarrera));
	
  for(;;)
  {
		// TODO : egoera bakoitzeko zatia funtzio batean jarri, txukunago egoteko
		
		if (estado_actual == ESPERA){
			while (pos_password < N_DIGITOS_PASSWORD){
				val=sinchronousGetSample(); 
				val=val>>8; 
				for(i=0;i<N_LEDS;i++){
					setGpioPinValue(GPIOF, ledPins[i],val & (0x01<<i));
					password[pos_password] = val;
				}
			}
			estado_actual = ENVIO;
		}
		else if (estado_actual == ENVIO){
			turnOffAllLeds(); // TODO : Luz naranja parpadeando mientras llega la respuesta?
			for (i = 0; i < N_DIGITOS_PASSWORD; i++){
				sprintf(str_temp, "%01X\r", password[i]);
				strcat(str, str_temp);
			}
		   	str[strlen(str)] = '\n';			
				writeToUart(USED_COM_PORT, (uint8_t*) str, strlen(str));
			estado_actual = LECTURA;
		}
		else if (estado_actual == LECTURA){
			
			i=blockingReadFromUart(USED_COM_PORT,(uint8_t *)(str+n), '$',5);
			
			if(i!=0)
			{
				n+=i;
				if(str[n-1]=='$')
				{
					if (strcmp(str, "OK$")){
						setGpioPinValue(GPIOF, ledPins[0], 1);
						writeToUart(USED_COM_PORT,(uint8_t *)str ,strlen(str));
						strcpy(str, "");
					}
					else if (strcmp(str, "EZ$")){
						setGpioPinValue(GPIOF, ledPins[2], 1);
						writeToUart(USED_COM_PORT,(uint8_t *)str ,strlen(str));
						strcpy(str, "");
					}
					
				
					n=0;
					
					for (i = 0; i < 2000000; i++);
					
					//for (i=0; i < 4; i++) waitSysTick();
					
					resetPassword();
					turnOffAllLeds();
        	estado_actual = ESPERA;

				}
				
				
			}
			
		}
    for(i=0;i<2;i++) waitSysTick();
  }
}

void initGPIO(void)
{
  uint32_t i;
  
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOC, 1);
  initGpioPinMode(GPIOC, 0, GPIO_Mode_AN);
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
	for(i=0;i<N_LEDS;i++)
		initGpioPinMode(GPIOF, ledPins[i], GPIO_Mode_OUT) ;
}

uint32_t blockingReadFromUart(COM com, uint8_t *pMsg, uint8_t endChar, uint8_t maxSize)
{
  uint32_t n=0, byteCount=0;
  
  do
	{
		n=readFromUart(com, pMsg+byteCount, maxSize-byteCount);
    byteCount+=n;
	}while((pMsg[byteCount-1]!=endChar) && (byteCount<maxSize));
	
	return byteCount;
}

void resetPassword(){
	int i;
	for (i = 0; i < N_DIGITOS_PASSWORD; i++) password[i] = 0;
	pos_password = 0;
}

void turnOffAllLeds(void){
	int i;
	for (i = 0; i < N_LEDS; i++) setGpioPinValue(GPIOF, ledPins[i], 0);
	
}


// TODO? -> Vaciar el vector password  (pos_password = 0) al darle al TAMPER

void ourExti0Handler(void)
{
  uint32_t *p;

  p=(uint32_t*)(EXTI_HASIERA+EXTI_PR_OFFSET);
  *p|=0x01;
  
	if (pos_password < N_DIGITOS_PASSWORD) pos_password += 1;
 
}

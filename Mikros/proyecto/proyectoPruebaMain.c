
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


#define USED_COM_PORT COM1
#define N_LEDS 4
#define N_DIGITOS_PASSWORD 4

uint32_t ledPins[N_LEDS]={6,7,8,9};

uint32_t password[N_DIGITOS_PASSWORD];
uint32_t pos_password;

enum estado {ESPERA, ENVIO, LECTURA};

void initGPIO(void);
void turnOffAllLeds(void);
uint32_t blockingReadFromUart(uint8_t *pMsg, uint8_t endChar, uint8_t maxSize);
void resetPassword(void);

int main(void)
{
  int i , n=0; // n para la lectura
  uint32_t val;
	char str_temp[128];
	char str[128];
	char str2[128];
	enum estado estado_actual;
	char str_sarrera[] = "wellcome\n";
	
	pos_password = 0;
	estado_actual = ESPERA;
	
	initSysTick(1000);
	hasieratuUsart6();
	//initCom(USED_COM_PORT,9600);	
	enablePA0interruptOnExti0WhenRising();
  initGPIO();
	initAdc01();
	
	USART_idatzi((uint8_t*)str_sarrera , strlen(str_sarrera));
	
  for(;;)
  {
		// TODO : egoera bakoitzeko zatia funtzio batean jarri, txukunago egoteko
		
		if (estado_actual == ESPERA){
			//USART_idatzi((uint8_t*) "estado espera\n", 14);
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
		//	USART_idatzi((uint8_t*) "estado envio\n", 13);
			turnOffAllLeds(); // TODO : Luz naranja parpadeando mientras llega la respuesta?
			for (i = 0; i < N_DIGITOS_PASSWORD; i++){
				sprintf(str_temp, "%01X\r", password[i]);
				strcat(str, str_temp);
			}
		   	str[strlen(str)] = '\n';			
				USART_idatzi((uint8_t*) str, strlen(str));
				strcpy(str, "\0");
		   	estado_actual = LECTURA;
		}
		else if (estado_actual == LECTURA){
		//	USART_idatzi((uint8_t*) "estado lectura\n", 15);
			str2[0] = USART_irakurri();
			//i=blockingReadFromUart((uint8_t *)(str2+n), '$',20);
			while (!strlen(str2));
			if(i!=0)
			{
				n+=i;
				//if(str2[n-1]=='$')				{
					if (str2[0] == 'N'){
						setGpioPinValue(GPIOF, ledPins[2], 1);
						USART_idatzi((uint8_t*)"lol no\n", 7);		
					}
					if (str2[0] == 'K'){
						setGpioPinValue(GPIOF, ledPins[0], 1);
		        USART_idatzi((uint8_t*)"lol yes\n", 8);
					}
				  i = 0;
					n=0;
					//for (i = 0; i < 3; i++) waitSysTick();
					for (i = 0; i < 2000000; i++);
					resetPassword();
					turnOffAllLeds();
        	estado_actual = ESPERA;
				//}			
			}
		}

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

uint32_t blockingReadFromUart( uint8_t *pMsg, uint8_t endChar, uint8_t maxSize)
{
  uint32_t n=0, byteCount=0;
  do
	{
		n = USART_irakurri();
		//n=USART_irakurri( pMsg+byteCount, maxSize-byteCount);
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

// TODO : Mugitu bi handlerrak OurExti.c-ra

void ourExti0Handler(void){
	EXTI->PR |= 0x01; 
	if (pos_password < N_DIGITOS_PASSWORD) pos_password += 1;
}

void ourExti13Handler(void){
	EXTI->PR |= 0x01<<13;
	resetPassword();
}

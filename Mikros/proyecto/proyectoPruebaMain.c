
//using ADC1, IN10 from PC0 (trimmer) - TRIMMER -> PIN: PC0-ra konektatuta

#include <ctype.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>
#include "sysClockandTick.h"
#include "ourRccGpio.h"
#include "ourExti.h"
#include "ourAdc.h"
#include "ourCom.h"
#include "ourUsart.h"
#include "ourBuffer.h"

#define USED_COM_PORT COM1
#define N_LEDS 4
#define N_DIGITOS_PASSWORD 4

uint32_t ledPins[N_LEDS]={6,7,8,9};

uint32_t password[N_DIGITOS_PASSWORD];
uint32_t pos_password;

enum estado {INIT, ESCRIBIR_CONTRASENA, ENVIAR_CONTRASENA, COMPROBAR_CONTRASENA, INTERIOR};

enum tipoPuerta {RECEPCION, HABITACION};

volatile enum estado estado_actual;
volatile 	int estadoHabitacion;

void initGPIO(void);
void turnOffAllLeds(void);
void resetPassword(void);
void cambiarEstadoHabitacion(void);

int main(void)
{
  int i ;
  uint32_t val;
	char str_temp[128];
	char str[128];
	char str2[128];
	
	enum tipoPuerta puerta;
	char str_sarrera[] = "MUFit al PBLDay\n";
	
	pos_password = 0;
	estado_actual = INIT;
	
	initSysTick(1000);
	initBuffer();
	hasieratuUSART(USED_COM_PORT, 9600); 	
	enablePA0interruptOnExti0WhenRising();
  initGPIO();
	initAdc01();	
	USART_idatzi(USED_COM_PORT, (uint8_t *) str_sarrera, strlen(str_sarrera));
	
  for(;;)
  {
		switch(estado_actual){
			case INIT:
			USART_irakurriBufferrera(USED_COM_PORT, (uint8_t*) str, 20, '$');	
			jasoBufferretik(USED_COM_PORT, (uint8_t*) str2, 20);
			if (strcmp(str2, "Puerta recepcion") == 0){
				puerta = RECEPCION;
			}
			else if (strcmp(str2, "Puerta habitacion") == 0){
				puerta = HABITACION;
			}
			estado_actual = ESCRIBIR_CONTRASENA;
			
			break;
			case ESCRIBIR_CONTRASENA:

			while (pos_password < N_DIGITOS_PASSWORD){
				val=sinchronousGetSample(); 
				val=val>>8; 
				for(i=0;i<N_LEDS;i++){
					setGpioPinValue(GPIOF, ledPins[i],val & (0x01<<i));
					password[pos_password] = val;
				}
			}
			estado_actual = ENVIAR_CONTRASENA;
			break;
			case ENVIAR_CONTRASENA:
		
			turnOffAllLeds(); // TODO : Luz naranja parpadeando mientras llega la respuesta?
			str[0] = '\0';
			for (i = 0; i < N_DIGITOS_PASSWORD; i++){
				sprintf(str_temp, "%01X", password[i]);
				strcat(str, str_temp);
			}
		   	str[strlen(str)] = '$';
				str[strlen(str)] = '\0';
				USART_idatzi(USED_COM_PORT, (uint8_t*) str, strlen(str));
				strcpy(str, "\0");
		   	estado_actual = COMPROBAR_CONTRASENA;
			break;
			case COMPROBAR_CONTRASENA:
      // $- arte irakurtzen
			USART_irakurriBufferrera(USED_COM_PORT, (uint8_t*) str, 20, '$');	
			jasoBufferretik(USED_COM_PORT, (uint8_t*) str2, 20);
			if (strcmp(str2, "Error")== 0){
				setGpioPinValue(GPIOF, ledPins[2], 1);
			}
			if (strcmp(str2, "Correcto")== 0){
				setGpioPinValue(GPIOF, ledPins[0], 1);
			}
			//for (i = 0; i < 3; i++) waitSysTick();
			for (i = 0; i < 2000000; i++);
			resetPassword();
			turnOffAllLeds();
			if (puerta == RECEPCION) estado_actual = ESCRIBIR_CONTRASENA;
			else if (puerta == HABITACION) estado_actual = INTERIOR;
			break;
			case INTERIOR:

			USART_irakurriBufferrera(USED_COM_PORT, (uint8_t*)str2, 20, '$');
			jasoBufferretik(USED_COM_PORT, (uint8_t*) str2, 20);
			
			if (strcmp(str2, "Limpieza SI")==0) estadoHabitacion = 1;
			else estadoHabitacion = 0;
			while(estado_actual == INTERIOR){
				setGpioPinValue(GPIOF, ledPins[1], estadoHabitacion);
			}
			break;
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


void resetPassword(){
	int i;
	for (i = 0; i < N_DIGITOS_PASSWORD; i++) password[i] = 0;
	pos_password = 0;
}

void turnOffAllLeds(void){
	int i;
	for (i = 0; i < N_LEDS; i++) setGpioPinValue(GPIOF, ledPins[i], 0);
}

void cambiarEstadoHabitacion(void){
	estadoHabitacion = !estadoHabitacion;
}

// TODO : Mugitu bi handlerrak OurExti.c-ra

void ourExti0Handler(void){
	EXTI->PR |= 0x01; 
	if (estado_actual ==  ESCRIBIR_CONTRASENA && pos_password < N_DIGITOS_PASSWORD) 
		pos_password += 1;
	else if (estado_actual == INTERIOR) cambiarEstadoHabitacion();
}

void ourExti13Handler(void){
	EXTI->PR |= 0x01<<13;
	if (estado_actual ==  ESCRIBIR_CONTRASENA) resetPassword();
	else if (estado_actual == INTERIOR) estado_actual = ESCRIBIR_CONTRASENA;
}

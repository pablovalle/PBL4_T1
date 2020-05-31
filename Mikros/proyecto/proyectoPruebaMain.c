
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

#define USED_COM_PORT COM1
#define N_LEDS 4
#define N_DIGITOS_PASSWORD 4

#define MENSAJE_RESET "reset$"
#define MENSAJE_LIMPIEZA_SI "para hacer$"
#define MENSAJE_LIMPIEZA_NO "hecho$"

uint32_t ledPins[N_LEDS]={6,7,8,9};

//uint32_t password[N_DIGITOS_PASSWORD];
uint32_t adcValue;
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
	char str[128];
	char str2[128];
	char str3[128];
	int estadoAnterior;
	enum tipoPuerta puerta;
	char str_sarrera[] = MENSAJE_RESET;
	
	pos_password = 0;
	estado_actual = INIT;
	

	
	initSysTick(1000);
	initGPIO();
	initAdc01();	
	initBuffer();
	hasieratuUSART(USED_COM_PORT, 9600); 	
	enablePA0interruptOnExti0WhenRising();
	USART_write(USED_COM_PORT, (uint8_t *) str_sarrera, strlen(str_sarrera));
	
  for(;;)
  {
		switch(estado_actual){
			case INIT: //---------------------------------------------
			if (getEndCharReceived(0)){
				readBuffer((uint8_t*) str2);
				if (strcmp(str2, "Puerta recepcion") == 0){
					puerta = RECEPCION;
				}
				else if (strcmp(str2, "Puerta habitacion") == 0){
					puerta = HABITACION;
				}
				estado_actual = ESCRIBIR_CONTRASENA;
		  }
			break;
			
			case ESCRIBIR_CONTRASENA: //-------------------------------
			while (pos_password < N_DIGITOS_PASSWORD){
				adcValue=sinchronousGetSample(); 
				adcValue=adcValue>>8; 
				for(i=0;i<N_LEDS;i++){
					setGpioPinValue(GPIOF, ledPins[i],adcValue & (0x01<<i));
				}
			}
			estado_actual = ENVIAR_CONTRASENA;
			break;
			
			case ENVIAR_CONTRASENA: //----------------------------------
			turnOffAllLeds(); 
			str[0] = '\0';
			sendBufferTx(USED_COM_PORT);
			strcpy(str, "\0");
			estado_actual = COMPROBAR_CONTRASENA;
			clearBuffer(0);
			break;
			
			case COMPROBAR_CONTRASENA: //---------------------------------
      // $- arte irakurtzen
			if (getEndCharReceived(0)){
				readBuffer((uint8_t*) str2);
				USART_write(USED_COM_PORT, (uint8_t*) str2, strlen(str2));
				if (strcmp(str2, "Error")== 0){
					setGpioPinValue(GPIOF, ledPins[2], 1);
					estado_actual = ESCRIBIR_CONTRASENA;
				}
				if (strcmp(str2, "Correcto")== 0){
					setGpioPinValue(GPIOF, ledPins[0], 1);
					if (puerta == RECEPCION) estado_actual = ESCRIBIR_CONTRASENA;
					else if (puerta == HABITACION) estado_actual = INTERIOR;
				}
				for (i = 0; i < 2000000; i++);
				//for (i = 0; i < 2; i++) waitSysTick();
				resetPassword();
				turnOffAllLeds();
		  }
			break;
			
			case INTERIOR: //------------------------------------			
			estadoHabitacion = 0;
			estadoAnterior = estadoHabitacion;
			while(estado_actual == INTERIOR){
				setGpioPinValue(GPIOF, ledPins[1], estadoHabitacion);
				if (estadoAnterior!=estadoHabitacion){
					strcpy(str3, (estadoHabitacion == 1)? MENSAJE_LIMPIEZA_SI : MENSAJE_LIMPIEZA_NO);
					USART_write(USED_COM_PORT, (uint8_t*)str3, strlen(str3));
					estadoAnterior = estadoHabitacion;
				}
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

void salirHabitacion(){
	char str[128] = "Ha salido de la habitacion$";
	USART_write(USED_COM_PORT, (uint8_t*) str, strlen(str));
}

void resetPassword(){
	clearBuffer(1);
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
	if (estado_actual ==  ESCRIBIR_CONTRASENA && pos_password < N_DIGITOS_PASSWORD){
		addToBuffer(1, adcValue + 48);
		pos_password++;
	}
	else if (estado_actual == INTERIOR) cambiarEstadoHabitacion();
}

void ourExti13Handler(void){
	EXTI->PR |= 0x01<<13;
	if (estado_actual ==  ESCRIBIR_CONTRASENA) resetPassword();
	else if (estado_actual == INTERIOR) {
		salirHabitacion();
		estado_actual = ESCRIBIR_CONTRASENA;
	}
}

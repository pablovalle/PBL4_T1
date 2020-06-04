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
#define MENSAJE_LIMPIEZA_SI "tarea si$"
#define MENSAJE_LIMPIEZA_NO "tarea no$"
#define MENSAJE_ERROR "Error$"
#define MENSAJE_CORRECTO "Correcto$"
#define MENSAJE_PUERTA_RECEPCION "Puerta recepcion$"
#define MENSAJE_PUERTA_HABITACION "Puerta habitacion$"
#define MENSAJE_SALIR_HABITACION "Ha salido de la habitacion$"

#define RED_LED_PIN 8
#define ORANGE_LED_PIN 7
#define GREEN_LED_PIN 6

uint32_t ledPins[N_LEDS]={6,7,8,9};

uint32_t password[N_DIGITOS_PASSWORD];
uint32_t adcValue;
uint32_t pos_password = 0;

enum estado {INIT, ESCRIBIR_CONTRASENA, ENVIAR_CONTRASENA, COMPROBAR_CONTRASENA, INTERIOR, ITXI_ATEA};
enum tipoPuerta {RECEPCION, HABITACION};

volatile enum estado estado_actual;
static int estadoHabitacion = 0;

void initGPIO(void);
void turnOffAllLeds(void);
void resetPassword(void);
void cambiarEstadoHabitacion(void);

int main(void)
{
  int i ;
	char str[128];
	int estadoAnterior;
	enum tipoPuerta puerta;
	
	char str_frogak[128];

	pos_password = 0;
	estado_actual = INIT;
	
	initSysTick(1000);
	initGPIO();
	initAdc01();	
	initBuffer();
	hasieratuUSART(USED_COM_PORT, 9600); 	
	enablePA0interruptOnExti0WhenRising();
	
	strcpy(str, MENSAJE_RESET);
	USART_write(USED_COM_PORT, (uint8_t *) str, strlen(str));
	memset(str, 0, strlen(str));
  for(;;)
  {
		switch(estado_actual){
			case INIT: //---------------------------------------------
			if (getEndCharReceived()){
				readBuffer((uint8_t*) str);
				if (strcmp(str, MENSAJE_PUERTA_RECEPCION) == 0){
					puerta = RECEPCION;
				}
				else if (strcmp(str, MENSAJE_PUERTA_HABITACION) == 0){
					puerta = HABITACION;
				}
				estado_actual = ESCRIBIR_CONTRASENA;
  			initBuffer();
			}
			memset(str, 0, strlen(str));
			
			break;
			
			case ESCRIBIR_CONTRASENA: //-------------------------------
			while (pos_password < N_DIGITOS_PASSWORD){
				adcValue=sinchronousGetSample(); 
				adcValue=adcValue/8; //Nahi dugu maximoa 7 izatea
				for(i=0;i<4;i++){
					setGpioPinValue(GPIOF, ledPins[i],adcValue & (0x01<<i));
				}
				
			}
			estado_actual = ENVIAR_CONTRASENA;
			break;
			
			case ENVIAR_CONTRASENA: //----------------------------------
			turnOffAllLeds(); 
			memset(str, 0, strlen(str));
			for (i = 0; i < N_DIGITOS_PASSWORD; i++){
				str[i] = password[i] + 48 ;
			}
			str[strlen(str)] = '\0'; //Amaiera jarri, gero strcat-ek jakiteko nun amaitzen den
			strcat(str, "$");
			USART_write(USED_COM_PORT, (uint8_t*)str, strlen(str));
			estado_actual = COMPROBAR_CONTRASENA;
			initBuffer();
			break;
			
			case COMPROBAR_CONTRASENA: //---------------------------------
      // $- arte irakurtzen
			if (getEndCharReceived()){
				memset(str, 0, strlen(str));
				readBuffer((uint8_t*) str);
				if (strcmp(str, MENSAJE_ERROR)== 0){
					setGpioPinValue(GPIOF, RED_LED_PIN, 1);
					estado_actual = ESCRIBIR_CONTRASENA;
				}
				if (strcmp(str, MENSAJE_CORRECTO) == 0){
					setGpioPinValue(GPIOF, GREEN_LED_PIN, 1);
					if (puerta == RECEPCION) estado_actual = ESCRIBIR_CONTRASENA;
					else if (puerta == HABITACION) estado_actual = INTERIOR;
				}
				memset(str, 0, strlen(str));
				resetPassword();
				initBuffer();
				for (i = 0; i < 2000000; i++);
				turnOffAllLeds();

		  }
			break;
			
			case INTERIOR: //------------------------------------		
			//memset(str, 0, strlen(str));				
			estadoHabitacion = 0;
			estadoAnterior = estadoHabitacion;
			while(estado_actual == INTERIOR){
				setGpioPinValue(GPIOF, ORANGE_LED_PIN, estadoHabitacion);
				if (estadoAnterior!=estadoHabitacion){
					strcpy(str, (estadoHabitacion == 1)? MENSAJE_LIMPIEZA_SI : MENSAJE_LIMPIEZA_NO);
					USART_write(USED_COM_PORT, (uint8_t*)str, strlen(str));
					memset(str, 0 , strlen(str));
					estadoAnterior = estadoHabitacion;
				}
			}
			break;
			
			case ITXI_ATEA:
				initBuffer();
				memset(str, 0, strlen(str));
			  
				estado_actual = ESCRIBIR_CONTRASENA;
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
	pos_password = 0;
}

void turnOffAllLeds(void){
	int i;
	for (i = 0; i < N_LEDS; i++) setGpioPinValue(GPIOF, ledPins[i], 0);
}

// TODO : Mugitu bi handlerrak OurExti.c-ra

void ourExti0Handler(void){
	EXTI->PR |= 0x01; 
	if (estado_actual ==  ESCRIBIR_CONTRASENA && pos_password < N_DIGITOS_PASSWORD){
		password[pos_password++] = adcValue;
	}
	else if (estado_actual == INTERIOR) estadoHabitacion = !estadoHabitacion;
}

void ourExti13Handler(void){
	EXTI->PR |= 0x01<<13;

	if (estado_actual ==  ESCRIBIR_CONTRASENA) resetPassword();
	else if (estado_actual == INTERIOR) {
		estado_actual = ITXI_ATEA;
	}
}

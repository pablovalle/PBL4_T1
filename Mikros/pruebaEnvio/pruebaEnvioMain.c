#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include "ourRccGpio.h"
#include "ourCom.h"
#include "sysClockAndTick.h"

#define LED_PIN 6

char RxComByte = 0;
uint8_t buffer[bufferTamaina];

void initLed(void);

int main(void)
{
  int i, n=0, a = 0;
	uint32_t* reg;
	char rxByte;
	char str[] = "Give Red LED control input (Y = On, N = off):\r\n";


	
  initSysTick(1000);
	initLed();
	togleGpioPinValue(GPIOF, LED_PIN);
  hasieratuUsart6();	
	USART_idatzi((uint8_t *)"MUFIT PBLDAY\n\n", 16);
  while(1){		
//    n = sprintf((char *)buffer, "a = %d\n", a);
//		USART_idatzi(buffer, n);
//		togleGpioPinValue(GPIOF, LED_PIN);
//		a += 1;
		USART_idatzi((uint8_t *)str, strlen(str));	
		rxByte = USART_irakurri();
		if (rxByte == 'N' || rxByte == 'n'){
			setGpioPinValue(GPIOF, LED_PIN, 0);
			
			USART_idatzi((uint8_t *)"LED is Off\r\n\r\n", 16);
		}
		else if (rxByte == 'Y' || rxByte == 'y'){
			setGpioPinValue(GPIOF, LED_PIN, 1);
			USART_idatzi( (uint8_t *)"LED is on\r\n\r\n", 15);
		}
		
		for(i=0;i<2;i++) waitSysTick();
	}
}


void initLed(void)
{
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
  initGpioPinMode(GPIOF, LED_PIN, GPIO_Mode_OUT);
}

#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include "ourRccGpio.h"
#include "sysClockAndTick.h"
#include "ourUsart.h"
#include "ourCom.h"

#define LED_PIN 6

#define USED_COM COM2

char RxComByte = 0;
uint8_t buffer[bufferTamaina];
void initLed(void);

int main(void)
{
  int i;
	char rxByte;
	char str[] = "Give Red LED control input (Y = On, N = off):\r\n";

  initSysTick(1000);
	initLed();
	togleGpioPinValue(GPIOF, LED_PIN);
  hasieratuUSART(USED_COM, 9600);	
	
  while(1){		

		USART_idatzi(USED_COM, (uint8_t *)str, strlen(str));	
		rxByte = USART_irakurri(USED_COM);
		if (rxByte == 'N' || rxByte == 'n'){
			setGpioPinValue(GPIOF, LED_PIN, 0);	
			USART_idatzi(USED_COM, (uint8_t *)"LED is Off\r\n\r\n", 16);
		}
		else if (rxByte == 'Y' || rxByte == 'y'){
			setGpioPinValue(GPIOF, LED_PIN, 1);
			USART_idatzi(USED_COM, (uint8_t *)"LED is on\r\n\r\n", 15);
		}
		
		for(i=0;i<2;i++) waitSysTick();
	}
}

void initLed(void)
{
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
  initGpioPinMode(GPIOF, LED_PIN, GPIO_Mode_OUT);
}

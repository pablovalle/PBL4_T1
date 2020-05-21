#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include "ourRccGpio.h"
#include "sysClockAndTick.h"
#include "ourUsart.h"
#include "ourCom.h"
#include "ourBuffer.h"

#define LED_PIN 6
#define USED_COM COM2

char RxComByte = 0;
uint8_t buffer[bufferTamaina];
void initLed(void);
uint32_t blockReadingFromUart(USART_TypeDef* usart, uint8_t* pMsg, uint8_t endChar, uint8_t maxSize);

int main(void)
{
  int i;
	char rxByte;
	char str[] = "Give Red LED control input (Y = On, N = off):\r\n";
	char str2[] = "";
  initSysTick(1000);
	initLed();
		initBuffer();
	togleGpioPinValue(GPIOF, LED_PIN);
  hasieratuUSART(USED_COM, 9600);	

  while(1){		
		
		USART_irakurriBufferrera(USED_COM, (uint8_t*)str2, 16, '$');
		idatziBufferretik(USED_COM, (uint8_t*)str2, 16);
		
		if (strcmp(str2, "Piztu argia")==0) setGpioPinValue(GPIOF, LED_PIN, 1);
		else if (strcmp(str2, "Itzali argia") == 0) setGpioPinValue(GPIOF, LED_PIN, 0);
		
		USART_idatzi(USED_COM, (uint8_t*)str2, strlen(str2));
		str2[0] = '\0';

		
	}
}

void initLed(void)
{
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
  initGpioPinMode(GPIOF, LED_PIN, GPIO_Mode_OUT);
}


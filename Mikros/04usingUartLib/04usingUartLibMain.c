#include <stdint.h>
#include <string.h>
#include <stdio.h>
#include <ctype.h>
#include "ourRccGpio.h"
#include "ourCom.h"
#include "sysClockAndTick.h"

#define LED_PIN 6
#define USED_COM_PORT COM1


void initLed(void);

int main(void)
{
  int i, n=0;
	uint32_t* reg;
	char str[128];
	
  initSysTick(1000);
	hasieratuUsart6(9600);
	
	//initCom(USED_COM_PORT,9600);
  initLed();
  while(1)
  {		
		*reg = USART6->BRR;
    sprintf(str," %zu\n",n++);
    writeToUart(USED_COM_PORT,(uint8_t *)str,strlen(str));
    togleGpioPinValue(GPIOF, LED_PIN);
		for(i=0;i<5;i++) waitSysTick();
	}
}


void initLed(void)
{
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
  initGpioPinMode(GPIOF, LED_PIN, GPIO_Mode_OUT);
}

#include "ourExti.h"
#include <stm32f407xx.h>


extern volatile uint32_t greenNotOrange;

void enablePA0interruptOnExti0WhenRising(void)
{
    
   //SYSCFG gaitu
	RCC->APB2ENR |= 0x01<<14;
	SYSCFG->EXTICR[3] &= ~(0x0F<<4);
	SYSCFG->EXTICR[3] |= 0x02<<4;
	SYSCFG->EXTICR[0] &= ~(0x0F);
  
  //exti konfiguratu
	EXTI->IMR |= 0x01;
	EXTI->RTSR |= 0x01;
	EXTI->FTSR &= ~(0x01);
	NVIC->ISER[0] |= 0x01<<6;
	
	EXTI->IMR |= 0x01<<13;
	EXTI->RTSR &= ~(0x01<<13);
	EXTI->FTSR |= 0x01<<13;
	NVIC->ISER[1] |= 0x01<<8;
}








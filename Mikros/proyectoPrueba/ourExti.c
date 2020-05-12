#include "ourExti.h"
#include <stm32f407xx.h>

#define SYSCFG_HASIERA 0x40013800
#define EXTI_HASIERA 0x40013C00
#define NVIC_HASIERA 0xE000E100
#define RCC_HASIERA 0x40023800

#define EXTICR1_OFFSET 0x08
#define EXTI_IMR_OFFSET 0x00
#define EXTI_RTSR_OFFSET 0x08
#define EXTI_FTSR_OFFSET 0x0C
#define EXTI_PR_OFFSET 0x14

#define APB2ENR_OFFSET 0x44
#define SYSCFG_EN_BIT (0x01<<14)


extern volatile uint32_t greenNotOrange;

void enablePA0interruptOnExti0WhenRising(void)
{
  uint32_t *p;
  
      //SYSCFG gaitu
  p=(uint32_t*)(RCC_HASIERA+APB2ENR_OFFSET);
  *p|=SYSCFG_EN_BIT;
	  //sysConfig configuratu (defektuzko balioen gauz bera idaztera goaz). 0 jarrita
   //	A portua aukeratuko dugo 0sarreran. Hau da PA0 EXTI0-ra.
  p=(uint32_t*)(SYSCFG_HASIERA+EXTICR1_OFFSET);
  *p &=~(0x0F);
  
  //exti konfiguratu
  p=(uint32_t*)(EXTI_HASIERA+EXTI_RTSR_OFFSET);
  *p|=0x01;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_FTSR_OFFSET);
  *p&=~0x01;
  p=(uint32_t*)(EXTI_HASIERA+EXTI_IMR_OFFSET);
  *p|=0x01;
  //NVIC konfiguratu
  p=(uint32_t*)(NVIC_HASIERA+0);
  *p|=0x01<<6;
}








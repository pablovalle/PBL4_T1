
//using ADC1, IN10 from PC0 (trimmer) - TRIMMER -> PIN: PC0-ra konektatuta
#include "ourRccGpio.h"
#include "ourAdc.h"

#define N_LEDS 4
uint32_t ledPins[N_LEDS]={6,7,8,9};


void initGPIO(void);

int main(void)
{
  int i;
  uint32_t val;
	
  initGPIO();
	initAdc01();
  for(;;)
  {
    val=sinchronousGetSample(); 
    val=val>>8; 
    for(i=0;i<4;i++) 
			//setGpioPinValue(GPIOF, ledPins[i], 1);
		  setGpioPinValue(GPIOF, ledPins[i],val & (0x01<<i));
    for(i=0;i<(200000);i++);
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


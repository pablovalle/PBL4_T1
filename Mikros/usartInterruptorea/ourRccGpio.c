#include "ourRccGpio.h"

void RCC_AHB1PeriphClockCmd(uint32_t nPeriph, uint32_t on)
{
	if(on) RCC->AHB1ENR |= nPeriph;
	else RCC->AHB1ENR &= ~nPeriph;
}

void RCC_APB1PeriphClockCmd(uint32_t nPeriph, uint32_t on)
{
	if(on) RCC->APB1ENR |= nPeriph;
	else RCC->APB1ENR &= ~nPeriph;
}

void RCC_APB2PeriphClockCmd(uint32_t nPeriph, uint32_t on)
{
	if(on) RCC->APB2ENR |= nPeriph;
	else RCC->APB2ENR &= ~nPeriph;
}

void setGpioPinOpenCollector(GPIO_TypeDef* gpio, uint32_t pin, int openCollector)
{
  if(openCollector) gpio->OTYPER|=(0x01<<pin);
  else gpio->OTYPER&=~(0x01<<pin);
}

void setGpioPinSpeed(GPIO_TypeDef* gpio, uint32_t pin,GPIOSpeed_Type speed)
{
  gpio->OSPEEDR &= ~(0x03 << (pin * 2));
  gpio->OSPEEDR |= ((uint32_t)(speed) << (pin * 2));
}

void initGpioPinMode(GPIO_TypeDef* gpio, uint32_t pin, GPIOMode_Type mode)
{
  gpio->MODER &= ~ (3<< (pin*2)); 
  gpio->MODER |= mode << (pin*2);
}

void togleGpioPinValue(GPIO_TypeDef * gpio, uint32_t pin)
{
	gpio->ODR ^= (1<<pin);
}

void setGpioPinValue(GPIO_TypeDef * gpio, uint32_t pin, uint32_t value)
{
	uint32_t rValue=0x01<<pin;
	if(!value)  rValue<<=16;
	gpio->BSRR=rValue;
}

uint32_t getGpioPinValue(GPIO_TypeDef * gpio, uint32_t pin)
{
	return (gpio->IDR & (0x01<<pin));
}

void setGpioPinAF(GPIO_TypeDef * gpio, uint32_t pin, uint32_t AF)
{
  if(pin<8)
	{
	  gpio->AFR[0] &= ~(0xF << (pin*4));
		gpio->AFR[0] |= AF << (pin*4);
	}
	else
  {
	  gpio->AFR[1] &= ~(0xF << ((pin-8)*4));
		gpio->AFR[1] |= AF << ((pin-8)*4);
	}
}


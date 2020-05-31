#ifndef OUR_RCC_H
#define OUR_RCC_H

#include <stdint.h>
#include <stm32f407xx.h>

#define RCC_AHB1Periph_GPIOA ((uint32_t)0x01)
#define RCC_AHB1Periph_GPIOC ((uint32_t)(0x01<<2))
#define RCC_AHB1Periph_GPIOD ((uint32_t)(0x01<<3))
#define RCC_AHB1Periph_GPIOF ((uint32_t)(0x01<<5))
#define RCC_AHB1Periph_GPIOG ((uint32_t)(0x01<<6))
#define RCC_APB1Periph_USART3 ((uint32_t)(0x01<<18))
#define RCC_APB2Periph_USART6 ((uint32_t)(0x01<<5))
#define RCC_AHB1Periph_TIM2 ((uint32_t)0x01)
#define RCC_AHB1Periph_TIM5 ((uint32_t)(0x01<<3))
#define RCC_APB1Periph_I2C2 ((uint32_t)(0x01<<22))
#define RCC_APB2Periph_ADC1 ((uint32_t)(0x01<<8))
#define RCC_APB1Periph_I2C2 ((uint32_t)(0x01<<22))

#define AF_TIM2 1

void RCC_AHB1PeriphClockCmd(uint32_t nPeriph, uint32_t on);
void RCC_APB1PeriphClockCmd(uint32_t nPeriph, uint32_t on);
void RCC_APB2PeriphClockCmd(uint32_t nPeriph, uint32_t on);

typedef enum
{ 
  GPIO_Mode_IN   = 0x00, /*!< GPIO Input Mode */
  GPIO_Mode_OUT  = 0x01, /*!< GPIO Output Mode */
  GPIO_Mode_AF   = 0x02, /*!< GPIO Alternate function Mode */
  GPIO_Mode_AN   = 0x03  /*!< GPIO Analog Mode */
}GPIOMode_Type;

typedef enum
{ 
  GPIO_LowSpeed   = 0x00,
  GPIO_MediumSpeed  = 0x01,
  GPIO_HighSpeed   = 0x02,
  GPIO_VeryHighSpeed   = 0x03
}GPIOSpeed_Type;


void initGpioPinMode(GPIO_TypeDef *, uint32_t pin, GPIOMode_Type mode);
void togleGpioPinValue(GPIO_TypeDef *, uint32_t pin);
void setGpioPinValue(GPIO_TypeDef *, uint32_t pin, uint32_t value);
void setGpioPinAF(GPIO_TypeDef *, uint32_t pin, uint32_t AF);
uint32_t getGpioPinValue(GPIO_TypeDef *, uint32_t pin);
void setGpioPinSpeed(GPIO_TypeDef *, uint32_t pin,GPIOSpeed_Type);
void setGpioPinOpenCollector(GPIO_TypeDef* gpio, uint32_t pin, int openCollector);

#endif

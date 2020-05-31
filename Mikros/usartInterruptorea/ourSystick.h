#ifndef SYSTICK_H
#define SYSTICK_H
#include <stdint.h>
#include <stm32f4xx.h>

//uses internalClock

void initSysTick(uint32_t ms);
uint32_t getSysClockFrequency(void);
void waitSysTick(void);
uint32_t getTime(void);
#define HwClockCount (SysTick->VAL)


#endif

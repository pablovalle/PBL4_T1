#ifndef OURADC_H
#define OURADC_H

#include <stm32f407xx.h>

void initAdc01(void);
uint32_t sinchronousGetSample(void);

#endif

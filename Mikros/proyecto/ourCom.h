#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>

#define bufferTamaina 32


typedef enum e_Com {COM1,COM2} COM;
	
void hasieratuUsart6();
uint8_t USART_irakurri(void);
void USART_idatzi(uint8_t *msg, uint32_t maxBytes);
void USART_Delay(uint32_t us);

#endif

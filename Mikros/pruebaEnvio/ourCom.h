#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>


#define bufferTamaina 32

#define COM1 USART6
#define COM2 USART3

#define USART_FLAG_RXNE 0x01<<5
#define USART_FLAG_TC 0x01<<6
#define USART_FLAG_TXE 0x01<<7


//typedef enum e_Com {COM1,COM2} COM;


uint8_t USART_irakurri(USART_TypeDef* usart);
void USART_idatzi(USART_TypeDef* usart, uint8_t *msg, uint32_t maxBytes);
void USART_Delay(uint32_t us);

#endif

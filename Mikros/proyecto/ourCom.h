#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>
#include "ourUsart.h"
#include "ourBuffer.h"


#define bufferTamaina 32

#define COM1 USART6
#define COM2 USART3

#define USART_FLAG_RXNE 0x01<<5
#define USART_FLAG_TC 0x01<<6
#define USART_FLAG_TXE 0x01<<7

//typedef enum e_Com {COM1,COM2} COM;

void readBuffer(uint8_t* msg);
void sendBufferTx(USART_TypeDef *usart);
uint8_t USART_read(USART_TypeDef *usart);
void USART_write(USART_TypeDef *usart, uint8_t* msg, uint32_t length);
void delay(uint32_t n);
void setTxRestart(int value);
void ourUSART6Handler(void);
void ourUSART3Handler(void);

#endif

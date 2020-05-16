#ifndef OUR_USART_H
#define OUR_USART_H

#include <stdint.h>
#include <stm32f407xx.h>
#include "ourRccGpio.h"

#define bufferTamaina 32

#define USART6_TX 6
#define USART6_RX 9
#define USART6_AF 8

#define USART3_TX 8
#define USART3_RX 9
#define USART3_AF 7

#define USART_EN 0x01<<13
#define USART_TX_EN 0x01<<3
#define USART_RX_EN 0x01<<2
#define FRQ_MHZ 1000000
#define USART_SR_FLAG_RXNE 0x01<<5
#define USART_M 0x01<<12
#define USART_PAR 0x01<<10
#define USART_RXNEIE 0x01<<6

#define USART_FLAG_TXE 0x01<<7

void hasieratuUSART6GPIO(void);
void hasieratuUSART3GPIO(void);
void hasieratuUSART(USART_TypeDef* usart, uint32_t baudRate);

#endif 

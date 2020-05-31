#ifndef FUNTZIOAK_H
 #define FUNTZIOAK_H
 
#include <ctype.h>
#include <string.h>
#include <stdint.h>
#include <stdio.h>
#include <stm32f407xx.h>
#include "ourRccGpio.h"
#include "ourSystick.h"
#include "ourBuffer.h"

#define bufferTamaina 32

#define USART6_TX 6
#define USART6_RX 9
#define USART6_AF 8

#define USART_EN 0x01<<13
#define USART_TX_EN 0x01<<3
#define USART_RX_EN 0x01<<2
#define FRQ_MHZ 1000000
#define USART_SR_FLAG_RXNE 0x01<<5
#define USART_M 0x01<<12
#define USART_PAR 0x01<<10
#define USART_RXNEIE 0x01<<5

#define USART_FLAG_TXE 0x01<<7

#define RCC_USART6_EN 0x01<<5

void hasieratuUSART(uint32_t baudRate);
void hasieratuUSART6GPIO(void);

void initGPIO(void);
void readBuffer(uint8_t* msg);
uint8_t USART_read();
void USART_write( uint8_t* msg, uint32_t length);
void delay(uint32_t n);
void enviarChar(uint8_t c);
void ourUSART6Handler(void);
void ourUSART3Handler(void);
 

 
#endif

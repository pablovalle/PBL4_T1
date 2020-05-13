#include "ourCom.h"
#include "ourRccGpio.h"


/*
	PC6 -> USART3_TX (AF8)
	PC7 -> USART3_RX (AF8)
	
*/

#define PIN_TX 6
#define PIN_RX 7
#define PIN_RTS 9
#define AF_8 8
#define USART_EN 0x01<<13
#define USART_TX_EN 0x01<<3
#define USART_RX_EN 0x01<<2

#define FRQ_MHZ 1000000
#define USART_SR_FLAG_RXNE 0x01<<5

void hasieratuUsart6(uint32_t baudRate){
	
	RCC->APB2ENR |= RCC_APB2ENR_USART6EN;
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIOCEN | 0x01<<20 | RCC_AHB1ENR_GPIOGEN;
	
	USART6->CR1 &= ~(USART_EN); 	
		
	initGpioPinMode(GPIOC, PIN_TX, GPIO_Mode_AF);
	//initGpioPinMode(GPIOC, PIN_RX, GPIO_Mode_AF);
	initGpioPinMode(GPIOG, PIN_RTS, GPIO_Mode_AF );
	setGpioPinAF(GPIOC, PIN_TX, AF_8);
	//setGpioPinAF(GPIOC, PIN_RX, AF_8);
	setGpioPinAF(GPIOG, PIN_RTS, AF_8);
	
	USART6->BRR = 0x682;  //FRQ_MHZ/baudRate;
	USART6->CR1 &= ~(0x01<<12); // 8 bits
	USART6->CR1 &= ~(0x01<<10); // parity none

	USART6->CR1 |= USART_SR_FLAG_RXNE;
	//An USART interrupt is generated whenever ORE=1 or RXNE=1 in the USART_SR register
	
	USART6->CR1 |= USART_EN | USART_TX_EN | USART_RX_EN;
	
	//USART6 INTERRUPT 71 
	NVIC->ISER[71/32] |= 0x01<<(71%32);
	
}



void gureUsart6_IRQ_Handlerra(void){
	uint32_t USART6_SR;
	USART6_SR = USART6->SR;
	
	if (USART6_SR & USART_SR_FLAG_RXNE){
	//	USART6->SR &= ~USART_SR_FLAG_RXNE;
	}

}
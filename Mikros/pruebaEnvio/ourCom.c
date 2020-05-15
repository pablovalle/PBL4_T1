#include "ourCom.h"
#include "ourRccGpio.h"

#define PIN_TX 6
#define PIN_RX 9
#define AF_8 8
#define USART_EN 0x01<<13
#define USART_TX_EN 0x01<<3
#define USART_RX_EN 0x01<<2
#define FRQ_MHZ 1000000
#define USART_SR_FLAG_RXNE 0x01<<5
#define USART_FLAG_TXE 0x01<<7

/*
	USART6_RX => PG9, AF8
	USART6_TX => PC6, AF8
*/


void hasieratuUsart6(){
	
	RCC->APB2ENR |= RCC_APB2ENR_USART6EN;
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIOCEN  | RCC_AHB1ENR_GPIOGEN;
	
	USART6->CR1 &= ~(USART_EN); 	
		
	initGpioPinMode(GPIOC, PIN_TX, GPIO_Mode_AF);
	initGpioPinMode(GPIOG, PIN_RX, GPIO_Mode_AF );
	setGpioPinAF(GPIOC, PIN_TX, AF_8);
	setGpioPinAF(GPIOG, PIN_RX, AF_8);
	
	USART6->BRR = 0x682;  //FRQ_MHZ/baudRate;
	USART6->CR1 &= ~(0x01<<12); // 8 bits
	USART6->CR1 &= ~(0x01<<10); // parity none
	USART6->CR1 |= 0x01<<6;
	
	//An USART interrupt is generated whenever ORE=1 or RXNE=1 in the USART_SR register
	
	USART6->CR1 |= USART_EN | USART_TX_EN | USART_RX_EN;

	//USART6 INTERRUPT 71 
	//NVIC->ISER[71/32] |= 0x01<<(71%32);
}

uint8_t USART_irakurri(void){
	while( !(USART6->SR & USART_SR_RXNE));
	//DR irakurtzek ya RXNE flag-a aldatzen du (ondorengo pausuan)
	return (uint8_t) (USART6->DR&0xFF);  //DR-ko azkeneko 8 bitak hartzeko
}

void USART_idatzi(uint8_t *msg, uint32_t maxBytes){
	int i;
	
	for (i = 0; i < maxBytes; i++){
		while (!(USART6->SR & USART_SR_TXE));		
		USART6->DR = msg[i] & 0xFF;
		USART_Delay(300);
	} 
	while (!(USART6->SR & USART_SR_TC));
	USART6->SR &= ~(USART_SR_TC);
}

void USART_Delay(uint32_t us) {
	uint32_t time = 100*us/7;    
	while(--time);   
}

void USART6_IRQHandler(uint8_t *msg, uint32_t *pRx_counter){
	if (USART6->SR & USART_SR_RXNE){ //Se han recibido los datos
		msg[*pRx_counter] = USART6->DR;
		(*pRx_counter)++;
		if ((*pRx_counter) >= bufferTamaina){
			(*pRx_counter) = 0;
		}
	}
	else if(USART6->SR & USART_SR_TXE){
		//while(1);
	}
	else if (USART6->SR & USART_SR_ORE){
		while(1);
	}
	else if(USART6->SR & USART_SR_PE){
		while(1);
	}
	else if(USART6->SR & USART_SR_NE){
		while(1);
	}

}


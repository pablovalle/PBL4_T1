#include "ourCom.h"

volatile int tx_restart = 1;

uint8_t USART_read(USART_TypeDef* usart){
	while( !(usart->SR & USART_FLAG_RXNE));
	//DR irakurtzek ya RXNE flag-a aldatzen du (ondorengo pausuan)
	return (uint8_t) (usart->DR&0xFF);  //DR-ko azkeneko 8 bitak hartzeko
}

void readBuffer(uint8_t* msg){
	int i = 0;
	while(!isBufferEmpty()){
		msg[i] = readFromBuffer();
		i++;
 	}
	initBuffer();
}


void USART_write(USART_TypeDef *usart, uint8_t* msg, uint32_t length){
	int i;
	for (i = 0; i < length; i++){
		while(!(usart->SR & USART_SR_TXE));
		usart->DR = msg[i] & 0xFF;
		delay(DELAY);
	}
	while(!(usart->SR & USART_SR_TC));
	usart->SR &= ~(USART_SR_TC);
}

void delay(uint32_t n){
	uint32_t time = 100*n/7;    
	while(--time); 
}

void ourUSART3Handler(){		
	if (USART3->SR & USART_SR_RXNE){
		char c =  USART3->DR & 0xFF;
		addToBuffer(c);
	}
	else if (USART3->SR & USART_SR_TXE){
		//DR-an idazterakoan garbituko da
	}
	else if (USART3->SR & USART_SR_ORE) // Overrun Error
		USART3->SR &= ~(USART_SR_ORE);
	else if (USART3->SR & USART_SR_NE) // Noise Error
		USART3->SR &= ~(USART_SR_NE);
	else if (USART3->SR & USART_SR_FE) // Framing Error
		USART3->SR &= ~(USART_SR_FE);
	else if(USART3->SR & USART_SR_PE)	// Parity Error
		USART3->SR &= ~(USART_SR_PE);
}

void ourUSART6Handler(void){	
	if (USART6->SR & USART_SR_RXNE){
		char c =  USART6->DR & 0xFF;
		addToBuffer(c);
	}
	else if (USART6->SR & USART_SR_TXE){
		//DR-an idazterakoan garbituko da
	}
	else if (USART6->SR & USART_SR_ORE) // Overrun Error
		USART6->SR &= ~(USART_SR_ORE);
	else if (USART6->SR & USART_SR_NE) // Noise Error
		USART6->SR &= ~(USART_SR_NE);
	else if (USART6->SR & USART_SR_FE) // Framing Error
		USART6->SR &= ~(USART_SR_FE);
	else if(USART6->SR & USART_SR_PE)	// Parity Error
		USART6->SR &= ~(USART_SR_PE);
}

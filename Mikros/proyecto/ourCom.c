#include "ourCom.h"

volatile int tx_restart = 1;

uint8_t USART_irakurri(USART_TypeDef* usart){
	while( !(usart->SR & USART_FLAG_RXNE));
	//DR irakurtzek ya RXNE flag-a aldatzen du (ondorengo pausuan)
	return (uint8_t) (usart->DR&0xFF);  //DR-ko azkeneko 8 bitak hartzeko
}

void readBuffer(uint8_t* msg){
	int i = 0;
	while(!isBufferEmpty(0)){
		msg[i] = readFromBuffer(0);
		i++;
 	}
	clearBuffer(0);
}


void sendBufferTx(USART_TypeDef *usart){
	addToBuffer(1, '$');
	if(tx_restart){
		tx_restart = 0;
		usart->CR1 |= USART_FLAG_TXE;
	}
}

uint8_t USART_read(USART_TypeDef *usart){
	while(!(usart->SR & USART_SR_RXNE));
	return (uint8_t) usart->DR & 0xFF;
}

void setTxRestart(int value){
	tx_restart = value;
}

void USART_write(USART_TypeDef *usart, uint8_t* msg, uint32_t length){
	int i;
	for (i = 0; i < length; i++){
		while(!(usart->SR & USART_SR_TXE));
		usart->DR = msg[i] & 0xFF;
		delay(300);
	}
	while(!(usart->SR & USART_SR_TC));
	usart->SR &= ~(USART_SR_TC);
	clearBuffer(0);
}

void delay(uint32_t n){
	uint32_t time = 100*n/7;    
	while(--time); 
}

void ourUSART3Handler(){		
	if (USART3->SR & USART_SR_RXNE){
		char c =  USART3->DR & 0xFF;
		addToBuffer(0,c);
	}
	else if (USART3->SR & USART_SR_TXE){
		USART3->SR &= ~(USART_FLAG_TXE);
		if (getBufferHead(1) != getBufferTail(1)){
			USART3->DR = readFromBuffer(1);
			tx_restart = 0;
		}
		else{
			tx_restart = 1;
			clearBuffer(1);
			USART3->CR1 &= ~(USART_FLAG_TXE);
		}
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
		addToBuffer(0,c);
	}
	else if (USART6->SR & USART_SR_TXE){
		USART6->SR &= ~(USART_FLAG_TXE);
		if (getBufferHead(1) != getBufferTail(1)){
			USART6->DR = readFromBuffer(1);
			tx_restart = 0;
		}
		else{
			tx_restart = 1;
			clearBuffer(1);
			USART6->CR1 &= ~(USART_FLAG_TXE);
		}
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

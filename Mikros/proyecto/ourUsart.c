#include "ourUsart.h"

void hasieratuUSART(USART_TypeDef* usart, uint32_t baudRate){
	uint16_t kalkulua = 0;
	if (usart == USART6){
		RCC->APB2ENR |= RCC_APB2ENR_USART6EN;
		hasieratuUSART6GPIO();
		NVIC->ISER[2] |= (0x01<<7);
	}
	else if (usart == USART3){
		RCC->APB1ENR |= RCC_APB1ENR_USART3EN;
		hasieratuUSART3GPIO();
		NVIC->ISER[1] |= (0x01<<7);

	}
	
	usart->CR1 &= ~(USART_EN); 		//USART-a itzali
	
	kalkulua = FRQ_MHZ/baudRate<<4; //Mantissa
	kalkulua |= FRQ_MHZ%baudRate;   //Frakzioa
	usart->BRR = kalkulua; 				
	usart->CR1 &= ~(USART_M); 			//"Hitz" bakoitzaren tamaina 8 bit
	usart->CR1 &= ~(USART_PAR); 		//Paritaterik ez
	usart->CR1 |= USART_RXNEIE; 		//USART etendura RXNE (RX not empty) flaga 1-era jartzean
	usart->CR1 |= USART_EN | USART_TX_EN | USART_RX_EN; //USART, TX (igorlea) eta RX (hartzailea) piztu
}

void hasieratuUSART3GPIO(void){
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIODEN;
	initGpioPinMode(GPIOD, USART3_TX, GPIO_Mode_AF);
	initGpioPinMode(GPIOD, USART3_RX, GPIO_Mode_AF );
	setGpioPinAF(GPIOD, USART3_TX, USART3_AF);
	setGpioPinAF(GPIOD, USART3_RX, USART3_AF);
}

void hasieratuUSART6GPIO(void){
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIOCEN  | RCC_AHB1ENR_GPIOGEN;
	initGpioPinMode(GPIOC, USART6_TX, GPIO_Mode_AF);
	initGpioPinMode(GPIOG, USART6_RX, GPIO_Mode_AF );
	setGpioPinAF(GPIOC, USART6_TX, USART6_AF);
	setGpioPinAF(GPIOG, USART6_RX, USART6_AF);
}

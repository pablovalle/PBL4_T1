#include "ourCom.h"

uint8_t USART_irakurri(USART_TypeDef* usart){
	while( !(usart->SR & USART_FLAG_RXNE));
	//DR irakurtzek ya RXNE flag-a aldatzen du (ondorengo pausuan)
	return (uint8_t) (usart->DR&0xFF);  //DR-ko azkeneko 8 bitak hartzeko
}

void USART_idatzi(USART_TypeDef* usart, uint8_t *msg, uint32_t maxBytes){
	int i;
	for (i = 0; i < maxBytes; i++){
		while (!(usart->SR & USART_FLAG_TXE));		
		usart->DR = msg[i] & 0xFF;
		USART_Delay(300);
	} 
	while (!(usart->SR & USART_FLAG_TC));
	usart->SR &= ~(USART_FLAG_TC);
}

void USART_Delay(uint32_t us) {
	uint32_t time = 100*us/7;    
	while(--time);   
}

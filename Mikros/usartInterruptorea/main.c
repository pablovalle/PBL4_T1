#include "funtzioak.h"

volatile char * rMsg;

char cData = 0;
#define USED_COM USART6

volatile int luz = 1;
#define MAXLENGTH 10
char *received_str;

volatile int tx_restart = 1;


int main(void){
	int i = 0;
	char str[] = "Hello world";
	char str2[] = "";
	char c = 'A';
	initSysTick(1000);
	initGPIO();
	setGpioPinValue(GPIOF, 6, luz);
	bufferInit(0);
	bufferInit(1);
	hasieratuUSART(9600);
	//USART_write((uint8_t*) str, strlen(str) );

	
	while(1){
		
		setGpioPinValue(GPIOF, 6, luz);
		addToBuffer(1, c++);
		if (getEndCharReceived(0)){
			readBuffer((uint8_t*)str2);
			USART_write((uint8_t*)str2, strlen(str2)); 
			if (strcmp(str2, "Piztu") == 0) luz = !luz;
			setEndCharReceived(0,0);
			//addToBuffer(1, '$');
		}
		if (c=='M') enviarChar(c);
		for (i = 0; i < 2; i++) waitSysTick();
	}
}

void initGPIO(void){
	
  RCC_AHB1PeriphClockCmd(RCC_AHB1Periph_GPIOF, 1);
	initGpioPinMode(GPIOF, 6, GPIO_Mode_OUT) ;
}

uint8_t USART_read(){
	while(!(USART6->SR & USART_SR_RXNE));
	return (uint8_t) USART6->DR & 0xFF;
}

void enviarChar(uint8_t c){
	addToBuffer(1, c);
	if(tx_restart){
		tx_restart = 0;
		USART6->CR1 |= USART_FLAG_TXE;
	}

}

void USART_write(uint8_t* msg, uint32_t length){
	int i;
	for (i = 0; i < length; i++){
		while(!(USART6->SR & USART_SR_TXE));
		USART6->DR = msg[i] & 0xFF;
		delay(300);
	}
	while(!(USART6->SR & USART_SR_TC));
	USART6->SR &= ~(USART_SR_TC);
}

void delay(uint32_t n){
	uint32_t time = 100*n/7;    
	while(--time); 
}

void hasieratuUSART(uint32_t baudRate){
	uint16_t kalkulua = 0;
	
	RCC->APB2ENR |= RCC_APB2ENR_USART6EN;
	NVIC->ISER[2] |= 0x01<<7;
	hasieratuUSART6GPIO();
	
	USART6->CR1 &= ~(USART_EN); 		//USART-a itzali
	kalkulua = FRQ_MHZ/baudRate<<4; //Mantissa
	kalkulua |= FRQ_MHZ%baudRate;   //Frakzioa
	USART6->BRR = kalkulua; 				
	USART6->CR1 &= ~(USART_M); 			//"Hitz" bakoitzaren tamaina 8 bit
	USART6->CR1 &= ~(USART_PAR); 		//Paritaterik ez
	USART6->CR1 |= USART_RXNEIE; 		//USART etendura RXNE (RX not empty) flaga 1-era jartzean
	
	USART6->CR1 |= USART_TX_EN | USART_RX_EN; //USART, TX (igorlea) eta RX (hartzailea) piztu
	USART6->CR1 |= USART_EN;

}


void hasieratuUSART6GPIO(void){
	RCC->AHB1ENR |= RCC_AHB1ENR_GPIOCEN  | RCC_AHB1ENR_GPIOGEN;
	initGpioPinMode(GPIOC, USART6_TX, GPIO_Mode_AF);
	initGpioPinMode(GPIOG, USART6_RX, GPIO_Mode_AF );
	setGpioPinAF(GPIOC, USART6_TX, USART6_AF);
	setGpioPinAF(GPIOG, USART6_RX, USART6_AF);
}

void readBuffer(uint8_t* msg){
	int i = 0;
	while(!isBufferEmpty(0)){
		msg[i] = readFromBuffer(0);
		i++;
 	}
	bufferInit(0);
}


void ourUSART6Handler(){
	char c;	
	if (USART6->SR & USART_SR_RXNE){
		c =  USART6->DR & 0xFF;
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
			bufferInit(1);
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


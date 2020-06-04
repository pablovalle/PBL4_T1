#include "ourAdc.h"
#include "ourRccGpio.h"


void initAdc01(void){
	//ADC1,2,3 daude APB2-n
	RCC->APB2ENR|= 0x01<<8;
	//6 biteko erresoluzioa
	ADC1->CR1 |= (0x03<<24); 
	//Right alignment : datuak nola jasotzen diren (pisu txikitik handira)
	ADC1->CR2 &= ~(0x01<<11);
	//Aldaketa bakarra (ez kontinuoa)
	ADC1->CR2 &= ~(0x01<<1);
	//Zein kanaletan ematen da aldaketa hori : Kanal bakarretik 
	ADC1->SQR1 &= ~(0x0F<<20);
	//Zein konbertsiotan, zein kanal -> 1 konbertsioan (sqr3 offset gabe), 10 kanala (0x0A)
	ADC1->SQR3 |= 0x0A;
	//Zenbat flankoren ondoren egin konbertsioa? 15
	//10 kanala SMPR1ean dago, offsetik gabe
	ADC1->SMPR1|= 0x01;
	//Piztu ADC1
	ADC1->CR2 |= 0x01;  
}

uint32_t sinchronousGetSample(void)
{
	ADC1->CR2 |= 0x01 <<30; //PIZTU KONBERTSIOAK!!! 
	while(! (ADC1->SR & (0x01<<1)) ); //EOC flaga
	return ADC1->DR;
}

#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>

typedef enum e_Com {COM1,COM2} COM;

uint32_t initCom(COM com, uint32_t baudRate); 
//COM1 edo COM2, 9600 abiadura (paritatea eta gainerakoa defektuzkoak)

uint32_t readFromUart(COM com, uint8_t *pMsg, uint32_t maxLen);
//Orain arte iritsitakoa irakurri, pMsg bufferrekoa irakurri, asko jota maxLen bit

uint32_t writeToUart(COM com, uint8_t *pMsg, uint32_t len);
//Mezua bidali, pMsg bufferrekoa irakurri, len luzerako mezua

#endif

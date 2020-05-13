#ifndef OUR_COM_H
#define OUR_COM_H

#include <stdint.h>
#include <stm32f407xx.h>

typedef enum e_Com {COM1,COM2} COM;

uint32_t initCom(COM com, uint32_t baudRate);
uint32_t readFromUart(COM com, uint8_t *pMsg, uint32_t maxLen);
void blockingWriteToUart(COM com, uint8_t *pMsg, uint32_t len);
uint32_t writeToUart(COM com, uint8_t *pMsg, uint32_t len);

void hasieratuUsart6(uint32_t baudRate);


#endif

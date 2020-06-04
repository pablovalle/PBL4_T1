#ifndef OUR_BUFFER_H
#define OUR_BUFFER_H


//Buffer zirkularra

#include <stdint.h>
#include <stdio.h>

#define BUFFER_SIZE 32
#define END_CHAR '$'

void initBuffer(void);
int isBufferFull(void);
int isBufferEmpty(void);
int getBufferHead(void);
int getBufferTail(void);
uint8_t readFromBuffer(void);
void addToBuffer(uint8_t c);
int getBufferSize(void);
void setEndCharReceived (uint32_t value);
uint32_t getEndCharReceived(void);

#endif

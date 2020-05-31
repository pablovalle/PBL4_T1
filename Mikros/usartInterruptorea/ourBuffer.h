#ifndef OUR_BUFFER_H
#define OUR_BUFFER_H


//Buffer zirkularra

#include <stdint.h>
#include <stdio.h>

#define BUFFER_SIZE 32
#define END_CHAR '$'

void bufferInit(int tx);
int isBufferFull(int tx);
int isBufferEmpty(int tx);
int getBufferHead(int tx);
int getBufferTail(int tx);
uint8_t readFromBuffer(int tx);
void addToBuffer(int tx, uint8_t c);
int getBufferSize(int tx);
void setEndCharReceived (int tx, uint32_t value);
uint32_t getEndCharReceived(int tx);

#endif

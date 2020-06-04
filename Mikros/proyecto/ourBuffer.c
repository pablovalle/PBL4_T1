#include "ourBuffer.h"

typedef struct buffer_struct{
	unsigned int head;
	unsigned int tail;
	unsigned int bufferFull;
	unsigned int endCharReceived;
	char buffer[BUFFER_SIZE];
}buffer_st;


volatile buffer_st rxBuf = {0,0,};

void initBuffer(void){
	rxBuf.head = 0;
	rxBuf.tail = 0;
	rxBuf.bufferFull = 0;
	rxBuf.endCharReceived = 0;
}

int isBufferFull(void){
	return rxBuf.bufferFull;
}

int isBufferEmpty(void){
	int bufferEmpty = 0;
	if (rxBuf.head == rxBuf.tail && rxBuf.bufferFull == 0) bufferEmpty = 1;	
	return bufferEmpty;
}

void addToBuffer(uint8_t c){
	if (isBufferFull() == 1) return;
	else if (c == END_CHAR){
		rxBuf.endCharReceived = 1;
	}
	rxBuf.buffer[rxBuf.head] = c;
	rxBuf.head++;
	if (rxBuf.head == rxBuf.tail)
		rxBuf.bufferFull = 1;	
}

void setEndCharReceived (uint32_t value){
	rxBuf.endCharReceived = value;
}

uint32_t getEndCharReceived(void){
	return rxBuf.endCharReceived;
}

uint8_t readFromBuffer(void){
	uint8_t c;

	if (isBufferEmpty() == 1) return '\0';
	c = rxBuf.buffer[rxBuf.tail];
	rxBuf.tail++;
	rxBuf.bufferFull = 0;
	return c;
}

int getBufferHead(void){
	return rxBuf.head;
}
int getBufferTail(void){
	return rxBuf.tail;
}

int getBufferSize(void){
	return rxBuf.head - rxBuf.tail;
}

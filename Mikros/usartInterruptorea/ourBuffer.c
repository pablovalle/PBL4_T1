#include "ourBuffer.h"

typedef struct buffer_struct{
	unsigned int head;
	unsigned int tail;
	unsigned int bufferFull;
	unsigned int endCharReceived;
	char buffer[BUFFER_SIZE];
}buffer_st;

volatile int bufferFull = 0;
volatile int endCharReceived = 0;

static buffer_st rxBuf = {0,0,};
static buffer_st txBuf = {0,0,};

void bufferInit(int tx){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	
	p->head = 0;
	p->tail = 0;
	p->bufferFull = 0;
	p->endCharReceived = 0;
		
}

int isBufferFull(int tx){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	return p->bufferFull;
}

int isBufferEmpty(int tx){
	int bufferEmpty = 0;
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	if (p->head == p->tail && p->bufferFull == 0) bufferEmpty = 1;	
	return bufferEmpty;
}

void addToBuffer(int tx, uint8_t c){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	if (isBufferFull(tx) == 1) return;
	else if (c == END_CHAR){
		c = '\0';
		p->endCharReceived = 1;
	}
	p->buffer[p->head] = c;
	p->head = p->head + 1;
	if (p->head == p->tail)
		p->bufferFull = 1;	
}

void setEndCharReceived (int tx, uint32_t value){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	p->endCharReceived = value;
}

uint32_t getEndCharReceived(int tx){	
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	return p->endCharReceived;
}

uint8_t readFromBuffer(int tx){
	uint8_t c;
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	if (isBufferEmpty(tx) == 1) return '\0';
	c = p->buffer[p->tail];
	p->tail = p->tail +1 ;
	p->bufferFull = 0;
	return c;
}

int getBufferHead(int tx){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	return p->head;
}
int getBufferTail(int tx){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	return p->tail;
}

int getBufferSize(int tx){
	buffer_st *p;
	if(tx==1) p = &txBuf;
	else p = &rxBuf;
	return p->head - p->tail;
}



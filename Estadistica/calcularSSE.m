function [SSE]=calcularSSE(SSyy,SSxy,B_1_hipotetico)
SSE=SSyy-(B_1_hipotetico*SSxy);

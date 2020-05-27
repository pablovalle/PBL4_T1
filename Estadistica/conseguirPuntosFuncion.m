function [fx,fy]=conseguirPuntosFuncion(b1,b0, min, max)

size=(max*1000)-(min*1000);
for i=1:size
fx(1,i)=min+(i/1000);
fy(1,i)=b0+(b1*fx(1,i));
    
end


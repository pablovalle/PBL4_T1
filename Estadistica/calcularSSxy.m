function [SSxy]=calcularSSxy(X,Y, mediaX, mediaY)
SSxy=0;
for i=1:size(X,2)
    SSxy=SSxy + ((X(1,i)-mediaX)*(Y(1,i)-mediaY));
end

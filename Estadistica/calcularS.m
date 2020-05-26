function [S]=calcularS(X)
mediaX= mean(X);
suma=0;
for i=1: size(X,2)
    suma=suma+((X(1,i)-(mediaX))^2);
end
S=sqrt(suma/(size(X,2)-1));
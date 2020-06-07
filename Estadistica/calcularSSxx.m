function [SSxx]=calcularSSxx(X)
sumatorioXcuadrado=0;
sumatorioCuadrado=0;

for i=1:size(X,2)
    sumatorioXcuadrado=sumatorioXcuadrado+(X(1,i)^2);
end

for i=1:size(X,2)
    sumatorioCuadrado=sumatorioCuadrado +X(1,i);
end

SSxx=sumatorioXcuadrado -(sumatorioCuadrado^2/size(X,2));
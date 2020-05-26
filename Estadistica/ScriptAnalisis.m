clc;
syms x;
syms y;
X=[40,37,56,47,43,42,54,60,39]; 
Y=[80,73,110,102,96,89,105,130,78] ;

%y=?_0+?_1 x

%?_1=SSxy/SSxx
%?_0=mean(Y)-?_1*mean(X)
%%%%%%%%%%%%%%    OBTENCIÓN DE LA FUNCION  %%%%%%%%%%%%%%%%%%%%%
mediaX= mean(X);
mediaY= mean(Y);

SSxy=calcularSSxy(X,Y, mediaX, mediaY);
SSxx=calcularSSxx(X);
SSyy=calcularSSxx(Y);

B_1_hipitetico=SSxy/SSxx;
B_0_hipitetico=mediaY-(B_1_hipitetico*mediaX);

%%%%%%%%%%%%%%   TESTS DE HIPÓTESIS  %%%%%%%%%%%%%%%%%%%%%
%H_0: ?_1=0
%H_1: ?_1?0

%ESTADISTICO : T= (B_1_hipotetico-0)/(s/?SSxx)
S=calcularS(X);
T_b=(B_1_hipitetico-0)/(S/sqrt(SSxx));

%REGION CRITICA : |T|> tinv(?/2,n-2)
T_absoluta_b=tinv(1-0.05/2,size(X,2)-2);

%%%%%%%%%%  INTERVALOS DE CONFIANZA PARA B_1 y B_  %%%%%%%%%%%%%

IC_B1=[B_1_hipitetico-(tinv(1-0.05/2,size(X,2)-2)*(std(X)/sqrt(SSxx))),B_1_hipitetico+(tinv(1-0.05/2,size(X,2)-2)*(std(X)/sqrt(SSxx)))];
IC_B0=[B_0_hipitetico-(tinv(1-0.05/2,size(X,2)-2)*std(X)*sqrt((1/size(X,2))+((mediaX^2)/SSxx))),B_0_hipitetico+(tinv(1-0.05/2,size(X,2)-2)*std(X)*sqrt((1/size(X,2))+((mediaX^2)/SSxx)))];

%%%%%%%%%%  COEFICIENTE DE RELACIÓN  %%%%%%%%%%%%%
%r puede tomar valores -1<r<1 si es 0 significa que no hay relación, en
%cambio si está cerca del -1 o 1 es que si hay fuerte relación.
r=SSxy/sqrt(SSxx*SSyy);

%%%%%%%%%%  PRUEBA DE HIPOTESIS  %%%%%%%%%%%%%
%H_0:  r=0
%H_1:  r?0

%ESTADISTICO : T=(r?(n-2))/?(1-r^2 )
T_r=r*sqrt(size(X,2)-2)/sqrt(1-(r^2));

%REGION CRITICA : |T|> tinv(?/2,n-2)
T_absoluto_r=tinv(1-0.05/2,size(X,2)-2);


%%%%%%%%%%  COEFICIENTE DE DETERMINACIÓN  %%%%%%%%%%%%%
%R=1-(SSE/SSyy)=r^2
R=r^2;
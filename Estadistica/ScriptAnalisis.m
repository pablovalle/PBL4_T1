clc;
syms x;
syms y;
X=[40,37,56,47,43,42,54,60,39,59]; 
Y=[80,73,110,102,96,89,105,130,78,129] ;


%y=?_0+?_1 x

%?_1=SSxy/SSxx
%?_0=mean(Y)-?_1*mean(X)
%%%%%%%%%%%%%%    OBTENCIÓN DE LA FUNCION  %%%%%%%%%%%%%%%%%%%%%
mediaX= mean(X);
mediaY= mean(Y);

SSxy=calcularSSxy(X,Y, mediaX, mediaY);
SSxx=calcularSSxx(X);
SSyy=calcularSSxx(Y);

B_1_hipotetico=SSxy/SSxx;
B_0_hipotetico=mediaY-(B_1_hipotetico*mediaX);

y=(B_1_hipotetico*x)+B_0_hipotetico;

SSE=calcularSSE(SSyy,SSxy,B_1_hipotetico);

S=calcularS(SSE, (size(X,2)-2));
%%%%%%%%%%%%%%   TESTS DE HIPÓTESIS  %%%%%%%%%%%%%%%%%%%%%
%H_0: ?_1=0
%H_1: ?_1?0

%ESTADISTICO : T= (B_1_hipotetico-0)/(s/?SSxx)

T_b=(B_1_hipotetico-0)/(S/sqrt(SSxx));

%REGION CRITICA : |T|> tinv(?/2,n-2)
T_absoluta_b=tinv(1-0.05/2,size(X,2)-2);

%%%%%%%%%%  INTERVALOS DE CONFIANZA PARA B_1 y B_0  %%%%%%%%%%%%%

IC_B1=[B_1_hipotetico-(tinv(1-0.05/2,size(X,2)-2)*(std(X)/sqrt(SSxx))),B_1_hipotetico+(tinv(1-0.05/2,size(X,2)-2)*(std(X)/sqrt(SSxx)))];
IC_B0=[B_0_hipotetico-(tinv(1-0.05/2,size(X,2)-2)*std(X)*sqrt((1/size(X,2))+((mediaX^2)/SSxx))),B_0_hipotetico+(tinv(1-0.05/2,size(X,2)-2)*std(X)*sqrt((1/size(X,2))+((mediaX^2)/SSxx)))];

%%%%% INTERVALO DE ESTIMACIÓN APRA LA RESPUESTA MEDIA XP = 60 %%%%%%%%%%

xp=60;
u=B_0_hipotetico+(xp*B_1_hipotetico);
ICMedia=[u-t*S*sqrt((1/length(X))+(((xp-mean(X)).^2)/SSxx));u+t*S*sqrt((1/length(X))+(((xp-mean(X)).^2)/SSxx))];

%%%%%% INTERVALO DE PREDICCIÓN PARA UNA OBSERVACIÓN FUTURA %%%%%%

%intervalo de predicción para una observación futura
ICPredict=[u-t*S*sqrt(1+(1/length(X))+(((xp-mean(X)).^2)/SSxx));u+t*S*sqrt(1+(1/length(X))+(((xp-mean(X)).^2)/SSxx))];


%%%%%%%%%%  COEFICIENTE DE RELACIÓN  %%%%%%%%%%%%%
%r puede tomar valores -1<r<1 si es 0 significa que no hay relación, en
%cambio si está cerca del -1 o 1 es que si hay fuerte relación.
r=SSxy/sqrt(SSxx*SSyy);

%%%%%%%%%%  PRUEBA DE HIPOTESIS  %%%%%%%%%%%%%
%H_0:  p=0
%H_1:  p!=0

%ESTADISTICO : T=(r?(n-2))/?(1-r^2 )
T_r=r*sqrt(size(X,2)-2)/sqrt(1-(r^2));

%REGION CRITICA : |T|> tinv(?/2,n-2)
T_absoluto_r=tinv(1-0.05/2,size(X,2)-2);


%%%%%%%%%%  COEFICIENTE DE DETERMINACIÓN  %%%%%%%%%%%%%
%R=1-(SSE/SSyy)=r^2
R=r^2;

%%%%%%%%%%%%%%%%%    PLOT    %%%%%%%%%%%%%%%%%%%%%%%
[fx,fy]=conseguirPuntosFuncion(B_1_hipotetico,B_0_hipotetico, min(X)-5 , max(X)+5);
plot(X,Y,'s','MarkerSize',9,'MarkerEdgeColor','b','MarkerFaceColor',[0.5,0.5,0.5])
hold on 
plot(fx,fy,'Color',[1,0,0],'LineWidth',3)
xlim([min(fx) max(fx)])
ylim([min(fy) max(fy)])
title('MUTel')
xlabel('Número de habitaciones ocupadas al dia')
ylabel('Ingresos diarios')





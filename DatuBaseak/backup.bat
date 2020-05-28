@echo off
 
::Carpeta destino de la copia
set bkupdir=C:\Users\Benat\Documents\MU Lanak\MUTel\PBL4_T1\DatuBaseak\backup
 
::Carpeta donde está instalado MySQL Server
set mysqldir=C:\MySQL
 
::Carpeta donde se guardará el fichero de log
set logdir=C:\Users\Benat\Desktop
 
::Usuario y contraseña de acceso al servidor MySQL
set dbuser=root
set dbpass=

::Nombre de la base de datos
set db=mutel

set endtime=0
 
::Obtenemos el día y la hora para añadir como sufijo al nombre del fichero de backup
:GETTIME
 
for /F "tokens=1,2,3 delims=/" %%i  in ('echo %date%') do (set dia=%%k%%j%%i)
for /F "tokens=1,2,3 delims=:, " %%n  in ('echo %time%') do (set hora=%%n%%o%%p)
 
:: Si ha acabado el script y sólo se pide la hora vamos al final
if "%endtime%"=="1" goto END
 
set fn=_%dia%_%hora%
 
:: Escribimos en el fichero de log
echo Iniciando proceso de backup de MySQL > %logdir%\resultado_%fn%.log
echo Hora de inicio = %dia% %hora% >> %logdir%\resultado_%fn%.log
echo --------------------------- >> %logdir%\resultado_%fn%.log
echo. >> %logdir%\resultado_%fn%.log
 
:: Ejecutamos mysqldump para cada base de datos y comprimimos el resultado con gzip
echo Haciendo backup de %%f%fn%.sql.gz
echo Haciendo backup de %%f%fn%.sql.gz >> %logdir%\resultado_%fn%.log
%mysqldir%\bin\mysqldump --user %dbuser% --password=%dbpass% --databases %db% --opt --quote-names --allow-keywords --complete-insert > "%bkupdir%\%fn%.sql"
echo Finalizado backup...
echo Finalizado backup... >> %logdir%\resultado_%fn%.log
 
:: Volvemos a la etiqueta GETTIME para obtener la hora de finalización del script
:: Ponemos a 1 "endtime" para que no vuelva a ejecutar las copias
set endtime=1
goto :GETTIME
 
:END
:: Escribimos el resulado en el fichero de log
echo. >> %logdir%\resultado_%fn%.log
echo --------------------------- >> %logdir%\resultado_%fn%.log
echo Proceso de backup de MySQL finalizado >> %logdir%\resultado_%fn%.log
echo Hora de finalización = %dia% %hora% >> %logdir%\resultado_%fn%.log
echo. >> %logdir%\resultado_%fn%.log
 
:: Volvemos al directorio del script
popd
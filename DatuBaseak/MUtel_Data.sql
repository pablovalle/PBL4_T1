USE mutel;

SET GLOBAL time_zone='+7:00';

DELETE FROM reserva;
DELETE FROM tarea;
DELETE FROM trabajador;
DELETE FROM usuario;
DELETE FROM habitacion;
DELETE FROM hotel;

/*USER*/
INSERT INTO usuario VALUE('benat_mutel','123456789aA','benat', 'gorostiaga', 'benat.gorostiaga@alumni.mondragon.edu');
INSERT INTO usuario VALUE('pablo_mutel','123456789aA','pablo', 'valle', 'pablo.valle@alumni.mondragon.edu');
INSERT INTO usuario VALUE('jon_mutel','123456789aA','jon', 'legaristi', 'jon.legaristi@alumni.mondragon.edu');
INSERT INTO usuario VALUE('irati_mutel','123456789aA','irati', 'izagirre', 'irati.izagirre@alumni.mondragon.edu');

/*HOTELES*/
INSERT INTO hotel VALUE(1,'Burj Khalifa','Dubai',1630,163,1234,9876);
INSERT INTO hotel VALUE(2,'President Wilson','Ginebra',40,4,5678,5432);
INSERT INTO hotel VALUE(3,'Rincón de Pepe','Murcia',30,3,2468,1357);

/*HABITACION BURJ KHALIFA*/
INSERT INTO habitacion VALUE(101,1,1,'exterior','vacio',550,'estandar');
INSERT INTO habitacion VALUE(102,1,2,'interior','vacio',1400,'luxury');
INSERT INTO habitacion VALUE(203,1,3,'exterior','vacio',1750,'estandar');
INSERT INTO habitacion VALUE(204,1,4,'interior','vacio',3000,'luxury');
INSERT INTO habitacion VALUE(2401,1,1,'exterior','vacio',750,'estandar');
INSERT INTO habitacion VALUE(4502,1,2,'interior','vacio',14000,'luxury');
INSERT INTO habitacion VALUE(903,1,3,'exterior','vacio',2450,'estandar');
INSERT INTO habitacion VALUE(1204,1,4,'interior','vacio',30000,'luxury');
INSERT INTO habitacion VALUE(601,1,1,'exterior','vacio',893,'estandar');
INSERT INTO habitacion VALUE(5602,1,2,'interior','vacio',9000,'luxury');
INSERT INTO habitacion VALUE(16201,1,3,'exterior','vacio',175000,'luxury');
INSERT INTO habitacion VALUE(504,1,4,'interior','vacio',6000,'luxury');

/*HABITACION PRESIDENT WILSON*/
INSERT INTO habitacion VALUE(308,2,1,'interior','vacio',250,'estandar');
INSERT INTO habitacion VALUE(202,2,2,'interior','vacio',550,'estandar');
INSERT INTO habitacion VALUE(107,2,3,'exterior','vacio',1600,'luxury');
INSERT INTO habitacion VALUE(403,2,4,'interior','vacio',2200,'luxury');
INSERT INTO habitacion VALUE(309,2,1,'interior','vacio',150,'estandar');
INSERT INTO habitacion VALUE(203,2,2,'interior','vacio',650,'estandar');
INSERT INTO habitacion VALUE(108,2,3,'exterior','vacio',1400,'luxury');
INSERT INTO habitacion VALUE(401,2,4,'interior','vacio',2000,'luxury');
INSERT INTO habitacion VALUE(305,2,1,'interior','vacio',230,'estandar');
INSERT INTO habitacion VALUE(201,2,2,'interior','vacio',450,'estandar');
INSERT INTO habitacion VALUE(106,2,3,'exterior','vacio',1200,'luxury');
INSERT INTO habitacion VALUE(402,2,4,'interior','vacio',2700,'luxury');

/*HABITACION RINCON DE PEPE*/
INSERT INTO habitacion VALUE(201,3,1,'interior','vacio',25,'estandar');
INSERT INTO habitacion VALUE(202,3,2,'interior','vacio',42,'estandar');
INSERT INTO habitacion VALUE(106,3,3,'interior','vacio',91,'estandar');
INSERT INTO habitacion VALUE(304,3,4,'interior','vacio',120,'estandar');
INSERT INTO habitacion VALUE(203,3,1,'interior','vacio',35,'estandar');
INSERT INTO habitacion VALUE(204,3,2,'interior','vacio',52,'estandar');
INSERT INTO habitacion VALUE(107,3,3,'interior','vacio',101,'estandar');
INSERT INTO habitacion VALUE(305,3,4,'interior','vacio',130,'estandar');
INSERT INTO habitacion VALUE(205,3,1,'interior','vacio',15,'estandar');
INSERT INTO habitacion VALUE(206,3,2,'interior','vacio',32,'estandar');
INSERT INTO habitacion VALUE(105,3,3,'interior','vacio',81,'estandar');
INSERT INTO habitacion VALUE(303,3,4,'interior','vacio',110,'estandar');

/*RESERVAS*/
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-13","2020-05-14",0000,'jon_mutel',1,101,0);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-07","2020-05-14",0001,'pablo_mutel',2,108,123);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-13","2020-05-23",0002,'benat_mutel',3,202,543);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-10","2020-05-20",0003,'irati_mutel',1,16201,8963);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-30","2020-05-31",0004,'irati_mutel',2,401,654);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-5","2020-05-17",0005,'pablo_mutel',3,107,231);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-13","2020-05-21",0006,'jon_mutel',1,903,1222);
INSERT INTO reserva(checkin, checkout,llave,username,idHotel,numHabitacion,precioActual)  VALUE("2020-05-3","2020-05-7",0007,'benat_mutel',2,305,65);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-4","2020-05-5",0008,'pablo_mutel',3,303,20);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-6","2020-05-8",0009,'benat_mutel',3,303,5643);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-16","2020-05-26",0010,'jon_mutel',2,402,983);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-24","2020-05-30",0011,'irati_mutel',3,303,87);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-8","2020-05-16",0012,'benat_mutel',1,203,12000);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-10","2020-05-13",0013,'pablo_mutel',2,106,400);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-11","2020-05-14",0014,'irati_mutel',3,105,30);
INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE("2020-05-1","2020-05-4",0015,'pablo_mutel',1,2401,2561);

/*TRABAJADORES*/
INSERT INTO trabajador VALUE(1,'dimitri','esnarov',2,'123456789aA');
INSERT INTO trabajador VALUE(1,'abdul','elyamahi',1,'123456789aA');
INSERT INTO trabajador VALUE(1,'santiago','abascal',3,'123456789aA');
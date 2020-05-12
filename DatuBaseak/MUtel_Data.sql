USE mutel;

DELETE FROM usuario;
DELETE FROM hotel;
DELETE FROM habitacion;
DELETE FROM reserva;
DELETE FROM tarea;
DELETE FROM trabajador;

INSERT INTO usuario VALUE('benat_mutel','123456789aA','benat', 'gorostiaga', 'benat.gorostiaga@alumni.mondragon.edu');
INSERT INTO usuario VALUE('pablo_mutel','123456789aA','pablo', 'valle', 'pablo.valle@alumni.mondragon.edu');
INSERT INTO usuario VALUE('jon_mutel','123456789aA','jon', 'legaristi', 'jon.legaristi@alumni.mondragon.edu');
INSERT INTO usuario VALUE('irati_mutel','123456789aA','irati', 'izagirre', 'irati.izagirre@alumni.mondragon.edu');

INSERT INTO hotel VALUE(1,'Burj Khalifa','Dubai',1630,163,1234,9876);
INSERT INTO hotel VALUE(2,'President Wilson','Ginebra',40,4,5678,5432);
INSERT INTO hotel VALUE(3,'Rincón de Pepe','Murcia',30,3,2468,1357);

INSERT INTO habitacion VALUE(101,1,1,'exterior','vacio',550,'estandar');
INSERT INTO habitacion VALUE(102,1,2,'interior','vacio',1400,'luxury');
INSERT INTO habitacion VALUE(203,1,3,'exterior','vacio',1750,'estandar');
INSERT INTO habitacion VALUE(204,1,4,'interior','vacio',3000,'luxury');

INSERT INTO habitacion VALUE(308,2,1,'interior','vacio',250,'estandar');
INSERT INTO habitacion VALUE(202,2,2,'interior','vacio',550,'estandar');
INSERT INTO habitacion VALUE(107,2,3,'exterior','vacio',1600,'luxury');
INSERT INTO habitacion VALUE(403,2,4,'interior','vacio',2200,'luxury');

INSERT INTO habitacion VALUE(201,3,1,'interior','vacio',25,'estandar');
INSERT INTO habitacion VALUE(202,3,2,'interior','vacio',42,'estandar');
INSERT INTO habitacion VALUE(106,3,3,'interior','vacio',91,'estandar');
INSERT INTO habitacion VALUE(304,3,4,'interior','vacio',120,'estandar');

INSERT INTO trabajador VALUE(1,'dimitri','esnarov',2,'123456789aA');
INSERT INTO trabajador VALUE(1,'abdul','elyamahi',1,'123456789aA');
INSERT INTO trabajador VALUE(1,'santiago','abascal',3,'123456789aA');
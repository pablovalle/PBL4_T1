DELIMITER $$
CREATE PROCEDURE filtrarHabitaciones(in ciudad VARCHAR(30),in personas SMALLINT UNSIGNED,in strFechaIn DATE ,in strFechaOut Date)
BEGIN
  SELECT haa.numHabitacion, hoo.nombre, haa.aforo, haa.orientacion, haa.precio, haa.categoria, hoo.ciudad, hoo.idHotel
	FROM (hotel hoo JOIN habitacion haa ON hoo.idHotel=haa.idHotel) LEFT JOIN 
					(SELECT ho.nombre, ha.numHabitacion, r.idReserva
					FROM (hotel ho JOIN habitacion ha ON ho.idHotel=ha.idHotel) JOIN reserva r ON (r.numHabitacion = ha.numHabitacion AND r.idHotel=ha.idHotel)
					WHERE (strFechaIn BETWEEN r.checkin and r.checkout)
					OR (strFechaOut BETWEEN r.checkin and r.checkout)
					OR (r.checkin BETWEEN strFechaIn AND strFechaOut)
					GROUP BY ha.numHabitacion,ho.nombre) T1

ON (hoo.nombre=T1.nombre AND haa.numHabitacion=T1.numHabitacion)
WHERE hoo.ciudad LIKE ciudad AND haa.aforo =personas AND T1.idReserva IS NULL 
GROUP BY haa.numHabitacion,hoo.nombre
ORDER BY haa.precio, haa.numHabitacion;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getHabitacionesPiso(IN numPequeño SMALLINT UNSIGNED, IN numGrande SMALLINT unsigned)
BEGIN
  SELECT estado FROM habitacion WHERE numHabitacion >numPequeño AND numHabitacion<numGrande;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getCiudades()
BEGIN
  SELECT ho.ciudad FROM hotel ho GROUP BY ho.ciudad;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getPisos(IN hotelid SMALLINT UNSIGNED)
BEGIN
  SELECT pisos FROM hotel WHERE idHotel=hotelid;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getReservas(in nombre_usuario VARCHAR(20))
BEGIN
  SELECT r.idReserva, ho.nombre, r.numHabitacion, r.checkin, r.checkout, ho.clave,r.llave
	FROM reserva r JOIN hotel ho on ho.idHotel=r.idHotel
	WHERE r.username LIKE nombre_usuario AND r.checkout>=CURDATE()
	ORDER BY r.checkin asc;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE cancelarReserva(in reservaid SMALLINT UNSIGNED)
BEGIN
  DELETE FROM reserva WHERE idReserva=reservaid;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE crearReserva(in entrada DATE ,in salida DATE ,in contraseña SMALLINT UNSIGNED,nombre_usuario VARCHAR(20),in hotelid SMALLINT UNSIGNED,in habitacionid SMALLINT UNSIGNED,in precio SMALLINT UNSIGNED)
BEGIN
  INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) VALUE(entrada,salida,contraseña,nombre_usuario,hotelid,habitacionid, precio);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE comprobarUsername(in nombre_usuario VARCHAR(20))
BEGIN
  SELECT username FROM usuario WHERE username LIKE nombre_usuario;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE comprobarContraseña(in nombre_usuario VARCHAR(20))
BEGIN
  SELECT contraseña FROM usuario WHERE username LIKE nombre_usuario;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE registrarse(in nombre_usuario VARCHAR(20), IN contraseña VARCHAR(20), IN nombre VARCHAR(20), IN apellidos VARCHAR(50), IN email VARCHAR(50))
BEGIN
  INSERT INTO mutel.usuario VALUES(nombre_usuario,contraseña,nombre,apellidos,email);
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE getTareas(IN trabajadorid SMALLINT UNSIGNED, IN hotelid SMALLINT UNSIGNED)
BEGIN
  SELECT idTarea, fecha, numHabitacion, descripcion FROM tarea
	WHERE idEmpleado=trabajadorid AND idHotel=hotelid AND estado is false ORDER BY fecha ASC;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE toggleTarea(IN tareaID SMALLINT UNSIGNED)
BEGIN
  UPDATE tarea SET estado=true WHERE idTarea= tareaid;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE toggleEstadoHabitacion(IN habitacionid SMALLINT UNSIGNED)
BEGIN
	UPDATE habitacion SET estado='Hecha' WHERE numHabitacoin= habitacionid;
END$$
DELIMITER ;



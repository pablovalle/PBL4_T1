/*Gestion de usuarios MuTel*/

/* Huesped*/


GRANT EXECUTE ON PROCEDURE mutel.crearReserva TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT EXECUTE ON PROCEDURE mutel.getReservas TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT EXECUTE ON PROCEDURE mutel.cancelarReserva TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT EXECUTE ON PROCEDURE mutel.filtrarHabitaciones TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT EXECUTE ON PROCEDURE mutel.getCiudades TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT EXECUTE ON PROCEDURE mutel.crearTarea TO huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT ON mutel.reserva TO huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT UPDATE ON mutel.habitacion TO huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT ON mutel.habitacion TO huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT ON mysql.proc TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';

/*Trabajador*/
GRANT EXECUTE ON PROCEDURE mutel.getTareas TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.toggleTarea TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.toggleEstadoHabitacion TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.getHabitacionesPiso TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.getPisos TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.comprobarUsernameWorker TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.comprobarContraseñaWorker TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT EXECUTE ON PROCEDURE mutel.getTrabajador TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT SELECT ON mysql.proc TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';



/*Gestion de usuarios MuTel*/

/* Huesped*/
GRANT SELECT ON mutel.usuario TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT, INSERT, DELETE ON mutel.reserva TO huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT ON mutel.habitacion TO  huesped@'%' IDENTIFIED BY 'SoyHuesped';
GRANT SELECT (idHotel, nombre,ciudad,clave) ON  mutel.hotel TO huesped@'%'IDENTIFIED BY 'SoyHuesped';
/*Trabajador*/
GRANT SELECT,UPDATE ON mutel.tarea TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT SELECT ON mutel.trabajador TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT SELECT ON mutel.trabajador TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT SELECT (MasterKey, idHotel) ON mutel.hotel TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
GRANT DROP ON mutel.tarea to trabajador@'%' IDENTIFIED BY 'SoyTrabajador'; 
GRANT UPDATE (estado) ON mutel.habitacion TO trabajador@'%' IDENTIFIED BY 'SoyTrabajador';
/*Admin Hotel*/
GRANT INSERT ON mutel.tarea to adminHotel@'%' IDENTIFIED BY 'SoyAdminHotel';


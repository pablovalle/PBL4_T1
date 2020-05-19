
public class Tarea {
int idTarea;
String fecha;
int numHabitacion;
String descripcion;


public Tarea(int idTarea, String fecha, int numHabitacion, String descripcion) {
	this.idTarea = idTarea;
	this.fecha = fecha;
	this.numHabitacion = numHabitacion;
	this.descripcion = descripcion;
}
public int getIdTarea() {
	return idTarea;
}
public void setIdTarea(int idTarea) {
	this.idTarea = idTarea;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public int getNumHabitacion() {
	return numHabitacion;
}
public void setNumHabitacion(int numHabitacion) {
	this.numHabitacion = numHabitacion;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return this.getDescripcion()+" "+this.getIdTarea()+" "+this.getNumHabitacion();
}


}

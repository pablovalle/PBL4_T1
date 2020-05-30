package Objetos;

public class Trabajador {

	int idTrabajador, idHotel;

	public Trabajador(int idTrabajador, int idHotel) {
		super();
		this.idTrabajador = idTrabajador;
		this.idHotel = idHotel;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	@Override
	public String toString() {
		return this.getIdTrabajador()+"    "+this.getIdHotel();
	}
	
}

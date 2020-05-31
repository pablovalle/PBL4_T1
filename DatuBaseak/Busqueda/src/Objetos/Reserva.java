package Objetos;

public class Reserva {

	int idReserva, numhabitacion, clavehabita, claveHotel;
	String nombreHotel, checkIn, checkOut;
	
	public Reserva(int idReserva, String nombreHotel, int numhabitacion,  String checkIn,String checkOut, int claveHotel, int claveHabitacion) {
		this.idReserva = idReserva;
		this.nombreHotel =nombreHotel;
		this.numhabitacion = numhabitacion;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.claveHotel=claveHotel;
		this.clavehabita=claveHabitacion;
	}
	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idHotel=" + nombreHotel + ", numhabitacion=" + numhabitacion
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getnombreHotel() {
		return nombreHotel;
	}
	public void setIdHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}
	public int getNumhabitacion() {
		return numhabitacion;
	}
	public void setNumhabitacion(int numhabitacion) {
		this.numhabitacion = numhabitacion;
	}
	public int getClavehabita() {
		return clavehabita;
	}
	public void setClavehabita(int clavehabita) {
		this.clavehabita = clavehabita;
	}
	public int getClaveHotel() {
		return claveHotel;
	}
	public void setClaveHotel(int claveHotel) {
		this.claveHotel = claveHotel;
	}
	public String getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}
	public String getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}
}

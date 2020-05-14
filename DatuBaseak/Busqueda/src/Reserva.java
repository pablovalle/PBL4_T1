
public class Reserva {

	int idReserva, idHotel, numhabitacion, clavehabita, claveHotel;
	String checkIn, checkOut;
	
	public Reserva(int idReserva, int idHotel, int numhabitacion,  String checkIn,String checkOut) {
		this.idReserva = idReserva;
		this.idHotel = idHotel;
		this.numhabitacion = numhabitacion;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "Reserva [idReserva=" + idReserva + ", idHotel=" + idHotel + ", numhabitacion=" + numhabitacion
				+ ", checkIn=" + checkIn + ", checkOut=" + checkOut + "]";
	}
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
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


public class Habitacion {

	int numhabitacion;
	String hotel;
	int aforo;
	String orientacion;
	int precio;
	String categoria;
	String ciudad;
	public Habitacion(int numhabitacion, String hotel, int aforo, String orientacion, int precio, String categoria, String ciudad) {
		this.numhabitacion = numhabitacion;
		this.hotel = hotel;
		this.aforo = aforo;
		this.orientacion = orientacion;
		this.precio = precio;
		this.categoria = categoria;
		this.ciudad = ciudad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getNumhabitacion() {
		return numhabitacion;
	}
	public void setNumhabitacion(int numhabitacion) {
		this.numhabitacion = numhabitacion;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public String toString() {
		return "Habitacion [numhabitacion=" + numhabitacion + ", hotel=" + hotel + ", aforo=" + aforo + ", orientacion="
				+ orientacion + ", precio=" + precio + ", categoria=" + categoria + "]";
	}

	
}

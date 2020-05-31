import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "root";
	
	public static String getClaveHabitacion(String numHabitacion) {
		String ret = "";
		Short key = -1;
		
		try {
			Statement stm = DriverManager.getConnection(url, usuario, password).createStatement();
			String strSQL = "SELECT r.llave FROM reserva r WHERE r.numHabitacion = " + numHabitacion;			
			ResultSet rs = stm.executeQuery(strSQL);
			while (rs.next())  key = rs.getShort("llave");		
			ret = String.format("%04d", key);
		} catch (SQLException e) {
			ret = "Error: " + e.toString();
		}
		
		return ret;
	}
	
	public static String getEstadoHabitacion(String numHabitacion, String idHotel) {
		String ret = "";
		
		try {
			Statement stm = DriverManager.getConnection(url, usuario, password).createStatement();
			String strSQL = "SELECT h.estado FROM habitacion h WHERE h.numHabitacion = " 
			+ numHabitacion + " AND idHotel = " + idHotel;
			ResultSet rs = stm.executeQuery(strSQL);
			while (rs.next())  ret = rs.getString("estado");
		} catch (SQLException e) {
			ret = "Error: " + e.toString();
		}
		
		return ret;
		
	}
	
	public static String setEstadoHabitacion(String numHabitacion, String idHotel, String estado) {
		String ret = "OK";
		try {
			Statement stm = DriverManager.getConnection(url, usuario, password).createStatement();
			String strSQL = "UPDATE habitacion SET estado = \"" + estado + "\" WHERE numHabitacion = "+ numHabitacion + " AND idHotel = " + idHotel;			
			stm.execute(strSQL);
			
		} catch (SQLException e) {
			ret = "Error = " + e.toString();
		}
		
		return ret;
	}
	
	public static String getClaveRecepcion(String numHotel) {
		String ret = "";
		
		try {
			Statement stm = DriverManager.getConnection(url, usuario, password).createStatement();
			String strSQL = "SELECT ho.clave FROM Hotel ho WHERE ho.idHotel = " + numHotel;
			ResultSet rs = stm.executeQuery(strSQL);	
			System.out.println(strSQL);
			while (rs.next()) ret = ""+ rs.getShort("clave");
			rs.close();
		} catch (SQLException e) {
			ret = "Error: " + e.toString();
		}
		System.out.println(ret);
		return ret;
	}
	
}

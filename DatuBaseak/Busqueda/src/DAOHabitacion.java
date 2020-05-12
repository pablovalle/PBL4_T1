import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel";
	private static final String usuario = "root";
	private static final String password = "";
	

	static public ArrayList<Habitacion> filtrarHabitaciones(String ciudad, int personas){
		ArrayList<Habitacion> lista = new ArrayList<Habitacion>();
		try {
			if(ciudad.equals("")) ciudad="%";
			
			Statement stm = (Statement) DriverManager.getConnection(url,usuario,password).createStatement();
			
			String strSQL = "SELECT ha.numHabitacion, ho.nombre, ha.aforo, ha.orientacion, ha.precio, ha.categoria"
					+ " FROM habitacion ha JOIN hotel ho on ha.IdHotel = ho.IdHotel"
					+ " WHERE ho.ciudad LIKE '"+ ciudad +"' AND ha.aforo ="+personas;
			ResultSet rs = stm.executeQuery(strSQL);
			
			if(!rs.isBeforeFirst()) System.out.println("No hay hoteles con esas caracterisicas");
			while(rs.next()) {
				lista.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		lista.forEach(System.out::println);
		return lista;		
	}
}

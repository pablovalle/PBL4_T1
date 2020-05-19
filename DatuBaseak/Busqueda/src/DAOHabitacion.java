import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "";
	

	static public Habitacion[] filtrarHabitaciones(String ciudad, int personas, Date fechaIN, Date fechaOut){
		List<Habitacion> lista = new ArrayList<Habitacion>();
		try {
			if(ciudad.equals("")) ciudad="%";
			
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			DateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
			String strFechaIn=formatter.format(fechaIN);
			String strFechaOut=formatter.format(fechaOut);
			/*String strSQL = "SELECT ha.numHabitacion, ho.nombre, ha.aforo, ha.orientacion, ha.precio, ha.categoria"
					+ " FROM habitacion ha JOIN hotel ho on ha.IdHotel = ho.IdHotel"
					+ " WHERE ho.ciudad LIKE '"+ ciudad +"' AND ha.aforo ="+personas;*/
			String strSQL="SELECT haa.numHabitacion, hoo.nombre, haa.aforo, haa.orientacion, haa.precio, haa.categoria, hoo.ciudad, hoo.idHotel\r\n" + 
					"FROM (hotel hoo JOIN habitacion haa ON hoo.idHotel=haa.idHotel) LEFT JOIN \r\n" + 
					"\r\n" + 
					"		(SELECT ho.nombre, ha.numHabitacion, r.idReserva\r\n" + 
					"		FROM (hotel ho JOIN habitacion ha ON ho.idHotel=ha.idHotel) JOIN reserva r ON (r.numHabitacion = ha.numHabitacion AND r.idHotel=ha.idHotel)\r\n" + 
					"		WHERE (\""+strFechaIn+"\" BETWEEN r.checkin and r.checkout)\r\n" + 
					"		OR (\""+strFechaOut+"\" BETWEEN r.checkin and r.checkout) \r\n" + 
					"		OR (r.checkin BETWEEN \""+strFechaIn+"\" AND \""+strFechaOut+"\")\r\n" + 
					"		GROUP BY ha.numHabitacion,ho.nombre) T1 \r\n" + 
					"\r\n" + 
					"ON (hoo.nombre=T1.nombre AND haa.numHabitacion=T1.numHabitacion)\r\n" + 
					"WHERE hoo.ciudad LIKE '"+ ciudad +"' AND haa.aforo ="+personas+" AND T1.idReserva IS NULL\r\n" + 
					"GROUP BY haa.numHabitacion,hoo.nombre\r\n" + 
					"ORDER BY haa.numHabitacion;";
			ResultSet rs = stm.executeQuery(strSQL);
			
			
			if(!rs.isBeforeFirst()) System.out.println("No hay hoteles con esas caracterisicas");
			while(rs.next()) {
				lista.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		lista.forEach(System.out::println);
		return lista.toArray(new Habitacion[0]);		
	}
}

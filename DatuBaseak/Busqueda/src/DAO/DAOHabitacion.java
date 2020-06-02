package DAO;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import Excepciones.StringFormatException;
import Objetos.Habitacion;



public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "";
	

	static public Habitacion[] filtrarHabitaciones(String ciudad, int personas, Date fechaIN, Date fechaOut, String tipo, String precioMin, String precioMax) throws NumberFormatException, StringFormatException{
		List<Habitacion> lista = new ArrayList<Habitacion>();
		int min, max;
		try {
			if(ciudad.equals("")) ciudad="%";
			if(tipo.equals("no importa")) tipo="%";
			if(precioMax.equals("")) precioMax = "9999999";
			if(precioMin.equals("")) precioMin = "0";
			min=Integer.valueOf(precioMin);
			max= Integer.valueOf(precioMax);
			
			checkString(ciudad);
			
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			DateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
			String strFechaIn=formatter.format(fechaIN);
			String strFechaOut=formatter.format(fechaOut);
			
			String strSQL="CALL filtrarHabitaciones('"+ciudad+"',"+personas+",\""+strFechaIn+"\",\""+strFechaOut+"\",'"+tipo+"',"+min+ ","+ max+ ");";
			ResultSet rs = stm.executeQuery(strSQL);
			
			
			if(!rs.isBeforeFirst()) System.out.println("No hay hoteles con esas caracterisicas");
			while(rs.next()) {
				lista.add(new Habitacion(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista.toArray(new Habitacion[0]);		
	}


	private static void checkString(String ciudad) throws StringFormatException{
		char [] palabra=ciudad.toUpperCase().toCharArray();
		int ascii;
		for(int i=0; i<palabra.length; i++) {
			ascii=(int)palabra[i];
			if(!String.valueOf(palabra[i]).equals("%") && (ascii<=64 || ascii>90) ) {
				throw new StringFormatException("Los datos no son correctos");
			}
		}
		
	}
}

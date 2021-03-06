package DAO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC"; //
	private static final String usuario = "trabajador";
	private static final String password = "SoyTrabajador";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	public static boolean cambiarEstadoHabitacion(int numHabitacion, int hotelId) {
		boolean ret=false;
		try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL toggleEstadoHabitacion(?,?)");
			sp.setInt(1, numHabitacion);
			sp.setInt(2, hotelId);
			sp.execute();
			ret=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	public static String[] getHabitacionesPiso(int piso, int idHotel){
		List<String> lista = new ArrayList<String>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getHabitacionesPiso("+piso*100+","+(piso+1)*100+","+idHotel+");";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				lista.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista.toArray(new String[0]);
	}
	
}

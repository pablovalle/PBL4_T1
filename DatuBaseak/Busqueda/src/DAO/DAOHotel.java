package DAO;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOHotel {
	
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "huesped";
	private static final String password = "SoyHuesped";
	
	
	public static List<String> getCiudades() {
		List<String>nombresCiudad= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getCiudades();";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				nombresCiudad.add(rs.getString(1).toLowerCase());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nombresCiudad;
	}
	
	
}

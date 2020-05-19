import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOHotel {
	
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "";
	
	
	public static List<String> getCiudades() {
		List<String>nombresCiudad= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT ho.ciudad FROM hotel ho GROUP BY ho.ciudad;";
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

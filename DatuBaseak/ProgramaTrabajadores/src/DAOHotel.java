import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOHotel {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC"; //
	private static final String usuario = "trabajador";
	private static final String password = "SoyTrabajador";
	
	public static String[] getPisos(int idHotel) {
		String[] pisos = null;
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getPisos("+idHotel+");";
			ResultSet rs = stm.executeQuery(strSQL);
			if(rs.next()) {
				pisos=new String[rs.getInt(1)];
				for(int i=0;i<rs.getInt(1);i++) {
					pisos[i]= String.valueOf(i+1);
				}				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return pisos;
	}
}

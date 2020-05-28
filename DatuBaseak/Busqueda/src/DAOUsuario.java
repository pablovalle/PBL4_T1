import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUsuario {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "root";
	private static final String password = "";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	static public boolean comprobarUsername(String username) {
		boolean ret=false;
		
		try {
			Class.forName(driver);
			Statement stm= DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL comprobarUsuario('"+username+"');";
			ResultSet rs = stm.executeQuery(strSQL);
			if(rs.isBeforeFirst()) ret=true;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
		
	}
	static public boolean comprobarContraseņa(String username, String string) {
		boolean ret= false;
		
		Statement stm;
		try {
			stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL comprobarContraseņa('"+username+"');";
			ResultSet rs = stm.executeQuery(strSQL);
			if(rs.next() && string.compareTo(rs.getString(1))==0) {
				ret=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	static public boolean registrarse(String nombre, String apellidos, String username, String passwoord, String email) {
		boolean ret= false;
		
	   try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL registrarse(?,?,?,?,?)");
			sp.setString(1, username);
			sp.setString(2, passwoord);
			sp.setString(3, nombre);
			sp.setString(4, apellidos);
			sp.setString(5, email);
			sp.execute();
			ret=true;
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		return ret;
	}

}

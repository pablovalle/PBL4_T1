import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
			String strSQL="SELECT username FROM usuario WHERE username LIKE '"+username+"'" ;
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
	static public boolean comprobarContraseña(String username, String string) {
		boolean ret= false;
		
		Statement stm;
		try {
			stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT contraseña FROM usuario WHERE username LIKE '"+username+"'" ;
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
		
		Statement stm;
		try {
			stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="INSERT INTO mutel.usuario VALUES('"+username+"','"+passwoord+"','"+nombre+"','"+apellidos+"','"+email+"');";
			int rs = stm.executeUpdate(strSQL);
			if(rs==1) {
				ret=true;
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		
		return ret;
	}
}

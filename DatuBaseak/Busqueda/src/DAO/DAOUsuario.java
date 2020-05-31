package DAO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Excepciones.ExcepcionCampos;
import Excepciones.ExcepcionPassword;
import Excepciones.ExcepcionUsername;
import Objetos.Check;

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
			String strSQL="CALL comprobarUsername('"+username+"');";
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
			String strSQL="CALL comprobarContraseña('"+username+"');";
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
	static public boolean registrarse(String nombre, String apellidos, String username, String password1, String password2, String email)throws ExcepcionPassword,ExcepcionUsername, ExcepcionCampos {
		boolean ret= false;
		Check.check(password1, password2,username, nombre, apellidos,email);
		
	   try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL registrarse(?,?,?,?,?)");
			sp.setString(1, username);
			sp.setString(2, password1);
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

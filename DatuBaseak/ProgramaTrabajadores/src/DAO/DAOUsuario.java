package DAO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Objetos.Trabajador;




public class DAOUsuario {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "trabajador";
	private static final String password = "SoyTrabajador";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	static public boolean comprobarUsername(String username) {
		boolean ret=false;
		
		try {
			Class.forName(driver);
			Statement stm= DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL comprobarUsernameWorker('"+username+"');";
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
			String strSQL="CALL comprobarContraseñaWorker('"+username+"');";
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
	public static Trabajador getTrabajador(String username) {
		Trabajador trabajador= null;
		Statement stm;
		try {
			stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getTrabajador('"+username+"');";
			ResultSet rs = stm.executeQuery(strSQL);
			rs.next();
			trabajador= new Trabajador(rs.getInt(1), rs.getInt(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trabajador;
	}
	
}

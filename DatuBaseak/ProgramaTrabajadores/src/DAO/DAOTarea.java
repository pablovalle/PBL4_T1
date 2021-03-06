package DAO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Objetos.Tarea;
import Objetos.Trabajador;

public class DAOTarea {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC"; //
	private static final String usuario = "trabajador";
	private static final String password = "SoyTrabajador";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	public static Tarea[] getTareas(Trabajador trabajador){
		List<Tarea> tareas = new ArrayList<Tarea>();
		try {
			Class.forName(driver);
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getTareas("+trabajador.getIdTrabajador()+","+trabajador.getIdHotel()+");";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				tareas.add(new Tarea(rs.getInt(1),rs.getString(2), rs.getInt(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tareas.toArray(new Tarea[0]);
	}

	public static boolean marcarToggleTarea(Tarea tarea) {
		boolean ret=false;
		try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL toggleTarea(?)");
			sp.setInt(1, tarea.getIdTarea());
			sp.execute();
			ret=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

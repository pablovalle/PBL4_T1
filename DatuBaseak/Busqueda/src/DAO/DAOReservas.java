package DAO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Objetos.Habitacion;
import Objetos.Reserva;

public class DAOReservas {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "huesped";
	private static final String password = "SoyHuesped";
	
	static public Reserva[] getReservas(String username) {
		List<Reserva>reservasusuario= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="CALL getReservas('"+username+"')";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				reservasusuario.add(new Reserva(rs.getInt(1),rs.getString(2),rs.getInt(3),
									String.valueOf(rs.getDate(4)),String.valueOf(rs.getDate(5)),rs.getInt(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservasusuario.toArray(new Reserva[0]);
	}

	
	static public boolean crearReserva(Habitacion habitacion, String checkin, String checkout,String username) {
		boolean ret=false;
		try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL crearReserva(?,?,?,?,?,?,?)");
			int c1=(int)(Math.random()*8);
			int c2=(int)(Math.random()*8)*10;
			int c3=(int)(Math.random()*8)*100;
			int c4=(int)(Math.random()*8)*1000;
			int contraseņa=c1+c2+c3+c4;
			sp.setString(1, checkin);
			sp.setString(2, checkout);
			sp.setInt(3,contraseņa);
			sp.setString(4, username);
			sp.setInt(5, habitacion.getIdHotel());
			sp.setInt(6, habitacion.getNumhabitacion());
			sp.setInt(7, habitacion.getPrecio());
			sp.execute();
			ret=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	static public boolean cancelarReserva(Reserva reserva) {
		boolean ret=false;
		try {
			CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL cancelarReserva(?)");
			sp.setInt(1, reserva.getIdReserva());
			sp.execute();
			ret= true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

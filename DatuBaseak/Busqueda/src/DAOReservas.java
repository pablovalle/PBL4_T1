import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class DAOReservas {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "huesped";
	private static final String password = "SoyHuesped";
	
	static public Reserva[] getReservas(String username) {
		List<Reserva>reservasusuario= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT r.idReserva, ho.nombre, r.numHabitacion, r.checkin, r.checkout, ho.clave,r.llave\r\n" + 
					"FROM reserva r JOIN hotel ho on ho.idHotel=r.idHotel\r\n" + 
					"WHERE r.username LIKE '"+ username+ "'AND r.checkout>=CURDATE()"
				  + "ORDER BY r.checkin asc;";
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
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="INSERT INTO reserva (checkin, checkout,llave,username,idHotel,numHabitacion,precioActual) "
					+ "VALUES (\""+checkin+"\",\""+checkout+"\","+String.valueOf((int)(Math.random()*10000))+",'"+username+"',"
					+ ""+habitacion.idHotel+","+habitacion.numhabitacion+","+habitacion.precio+");";
			int rs = stm.executeUpdate(strSQL);
			if(rs==1) {
				ret= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	static public boolean cancelarReserva(Reserva reserva) {
		boolean ret=false;
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="DELETE FROM reserva WHERE idReserva="+reserva.getIdReserva()+";";
			int rs = stm.executeUpdate(strSQL);
			if(rs==1) {
				ret= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}

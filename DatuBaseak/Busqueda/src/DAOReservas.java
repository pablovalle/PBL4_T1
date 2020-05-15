import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOReservas {
	private static final String url = "jdbc:mysql://localhost:3306/mutel";
	private static final String usuario = "huesped";
	private static final String password = "SoyHuesped";
	
	public static Reserva[] getReservas(String username) {
		List<Reserva>reservasusuario= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT r.idReserva, ho.nombre, r.numHabitacion, r.checkin, r.checkout\r\n" + 
					"FROM reserva r JOIN hotel ho on ho.idHotel=r.idHotel\r\n" + 
					"WHERE r.username LIKE '"+ username+ "'AND r.checkout>=CURDATE()"
				  + "ORDER BY r.checkin asc;";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				reservasusuario.add(new Reserva(rs.getInt(1),rs.getString(2),rs.getInt(3),
									String.valueOf(rs.getDate(4)),String.valueOf(rs.getDate(5))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservasusuario.toArray(new Reserva[0]);
	}
}

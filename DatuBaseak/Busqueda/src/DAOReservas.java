import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOReservas {
	private static final String url = "jdbc:mysql://localhost:3306/mutel";
	private static final String usuario = "root";
	private static final String password = "";
	
	public static Reserva[] getReservas(String username) {
		List<Reserva>reservasusuario= new ArrayList<>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT r.idReserva, r.idHotel, r.numHabitacion, r.checkin, r.checkout\r\n" + 
					"FROM reserva r\r\n" + 
					"WHERE r.username LIKE '"+ username+ "';";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				reservasusuario.add(new Reserva(rs.getInt(1),rs.getInt(2),rs.getInt(3),
									String.valueOf(rs.getDate(4)),String.valueOf(rs.getDate(5))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservasusuario.toArray(new Reserva[0]);
	}
}

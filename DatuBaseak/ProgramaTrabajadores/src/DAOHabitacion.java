import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOHabitacion {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC"; //
	private static final String usuario = "trabajador";
	private static final String password = "SoyTrabajador";
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	
	public static boolean cambiarEstadoHabitacion(int numHabitacion) {
		boolean ret=false;
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="UPDATE habitacion SET estado='Hecha' WHERE numHabitacoin= "+numHabitacion+";";
			int rs=stm.executeUpdate(strSQL);
			if(rs==1) {
				ret=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	public static String[] getHabitacionesPiso(int piso){
		List<String> lista = new ArrayList<String>();
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="SELECT estado FROM habitacion WHERE numHabitacion >"+piso*100+" AND numHabitacion<"+(piso+1)*100+";";
			ResultSet rs = stm.executeQuery(strSQL);
			while(rs.next()) {
				lista.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista.toArray(new String[0]);
	}
	
}

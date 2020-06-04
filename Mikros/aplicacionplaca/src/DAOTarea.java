
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOTarea {
	private static final String url = "jdbc:mysql://localhost:3306/mutel?serverTimezone=UTC";
	private static final String usuario = "huesped";
	private static final String password = "SoyHuesped";
	
	public static void crearTarea(String numHotel, String numHabitacion, int idEmpleado) {
		//idEmpleado = 1 
		
		
		Date fecha = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strFecha=formatter.format(fecha);
		
		
		try {
            java.sql.CallableStatement sp = DriverManager.getConnection(url,usuario,password).prepareCall(" CALL crearTarea(?,?,?,?,?,?)");
            sp.setInt(1, Integer.parseInt(numHotel));
            sp.setString(2,strFecha);
            sp.setInt(3,  idEmpleado);
            sp.setInt(4, Integer.parseInt(numHabitacion));
            sp.setString(5, "limpiar habitación");
            sp.setInt(6,  0);
            sp.execute();
            

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public static String getClaveRecepcion(String numHotel) {
		String ret = "";
		
		try {
			Statement stm = DriverManager.getConnection(url, usuario, password).createStatement();
			String strSQL = "SELECT ho.clave FROM Hotel ho WHERE ho.idHotel = " + numHotel;
			ResultSet rs = stm.executeQuery(strSQL);	
			System.out.println(strSQL);
			while (rs.next()) ret = ""+ rs.getShort("clave");
			rs.close();
		} catch (SQLException e) {
			ret = "Error: " + e.toString();
		}
		System.out.println(ret);
		return ret;
	}
	
}

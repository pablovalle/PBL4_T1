import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			String strSQL="SELECT idTarea, fecha, numHabitacion, descripcion FROM tarea"
					+ " WHERE idEmpleado="+trabajador.getIdTrabajador()+" AND idHotel="+trabajador.getIdHotel()+" AND estado is false ORDER BY fecha ASC;";
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
		tareas.forEach(System.out::println);
		return tareas.toArray(new Tarea[0]);
	}

	public static boolean marcarToggleTarea(Tarea tarea) {
		boolean ret=false;
		try {
			Statement stm = DriverManager.getConnection(url,usuario,password).createStatement();
			String strSQL="UPDATE tarea SET estado=true WHERE idTarea= "+tarea.getIdTarea()+";";
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
}

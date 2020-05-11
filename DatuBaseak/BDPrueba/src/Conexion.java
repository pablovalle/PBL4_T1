import java.sql.*;
public class Conexion {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String usuario = "root";
	private static final String password = "";
	private static final String url = "jdbc:mysql://localhost:3306/mutel";
	
	public void Conectar() {
		Connection conn = null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, usuario, password);
		
			if (conn != null) {
				System.out.println("Conexión establecida...");						
					}
		
		}catch(Exception e) {
			System.out.println("Estás en catch: " + e);
		}
		
		
	}
	public void desconectar() {
		Connection conn = null;
		if (conn == null) {
			System.out.println("Te has desconectado");
		}
	}
	public static void main(String[] args) {
		Conexion programa = new Conexion();
		programa.Conectar();
		programa.desconectar();
	}
}


import java.sql.*;


public class Conexion {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	//private static final String driver = "com.mysql.jdbc.Driver";
	private static final String usuario = "root";
	private static final String password = "";
	private static final String url = "jdbc:mysql://localhost:3306/mutel";
	
	Connection conn = null;
	
	
	public void Conectar() {
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
		conn = null;
		if (conn == null) {
			System.out.println("Te has desconectado");
		}
	}
	public void insert() {
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement("insert into usuario values(?,?,?,?)");
			ps.setString(1, "benat");
			ps.setString(2, "Beñat");
			ps.setString(3, "Gorostiaga");
			ps.setString(4, "97bgorostiaga@gmail.com");
			int lineasAfectadas = ps.executeUpdate();
			if(lineasAfectadas>0) System.out.println(lineasAfectadas+" lineas afectadas");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void select() {
		try {
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery("select * from usuario") ;
			if(rs.getRow()==0) System.out.println("vacio");
			while(rs.next()) {
				System.out.println (rs.getString (1) + " " + rs.getString (2)+ " "  + rs.getString(3) + " " + rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Conexion programa = new Conexion();
		programa.Conectar();
		//programa.insert();
		programa.select();
		programa.desconectar();
	}
}


package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public  class Conector {
	public static String bd="xe";
	public static String login="biblio";
	public static String pass="biblio";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection conexion=null;
	private  static Statement st;
	private static ResultSet rs;
	
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conexion=DriverManager.getConnection(url, login, pass);
		return conexion;
		
		
	}
	public static void cerrar() throws SQLException {
		if(rs!=null)
			rs.close();
		if(st!=null)
			st.close();
		if(conexion!=null)
			conexion.close();
	}

}

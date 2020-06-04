package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase esta dedicada a conectar con la base de datos.
 * @author Ivan, Luis y Sergio
 *
 */
public  class Conector {
	public static String bd="xe";
	public static String login="biblio";
	public static String pass="biblio";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection conexion=null;
	private  static Statement st;
	private static ResultSet rs;
	
	/**
	 * Metodo encargado de conectar con la base de datos.
	 * @return una conexion, referente a la conexion a nustra base de datos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conexion=DriverManager.getConnection(url, login, pass);
		return conexion;
		
		
	}
	
	/**
	 * Metodo encargado de cerrar todos los Statement y los ResulSet.
	 * @throws SQLException
	 */
	public static void cerrar() throws SQLException {
		if(rs!=null)
			rs.close();
		if(st!=null)
			st.close();
		if(conexion!=null)
			conexion.close();
	}

}

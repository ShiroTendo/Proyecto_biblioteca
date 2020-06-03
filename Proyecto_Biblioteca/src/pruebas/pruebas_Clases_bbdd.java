package pruebas;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import modelo.Biblioteca;
import modelo.Bibliotecario;
import modelo.Generos;
import modelo.Libros;
import modelo.Prestamos;
import modelo.Socio;

public class pruebas_Clases_bbdd implements Generos{
	public static String bd="xe";
	public static String login="biblio";
	public static String pass="biblio";
	private static String url="jdbc:oracle:thin:@localhost:1521:"+bd;
	static Connection conexion=null;
	private  static Statement st;
	private static ResultSet rs;
	
	public static void conectar() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conexion=DriverManager.getConnection(url, login, pass);
		if(conexion!=null)
			System.out.println("Conexion realizada con exito");
		
	}
	public static void cerrar() throws SQLException {
		if(rs!=null)
			rs.close();
		if(st!=null)
			st.close();
		if(conexion!=null)
			conexion.close();
	}
	public static void mostrar() throws SQLException {
		int n_cliente=0;
		String nombre="";
		String dirr="";
		String tlf="";
		Date fecha;
		 st=conexion.createStatement();
		 rs=st.executeQuery("select ncliente,nombre,direccion,telefono,fecha_alta from clientes_taller");
		while(rs.next()) {
			n_cliente=rs.getInt("ncliente");
			nombre=rs.getString("nombre");
			dirr=rs.getString("direccion");
			tlf=rs.getString("telefono");
			fecha=rs.getDate("fecha_alta");
			System.out.println(n_cliente+" * "+nombre+" * "+dirr+" * "+tlf+" * "+fecha);
		}
		
	}
	
	/**public static void insertar() throws SQLException {
		Scanner sc= new Scanner(System.in);
		int n_cliente=0;
		String nombre="";
		String dirr="";
		String tlf="";
		Date fecha;
		System.out.println("Dame el numero");
		n_cliente=sc.nextInt();
		System.out.println("Dame el nombre");
		nombre=sc.next();
		System.out.println(" ");
		System.out.println("Dame la direccion");
		dirr=sc.nextLine();
		System.out.println(" ");
		System.out.println("Dame tu numero de telefono");
		tlf=sc.next();
		System.out.println(" ");
		System.out.println("Dame la fecha");
		fecha= Date.valueOf(sc.next());
		String insert=" Insert into clientes_taller values(?,?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setInt(1, n_cliente);
		st2.setString(2, nombre);
		st2.setString(3, dirr);
		st2.setString(4, tlf);
		st2.setDate(5, fecha);
		st2.executeUpdate();
		System.out.println("Exito");
	}**/
	
	public static  void insertarSocio(Socio s1) throws SQLException {
		String insert=" Insert into socios values(?,?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setString(1, s1.getDni());
		st2.setString(2, s1.getNombre());
		st2.setString(3, s1.getApellidos());
		st2.setInt(4, s1.getN_telefono());
		st2.setInt(5, s1.getCod_Socio());
		st2.executeUpdate();
		
	}
	
	public static  void insertarBibliotecario(Bibliotecario s1) throws SQLException {
		String insert=" Insert into bibliotecarios values(?,?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setString(1, s1.getDni());
		st2.setString(2, s1.getNombre());
		st2.setString(3, s1.getApellidos());
		st2.setInt(4, s1.getN_telefono());
		st2.setInt(5, s1.getCod_Emple());
		st2.executeUpdate();
		
	}
	
	public static void insertarLibro(Libros l1) throws SQLException {
		String insert=" Insert into libros values(?,?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setInt(1, l1.getId_libro());
		st2.setString(2, l1.getTitulo());
		st2.setString(3, l1.getAutor());
		st2.setString(4, l1.getGenero());
		st2.setInt(5, 0);
		st2.executeUpdate();
	}
	public static void insertarPrestamo(Prestamos p1) throws SQLException {
		String insert=" Insert into prestamos values(?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setDate(1, p1.getFecha_inicio());
		st2.setDate(2, p1.getFecha_fin());
		st2.setInt(3, p1.getSocio_asocidado());
		st2.setInt(4, p1.getLibro_asociado());
		st2.executeUpdate();
	}
	
	
	public static void eliminaPrestamos(int i) throws SQLException {
		String insert=" delete  from prestamos where id_libro ="+i;
		st=conexion.createStatement();
		st.executeUpdate(insert);
		
	}
	public static void eliminaLibro(int i) throws SQLException {
		String insert="delete from libros where id_libro ="+i;
		st=conexion.createStatement();
		st.executeUpdate(insert);
		
	}
	
	public static void eliminarSocio(int i) throws SQLException {
		try {
		String insert=" delete  from socios where cod_socio ="+i;
		st=conexion.createStatement();
		st.executeUpdate(insert);
		}catch (SQLException e) {
			System.out.println("El id dado no es valido, introduzca uno valido");
		}
	}



	public static void main(String[] args){
		try {
		java.util.Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate = dateFormat.format(date); 
        Calendar cal=Calendar.getInstance();
        cal.add(cal.MONTH, 1);
        java.util.Date date2 = cal.getTime();  
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");  
        String strDate2 = dateFormat.format(date2); 
        Biblioteca bi1 = new Biblioteca();
        Socio s1= new Socio("021584Q", "Mortador", "RJ", 85964217, 5);
        Socio s2= new Socio("025554A", "Oswaldo", "Willy", 65465654, 6);
		Bibliotecario b1= new Bibliotecario("2241183V", "Gemma", "Gonzalez", 657895127, 1);
		Bibliotecario b2= new Bibliotecario("2362576A", "Luis", "Reyes", 665448485, 2);
		Libros l1= new Libros(1234, "EL cid campeador", "Maritn","accion");
		Libros l2= new Libros(1324, "El lobo", "Clase","animales");
		Libros l3= new Libros(2001, "El perro", "Clase1","animales");
		Prestamos p1= new Prestamos(Date.valueOf(strDate), Date.valueOf(strDate2), s1.getCod_Socio(),l1.getId_libro());
		conectar();
		//l3.insertarLibroBD(bi1);
		//b1.PrestarLibro(s1.getCod_Socio(),l3.getId_libro(), bi1);
		bi1.mostrar_libros();
		System.out.println(bi1.getLista_prestamos().size());
		bi1.mostrar_Prestamos();
		
		
		cerrar();
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Error, el id ya existe en la tabla");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}

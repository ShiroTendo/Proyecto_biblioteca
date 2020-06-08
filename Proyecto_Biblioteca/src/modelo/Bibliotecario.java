package modelo;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Objects;
/**
 * Clase centrada en la gestion y uso de la biblioteca a traves de Bibliotecarios.
 * @author Ivan,Luis y Sergio
 *
 */

public class Bibliotecario extends Personas implements Comparable<Bibliotecario>{
	private int Cod_Emple;
	private static int num_biblio;
	static {
		try {
			num_biblio=buscaMaxCod() ;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Constructor de la clase.
	 * @param Dni String
	 * @param Nombre String
	 * @param Apellidos String
	 * @param N_telefono int 
	 * @param Cod_Emple int
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Bibliotecario(String Dni, String Nombre, String Apellidos, int N_telefono, int Cod_Emple) {
		super(Dni, Nombre, Apellidos, N_telefono);
		this.Cod_Emple=Cod_Emple;
	}
	
	/**
	 * Constructor sobrecargado de la clase
	 * @param Dni String
	 * @param Nombre String 
	 * @param Apellidos String
	 * @param N_telefono int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Bibliotecario(String Dni, String Nombre, String Apellidos, int N_telefono) throws ClassNotFoundException, SQLException {
		super(Dni, Nombre, Apellidos, N_telefono);
		this.Cod_Emple=num_biblio+1;
		num_biblio++;
	}
	/**Constructor de copia de la clase
	 * 
	 * @param obj objecto a copiar
	 */
	public Bibliotecario(Bibliotecario obj) {
		super(obj);
		this.Cod_Emple=obj.getCod_Emple();
	}
	/**Metodo encargado de encontrar el cod_emple mas alto de la base de datos
	 * Este metodo busca el numero mas alto en la base de datos, para devolverlo y que sirve de apoyo en el constructor
	 * 
	 * @return devuelve un int, correspondiente al cod mas alto
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static  int buscaMaxCod() throws ClassNotFoundException, SQLException {
		int num=0;
		Statement st = Conector.conectar().createStatement();
		ResultSet rs = st.executeQuery("select max(cod_emple) from bibliotecarios");
		if(rs.next()) {
			num=rs.getInt(1);
		}
		if(String.valueOf(num)==null) {
			return 0;
		}
		return num;
	}

	/**Metodo encargado de insertar el bibliotecario en la base de datos.
	 * Despues de insertarlo en la base, tambien sera insertdo en la bilioteca pasada como parametro
	 * @param bi1 la biblioteca que usaremos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertarBibliotecarioBD(Biblioteca bi1) throws ClassNotFoundException, SQLException {
		try {
			String insert = " insert into bibliotecarios values(?,?,?,?,?)";
			PreparedStatement st2 = Conector.conectar().prepareStatement(insert);
			st2.setString(1, this.getDni());
			st2.setString(2, this.getNombre());
			st2.setString(3, this.getApellidos());
			st2.setInt(4, this.getN_telefono());
			st2.setInt(5, this.getCod_Emple());
			st2.executeUpdate();
			bi1.getLista_bibliotecarios().add(this);
		}finally {
			Conector.cerrar();
		}
	}
	/**Metodo encargado de eliminar un Bibliotecario de la base de datos y de la biblioteca.
	 *
	 * El metodo elimina de la base de datos el empleado que tenga el cod que se le pasa, eimediatamente despues se elimina de la biblioteca tambien.
	 *
	 * @param bi1 biblioteca de la que tambien sera eliminado
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public void eliminarBibliotecarioBD(Biblioteca bi1) throws SQLException, ClassNotFoundException {
		try {
			String insert="delete from bibliotecarios where cod_emple ="+this.getCod_Emple();
			Statement st=Conector.conectar().createStatement();
			st.executeUpdate(insert);
			bi1.getLista_bibliotecarios().remove(this);
		}finally {
			Conector.cerrar();
		}
	}
	/**Este metodo getiona el prestamo de libros a los socios de la biblioteca.
	 * Este metodo funciona de manera que, unido al uso de otros metodos, se comprueba si tanto el usuario como el libro existen.
	 * En caso de que esto se cumpla, se revisa si el libro no esta prestado, en cuyo caso se creara un prestamo, el cual sera insertado en la base de datos y en la biblioteca.
	 * Ademas se realizaran una serie de cambios tanto en el socio como en el libro asociados, de manera que se ajusten a los datos del prestamo
	 * 
	 * @param socio_id id del socio que quiere el libro
	 * @param id_libro id del libro a prestar
	 * @param biblio biblioteca que usaremos para otras gestiones
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int PrestarLibro(int socio_id, int id_libro,Biblioteca biblio) throws ClassNotFoundException, SQLException {
		Socio socio=biblio.buscarSocio(socio_id);
		Libros libro=biblio.buscaLibro(id_libro);
		if(socio!=null){
			if(socio.getLibros_Tiene().size()<3) {
				if(libro!=null){
					if(!libro.isPrestado()) {			
						String[] fechas=devuelveFecha();
						Prestamos prestamo= new Prestamos(Date.valueOf(fechas[0]), Date.valueOf(fechas[1]), socio.getCod_Socio(), libro.getId_libro());
						prestamo.insertarPrestamo();
						biblio.getLista_prestamos().add(prestamo);
						libro.updateStatusLibroTrue(biblio);
						libro.updateTrueBd();
						Socio socio2=socio.insertarLibroenSocio(libro);
						socio2.updateSocioenBiblio(socio, biblio);
						return 0;
					}
					else
						return 1;

				}
				else
					return 2;
			}
			else			
				return 3;
		}
		else
			return 4;
	}
	/**Metodo centrado en menejar la devolucion y los elementos asociados con la devolucion.
	 * Este metodo funciona de manera que tras recuperar el socio y el libro( si existen), busca si existe el prestamo con los datos dados.
	 * En caso de que no sea asi manda un mensaje de error.
	 * En caso contrario realiza los cambios pertinentes en la base de datos y en la biblioteca en los elementos asociados con dicho prestamos,ahora eliminado
	 *  
	 * @param socio_id int id del socio
	 * @param id_libro int id del libro
	 * @param biblio biblioteca a actualizar
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int DevolverLibro(int socio_id,int id_libro,Biblioteca biblio) throws ClassNotFoundException, SQLException {
		Socio socio=biblio.buscarSocio(socio_id);
		Libros libro= biblio.buscaLibro(id_libro);
		Prestamos prestamo=biblio.existePrestamo(socio_id, id_libro);
		if(prestamo!=null) {
			prestamo.eliminarPrestamoBD();
			biblio.eliminarPrestamoBiblio(prestamo);
			Socio socio2=socio.eliminarLibroenSocio(libro);
			socio2.updateSocioenBiblio(socio,biblio);
			libro.updateStatusLibroFalse(biblio);
			libro.updateFalseBd();
			return 0;


		}
		else
			return 1;
	}


	/**Metodo encargado de darnos la fecha en la que se hace el prestamo y la mecha de devolucion maxima del mismo.
	 * Se calcula la fecha actual con la clase java.util.date y con Calendar, ademas de la fecha pasado un mes de la actual
	 * 
	 * @return un array de String con las dos fechas pasadas a String
	 */
	public String[] devuelveFecha(){
		java.util.Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(date); 
		Calendar cal=Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		java.util.Date date2 = cal.getTime();  
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate2 = dateFormat2.format(date2); 
		String [] devolver= {strDate,strDate2};
		return devolver;

	}
	/**
	 * Metodo hashCode de la clae.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(Cod_Emple);
	}
	/**
	 * Metodo equals de la clase
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Bibliotecario) {
			Bibliotecario aux=(Bibliotecario)obj;
			if(this.Cod_Emple==aux.getCod_Emple())
				return(true);
		}
		return false;
	}

	/**Metodo get cod_empleado
	 * 	
	 * @return retorna el codigo del  empleado
	 */
	public int getCod_Emple() {
		return Cod_Emple;
	}
	/**Metodo set cod_empleado
	 * 
	 * @param cod_Emple  el valor que se le quiere dar al cod_empleado
	 */
	public void setCod_Emple(int cod_Emple) {
		Cod_Emple = cod_Emple;
	}
	/*Metodo por defecto de ordenacion de lo bibliotecarios.
	 * 
	 */
	@Override
	public int compareTo(Bibliotecario o) {
		return String.valueOf(this.Cod_Emple).compareTo(String.valueOf(o.getCod_Emple()));
	}
	/**Metodo toString de la clase.
	 * 
	 */
	@Override
	public String toString() {
		return "Bibliotecario: " + Cod_Emple + " " + super.toString();
	}


}

package modelo;

import java.sql.Date;

import java.sql.PreparedStatement;
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
	/**
	 * Constructor de la clase.
	 * @param Dni String
	 * @param Nombre String
	 * @param Apellidos String
	 * @param N_telefono int 
	 * @param Cod_Emple int
	 */
	public Bibliotecario(String Dni, String Nombre, String Apellidos, int N_telefono, int Cod_Emple) {
		super(Dni, Nombre, Apellidos, N_telefono);
		this.Cod_Emple=Cod_Emple;
	}
	
	/**Metodo encargado de insertar el bibliotecario en la base de datos.
	 * Despues de insertarlo en la base, tambien sera insertdo en la bilioteca pasada como parametro
	 * @param bi1 la biblioteca que usaremos
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void insertarBibliotecarioBD(Biblioteca bi1) throws ClassNotFoundException, SQLException {
		try {
			String insert = " Insert into bibliotecarios values(?,?,?,?,?)";
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
	//Mirar y eliminar 90% seguro
	public void eliminarBibliotecarioBD(Biblioteca bi1) throws SQLException, ClassNotFoundException {
		try {
		String insert="delete from bibliotecarios where cod_emple ="+this.getCod_Emple();
		Statement st=Conector.conectar().createStatement();
		st.executeUpdate(insert);		
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
	public  void PrestarLibro(int socio_id, int id_libro,Biblioteca biblio) throws ClassNotFoundException, SQLException {
		Socio socio=biblio.buscarSocio(socio_id);
		Libros libro=biblio.buscaLibro(id_libro);
		if(socio!=null){
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
				}
				else
					System.out.println("El libro ya esta prestado, prueba mas tarde");

			}
			else
				System.out.println("El libro no existe, introduce un id de libro valido");
		}
		else			
			System.out.println("El usuario no se encuentra en la base de datos, intrudzca un id valido");
	}
	
	public void DevolverLibro(int socio_id,int id_libro,Biblioteca biblio) throws ClassNotFoundException, SQLException {
		Socio socio=biblio.buscarSocio(socio_id);
		Libros libro= biblio.buscaLibro(id_libro);
		Prestamos prestamo=biblio.existePrestamo(socio_id, id_libro);
		if(prestamo!=null) {
			prestamo.eliminarPrestamoBD();
			biblio.eliminarPrestamoBiblio(prestamo);
			libro.updateStatusLibroFalse(biblio);
			Socio socio2=socio.eliminarLibroenSocio(libro);
			socio2.updateSocioenBiblio(socio,biblio);


		}
		else
			System.out.println("Introduzca unos datos validos, puesto que los introducidos no son referentes a ningun prestamo");

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
			Socio aux=(Socio)obj;
			if(this.Cod_Emple==aux.getCod_Socio())
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
		return super.toString() + "Cod_Emple=" + Cod_Emple;
	}

	
}

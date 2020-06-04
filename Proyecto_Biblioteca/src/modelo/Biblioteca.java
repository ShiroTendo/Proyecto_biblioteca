package modelo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;


/**
 * Clase dedicada a la gestión de la biblioteca añadiendo socios, libros y bibliotecarios.
 * 
 * @author Ivan,Luis y Sergio
 *
 */
public class Biblioteca {
	private HashSet<Libros> lista_libros;
	private HashSet<Socio> lista_socios;
	private HashSet<Bibliotecario> lista_bibliotecarios;
	private HashSet<Prestamos> lista_prestamos;
	
	/**
	 * Constructor de la clase.
	 * En este constructor a los atributos se les añade el método volcar, para introducir objetos en sus respectivas colecciones.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Biblioteca() throws ClassNotFoundException, SQLException{
		this.lista_libros= new HashSet<Libros>(volcarLibros());
		this.lista_socios= new HashSet<Socio>(volcarSocios());
		this.lista_bibliotecarios= new HashSet<Bibliotecario>(volcarBibliotecario());
		this.lista_prestamos= new HashSet<Prestamos>(volcarPrestamos());
		
	}
	
	/**
	 * Método encargado de introducir un Libro en la biblioteca mediante el uso de colecciones.
	 * Este método recorre la tabla Libros de la base de datos y obtiene los datos del libro.
	 * Estos datos se introducen en un objeto Libros, este se almacenará en una colección y se añadirá a la biblioteca.
	 * 
	 * @return una colección de Libros
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public  HashSet<Libros> volcarLibros() throws SQLException, ClassNotFoundException {
		int id_libro=0;
		String titulo="";
		String autor="";
		String genero="";
		int aux=0;
		boolean estado=false;
		Libros libro;
		HashSet<Libros> lista= new HashSet<Libros>();
		 Statement st=Conector.conectar().createStatement();
		 ResultSet rs=st.executeQuery("select id_libro,titulo,autor,genero,estado from libros");
		while(rs.next()) {
			id_libro=rs.getInt("id_libro");
			titulo=rs.getString("titulo");
			autor=rs.getString("autor");
			genero=rs.getString("genero");
			aux=rs.getInt("estado");
			if(aux==0)
				estado=false;
			if(aux==1)
				estado=true;
			libro= new Libros(id_libro, titulo, autor, genero,estado);
			lista.add(libro);
				
		}
		Conector.cerrar();
		return lista;
		
	}
	
	/**
	 * Método encargado de introducir un Socio en la biblioteca mediante el uso de colecciones.
	 * Este método recorre la tabla Socios de la base de datos y obtiene los datos del socio.
	 * Estos datos se introducen en un objeto Socios, este se almacenará en una colección y se añadirá a la biblioteca.
	 * 
	 * @return una colección de Socios
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HashSet<Socio> volcarSocios() throws ClassNotFoundException, SQLException{
		String dni=" ";
		String nombre=" ";
		String apellidos=" ";
		int telefono;
		int cod_socio;
		Socio socio;
		HashSet<Socio> lista= new HashSet<Socio>();
		Statement st=Conector.conectar().createStatement();
		ResultSet rs=st.executeQuery("select dni,nombre,apellidos,n_telefono,cod_socio from socios");
		while(rs.next()) {
			dni=rs.getString("dni");
			nombre=rs.getString("nombre");
			apellidos=rs.getString("apellidos");
			telefono=rs.getInt("n_telefono");
			cod_socio=rs.getInt("cod_socio");
			socio= new Socio(dni, nombre, apellidos, telefono, cod_socio);
			lista.add(socio);
		}
		Conector.cerrar();
		return lista;
	}
	
	/**
	 * Método encargado de introducir un Prestamo en la biblioteca mediante el uso de colecciones.
	 * Este método recorre la tabla Prestamos de la base de datos y obtiene los datos del préstamo.
	 * Estos datos se introducen en un objeto Prestamos, este se almacenará en una colección y se añadirá a la biblioteca.
	 * 
	 * @return una colección de Prestamos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public HashSet<Prestamos> volcarPrestamos() throws SQLException, ClassNotFoundException{
		Date fecha_ini;
		Date fecha_fin;
		int socio_asociado;
		int libro_asociado;
		Prestamos prestamo;
		HashSet<Prestamos> lista = new HashSet<Prestamos>();
		Statement st=Conector.conectar().createStatement();
		ResultSet rs=st.executeQuery("select fecha_inicio, fecha_fin, cod_socio, id_libro from prestamos");
		while(rs.next()) {
			fecha_ini=rs.getDate("fecha_inicio");
			fecha_fin=rs.getDate("fecha_fin");
			socio_asociado=rs.getInt("cod_socio");
			libro_asociado=rs.getInt("id_libro");
			prestamo= new Prestamos(fecha_ini, fecha_fin, socio_asociado, libro_asociado);
			lista.add(prestamo);
		}
		Conector.cerrar();
		return lista;
		
	}
	
	/**
	 * Método encargado de introducir un Bibliotecario en la biblioteca mediante el uso de colecciones.
	 * Este método recorre la tabla Bibliotecario de la base de datos y obtiene los datos del bibliotecario.
	 * Estos datos se introducen en un objeto Bibliotecario, este se almacenará en una colección y se añadirá a la biblioteca.
	 * 
	 * @return una colección de Bibliotecario
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public HashSet<Bibliotecario> volcarBibliotecario() throws ClassNotFoundException, SQLException {
		String dni = " ";
		String nombre = " ";
		String apellidos = " ";
		int telefono;
		int cod_emple;
		Bibliotecario bibliotecario;
		HashSet<Bibliotecario> lista = new HashSet<Bibliotecario>();
		Statement st = Conector.conectar().createStatement();
		ResultSet rs = st.executeQuery("select dni, nombre, apellidos, n_telefono, cod_emple from bibliotecarios");
		while(rs.next()) {
			dni = rs.getString("dni");
			nombre = rs.getString("nombre");
			apellidos = rs.getString("apellidos");
			telefono = rs.getInt("n_telefono");
			cod_emple = rs.getInt("cod_emple");
			bibliotecario = new Bibliotecario(dni, nombre, apellidos, telefono, cod_emple);
			lista.add(bibliotecario);
		}
		Conector.cerrar();
		return lista;
	}
	
	/**
	 * Método encargado de buscar un libro en la biblioteca mediante su ID.
	 * Este método comprueba si en la lista de libros hay alguno que tenga la misma ID que se le pasa como parámetro.
	 * 
	 * @param id ID del libro que se quiere buscar.
	 * @return un objeto de tipo Libros si se encuentra el ID o null si no se encuentra el ID.
	 */
	public Libros buscaLibro(int id) {
		Iterator it=this.getLista_libros().iterator();
		Libros retornar;
		while(it.hasNext()) {
			Libros aux=(Libros) it.next();
			if(aux.getId_libro()==id)
				return retornar=new Libros(aux);
		}
		return null;

	}

	/**
	 * Método encargado de buscar un socio en la biblioteca mediante su ID.
	 * Este método comprueba si en la lista de socios hay alguno que tenga el mismo ID que se le pasa como parámetro.
	 * 
	 * @param id ID del socio que se quiere buscar
	 * @return un objeto de tipo Socios si se encuentra el ID o null si no se encuentra el ID
	 */
	public Socio buscarSocio(int id) {
		Iterator it=this.getLista_socios().iterator();
		Socio retornar;
		while(it.hasNext()) {
			Socio aux=(Socio) it.next();
			if(aux.getCod_Socio()==id)
				return retornar=new Socio(aux);
		}
		return null;

	}
	public Prestamos existePrestamo(int socio_id,int id_libro) {
		Iterator it=this.getLista_prestamos().iterator();
		Prestamos retornar;
		while(it.hasNext()) {
			Prestamos aux=(Prestamos)it.next();
			if(aux.getLibro_asociado()==id_libro && aux.getSocio_asocidado()==socio_id)
				return retornar=aux;
		}
		return null;

	}
	public void eliminarPrestamoBiblio(Prestamos presta) {
		if(this.getLista_prestamos().contains(presta)) {
			this.getLista_prestamos().remove(presta);
		}
	}
	
	/**
	 * Método get lista_prestamos.
	 * 
	 * @return la lista de prestamos
	 */
	public HashSet<Prestamos> getLista_prestamos() {
		return lista_prestamos;
	}

	/**
	 * Método set lista_prestamos.
	 * 
	 * @param lista_prestamos el valor que se le quiere dar a lista_prestamos
	 */
	public void setLista_prestamos(HashSet<Prestamos> lista_prestamos) {
		this.lista_prestamos = lista_prestamos;
	}

	/**
	 * Método encargado de mostrar la lista de Libros almacenados en la biblioteca.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void mostrar_libros() throws ClassNotFoundException, SQLException{
		ArrayList<Libros> orden= new ArrayList<Libros>(this.lista_libros);
		Collections.sort(orden);
		System.out.println("Listado de libros:");
		for (Libros Libros : orden) {
			System.out.println(Libros.toString());
		}
	}
	
	/**
	 * Método encargado de mostrar la lista de Socios almacenados en la biblioteca.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void mostrar_Socios() throws ClassNotFoundException, SQLException {
		ArrayList<Socio> orden= new ArrayList<Socio>(this.lista_socios);
		Collections.sort(orden);
		System.out.println("Listado de socios:");
		for (Socio Socio : orden) {
			System.out.println(Socio.toString());
			
		}
	}
	
	/**
	 * Método encargado de mostrar la lista de Prestamos almacenados en la biblioteca.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void mostrar_Prestamos() throws ClassNotFoundException, SQLException {
		ArrayList<Prestamos> orden= new ArrayList<Prestamos>(this.lista_prestamos);
		Collections.sort(orden);
		System.out.println("Listado de prestamos:");
		for(Prestamos Prestamo : orden) {
			System.out.println(Prestamo.toString());
		}
	}
	
	/**
	 * Método encargado de mostrar la lista de Bibliotecarios almacenados en la biblioteca.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void mostrar_bibliotecarios() throws ClassNotFoundException, SQLException {
		ArrayList<Bibliotecario> orden = new ArrayList<Bibliotecario>(this.lista_bibliotecarios);
		Collections.sort(orden);
		System.out.println("Listado de bibliotecarios:");
		for (Bibliotecario bibliotecario : orden) {
			System.out.println(bibliotecario.toString());
		}
	}
	
	/**
	 * Método encargado de añadir un libro a la lista de libros.
	 * 
	 * @param libro un objeto de tipo Libros que se quiera añadir
	 * @return
	 */
	public boolean aniade_libro(Libros libro) {
		return lista_libros.add(libro);
	}
	
	/**
	 * Método encargado de eliminar un libro de la lista de libros.
	 * 
	 * @param libro un objeto de tipo Libros que se quiera eliminar
	 * @return
	 */
	public boolean eliminar_libro(Libros libro) {
		if(lista_libros.contains(libro)) {
			return lista_libros.remove(libro);
		}
		return false;
	}
	
	public void imprimirSocios() throws IOException {
		 File f= new File("./impresiones/socios.txt");
		BufferedWriter bw= new BufferedWriter(new FileWriter(f));
		Iterator it= this.lista_socios.iterator();
		bw.write("Lista de socios: "+"\n");
		while(it.hasNext()) {
			Socio aux=(Socio)it.next();
			bw.write(aux.toString()+"\n");
		}
		bw.close();
	}
	public void imprimirBibliotecarios() throws IOException {
		 File f= new File("./impresiones/bibliotecarios.txt");
		BufferedWriter bw= new BufferedWriter(new FileWriter(f));
		Iterator it= this.lista_bibliotecarios.iterator();
		bw.write("Lista de bibliotecarios: "+"\n");
		while(it.hasNext()) {
			Bibliotecario aux=(Bibliotecario)it.next();
			bw.write(aux.toString()+"\n");
		}
		bw.close();
	}
	public void imprimirLibros() throws IOException {
		 File f= new File("./impresiones/libros.txt");
		BufferedWriter bw= new BufferedWriter(new FileWriter(f));
		Iterator it= this.lista_libros.iterator();
		bw.write("Lista de libros: "+"\n");
		while(it.hasNext()) {
			Libros aux=(Libros)it.next();
			bw.write(aux.toString()+"\n");
		}
		bw.close();
	}
	public void imprimirPrestamos() throws IOException {
		 File f= new File("./impresiones/prestamos.txt");
		BufferedWriter bw= new BufferedWriter(new FileWriter(f));
		Iterator it= this.lista_prestamos.iterator();
		bw.write("Lista de prestamoss: "+"\n");
		while(it.hasNext()) {
			Prestamos aux=(Prestamos)it.next();
			bw.write(aux.toString()+"\n");
		}
		bw.close();
	}

	/**
	 * Método get lista_libros.
	 * 
	 * @return devuelve la lista de libros
	 */
	public HashSet<Libros> getLista_libros() {
		return lista_libros;
	}

	/**
	 * Método set lista_libros.
	 * 
	 * @param lista_libros el valor que se le quiera dar a lista_libros
	 */
	public void setLista_libros(HashSet<Libros> lista_libros) {
		this.lista_libros = lista_libros;
	}

	/**
	 * Método get lista_socios.
	 * 
	 * @return devuelve la lista de socios
	 */
	public HashSet<Socio> getLista_socios() {
		return lista_socios;
	}

	/**
	 * Método set lista_socios.
	 * 
	 * @param lista_socios el valor que le quiera dar a lista_socios
	 */
	public void setLista_socios(HashSet<Socio> lista_socios) {
		this.lista_socios = lista_socios;
	}

	/**
	 * Método get lista_bibliotecarios.
	 * 
	 * @return devuelve la lista de bibliotecarios
	 */
	public HashSet<Bibliotecario> getLista_bibliotecarios() {
		return lista_bibliotecarios;
	}

	/**
	 * Método set lista_bibliotecarios.
	 * 
	 * @param lista_bibliotecarios el valor que se le quiera dar a lista_bibliotecarios
	 */
	public void setLista_bibliotecarios(HashSet<Bibliotecario> lista_bibliotecarios) {
		this.lista_bibliotecarios = lista_bibliotecarios;
	}


	
	
	
	
	
	

}

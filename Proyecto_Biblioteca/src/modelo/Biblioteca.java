package modelo;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Biblioteca {
	private HashSet<Libros> lista_libros;
	private HashSet<Socio> lista_socios;
	private HashSet<Bibliotecario> lista_bibliotecarios;
	private HashSet<Prestamos> lista_prestamos;
	
	public Biblioteca() throws ClassNotFoundException, SQLException{
		this.lista_libros= new HashSet<Libros>(volcarLibros());
		this.lista_socios= new HashSet<Socio>(volcarSocios());
		this.lista_bibliotecarios= new HashSet<Bibliotecario>(volcarBibliotecario());
		this.lista_prestamos= new HashSet<Prestamos>(volcarPrestamos());
		
	}
	
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
		return lista;
		
	}
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
		Conector.conectar();
		return lista;
	}

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
		Conector.conectar();
		return lista;
		
	}
	
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
		Conector.conectar();
		return lista;
	}
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
	
	public HashSet<Prestamos> getLista_prestamos() {
		return lista_prestamos;
	}

	public void setLista_prestamos(HashSet<Prestamos> lista_prestamos) {
		this.lista_prestamos = lista_prestamos;
	}

	//hola
	public void mostrar_libros(){
		ArrayList<Libros> orden= new ArrayList<Libros>(this.lista_libros);
		Collections.sort(orden);
		System.out.println("Listado de libros:");
		for (Libros Libros : orden) {
			System.out.println(Libros.toString());
		}
	}
	//ajajajajajajajaj
	public void mostar_Socios() {
		ArrayList<Socio> orden= new ArrayList<Socio>(this.lista_socios);
		Collections.sort(orden);
		System.out.println("Listado de socios:");
		for (Socio Socio : orden) {
			System.out.println(Socio.toString());
			
		}
	}
	
	public void mostrar_Prestamos() {
		ArrayList<Prestamos> orden= new ArrayList<Prestamos>(this.lista_prestamos);
		Collections.sort(orden);
		System.out.println("Listado de prestamos:");
		for(Prestamos Prestamo : orden) {
			System.out.println(Prestamo.toString());
		}
	}
	
	public void mostrar_bibliotecarios() {
		ArrayList<Bibliotecario> orden = new ArrayList<Bibliotecario>(this.lista_bibliotecarios);
		Collections.sort(orden);
		System.out.println("Listado de bibliotecarios:");
		for (Bibliotecario bibliotecario : orden) {
			System.out.println(bibliotecario.toString());
		}
	}
	
	public boolean aniade_libro(Libros libro) {
		return lista_libros.add(libro);
	}
	
	public boolean eliminar_libro(Libros libro) {
		if(lista_libros.contains(libro)) {
			return lista_libros.remove(libro);
		}
		return false;
	}

	public HashSet<Libros> getLista_libros() {
		return lista_libros;
	}

	public void setLista_libros(HashSet<Libros> lista_libros) {
		this.lista_libros = lista_libros;
	}

	public HashSet<Socio> getLista_socios() {
		return lista_socios;
	}

	public void setLista_socios(HashSet<Socio> lista_socios) {
		this.lista_socios = lista_socios;
	}

	public HashSet<Bibliotecario> getLista_bibliotecarios() {
		return lista_bibliotecarios;
	}

	public void setLista_bibliotecarios(HashSet<Bibliotecario> lista_bibliotecarios) {
		this.lista_bibliotecarios = lista_bibliotecarios;
	}


	
	
	
	
	
	

}

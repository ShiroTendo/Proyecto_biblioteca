package modelo;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Biblioteca {
	private HashSet<Libros> lista_libros;
	private HashSet<Socio> lista_socios;
	private HashSet<Bibliotecario> lista_bibliotecarios;
	private HashSet<Prestamos> lista_prestamos;
	
	public Biblioteca() throws ClassNotFoundException, SQLException{
		this.lista_libros= new HashSet<Libros>(volcarLibros());
		this.lista_socios= new HashSet<Socio>();
		this.lista_bibliotecarios= new HashSet<Bibliotecario>();
		this.lista_prestamos= new HashSet<Prestamos>();
		
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
		ResultSet rs=st.executeQuery("select fecha_inicio, fecha_fin, cod_socio, id_libro");
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
		for (Libros Libros : orden) {
			System.out.println(Libros.toString());
		}
	}
	//ajajajajajajajaj
	public void mostar_Socios() {
		ArrayList<Socio> orden= new ArrayList<Socio>(this.lista_socios);
		Collections.sort(orden);
		for (Socio Socio : lista_socios) {
			System.out.println(Socio.toString());
			
		}
	}
	
	public void mostrar_Prestamos() {
		ArrayList<Prestamos> orden= new ArrayList<Prestamos>(this.lista_prestamos);
		Collections.sort(orden);
		for(Prestamos Prestamo : lista_prestamos) {
			System.out.println(Prestamo.toString());
		}
	}
	
	public void mostrar_bibliotecarios() {
	
		for (Bibliotecario bibliotecario : lista_bibliotecarios) {
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

package modelo;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class Biblioteca {
	private HashSet<Libros> lista_libros;
	private HashSet<Socio> lista_socios;
	private HashSet<Bibliotecario> lista_bibliotecarios;
	private HashSet<Prestamos> lista_prestamos;
	
	Biblioteca() throws ClassNotFoundException, SQLException{
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
	
	
	
	
	
	public HashSet<Prestamos> getLista_prestamos() {
		return lista_prestamos;
	}

	public void setLista_prestamos(HashSet<Prestamos> lista_prestamos) {
		this.lista_prestamos = lista_prestamos;
	}

	//hola
	public void mostrar_libros(){
		for (Libros Libros : lista_libros) {
			System.out.println(Libros.toString());
		}
	}
	//ajajajajajajajaj
	public void mostar_Socios() {
		for (Socio Socio : lista_socios) {
			System.out.println(Socio.toString());
			
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

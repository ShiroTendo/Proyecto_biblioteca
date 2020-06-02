package modelo;

public class Libros {
	//public enum generos {fantasia,accion,aventura,comic,historia,sobrenatural,terror,misterio};
	private int id_libro;
	private String titulo;
	private String autor;
	private String genero;
	private boolean prestado;
	
	
	
	public Libros(int id,String titulo,String autor,String genero){
		this.id_libro=id;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.prestado=false;
	}
	
	public static boolean Prestar(Socio  socio, Libros libro,Biblioteca biblio) {
		if(socioExiste(biblio, socio)) {
			
		}
			
		
		return true;
	}
	
	public static boolean socioExiste(Biblioteca biblio,Socio socio) {
		if(biblio.getLista_socios().contains(socio))
			return true;
		return false;
	}
	
	public static boolean Devolver(Socio socio, Libros libro) {
		return true;
	}
	
	
	


	@Override
	public String toString() {
		return "Libros [id_libro=" + id_libro + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", prestado=" + prestado + "]";
	}






	public int getId_libro() {
		return id_libro;
	}



	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public boolean isPrestado() {
		return prestado;
	}



	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	
	

}

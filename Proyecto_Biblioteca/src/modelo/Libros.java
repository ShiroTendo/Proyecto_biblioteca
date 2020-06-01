package modelo;

public class Libros implements Generos {
	enum generos {fantasia,accion,aventura,comic,historia,sobrenatural,terror,misterio};
	private String id_libro;
	private String titulo;
	private String autor;
	private generos genero;
	private boolean prestado;
	
	
	
	Libros(String id,String titulo,String autor,generos genero){
		this.id_libro=id;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.prestado=false;
	}
	
	public static boolean Prestar(Socio socio, Libros libro) {
		if(!libro.prestado) {
			//lista.add(new Prestamos(socio.id
		}
			
		
		return true;
	}
	
	public static boolean Devolver(Socio socio, Libros libro) {
		return true;
	}
	
	
	


	@Override
	public String toString() {
		return "Libros [id_libro=" + id_libro + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", prestado=" + prestado + "]";
	}






	public String getId_libro() {
		return id_libro;
	}



	public void setId_libro(String id_libro) {
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



	public generos getGenero() {
		return genero;
	}



	public void setGenero(generos genero) {
		this.genero = genero;
	}



	public boolean isPrestado() {
		return prestado;
	}



	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	
	

}

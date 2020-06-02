package modelo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;

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
	public Libros(Libros obj) {
		this.id_libro=obj.getId_libro();
		this.titulo=obj.getTitulo();
		this.autor=obj.getAutor();
		this.genero=obj.getGenero();
		this.prestado=obj.isPrestado();
	}

	public  void PrestarLibro(int  socio_id, int  id_libro,Biblioteca biblio) {
		Socio socio=buscarSocio(socio_id, biblio);
		Libros libro= buscaLibro(id_libro, biblio);
		if(socio!=null){
			if(libro!=null){
				if(!libro.isPrestado()) {				
		                //Prestamos p1= new Prestamos(millis, fin, socio, libro);
					
					
					
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
	public Libros buscaLibro(int id,Biblioteca biblio) {
		Iterator it=biblio.getLista_libros().iterator();
		Libros retornar;
		while(it.hasNext()) {
			Libros aux=(Libros) it.next();
			if(aux.getId_libro()==id)
				return retornar=new Libros(aux);
		}
		return null;
		
	}
	
	public Socio buscarSocio(int id,Biblioteca biblio) {
		Iterator it=biblio.getLista_socios().iterator();
		Socio retornar;
		while(it.hasNext()) {
			Socio aux=(Socio) it.next();
			if(aux.getCod_Socio()==id)
				return retornar=new Socio(aux);
		}
		return null;

	}
	/**
	public  boolean libroExiste(Biblioteca biblio,Libros libro) {
		if(biblio.getLista_libros().contains(libro))
			return true;
		else
			return false;
		
	}

	public  boolean socioExiste(Biblioteca biblio,Socio socio) {
		if(biblio.getLista_socios().contains(socio))
			return true;
		return false;
	}**/

	public static boolean Devolver(Socio socio, Libros libro) {
		return true;
	}





	@Override
	public String toString() {
		return "Libros [id_libro=" + id_libro + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", prestado=" + prestado + "]";
	}








	@Override
	public int hashCode() {
		return Objects.hash(id_libro,titulo,autor,genero,prestado);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Libros) {
			Libros aux=(Libros)obj;
			if(this.id_libro==aux.getId_libro()&& this.titulo.equalsIgnoreCase(aux.getTitulo())&& this.autor.equalsIgnoreCase(aux.getAutor())&&
					this.genero.equalsIgnoreCase(aux.genero)&&this.prestado==aux.isPrestado())
				return(true);

		}
		return false;

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

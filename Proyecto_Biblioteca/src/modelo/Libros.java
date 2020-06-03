package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;

import oracle.security.o3logon.b;

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
					String[] fechas=devuelveFecha();
					Prestamos prestamo= new Prestamos(Date.valueOf(fechas[0]), Date.valueOf(fechas[1]), socio.getCod_Socio(), libro.getId_libro());
					//insertarPrestamos(prestamo);
					biblio.getLista_prestamos().add(prestamo);
					updateStatusLibroTrue(libro, biblio);
					//updateStatusLibroBbdd(int id);
					Socio socio2=insertarLibroenSocio(socio, libro);
					updateSocioenBiblio(socio, socio2, biblio);
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
	
	
	public Socio insertarLibroenSocio(Socio socio, Libros libro) {
		libro.setPrestado(true);
		socio.getLibros_Tiene().add(libro);
		return socio;

		
	}
	public void updateSocioenBiblio(Socio socioold,Socio socionew,Biblioteca biblio) {
		if(biblio.getLista_socios().contains(socioold)) {
			biblio.getLista_socios().remove(socioold);
			biblio.getLista_socios().add(socionew);
		}
		
	}
	public void updateStatusLibroTrue(Libros libro,Biblioteca biblio) {
		if(biblio.getLista_libros().contains(libro)) {
			biblio.getLista_libros().remove(libro);
			libro.setPrestado(true);
			biblio.getLista_libros().add(libro);
		}
	}
	/**public static void insertarPrestamo(Prestamos p1) throws SQLException {
		String insert=" Insert into prestamos values(?,?,?,?)";
		PreparedStatement st2=conexion.prepareStatement(insert);
		st2.setDate(1, p1.getFecha_inicio());
		st2.setDate(2, p1.getFecha_fin());
		st2.setInt(3, p1.getSocio_asocidado());
		st2.setInt(4, p1.getLibro_asociado());
		st2.executeUpdate();
	}**/
	
	
	
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

	public void DevolverLibro(int socio_id,int id_libro,Biblioteca biblio) {
		Socio socio=buscarSocio(socio_id, biblio);
		Libros libro= buscaLibro(id_libro, biblio);
		Prestamos prestamo=existePrestamo(socio_id, id_libro, biblio);
		if(prestamo!=null) {
			//eliminarPrestamosBd
			eliminarPrestamoBiblio(prestamo, biblio);
			eliminarLibroenSocio(socio, libro);
			updateStatusLibroFalse(libro, biblio);
			Socio socio2=eliminarLibroenSocio(socio, libro);
			updateSocioenBiblio(socio, socio2, biblio);
			
			
		}
	
	}
	
	public Socio eliminarLibroenSocio(Socio socio, Libros libro) {
		libro.setPrestado(false);
		socio.getLibros_Tiene().remove(libro);
		return socio;

		
	}
	
	
	public void updateStatusLibroFalse(Libros libro,Biblioteca biblio) {
		if(biblio.getLista_libros().contains(libro)) {
			biblio.getLista_libros().remove(libro);
			libro.setPrestado(false);
			biblio.getLista_libros().add(libro);
		}
	}
	
	public void eliminarPrestamoBiblio(Prestamos presta,Biblioteca biblio) {
		if(biblio.getLista_prestamos().contains(presta)) {
			biblio.getLista_prestamos().remove(presta);
		}
	}
	
	
	public Prestamos existePrestamo(int socio_id,int id_libro,Biblioteca biblio) {
		Iterator it=biblio.getLista_prestamos().iterator();
		Prestamos retornar;
		while(it.hasNext()) {
			Prestamos aux=(Prestamos)it.next();
			if(aux.getLibro_asociado()==id_libro && aux.getSocio_asocidado()==socio_id)
				return retornar=aux;
		}
		return null;
		
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

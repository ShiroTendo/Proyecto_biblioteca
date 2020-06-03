package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Objects;

import oracle.security.o3logon.b;

public class Libros implements Comparable<Libros>{
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
	public Libros(int id,String titulo,String autor,String genero,boolean estado){
		this.id_libro=id;
		this.titulo=titulo;
		this.autor=autor;
		this.genero=genero;
		this.prestado=estado;
	}
	public Libros(Libros obj) {
		this.id_libro=obj.getId_libro();
		this.titulo=obj.getTitulo();
		this.autor=obj.getAutor();
		this.genero=obj.getGenero();
		this.prestado=obj.isPrestado();
	}
	/**
	 * Metodo centrado en crear un prestamo.
	 * @param socio_id
	 * @param id_libro
	 * @param biblio
	 */
	/**public  void PrestarLibro(int socio_id, int id_libro,Biblioteca biblio) {
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
	 * @throws SQLException 
	 * @throws ClassNotFoundException **/
	public void updateTrueBd() throws ClassNotFoundException, SQLException {
		try {
			String insert="update libros set estado=1 where id_libro ="+this.getId_libro();
			Statement st=Conector.conectar().createStatement();
			st.executeUpdate(insert);
			}finally {
				Conector.cerrar();
			}
		
	}


	public void updateSocioenBiblio(Socio socioold,Socio socionew,Biblioteca biblio) {
		if(biblio.getLista_socios().contains(socioold)) {
			biblio.getLista_socios().remove(socioold);
			biblio.getLista_socios().add(socionew);
		}

	}
	public void updateStatusLibroTrue(Biblioteca biblio) {
		if(biblio.getLista_libros().contains(this)) {
			biblio.getLista_libros().remove(this);
			this.setPrestado(true);
			biblio.getLista_libros().add(this);
		}
	}
	
	
	public void insertarLibroBD(Biblioteca bi1) throws SQLException, ClassNotFoundException {
		try {
		String insert=" Insert into libros values(?,?,?,?,?)";
		PreparedStatement st2=Conector.conectar().prepareStatement(insert);
		st2.setInt(1, this.getId_libro());
		st2.setString(2, this.getTitulo());
		st2.setString(3, this.getAutor());
		st2.setString(4, this.getGenero());
		st2.setInt(5, 0);
		st2.executeUpdate();
		bi1.getLista_libros().add(this);
		}finally {
			Conector.cerrar();
		}
		
	}
	
	public void eliminarLibroBD() throws SQLException, ClassNotFoundException {
		try {
		String insert="delete from libros where id_libro ="+this.getId_libro();
		Statement st=Conector.conectar().createStatement();
		st.executeUpdate(insert);
		}finally {
			Conector.cerrar();
		}
	}

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
		else
			System.out.println("Introduzca unos datos validos, puesto que los introducidos no son referentes a ningun prestamo");

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
		return "id_libro=" + id_libro + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero
				+ ", prestado=" + prestado;
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
	@Override
	public int compareTo(Libros o) {
		return String.valueOf(this.id_libro).compareTo(String.valueOf(o.getId_libro()));
	}




}

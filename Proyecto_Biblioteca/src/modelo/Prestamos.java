package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

/**
 * Clase dedicada a guardar los prestamos que hace un socio.
 * @author Ivan, Luis y Sergio
 *
 */

public class Prestamos implements Comparable<Prestamos>{

	private Date fecha_inicio;
	private Date fecha_fin;
	private int socio_asocidado;
	private int libro_asociado;

	/**
	 * Constructor de la clase.
	 * @param inicio Date
	 * @param fin Date
	 * @param socio int
	 * @param libro int
	 */

	public Prestamos(Date inicio,Date fin,int socio,int libro) {
		this.fecha_inicio=inicio;
		this.fecha_fin=fin;
		this.socio_asocidado=socio;
		this.libro_asociado=libro;
	}
	
	/**
	 * Constructor de copia de Prestamos.
	 * @param obj Prestamos
	 */
	
	public Prestamos(Prestamos obj) {
		this.fecha_inicio=obj.getFecha_inicio();
		this.fecha_fin=obj.getFecha_fin();
		this.socio_asocidado=obj.getSocio_asocidado();
		this.libro_asociado=obj.libro_asociado;
	}
	
	/**
	 * Metodo que inserta un prestamo en la base de datos.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public  void insertarPrestamo() throws SQLException, ClassNotFoundException {
		try {
		String insert=" Insert into prestamos values(?,?,?,?)";
		PreparedStatement st2=Conector.conectar().prepareStatement(insert);
		st2.setDate(1, this.getFecha_inicio());
		st2.setDate(2, this.getFecha_fin());
		st2.setInt(3, this.getSocio_asocidado());
		st2.setInt(4, this.getLibro_asociado());
		st2.executeUpdate();
		}finally {
			Conector.cerrar();
		}
	}
	
	/**
	 * Metodo que elimina un prestamo de la base de datos
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public void eliminarPrestamoBD() throws SQLException, ClassNotFoundException {
		try {
		String insert = " delete from prestamos where cod_socio =" + this.getSocio_asocidado()+" and id_libro="+this.getLibro_asociado();
		Statement st = Conector.conectar().createStatement();
		st.executeUpdate(insert);
		}finally {
			Conector.cerrar();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha_inicio,fecha_fin,socio_asocidado,libro_asociado);
	}

	/**
	 * Metodo equals de la clase Prestamos
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Prestamos) {
			Prestamos aux=(Prestamos)obj;
			if(this.fecha_inicio.equals(aux.getFecha_inicio())&&this.fecha_fin.equals(aux.getFecha_fin())&&
					this.socio_asocidado==aux.getSocio_asocidado()&&
					this.libro_asociado==aux.libro_asociado)
				return(true);
		}
		return false;
	}

	/**
	 * Metodo toString
	 */

	@Override
	public String toString() {
		return "Datos del prestamo: Dia del prestamo " + fecha_inicio + ", Fecha limite del prestamo " + fecha_fin + ", ID del libro prestado "
				+ libro_asociado + ", ID del socio " + socio_asocidado;
	}

	/**
	 * Metodo get fecha_inicio
	 * @return retorna la fecha de cuando se ha prestado el libro
	 */

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Metodo set fecha_inicio
	 * @param fecha_inicio el valor que se le quiere dar a la fecha_inicio
	 */

	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Metodo get fecha_fin
	 * @return retorna la fecha limite de devolucion
	 */

	public Date getFecha_fin() {
		return fecha_fin;
	}

	/**
	 * Metodo set fecha_fin
	 * @param fecha_fin el valor que se le quiere dar a la fecha_fin
	 */

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Metodo get libro_asociado
	 * @return retorna el libro que se ha prestado en ese prestamo
	 */

	public int getLibro_asociado() {
		return libro_asociado;
	}

	/**
	 * Metodo set libro_asociado
	 * @param libro_asociado el valor que se le quiere dar al libro que se ha prestado
	 */

	public void setLibro_asociado(int libro_asociado) {
		this.libro_asociado = libro_asociado;
	}

	/**
	 * Metodo get socio_asociado
	 * @return retorna el socio que ha cogido un libro
	 */

	public int getSocio_asocidado() {
		return socio_asocidado;
	}

	/**
	 * Metodo set socio_asociado
	 * @param socio_asocidado el valor que se le quiere dar al socio que ha cogido un libro
	 */

	public void setSocio_asocidado(int socio_asocidado) {
		this.socio_asocidado = socio_asocidado;
	}
	
	/**
	 * Metodo por defecto de ordenacion de los prestamos
	 */
	
	@Override
	public int compareTo(Prestamos o) {
		return String.valueOf(this.libro_asociado).compareTo(String.valueOf(o.getLibro_asociado()));
	}
}

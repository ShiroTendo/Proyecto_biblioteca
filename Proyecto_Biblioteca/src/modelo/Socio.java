package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;

/**
 * Clase centrada en la gesti�n y uso de la biblioteca a trav�s de Socios.
 * @author Ivan,Luis y Sergio
 *
 */
public class Socio extends Personas implements Comparable<Socio>{
		
		private int Cod_Socio;
		private HashSet<Libros> Libros_Tiene;
		
		
		/**
		 * Constructor de la clase.
		 * @param Dni String
		 * @param Nombre String
		 * @param Apellidos String
		 * @param N_telefono int
		 * @param cod_Socio int
		 */
		public Socio(String Dni, String Nombre, String Apellidos, int N_telefono, int cod_Socio) {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = cod_Socio;
			Libros_Tiene = new HashSet<Libros>();
		}
		/*public Socio(String Dni, String Nombre, String Apellidos, int N_telefono) throws ClassNotFoundException, SQLException {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = buscaMaxCod()+1;
			Libros_Tiene = new HashSet<Libros>();
		}*/

		/**
		 * M�todo encargado de mostrar los libros que tiene el socio.
		 */
		public void show_libros() {
			for (Libros libros : Libros_Tiene) {
				System.out.println(libros.toString());
			}
			
		}
		
		/*public int buscaMaxCod() throws ClassNotFoundException, SQLException {
			int num=0;
			Statement st = Conector.conectar().createStatement();
			ResultSet rs = st.executeQuery("select max(cod_socio) from socios");
			if(rs.next()) {
				num=rs.getInt(1);
			}
			if(String.valueOf(num)==null) {
				return 0;
			}
			return num;
		}*/
		
		/**
		 * Constructor de copia.
		 * 
		 * @param obj un objeto de tipo Socio
		 */
		public Socio(Socio obj) {
			super(obj);
			this.Cod_Socio=obj.getCod_Socio();
			this.Libros_Tiene=obj.getLibros_Tiene();
		}
		
		/**
		 * M�todo encargado de insertar un socio en la base de datos y en la biblioteca pasada como par�metro.
		 * 
		 * @param bl objeto de tipo Biblioteca
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		public void insertarSocioBD(Biblioteca bl) throws SQLException, ClassNotFoundException {
			try {
			String insert = " Insert into socios values(?,?,?,?,?)";
			PreparedStatement st2 = Conector.conectar().prepareStatement(insert);
			st2.setString(1, this.getDni());
			st2.setString(2, this.getNombre());
			st2.setString(3, this.getApellidos());
			st2.setInt(4, this.getN_telefono());
			st2.setInt(5, this.getCod_Socio());
			st2.executeUpdate();
			bl.getLista_socios().add(this);
			}finally {
				Conector.cerrar();
			}
		}
		
		/**
		 * M�todo encargado de eliminar un socio de la base de datos.
		 * 
		 * @throws SQLException
		 * @throws ClassNotFoundException
		 */
		public void eliminarSocioBD() throws SQLException, ClassNotFoundException {
			try {
			String insert = " delete from socios where cod_socio =" + this.getCod_Socio();
			Statement st = Conector.conectar().createStatement();
			st.executeUpdate(insert);
			}catch (SQLException e) {
				System.out.println("El c�digo de socio introducido no es v�lido, introduce otro");
			}
			
		}
		
		/**
		 * M�todo encargado de a�adir un libro a un socio.
		 * 
		 * @param libro un objeto de tipo Libros que se le quiera a�adir al socio.
		 * @return
		 */
		public Socio insertarLibroenSocio( Libros libro) {
			libro.setPrestado(true);
			this.getLibros_Tiene().add(libro);
			Socio aux = this;
			return aux;


		}
		
		public Socio eliminarLibroenSocio( Libros libro) {
			this.getLibros_Tiene().remove(libro);
			return this;


		}
		
		/**
		 * M�todo encargado de a�adir o eliminar un socio en biblioteca.
		 * 
		 * @param socioold un objeto de tipo Socio
		 * @param biblio un objeto de tipo Biblioteca
		 */
		public void updateSocioenBiblio(Socio socioold,Biblioteca biblio) {
			if(biblio.getLista_socios().contains(socioold)) {
				biblio.getLista_socios().remove(socioold);
				biblio.getLista_socios().add(this);
			}

		}
		
		/**
		 * M�todo hashCode de la clase.
		 */
		@Override
		public int hashCode() {
			return Objects.hash(Cod_Socio,Libros_Tiene);
		}

		/**
		 * M�todo equals de la clase.
		 * Este m�todo compara el codigo del socio y los libros que tiene el socio.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if(obj instanceof Socio) {
				Socio aux=(Socio)obj;
				if(this.Cod_Socio==aux.Cod_Socio&&this.Libros_Tiene.equals(aux.getLibros_Tiene()))
					return(true);
			}
			return false;
		}

		/**
		 * M�todo get cod_socio
		 * 
		 * @return devuelve el c�digo del socio
		 */
		public int getCod_Socio() {
			return Cod_Socio;
		}

		/**
		 * M�todo set cod_socio.
		 * 
		 * @param cod_Socio el valor que se le quiera dar a cod_socio
		 */
		public void setCod_Socio(int cod_Socio) {
			Cod_Socio = cod_Socio;
		}

		/**
		 * M�todo get libros_tiene.
		 * 
		 * @return devuelve una lista de los libros que tiene el socio
		 */
		public HashSet<Libros> getLibros_Tiene() {
			return Libros_Tiene;
		}

		/**
		 * M�todo set libros_tiene.
		 * 
		 * @param libros_Tiene el valor que se le quiera dar a libros_tiene
		 */
		public void setLibros_Tiene(HashSet<Libros> libros_Tiene) {
			Libros_Tiene = libros_Tiene;
		}

		/**
		 * M�todo toString de la clase.
		 */
		@Override
		public String toString() {
			return "Socio: " + Cod_Socio + " " + super.toString() + ", y sus libros en pertenencia son: \n" + this.getLibros_Tiene().toString();
		}
		
		/**
		 * M�todo que ordena los socios por su c�digo de menor a mayor.
		 */
		@Override
		public int compareTo(Socio o) {
			return String.valueOf(this.Cod_Socio).compareTo(String.valueOf(o.getCod_Socio()));
		}
		

		
}

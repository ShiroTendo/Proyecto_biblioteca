package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/**
 * Clase centrada en la gestión y uso de la biblioteca a través de Socios.
 * @author Ivan,Luis y Sergio
 *
 */
public class Socio extends Personas implements Comparable<Socio>{
		private int Cod_Socio;
		private HashSet<Libros> Libros_Tiene;
		private static int  num_socio;
		static {
			try {
				num_socio= buscaMaxCod();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		/**
		 * Constructor de la clase.
		 * @param Dni String
		 * @param Nombre String
		 * @param Apellidos String
		 * @param N_telefono int
		 * @param cod_Socio int
		 * @throws SQLException 
		 * @throws ClassNotFoundException 
		 */
		public Socio(String Dni, String Nombre, String Apellidos, int N_telefono, int cod_Socio) throws ClassNotFoundException, SQLException {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = cod_Socio;
			Libros_Tiene = new HashSet<Libros>(cargaPrestamos());
		}
		
		/**
		 * Constructor de la clase con el cod_socio automático.
		 * 
		 * @param Dni String
		 * @param Nombre String
		 * @param Apellidos String
		 * @param N_telefono int
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public Socio(String Dni, String Nombre, String Apellidos, int N_telefono) throws ClassNotFoundException, SQLException {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = num_socio+1;
			Libros_Tiene = new HashSet<Libros>(cargaPrestamos());
			num_socio++;
		}
		
		public static  int buscaMaxCod() throws ClassNotFoundException, SQLException {
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
			};
			
			
			public   HashSet<Libros> cargaPrestamos() throws SQLException, ClassNotFoundException {
				int id_libro=0;
				String titulo="";
				String autor="";
				String genero="";
				int aux=0;
				boolean estado=false;
				Libros libro;
				HashSet<Libros> lista= new HashSet<Libros>();
				 Statement st=Conector.conectar().createStatement();
				 ResultSet rs=st.executeQuery("select libros.id_libro,titulo,autor,genero,estado from libros,prestamos,socios"
				 		+ " where libros.id_libro=prestamos.id_libro and prestamos.cod_socio=socios.cod_socio and socios.cod_socio="+this.Cod_Socio);
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
				Conector.cerrar();
				return lista;
				
			}

		
			
			
			
		
		
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
		 * Método encargado de insertar un socio en la base de datos y en la biblioteca pasada como parámetro.
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
		 * Método encargado de eliminar un socio de la base de datos.
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
				System.out.println("El código de socio introducido no es válido, introduce otro");
			}
			
		}
		
		/**
		 * Método encargado de añadir un libro a un socio.
		 * 
		 * @param libro un objeto de tipo Libros que se le quiera añadir al socio
		 * @return
		 */
		public Socio insertarLibroenSocio( Libros libro) {
			libro.setPrestado(true);
			this.getLibros_Tiene().add(libro);
			Socio aux = this;
			return aux;


		}
		
		/**
		 * Método encargado de eliminar un libro a un socio.
		 * 
		 * @param libro un objeto de tipo libro que se le quiera eliminar al socio
		 * @return el libro que se elimina
		 */
		public Socio eliminarLibroenSocio( Libros libro) {
			this.getLibros_Tiene().remove(libro);
			return this;


		}
		
		/**
		 * Método encargado de añadir o eliminar un socio en biblioteca.
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
		
		public String devuelveID() {
			if(this.Libros_Tiene.size()!=0) {		
				Iterator it = this.Libros_Tiene.iterator();
				String retorno = " ";
				while(it.hasNext()) {
					Libros aux = (Libros) it.next();
					retorno+= String.valueOf(aux.getId_libro())+" ";
				}
				return retorno;
			}
			else
				return "Ningun libro";
		}
		
		/**
		 * Método hashCode de la clase.
		 */
		@Override
		public int hashCode() {
			return Objects.hash(Cod_Socio,Libros_Tiene);
		}

		/**
		 * Método equals de la clase.
		 * Este método compara el codigo del socio y los libros que tiene el socio.
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
		 * Método get cod_socio
		 * 
		 * @return devuelve el código del socio
		 */
		public int getCod_Socio() {
			return Cod_Socio;
		}

		/**
		 * Método set cod_socio.
		 * 
		 * @param cod_Socio el valor que se le quiera dar a cod_socio
		 */
		public void setCod_Socio(int cod_Socio) {
			Cod_Socio = cod_Socio;
		}

		/**
		 * Método get libros_tiene.
		 * 
		 * @return devuelve una lista de los libros que tiene el socio
		 */
		public HashSet<Libros> getLibros_Tiene() {
			return Libros_Tiene;
		}

		/**
		 * Método set libros_tiene.
		 * 
		 * @param libros_Tiene el valor que se le quiera dar a libros_tiene
		 */
		public void setLibros_Tiene(HashSet<Libros> libros_Tiene) {
			Libros_Tiene = libros_Tiene;
		}

		/**
		 * Método toString de la clase.
		 */
		@Override
		public String toString() {
			return "Socio: " + Cod_Socio + " " + super.toString() + ", y sus libros en pertenencia son: \n" + this.getLibros_Tiene().toString();
		}
		
		/**
		 * Método que ordena los socios por su código de menor a mayor.
		 */
		@Override
		public int compareTo(Socio o) {
			return String.valueOf(this.Cod_Socio).compareTo(String.valueOf(o.getCod_Socio()));
		}
		

		
}

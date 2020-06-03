package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Objects;

public class Socio extends Personas implements Comparable<Socio>{
		
		private int Cod_Socio;
		private HashSet<Libros> Libros_Tiene;
		
		
		
		public Socio(String Dni, String Nombre, String Apellidos, int N_telefono, int cod_Socio) {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = cod_Socio;
			Libros_Tiene = new HashSet<Libros>();
		}

		public void show_libros() {
			for (Libros libros : Libros_Tiene) {
				System.out.println(libros.toString());
			}
			
		}
		
		
		public Socio(Socio obj) {
			super(obj);
			this.Cod_Socio=obj.getCod_Socio();
			this.Libros_Tiene=obj.getLibros_Tiene();
		}
		
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
		
		public void eliminarSocioBD() throws SQLException, ClassNotFoundException {
			try {
			String insert = " delete from socios where cod_socio =" + this.getCod_Socio();
			Statement st = Conector.conectar().createStatement();
			st.executeUpdate(insert);
			}catch (SQLException e) {
				System.out.println("El código de socio introducido no es válido, introduce otro");
			}
			
		}
		

		@Override
		public int hashCode() {
			return Objects.hash(Cod_Socio,Libros_Tiene);
		}

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

		public int getCod_Socio() {
			return Cod_Socio;
		}

		public void setCod_Socio(int cod_Socio) {
			Cod_Socio = cod_Socio;
		}

		public HashSet<Libros> getLibros_Tiene() {
			return Libros_Tiene;
		}

		public void setLibros_Tiene(HashSet<Libros> libros_Tiene) {
			Libros_Tiene = libros_Tiene;
		}

		@Override
		public String toString() {
			return "Socio [Cod_Socio=" + Cod_Socio + ", Libros_Tiene=" + Libros_Tiene + "]";
		}
		
		@Override
		public int compareTo(Socio o) {
			return String.valueOf(this.Cod_Socio).compareTo(String.valueOf(o.getCod_Socio()));
		}
		

		
}

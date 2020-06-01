package modelo;

import java.util.HashSet;

public class Socio extends Personas {
		
		private String Cod_Socio;
		private HashSet<Libros> Libros_Tiene;
		
		
		
		public Socio(String Dni, String Nombre, String Apellidos, int N_telefono, String cod_Socio,
				String[] libros_Tiene) {
			super(Dni, Nombre, Apellidos, N_telefono);
			Cod_Socio = cod_Socio;
			Libros_Tiene = new HashSet<Libros>();
		}

		public void show_libros() {
			for (Libros libros : Libros_Tiene) {
				System.out.println(libros.toString());
			}
			
		}

		public String getCod_Socio() {
			return Cod_Socio;
		}

		public void setCod_Socio(String cod_Socio) {
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
		

		
}

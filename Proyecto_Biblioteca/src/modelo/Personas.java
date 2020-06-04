package modelo;

import java.util.Objects;

/**
 * Clase centrada en los atributos que tendrán las personas de las clases Bibliotecario y Socio.
 * @author Ivan,Luis y Sergio
 *
 */
public class Personas {

	private String Dni;
	private String Nombre;
	private String Apellidos;
	private int N_telefono;
	
	/**
	 * Constructor de la clase.
	 * @param Dni String
	 * @param Nombre String
	 * @param Apellidos String
	 * @param N_telefono int
	 */
	public Personas(String Dni, String Nombre, String Apellidos, int N_telefono) {
		this.Dni=Dni;
		this.Nombre=Nombre;
		this.Apellidos=Apellidos;
		this.N_telefono=N_telefono;
	}
	

	/**
	 * Método toString de la clase.
	 */
	@Override
	public String toString() {
		return "Dni: " + Dni + ", Nombre: " + Nombre + " " + Apellidos + ", N_telefono:" + N_telefono + " ";
	}
	
	/**
	 * Constructor de copia de clase.
	 * 
	 * @param obj un objeto de tipo Personas
	 */
	public Personas(Personas obj) {
		this.Dni=obj.getDni();
		this.Nombre=obj.getNombre();
		this.Apellidos=obj.getApellidos();
		this.N_telefono=obj.getN_telefono();
	}
	
	
	
	

	/**
	 * Método hashCode de la clase.
	 */
	@Override
	public int hashCode() {
	return Objects.hash(Dni,Nombre,Apellidos,N_telefono); 
	}

	
	/**
	 * Método equals de la clase.
	 * Este método compara todos los atributos de la clase.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Personas) {
			Personas aux=(Personas) obj;
			if(this.Dni.equalsIgnoreCase(aux.getDni())&&this.Nombre.equalsIgnoreCase(aux.getNombre())&&
					this.Apellidos.equalsIgnoreCase(aux.getApellidos())&&this.N_telefono==aux.getN_telefono())
				return(true);
				
		}
		return false;
	}


	/**
	 * Método get dni.
	 * 
	 * @return devuele el dni de la persona
	 */
	public String getDni() {
		return Dni;
	}

	/**
	 * Método set dni.
	 * 
	 * @param dni el valor que sele quiera dar a dni
	 */
	public void setDni(String dni) {
		Dni = dni;
	}

	/**
	 * Método get nombre.
	 * 
	 * @return devuelve el nombre de la persona
	 */
	public String getNombre() {
		return Nombre;
	}

	/**
	 * Método set nombre.
	 * 
	 * @param nombre el valor que se le quiera dar a nombre
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	/**
	 * Método get apellidos.
	 * 
	 * @return devuelve el apellido de la persona
	 */
	public String getApellidos() {
		return Apellidos;
	}

	/**
	 * Método set apellidos.
	 * 
	 * @param apellidos el valor que se le quiera dar a apellidos
	 */
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	/**
	 * Método get n_telefono.
	 * 
	 * @return devuelve el numero de telefono de la persona
	 */
	public int getN_telefono() {
		return N_telefono;
	}

	/**
	 * Método set ntelefono-
	 * 
	 * @param n_telefono el valor que se le quiera dar a n_telefono
	 */
	public void setN_telefono(int n_telefono) {
		N_telefono = n_telefono;
	}
}

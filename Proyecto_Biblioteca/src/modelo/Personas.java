package modelo;

import java.util.Objects;

public class Personas {

	private String Dni;
	private String Nombre;
	private String Apellidos;
	private int N_telefono;
	
	public Personas(String Dni, String Nombre, String Apellidos, int N_telefono) {
		this.Dni=Dni;
		this.Nombre=Nombre;
		this.Apellidos=Apellidos;
		this.N_telefono=N_telefono;
	}
	

	@Override
	public String toString() {
		return "Dni: " + Dni + ", Nombre: " + Nombre + Apellidos + ", N_telefono:" + N_telefono;
	}
	
	public Personas(Personas obj) {
		this.Dni=obj.getDni();
		this.Nombre=obj.getNombre();
		this.Apellidos=obj.getApellidos();
		this.N_telefono=obj.getN_telefono();
	}
	
	
	
	


	@Override
	public int hashCode() {
	return Objects.hash(Dni,Nombre,Apellidos,N_telefono); 
	}


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


	public String getDni() {
		return Dni;
	}

	public void setDni(String dni) {
		Dni = dni;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public int getN_telefono() {
		return N_telefono;
	}

	public void setN_telefono(int n_telefono) {
		N_telefono = n_telefono;
	}
}

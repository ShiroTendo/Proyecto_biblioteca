package modelo;

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
		return "Dni: " + Dni + ", Nombre: " + Nombre + Apellidos + ", N_telefono:" + N_telefono+ super.toString();
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

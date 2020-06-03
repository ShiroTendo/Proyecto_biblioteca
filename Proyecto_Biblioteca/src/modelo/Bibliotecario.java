package modelo;

import java.util.HashSet;

public class Bibliotecario extends Personas {
	private int Cod_Emple;
	
	public Bibliotecario(String Dni, String Nombre, String Apellidos, int N_telefono, int Cod_Emple) {
		super(Dni, Nombre, Apellidos, N_telefono);
		this.Cod_Emple=Cod_Emple;
	}
	
	//hola
	//te sale
	public  boolean Alta_Socio(Socio socio,HashSet<Socio> listasocios) {
		if(listasocios.contains(socio))
			return false;
		else
			return listasocios.add(socio); 
	}
	
	
	public int getCod_Emple() {
		return Cod_Emple;
	}

	public void setCod_Emple(int cod_Emple) {
		Cod_Emple = cod_Emple;
	}

	
}

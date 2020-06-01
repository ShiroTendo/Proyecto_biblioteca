package modelo;

import java.util.HashSet;

public class Bibliotecario extends Personas {
	private String Cod_Emple;
	private String Horario;
	
	public Bibliotecario(String Dni, String Nombre, String Apellidos, int N_telefono, String Cod_Emple, String Horario) {
		super(Dni, Nombre, Apellidos, N_telefono);
		this.Cod_Emple=Cod_Emple;
		this.Horario=Horario;
	}
	
	public  boolean Alta_Socio(Socio socio,HashSet<Socio> listasocios) {
		if(listasocios.contains(socio))
			return false;
		else
			return listasocios.add(socio); 
	}
	

	public String getCod_Emple() {
		return Cod_Emple;
	}

	public void setCod_Emple(String cod_Emple) {
		Cod_Emple = cod_Emple;
	}

	public String getHorario() {
		return Horario;
	}

	public void setHorario(String horario) {
		Horario = horario;
	}
}

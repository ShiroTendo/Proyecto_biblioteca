package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public void insertarBibliotecarioBD() throws ClassNotFoundException, SQLException {
		try {
			String insert = " Insert into bibliotecarios values(?,?,?,?,?)";
			PreparedStatement st2 = Conector.conectar().prepareStatement(insert);
			st2.setString(1, this.getDni());
			st2.setString(2, this.getNombre());
			st2.setString(3, this.getApellidos());
			st2.setInt(4, this.getN_telefono());
			st2.setInt(5, this.getCod_Emple());
			st2.executeUpdate();
		}finally {
			Conector.cerrar();
		}
	}
	
	public void eliminarBibliotecarioBD() throws SQLException, ClassNotFoundException {
		try {
		String insert="delete from bibliotecarios where cod_emple ="+this.getCod_Emple();
		Statement st=Conector.conectar().createStatement();
		st.executeUpdate(insert);
		}finally {
			Conector.cerrar();
		}
	}
	
	
	public int getCod_Emple() {
		return Cod_Emple;
	}

	public void setCod_Emple(int cod_Emple) {
		Cod_Emple = cod_Emple;
	}

	
}

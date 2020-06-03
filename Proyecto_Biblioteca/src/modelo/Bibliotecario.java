package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;

public class Bibliotecario extends Personas implements Comparable<Bibliotecario>{
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
	
	public void insertarBibliotecarioBD(Biblioteca bi1) throws ClassNotFoundException, SQLException {
		try {
			String insert = " Insert into bibliotecarios values(?,?,?,?,?)";
			PreparedStatement st2 = Conector.conectar().prepareStatement(insert);
			st2.setString(1, this.getDni());
			st2.setString(2, this.getNombre());
			st2.setString(3, this.getApellidos());
			st2.setInt(4, this.getN_telefono());
			st2.setInt(5, this.getCod_Emple());
			st2.executeUpdate();
			bi1.getLista_bibliotecarios().add(this);
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
	
	public  void PrestarLibro(int socio_id, int id_libro,Biblioteca biblio) throws ClassNotFoundException, SQLException {
		Socio socio=biblio.buscarSocio(socio_id);
		Libros libro=biblio.buscaLibro(id_libro);
		if(socio!=null){
			if(libro!=null){
				if(!libro.isPrestado()) {			
					String[] fechas=devuelveFecha();
					Prestamos prestamo= new Prestamos(Date.valueOf(fechas[0]), Date.valueOf(fechas[1]), socio.getCod_Socio(), libro.getId_libro());
					prestamo.insertarPrestamo();
					biblio.getLista_prestamos().add(prestamo);
					libro.updateStatusLibroTrue(biblio);
					libro.updateTrueBd();
					Socio socio2=socio.insertarLibroenSocio(libro);
					socio2.updateSocioenBiblio(socio, biblio);
				}
				else
					System.out.println("El libro ya esta prestado, prueba mas tarde");

			}
			else
				System.out.println("El libro no existe, introduce un id de libro valido");
		}
		else			
			System.out.println("El usuario no se encuentra en la base de datos, intrudzca un id valido");
	}
	public String[] devuelveFecha(){
		java.util.Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate = dateFormat.format(date); 
		Calendar cal=Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		java.util.Date date2 = cal.getTime();  
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");  
		String strDate2 = dateFormat2.format(date2); 
		String [] devolver= {strDate,strDate2};
		return devolver;

	}
	
	
	public int getCod_Emple() {
		return Cod_Emple;
	}

	public void setCod_Emple(int cod_Emple) {
		Cod_Emple = cod_Emple;
	}

	@Override
	public int compareTo(Bibliotecario o) {
		return String.valueOf(this.Cod_Emple).compareTo(String.valueOf(o.getCod_Emple()));
	}

	@Override
	public String toString() {
		return super.toString() + "Cod_Emple=" + Cod_Emple;
	}

	
}

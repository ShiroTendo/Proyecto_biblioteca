package pruebas_Junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Biblioteca;
import modelo.Libros;
import modelo.Socio;

public class Test_Buscadores {
	

	@Test
	public void buscarSocioNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNull(aux.buscarSocio(20));
	}
	@Test
	public void buscarSocioNotNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNotNull(aux.buscarSocio(1));
	}
	
	@Test
	public void buscarLibroNotNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNull(aux.buscaLibro(20));
	}
	@Test
	public void buscarLibroNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNotNull(aux.buscaLibro(1));
	}
	
	@Test
	public void buscarBibliotecarioNotNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNull(aux.buscarBibliotecario(20));
	}
	@Test
	public void buscarBibliotecarioNull() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNotNull(aux.buscarBibliotecario(1));
	}
	
	



}

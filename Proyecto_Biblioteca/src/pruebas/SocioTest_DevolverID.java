package pruebas;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Biblioteca;
import modelo.Libros;
import modelo.Socio;

public class SocioTest_DevolverID {
	

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Socio aux = new Socio("12345678A", "Juan", "Antonio", 123456789, 1);
		Libros l1= new Libros(1,  "La vie", "Jean", "fantasia", false);
		aux.getLibros_Tiene().add(l1);
		assertEquals("1 1 ", aux.devuelveID());
	}
	@Test
	public void test2() throws ClassNotFoundException, SQLException {
		Socio aux = new Socio("12345678A", "Juan", "Antonio", 123456789, 1);
		Libros l1= new Libros(1,  "La vie", "Jean", "fantasia", false);
		aux.getLibros_Tiene().add(l1);
		assertNotEquals("0 ", aux.devuelveID());
	}
	@Test(expected = java.sql.SQLException.class)
	public void test3() throws ClassNotFoundException, SQLException {
		Socio aux = new Socio("12345678515154156151A", "Juan", "Antonio", 123456789, 1);
		Biblioteca bl = new Biblioteca();
		aux.insertarSocioBD(bl);
	}

}

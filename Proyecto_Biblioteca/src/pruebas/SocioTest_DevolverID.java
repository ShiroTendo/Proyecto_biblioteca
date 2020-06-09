package pruebas;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Libros;
import modelo.Socio;

public class SocioTest_DevolverID {
	

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Socio aux = new Socio("12345678A", "Juan", "Antonio", 123456789, 1);
		Libros l1= new Libros(1,  "La vie", "Jean", "fantasia", false);
		aux.getLibros_Tiene().add(l1);
		assertEquals("1 ", aux.devuelveID());
	}

}

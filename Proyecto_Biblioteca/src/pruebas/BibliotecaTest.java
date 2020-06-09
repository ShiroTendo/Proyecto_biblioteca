package pruebas;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Biblioteca;

public class BibliotecaTest {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNull(aux.buscaLibro(20));
	}

}

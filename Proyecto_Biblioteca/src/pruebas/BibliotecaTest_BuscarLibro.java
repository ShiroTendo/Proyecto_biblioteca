package pruebas;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Biblioteca;

public class BibliotecaTest_BuscarLibro {

	@Test
	public void test() throws ClassNotFoundException, SQLException {
		Biblioteca aux= new Biblioteca();
		assertNull(aux.buscaLibro(20));
	}

}

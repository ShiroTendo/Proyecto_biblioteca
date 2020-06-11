package pruebas_Junit;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import modelo.Biblioteca;
import modelo.Bibliotecario;

public class BibliotecarioTest_Prestar_Devolver {

	@Test
	public void testPrestar1() throws ClassNotFoundException, SQLException {
		Bibliotecario bl1= new Bibliotecario("21345698W", "Carla", "Romero", 645231897);
		Biblioteca b1= new Biblioteca();
		bl1.DevolverLibro(1, 1, b1);
		assertEquals(0,bl1.PrestarLibro(1, 1, b1));
	}
	@Test
	public void testPrestar2() throws ClassNotFoundException, SQLException {
		Bibliotecario bl1= new Bibliotecario("21345698W", "Carla", "Romero", 645231897);
		Biblioteca b1= new Biblioteca();
		bl1.DevolverLibro(1, 1, b1);
		bl1.PrestarLibro(1, 1, b1);
		assertEquals(1,bl1.PrestarLibro(1, 1, b1));
	}
	@Test
	public void testPrestar3() throws ClassNotFoundException, SQLException {
		Bibliotecario bl1= new Bibliotecario("21345698W", "Carla", "Romero", 645231897);
		Biblioteca b1= new Biblioteca();
		bl1.DevolverLibro(1, 1, b1);
		bl1.PrestarLibro(1, 1, b1);
		assertNotEquals(20,bl1.PrestarLibro(1, 1, b1));
	}
	
	@Test
	public void teestDevolver() throws ClassNotFoundException, SQLException {
		Bibliotecario bl1= new Bibliotecario("21345698W", "Carla", "Romero", 645231897);
		Biblioteca b1= new Biblioteca();
		assertEquals(1,bl1.DevolverLibro(20, 1, b1));
	}
	@Test
	public void teestDevolver2() throws ClassNotFoundException, SQLException {
		Bibliotecario bl1= new Bibliotecario("21345698W", "Carla", "Romero", 645231897);
		Biblioteca b1= new Biblioteca();
		assertNotEquals(2,bl1.DevolverLibro(20, 1, b1));
	}

	
	
	
	
	
	
	

}

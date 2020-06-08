package pruebas;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import modelo.Biblioteca;
import modelo.Bibliotecario;
import modelo.Libros;
import modelo.Prestamos;
import modelo.Socio;

public class pruebas_Clases_bbdd {
	


	public static void main(String[] args){
		try {
			Libros l1= new Libros("El Quijote", "Cervantes", "novela");
			Libros l2= new Libros("Los juegos del Hambre", "Suzane Collins", "fantasia");
			Libros l3= new Libros("El Lazarillo de Tormes", "Desconocido", "novela picaresca");
			Libros l4= new Libros("La Biblia", "Desconocido", "fantasia");
			Libros l5= new Libros("Harry Potter", "J.K Rollings", "fantasia");
			Libros l6= new Libros("Señor de los Anillos", "J.R.R Tolkien", "fantasia");
			Libros l7= new Libros("Dr Stone", "Richiro", "manga");
			Libros l8= new Libros("Cancion de hielo y fuego", "George R. R. Martin", "novela");
			Libros l9= new Libros("El Principito", "Antoine", "fabula");
			Socio s1= new Socio("12345678A", "Gorka", "Iraizoz",635789426);
			Socio s2= new Socio("28746379R", "Oswaldo", "Nuñez",965471235);
			Socio s3= new Socio("96843571P", "Morta", "Rama",945123674);
			Bibliotecario b1= new Bibliotecario("97852461Z", "Alfonso", "Milos", 632178945);
			Bibliotecario b2= new Bibliotecario("45123657F", "Ana", "Suarez", 654124896);
			Biblioteca biblio= new Biblioteca();
			l1.insertarLibroBD(biblio);
			l2.insertarLibroBD(biblio);
			l3.insertarLibroBD(biblio);
			l4.insertarLibroBD(biblio);
			l5.insertarLibroBD(biblio);
			l6.insertarLibroBD(biblio);
			l7.insertarLibroBD(biblio);
			l8.insertarLibroBD(biblio);
			l9.insertarLibroBD(biblio);
			s1.insertarSocioBD(biblio);
			s2.insertarSocioBD(biblio);
			s3.insertarSocioBD(biblio);
			b1.insertarBibliotecarioBD(biblio);
			b2.insertarBibliotecarioBD(biblio);
	
		
		}catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Error, el id ya existe en la tabla");
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}

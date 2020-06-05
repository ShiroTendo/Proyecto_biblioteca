package vista;

import modelo.Biblioteca;

public class MainVentana {

	static Biblioteca biblioteca;
	
	public static void main(String[] args) {
		
		try {
			
			biblioteca = new Biblioteca();
			new VentanaInicio();
			
		} catch (Exception e) {
			
		}

	}

}

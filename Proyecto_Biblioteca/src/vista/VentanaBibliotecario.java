package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Bibliotecario;
import modelo.Libros;
import modelo.Prestamos;
import modelo.Socio;
import net.miginfocom.swing.MigLayout;

/**
 * Clase dedicada a la interfaz de la ventana bibliotecario con sus respectivas pestañas
 * @author Ivan, Luis y Sergio
 *
 */
public class VentanaBibliotecario extends JFrame implements ActionListener, WindowListener{

	//PESTAÑA IMPRIMIR
	private JLabel tituloImprimir;
	private JButton imprimirBibliotecarios;
	private JButton imprimirLibros;
	private JButton imprimirPrestamos;
	private JButton imprimirSocios;

	//PESTAÑA MOSTRARTODO
	private JLabel tituloMostrar;
	private JButton mostrarBibliotecarios;
	private JButton mostrarLibros;
	private JButton mostrarPrestamos;
	private JButton mostrarSocios;
	private DefaultTableModel modeloMostrar;
	private JTable tablaMostrar;

	//PESTAÑA AÑADIR/BORRAR LIBRO
	private JLabel tituloAnadirBorrar;
	private JLabel anadir;
	private JLabel borrar;
	private JLabel tituloLibro;
	private JLabel autorLibro;
	private JLabel generoLibro;
	private JLabel idLibroBorrar;
	private JTextField escribirTitulo;
	private JTextField escribirAutor;
	private JTextField escribirGenero;
	private JTextField borrarIdLibro;
	private JButton anadirLibro;
	private JButton borrarLibro;

	//PESTAÑA PRESTAR/DEVOLVER
	private JLabel tituloPrestarDevolver;
	private JLabel tituloPrestar;
	private JLabel tituloDevolver;
	private JLabel idSocioPrestar;
	private JLabel idLibroPrestar;
	private JLabel idSocioDevolver;
	private JLabel idLibroDevolver;
	private JTextField escribirIdSocioPrestar;
	private JTextField escribirIdLibroPrestar;
	private JTextField escribirIdSocioDevolver;
	private JTextField escribirIdLibroDevolver;
	private JButton prestar;
	private JButton devolver;

	//PESTAÑA AÑADIR/ELIMINAR BIBLIOTECARIOS
	private JLabel tituloAnadirEliminarBibiliotecarios;
	private JLabel tituloAnadirBibiliotecarios;
	private JLabel tituloEliminarBibliotecarios;
	private JLabel nombreBibliotecario;
	private JLabel apellidosBibliotecario;
	private JLabel telefonoBibliotecario;
	private JLabel dniBibliotecario;
	private JLabel idBibliotecario;
	private JTextField escribirNombreBibliotecario;
	private JTextField escribirApellidosBibliotecario;
	private JTextField escribirTelefonoBibliotecario;
	private JTextField escribirDniBibliotecario;
	private JTextField escribirIdBibliotecario;
	private JButton anadirBibliotecario;
	private JButton eliminarBibliotecario;

	//PESTAÑA AÑADIR/ELIMINAR SOCIOS
	private JLabel tituloAnadirEliminarSocios;
	private JLabel tituloAnadirSocios;
	private JLabel tituloEliminarSocios;
	private JLabel nombreSocios;
	private JLabel apellidosSocios;
	private JLabel telefonoSocios;
	private JLabel dniSocios;
	private JLabel idSocios;
	private JTextField escribirNombreSocios;
	private JTextField escribirApellidosSocios;
	private JTextField escribirTelefonoSocios;
	private JTextField escribirDniSocios;
	private JTextField escribirIdSocios;
	private JButton anadirSocios;
	private JButton eliminarSocios;


	//PANELES
	private JTabbedPane pestanas;
	private JPanel panelImprimir;
	private JPanel panelTabla;
	private JPanel mostrarBotones;
	private JPanel panelAnadirBorrar;
	private JPanel panelAnadirLibro;
	private JPanel panelEliminarLibro;
	private JPanel panelTituloLibro;
	private JPanel panelPrestarDevolver;
	private JPanel panelPrestarDevolverTitulo;
	private JPanel panelPrestarLibro;
	private JPanel panelDevolverLibro;
	private JPanel panelAnadirEliminarBibliotecarios;
	private JPanel panelAnadirEliminarBibliotecariosTitulo;
	private JPanel panelAnadirBibliotecarios;
	private JPanel panelEliminarBibliotecarios;
	private JPanel panelAnadirEliminarSocios;
	private JPanel panelAnadirEliminarSociosTitulo;
	private JPanel panelAnadirSocios;
	private JPanel panelEliminarSocios;
	private JScrollPane scrollMostrar;

	private Bibliotecario bibliotecario;

	/**
	 * Constructor de la clase
	 * 
	 * @param b un objeto de tipo bibliotecario
	 */
	public VentanaBibliotecario(Bibliotecario b) {
		bibliotecario = new Bibliotecario(b);
		crearAlgo();
	}

	/**
	 * Método encargado de crear la interfaz de la ventana con las diferentes pestañas
	 */
	public void crearAlgo() {

		//CREAR PESTAÑA
		pestanas = new JTabbedPane();

		//CREAR PANEL IMPRIMIR
		panelImprimir = new JPanel();
		panelImprimir.setLayout(new MigLayout());

		//AÑADIR PANEL IMPRIMIR A LA PESTAÑA
		pestanas.addTab("IMPRIMIR", panelImprimir);

		//DATOS PANEL IMPRIMIR
		tituloImprimir = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());

		imprimirBibliotecarios = new JButton("Imprimir bibliotecarios");
		imprimirLibros = new JButton("Imprimir libros");
		imprimirPrestamos = new JButton("Imprimir préstamos");
		imprimirSocios = new JButton("Imprimir socios");

		imprimirBibliotecarios.addActionListener(this);
		imprimirLibros.addActionListener(this);
		imprimirPrestamos.addActionListener(this);
		imprimirSocios.addActionListener(this);
		imprimirBibliotecarios.setPreferredSize(new Dimension(400,70));
		imprimirLibros.setPreferredSize(new Dimension(400,70));
		imprimirPrestamos.setPreferredSize(new Dimension(400,70));
		imprimirSocios.setPreferredSize(new Dimension(400,70));
		
		panelImprimir.add(tituloImprimir, "pos 0.5al 0al");
		panelImprimir.add(imprimirBibliotecarios, "pos 0al 0.2al");
		panelImprimir.add(imprimirLibros, "pos 1al 0.2al");
		panelImprimir.add(imprimirPrestamos, "pos 0al 0.6al");
		panelImprimir.add(imprimirSocios, "pos 1al 0.6al");

		//CREAR PANEL MOSTRAR
		panelTabla = new JPanel();
		mostrarBotones = new JPanel();
		mostrarBotones.setLayout(new MigLayout());
		panelTabla.setLayout(new MigLayout("align 50%"));

		//AÑADIR PANEL MOSTRAR A PESTAÑA
		pestanas.addTab("LISTADO", panelTabla);

		//CREAR LA TABLA PARA MOSTRAR
		tablaMostrar = new JTable();
		scrollMostrar = new JScrollPane(tablaMostrar);

		//DATOS PANEL MOSTRARTODO
		tituloMostrar = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase().toUpperCase());

		mostrarBibliotecarios = new JButton("Mostrar bibliotecarios");
		mostrarLibros = new JButton("Mostrar libros");
		mostrarPrestamos = new JButton("Mostrar préstamos");
		mostrarSocios = new JButton("Mostrar socios");

		mostrarBibliotecarios.addActionListener(this);
		mostrarLibros.addActionListener(this);
		mostrarPrestamos.addActionListener(this);
		mostrarSocios.addActionListener(this);

		mostrarBotones.add(tituloMostrar, "align center, wrap");
		mostrarBotones.add(mostrarBibliotecarios, "wrap");
		mostrarBotones.add(mostrarLibros, "wrap");
		mostrarBotones.add(mostrarPrestamos, "wrap");
		mostrarBotones.add(mostrarSocios);
		panelTabla.add(mostrarBotones, "growy, pushy");
		panelTabla.add(scrollMostrar, "grow, push, shrink 0");

		//CREAR PANEL AÑADIR/BORRAR LIBRO
		panelAnadirBorrar = new JPanel();
		panelAnadirBorrar.setLayout(new MigLayout());
		panelAnadirLibro = new JPanel();
		panelAnadirLibro.setLayout(new MigLayout());
		panelEliminarLibro = new JPanel();
		panelEliminarLibro.setLayout(new MigLayout());
		panelTituloLibro = new JPanel();
		panelTituloLibro.setLayout(new MigLayout());

		//AÑADIR PANEL AÑADIR/BORRAR LIBRO A PESTAÑA
		pestanas.addTab("AÑADIR/BORRAR LIBRO", panelAnadirBorrar);

		//DATOS PANEL MOSTRAR

		tituloAnadirBorrar = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		anadir = new JLabel("AÑADIR LIBROS");
		borrar = new JLabel("BORRAR LIBROS");
		tituloLibro = new JLabel("Título del libro: ");
		autorLibro = new JLabel("Autor del libro: ");
		generoLibro = new JLabel("Género del libro: ");
		idLibroBorrar = new JLabel("ID del libro: ");

		escribirTitulo = new JTextField(30);
		escribirAutor = new JTextField(25);
		escribirGenero = new JTextField(15);
		borrarIdLibro = new JTextField(3);
		
		anadirLibro = new JButton("Añadir libro");
		borrarLibro = new JButton("Borrar libro");
		anadirLibro.addActionListener(this);
		borrarLibro.addActionListener(this);
		
		panelAnadirBorrar.add(panelTituloLibro, "pos 0.5al 0al");
		panelAnadirBorrar.add(panelAnadirLibro, "pos 0.2al 0.35al");
		panelAnadirBorrar.add(panelEliminarLibro, "pos 0.8al 0.295al");
		panelTituloLibro.add(tituloAnadirBorrar, "align center");
		panelAnadirLibro.add(anadir, "align center, wrap");
		panelAnadirLibro.add(tituloLibro);
		panelAnadirLibro.add(escribirTitulo, "wrap");
		panelAnadirLibro.add(autorLibro);
		panelAnadirLibro.add(escribirAutor, "wrap");
		panelAnadirLibro.add(generoLibro);
		panelAnadirLibro.add(escribirGenero, "wrap");
		panelAnadirLibro.add(anadirLibro, "align center");
		panelEliminarLibro.add(borrar, "align center, wrap");
		panelEliminarLibro.add(idLibroBorrar, "split2");
		panelEliminarLibro.add(borrarIdLibro, "wrap");
		panelEliminarLibro.add(borrarLibro);

		//CREAR PANEL PRESTAR/DEVOLVER LIBRO
		panelPrestarDevolver = new JPanel();
		panelPrestarDevolver.setLayout(new MigLayout());
		panelPrestarDevolverTitulo = new JPanel();
		panelPrestarDevolverTitulo.setLayout(new MigLayout());
		panelPrestarLibro = new JPanel();
		panelPrestarLibro.setLayout(new MigLayout());
		panelDevolverLibro = new JPanel();
		panelDevolverLibro.setLayout(new MigLayout());
		

		//AÑADIR PANEL PRESTAR/DEVOLVER A PESTAÑA
		pestanas.addTab("PRESTAR/DEVOLVER", panelPrestarDevolver);

		//DATOS PANEL PRESTAR/DEVOLVER
		tituloPrestarDevolver = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		tituloPrestar = new JLabel("PRÉSTAMO DE LIBROS");
		tituloDevolver = new JLabel("DEVOLUCIÓN DE LIBROS");
		idSocioPrestar = new JLabel("ID del socio: ");
		idLibroPrestar = new JLabel("ID del libro: ");
		idSocioDevolver = new JLabel("ID del socio: ");
		idLibroDevolver = new JLabel("ID del libro: ");

		escribirIdSocioPrestar = new JTextField(4);
		escribirIdLibroPrestar = new JTextField(4);
		escribirIdSocioDevolver = new JTextField(4);
		escribirIdLibroDevolver = new JTextField(4);

		prestar = new JButton("Prestar libro");
		devolver = new JButton("Devolver libro");
		prestar.addActionListener(this);
		devolver.addActionListener(this);
		
		panelPrestarDevolver.add(panelPrestarDevolverTitulo, "pos 0.5al 0al");
		panelPrestarDevolver.add(panelPrestarLibro, "pos 0.2al 0.4al");
		panelPrestarDevolver.add(panelDevolverLibro, "pos 0.8al 0.4al");
		panelPrestarDevolverTitulo.add(tituloPrestarDevolver, "align center");
		panelPrestarLibro.add(tituloPrestar, "align center, wrap");
		panelPrestarLibro.add(idSocioPrestar);
		panelPrestarLibro.add(escribirIdSocioPrestar, "wrap");
		panelPrestarLibro.add(idLibroPrestar);
		panelPrestarLibro.add(escribirIdLibroPrestar, "wrap");
		panelPrestarLibro.add(prestar);
		panelDevolverLibro.add(tituloDevolver, "align center, wrap");
		panelDevolverLibro.add(idSocioDevolver);
		panelDevolverLibro.add(escribirIdSocioDevolver, "wrap");
		panelDevolverLibro.add(idLibroDevolver);
		panelDevolverLibro.add(escribirIdLibroDevolver, "wrap");
		panelDevolverLibro.add(devolver);

		//CREAR PANEL AÑADIR/ELIMINAR BIBLIOTECARIOS
		panelAnadirEliminarBibliotecarios = new JPanel();
		panelAnadirEliminarBibliotecarios.setLayout(new MigLayout());
		panelAnadirEliminarBibliotecariosTitulo = new JPanel();
		panelAnadirEliminarBibliotecariosTitulo.setLayout(new MigLayout());
		panelAnadirBibliotecarios = new JPanel();
		panelAnadirBibliotecarios.setLayout(new MigLayout());
		panelEliminarBibliotecarios = new JPanel();
		panelEliminarBibliotecarios.setLayout(new MigLayout());

		//AÑADIR PANEL AÑADIR/ELIMINAR BIBLIOTECARIOS A PESTAÑA
		pestanas.addTab("AÑADIR/ELIMINAR BIBLIOTECARIOS", panelAnadirEliminarBibliotecarios);

		//DATOS PANEL AÑADIR/ELIMINAR BIBLIOTECARIOS
		tituloAnadirEliminarBibiliotecarios = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		tituloAnadirBibiliotecarios = new JLabel("AÑADIR BIBLIOTECARIOS");
		tituloEliminarBibliotecarios = new JLabel("ELIMINAR BIBLIOTECARIOS");
		nombreBibliotecario = new JLabel("Nombre: ");
		apellidosBibliotecario = new JLabel("Apellidos: ");
		telefonoBibliotecario = new JLabel("Teléfono: ");
		dniBibliotecario = new JLabel("DNI: ");
		idBibliotecario = new JLabel("Id bibliotecario: ");

		escribirNombreBibliotecario = new JTextField(15);
		escribirApellidosBibliotecario = new JTextField(40);
		escribirTelefonoBibliotecario = new JTextField(9);
		escribirDniBibliotecario = new JTextField(9);
		escribirIdBibliotecario = new JTextField(4);

		anadirBibliotecario = new JButton("Añadir");
		eliminarBibliotecario = new JButton("Eliminar");
		anadirBibliotecario.addActionListener(this);
		eliminarBibliotecario.addActionListener(this);

		
		panelAnadirEliminarBibliotecarios.add(panelAnadirEliminarBibliotecariosTitulo, "pos 0.5al 0al");
		panelAnadirEliminarBibliotecarios.add(panelAnadirBibliotecarios, "pos 0.1al 0.4al");
		panelAnadirEliminarBibliotecarios.add(panelEliminarBibliotecarios, "pos 0.9al 0.4al");
		panelAnadirEliminarBibliotecariosTitulo.add(tituloAnadirEliminarBibiliotecarios, "align center");
		panelAnadirBibliotecarios.add(tituloAnadirBibiliotecarios, "span, align left, wrap");
		panelAnadirBibliotecarios.add(nombreBibliotecario);
		panelAnadirBibliotecarios.add(escribirNombreBibliotecario, "wrap");
		panelAnadirBibliotecarios.add(apellidosBibliotecario);
		panelAnadirBibliotecarios.add(escribirApellidosBibliotecario, "wrap");
		panelAnadirBibliotecarios.add(telefonoBibliotecario);
		panelAnadirBibliotecarios.add(escribirTelefonoBibliotecario, "wrap");
		panelAnadirBibliotecarios.add(dniBibliotecario);
		panelAnadirBibliotecarios.add(escribirDniBibliotecario, "wrap");
		panelAnadirBibliotecarios.add(anadirBibliotecario);
		panelEliminarBibliotecarios.add(tituloEliminarBibliotecarios, "align center, wrap");
		panelEliminarBibliotecarios.add(idBibliotecario, "split2");
		panelEliminarBibliotecarios.add(escribirIdBibliotecario, "wrap");
		panelEliminarBibliotecarios.add(eliminarBibliotecario);
		
		/*panelAnadirEliminarBibliotecarios.add(tituloAnadirEliminarBibiliotecarios, "skip2, align center, wrap");
		panelAnadirEliminarBibliotecarios.add(tituloAnadirBibiliotecarios, "span4");
		panelAnadirEliminarBibliotecarios.add(tituloEliminarBibliotecarios, "wrap");
		panelAnadirEliminarBibliotecarios.add(nombreBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirNombreBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(apellidosBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirApellidosBibliotecario);
		panelAnadirEliminarBibliotecarios.add(idBibliotecario, "skip");
		panelAnadirEliminarBibliotecarios.add(escribirIdBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(telefonoBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirTelefonoBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(dniBibliotecario);
		panelAnadirEliminarBibliotecarios.add(escribirDniBibliotecario, "wrap");
		panelAnadirEliminarBibliotecarios.add(anadirBibliotecario);
		panelAnadirEliminarBibliotecarios.add(eliminarBibliotecario, "skip3, align left");*/

		//CREAR PANEL AÑADIR/ELIMINAR SOCIOS
		panelAnadirEliminarSocios = new JPanel();
		panelAnadirEliminarSocios.setLayout(new MigLayout());
		panelAnadirEliminarSociosTitulo = new JPanel();
		panelAnadirEliminarSociosTitulo.setLayout(new MigLayout());
		panelAnadirSocios = new JPanel();
		panelAnadirSocios.setLayout(new MigLayout());
		panelEliminarSocios = new JPanel();
		panelEliminarSocios.setLayout(new MigLayout());

		//AÑADIR PANEL AÑADIR/ELIMINAR SOCIOS A PESTAÑA
		pestanas.addTab("AÑADIR/ELIMINAR SOCIOS", panelAnadirEliminarSocios);

		//DATOS PANEL AÑADIR/ELIMINAR SOCIOS
		tituloAnadirEliminarSocios = new JLabel("BIENVENIDO/A " + bibliotecario.getNombre().toUpperCase());
		tituloAnadirSocios = new JLabel("AÑADIR SOCIOS");
		tituloEliminarSocios = new JLabel("ELIMINAR SOCIOS");
		nombreSocios = new JLabel("Nombre: ");
		apellidosSocios = new JLabel("Apellidos: ");
		telefonoSocios = new JLabel("Teléfono: ");
		dniSocios = new JLabel("DNI: ");
		idSocios = new JLabel("ID del socio: ");

		escribirNombreSocios = new JTextField(15);
		escribirApellidosSocios = new JTextField(40);
		escribirTelefonoSocios = new JTextField(9);
		escribirDniSocios = new JTextField(9);
		escribirIdSocios = new JTextField(4);

		anadirSocios = new JButton("Añadir socio");
		eliminarSocios = new JButton("Eliminar socio");
		anadirSocios.addActionListener(this);
		eliminarSocios.addActionListener(this);

		
		panelAnadirEliminarSocios.add(panelAnadirEliminarSociosTitulo, "pos 0.5al 0al");
		panelAnadirEliminarSocios.add(panelAnadirSocios, "pos 0.1al 0.4al");
		panelAnadirEliminarSocios.add(panelEliminarSocios, "pos 0.9al 0.4al");
		panelAnadirEliminarSociosTitulo.add(tituloAnadirEliminarSocios, "align center");
		panelAnadirSocios.add(tituloAnadirSocios, "span, align left, wrap");
		panelAnadirSocios.add(nombreSocios);
		panelAnadirSocios.add(escribirNombreSocios, "wrap");
		panelAnadirSocios.add(apellidosSocios);
		panelAnadirSocios.add(escribirApellidosSocios, "wrap");
		panelAnadirSocios.add(telefonoSocios);
		panelAnadirSocios.add(escribirTelefonoSocios, "wrap");
		panelAnadirSocios.add(dniSocios);
		panelAnadirSocios.add(escribirDniSocios, "wrap");
		panelAnadirSocios.add(anadirSocios);
		panelEliminarSocios.add(tituloEliminarSocios, "align center, wrap");
		panelEliminarSocios.add(idSocios, "split2");
		panelEliminarSocios.add(escribirIdSocios, "wrap");
		panelEliminarSocios.add(eliminarSocios);
		
		/*panelAnadirEliminarSocios.add(tituloAnadirEliminarSocios, "skip2, align center, wrap");
		panelAnadirEliminarSocios.add(tituloAnadirSocios, "span4");
		panelAnadirEliminarSocios.add(tituloEliminarSocios, "wrap");
		panelAnadirEliminarSocios.add(nombreSocios);
		panelAnadirEliminarSocios.add(escribirNombreSocios, "wrap");
		panelAnadirEliminarSocios.add(apellidosSocios);
		panelAnadirEliminarSocios.add(escribirApellidosSocios);
		panelAnadirEliminarSocios.add(idSocios, "skip");
		panelAnadirEliminarSocios.add(escribirIdSocios, "wrap");
		panelAnadirEliminarSocios.add(telefonoSocios);
		panelAnadirEliminarSocios.add(escribirTelefonoSocios, "wrap");
		panelAnadirEliminarSocios.add(dniSocios);
		panelAnadirEliminarSocios.add(escribirDniSocios, "wrap");
		panelAnadirEliminarSocios.add(anadirSocios);
		panelAnadirEliminarSocios.add(eliminarSocios, "skip3, align left");*/

		this.add(pestanas);
		setVisible(true);
		setTitle("Imprimir");
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        setSize((int)(width/2), height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Método encargado de hacer que los botones funcionen dependiendo del botón que hagamos click.
	 */
	public void actionPerformed(ActionEvent e) {

		/**
		 * Botón encargado de imprimir en un archivo de texto los bibliotecarios de la biblioteca.
		 */
		if(e.getSource().equals(imprimirBibliotecarios)) {
			try {
				MainVentana.biblioteca.imprimirBibliotecarios();
				JOptionPane.showMessageDialog(this, "Bibliotecarios impresos con éxito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * Botón encargado de imprimir en un archivo de texto los libros de la biblioteca.
		 */
		if(e.getSource().equals(imprimirLibros)) {
			try {
				MainVentana.biblioteca.imprimirLibros();
				JOptionPane.showMessageDialog(this, "Libros impresos con éxito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * Botón encargado de imprimir en un archivo de texto los préstamos de la biblioteca.
		 */
		if(e.getSource().equals(imprimirPrestamos)) {
			try {
				MainVentana.biblioteca.imprimirPrestamos();
				JOptionPane.showMessageDialog(this, "Préstamos impresos con éxito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		/**
		 * Botón encargado de imprimir en un archivo de texto los socios de la biblioteca.
		 */
		if(e.getSource().equals(imprimirSocios)) {
			try {
				MainVentana.biblioteca.imprimirSocios();
				JOptionPane.showMessageDialog(this, "Socios impresos con éxito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		/**
		 * Botón encargado de mostrar en una tabla los bibliotecarios de la biblioteca.
		 */
		if(e.getSource().equals(mostrarBibliotecarios)) {
			String listaBibliotecarios[] = {"Cod", "Nombre", "Apellidos", "Teléfono", "DNI"};
			modeloMostrar = new DefaultTableModel(null, listaBibliotecarios);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_bibliotecarios().size()!=0) {
				ArrayList<Bibliotecario> orden= new ArrayList<Bibliotecario>(MainVentana.biblioteca.getLista_bibliotecarios());
				Collections.sort(orden);
				for (Bibliotecario i : orden) {
					introducirBibliotecario(i);
				}

			}
		}

		/**
		 * Botón encargado de mostrar en una tabla los libros de la biblioteca.
		 */
		if(e.getSource().equals(mostrarLibros)) {
			String listaLibros[] = {"ID", "Título", "Autor", "Género", "Estado"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaLibros);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_libros().size()!=0) {
				ArrayList<Libros> orden= new ArrayList<Libros>(MainVentana.biblioteca.getLista_libros());
				Collections.sort(orden);
				for (Libros i : orden) {
					introducirLibro(i);
				}
			}
		}

		/**
		 * Botón encargado de mostrar en una tabla los préstamos de la biblioteca.
		 */
		if(e.getSource().equals(mostrarPrestamos)) {
			String listaPrestamos[] = {"Fecha_Inicio", "Fecha_Fin", "Cod_Socio", "ID_Libro"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaPrestamos);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_prestamos().size()!=0) {
				ArrayList<Prestamos> orden = new ArrayList<Prestamos>(MainVentana.biblioteca.getLista_prestamos());
				Collections.sort(orden);
				for (Prestamos i : orden) {
					introducirPrestamos(i);
				}
			}
		}

		/**
		 * Botón encargado de mostrar en una tabla los socios de la biblioteca.
		 */
		if(e.getSource().equals(mostrarSocios)) {
			String listaSocios[] = {"Cod", "Nombre", "Apellidos", "Teléfono", "DNI", "ID_Libro"};
			limpiarTabla(tablaMostrar, modeloMostrar);
			modeloMostrar = new DefaultTableModel(null, listaSocios);
			tablaMostrar.setModel(modeloMostrar);
			if(MainVentana.biblioteca.getLista_socios().size()!=0) {
				ArrayList<Socio> orden = new ArrayList<Socio>(MainVentana.biblioteca.getLista_socios());
				Collections.sort(orden);
				for (Socio i : orden) {
					introducirSocios(i);
				}
			}
		}

		/**
		 * Botón encargado de añadir un libro a la biblioteca.
		 */
		if(e.getSource().equals(anadirLibro)) {
			try {
				if((escribirTitulo.getText().equals("") || escribirTitulo.getText().equals(" ")) || (escribirAutor.getText().equals("") || escribirAutor.getText().equals(" ")) || (escribirGenero.getText().equals("") || escribirGenero.getText().equals(" "))) {
					JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
				}
				else {
					Libros aux = new Libros(escribirTitulo.getText(), escribirAutor.getText(), escribirGenero.getText());
					aux.insertarLibroBD(MainVentana.biblioteca);
					JOptionPane.showMessageDialog(this, "Libro añadidio con éxito");
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}

		/**
		 * Botón encargado de eliminar un libro de la biblioteca.
		 */
		if(e.getSource().equals(borrarLibro)) {
			if(borrarIdLibro.getText().equals("") ||borrarIdLibro.getText().equals(" "))
				JOptionPane.showMessageDialog(this, "Rellene el campo por favor");
			else {
				Libros aux = MainVentana.biblioteca.buscaLibro(Integer.parseInt(borrarIdLibro.getText()));
				if(aux.isPrestado())
					JOptionPane.showMessageDialog(this, "Error, el libro esta prestado, y por tanto no se puede eliminar");
				else {
					try {
						MainVentana.biblioteca.borradoTotalLibro(aux);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "Libro borrado con éxito");
				}
			}
		}

		/**
		 * Botón encargado de prestar un libro a un socio.
		 */
		if(e.getSource().equals(prestar)) {
			if((escribirIdSocioPrestar.getText().equals("") || escribirIdSocioPrestar.getText().equals(" ")) || escribirIdLibroPrestar.getText().equals("") || (escribirIdLibroPrestar.getText().equals(" "))) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				try {
					int aux = bibliotecario.PrestarLibro(Integer.parseInt(escribirIdSocioPrestar.getText()), Integer.parseInt(escribirIdLibroPrestar.getText()), MainVentana.biblioteca);
					opcionesPrestar(aux);
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		}

		/**
		 * Botón encargado de devolver un libro prestado a la biblioteca.
		 */
		if(e.getSource().equals(devolver)) {
			if((escribirIdSocioDevolver.getText().equals("") || escribirIdSocioDevolver.getText().equals(" ")) || (escribirIdLibroDevolver.getText().equals("") || escribirIdLibroDevolver.getText().equals(" "))) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				try {
					int aux = bibliotecario.DevolverLibro(Integer.parseInt(escribirIdSocioDevolver.getText()), Integer.parseInt(escribirIdLibroDevolver.getText()), MainVentana.biblioteca);
					opcionesDevolver(aux);
				} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		}

		/**
		 * Botón encargado de añadir un bibliotecario a la biblioteca.
		 */
		if(e.getSource().equals(anadirBibliotecario)) {
			if((escribirNombreBibliotecario.getText().equals("") || escribirNombreBibliotecario.getText().equals(" ")) || (escribirApellidosBibliotecario.getText().equals("") || escribirApellidosBibliotecario.getText().equals(" ")) || (escribirTelefonoBibliotecario.getText().equals("") || escribirTelefonoBibliotecario.getText().equals(" ")) || (escribirDniBibliotecario.getText().equals("") || escribirDniBibliotecario.getText().equals(" "))) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				String dni =comprobarDni(escribirDniBibliotecario.getText());
				String telefono = comprobarNum(escribirTelefonoBibliotecario.getText());
				if(dni!=null && telefono!=null) {
					try {
						Bibliotecario aux = new Bibliotecario(dni, escribirNombreBibliotecario.getText(), escribirApellidosBibliotecario.getText(), Integer.parseInt(telefono));
						aux.insertarBibliotecarioBD(MainVentana.biblioteca);
						JOptionPane.showMessageDialog(this, "Bibliotecario añadido con éxito");
					} catch (NumberFormatException | ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		/**
		 * Botón encargado de eliminar un bibliotecario de la biblioteca.
		 * Se tiene en cuenta que no se puede eliminar un bibliotecario que esté en uso.
		 */
		if(e.getSource().equals(eliminarBibliotecario)) {
			if(escribirIdBibliotecario.getText().equals("") ||escribirIdBibliotecario.getText().equals(" ")) {
				JOptionPane.showMessageDialog(this, "Rellene el campo por favor");
			}
			else {
				if(bibliotecario.getCod_Emple()==Integer.parseInt(escribirIdBibliotecario.getText())) {
					JOptionPane.showMessageDialog(this, "Error, no se puede eliminar el bibliotecario que está en uso");
				}
				else {
					Bibliotecario aux = MainVentana.biblioteca.buscarBibliotecario(Integer.parseInt(escribirIdBibliotecario.getText()));
					if(aux == null) {
						JOptionPane.showMessageDialog(this, "Bibliotecario no existe");
					}
					else {

						try {
							aux.eliminarBibliotecarioBD(MainVentana.biblioteca);
							JOptionPane.showMessageDialog(this, "Bibliotecario eliminado con éxito");
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		}

		/**
		 * Botón encargado de añadir un socio a la biblioteca.
		 */
		if(e.getSource().equals(anadirSocios)) {
			if((escribirNombreSocios.getText().equals("") || escribirNombreSocios.getText().equals(" ")) || (escribirApellidosSocios.getText().equals("") || escribirApellidosSocios.getText().equals(" ")) || (escribirTelefonoSocios.getText().equals("") || escribirTelefonoSocios.getText().equals(" ")) || (escribirDniSocios.getText().equals("")) || escribirDniSocios.getText().equals(" ")) {
				JOptionPane.showMessageDialog(this, "Rellene los campos por favor");
			}
			else {
				String dni = comprobarDni(escribirDniSocios.getText());
				String telefono = comprobarNum(escribirTelefonoSocios.getText());
				if(dni!=null && telefono!=null) {
					try {
						Socio aux = new Socio(dni, escribirNombreSocios.getText(), escribirApellidosSocios.getText(), Integer.parseInt(telefono));
						aux.insertarSocioBD(MainVentana.biblioteca);
						JOptionPane.showMessageDialog(this, "Socio introducido con éxito");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		/**
		 * Botón encargado de eliminar un socio de la biblioteca.
		 */
		if(e.getSource().equals(eliminarSocios)) {
			if(escribirIdSocios.getText().equals("") ||escribirIdSocios.getText().equals(" ")) {
				JOptionPane.showMessageDialog(this, "Rellene el campo por favor");
			}
			else {
				Socio aux = MainVentana.biblioteca.buscarSocio(Integer.parseInt(escribirIdSocios.getText()));
				if(aux == null) {
					JOptionPane.showMessageDialog(this, "Socio no existe");
				}
				else {
					try {
						aux.eliminarSocioTotal(MainVentana.biblioteca, bibliotecario);
						JOptionPane.showMessageDialog(this, "Socio eliminado con éxito");
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * Método encargado de comprobar si un dni cumple el patrón.
	 * 
	 * @param comdni el dni que se quiera comprobar
	 * @return el dni si cumple el patrón o null si no cumple el patrón
	 */
	public String comprobarDni(String comdni) {
		String regex = "\\d{8}[a-zA-Z]";
		if(Pattern.matches(regex, comdni)) {
			return comdni.toUpperCase();
		}
		JOptionPane.showMessageDialog(this, "DNI no válido, respete el formato");
		return null;
	}

	/**
	 * Método encargado de comprobar si un número cumple el patrón.
	 * 
	 * @param num el número que se quiera comprobar
	 * @return el número si cumple el patrón o null si no cumple el patrón.
	 */
	public String comprobarNum(String num) {
		String regex = "\\d{9}";
		if(Pattern.matches(regex, num)) {
			return num;
		}
		JOptionPane.showMessageDialog(this, "Número no válido, respete el formato");
		return null;
	}

	/**
	 * Método encargado de introducir un bibliotecario en la tabla.
	 * 
	 * @param b un objeto de tipo Bibliotecario
	 */
	public void introducirBibliotecario(Bibliotecario b) {
		Bibliotecario aux;
		if(b!=null) {
			Object lista[] = new Object[5];
			lista [0] = b.getCod_Emple();
			lista [1] = b.getNombre();
			lista [2] = b.getApellidos();
			lista [3] = b.getN_telefono();
			lista [4] = b.getDni();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base.");
	}

	/**
	 * Método encargado de introducir un libro en la tabla.
	 * 
	 * @param l un objeto de tipo Libros
	 */
	public void introducirLibro(Libros l) {
		Libros aux;
		if(l!=null) {
			Object lista[] = new Object[5];
			lista [0] = l.getId_libro();
			lista [1] = l.getTitulo();
			lista [2] = l.getAutor();
			lista [3] = l.getGenero();
			if(l.isPrestado())
				lista [4] = "prestado";
			else
				lista [4] = "disponible";
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado un libro con ese ID en la base de datos");
	}

	/**
	 * Método encargado de introducri un préstamo en la tabla.
	 * 
	 * @param p un objeto de tipo Prestamos
	 */
	public void introducirPrestamos(Prestamos p) {
		Prestamos aux;
		if(p!=null) {
			Object lista[] = new Object[5];
			lista [0] = p.getFecha_inicio();
			lista [1] = p.getFecha_fin();
			lista [2] = p.getSocio_asocidado();
			lista [3] = p.getLibro_asociado();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base");
	}

	/**
	 * Método encargado de introducir un socio en la tabla.
	 * 
	 * @param s un objeto de tipo Socio
	 */
	public void introducirSocios(Socio s) {
		Socio aux;
		if(s!=null) {
			Object lista[] = new Object[6];
			lista [0] = s.getCod_Socio();
			lista [1] = s.getNombre();
			lista [2] = s.getApellidos();
			lista [3] = s.getN_telefono();
			lista [4] = s.getDni();
			lista [5] = s.devuelveID();
			modeloMostrar.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base");
	}

	/**
	 * Método encargado de enviarnos un mensaje según el parámetro tome un valor u otro.
	 * 
	 * @param x integer que será usado para la expresión del switch
	 */
	public void opcionesPrestar(int x) {
		switch(x) {
		case 0: JOptionPane.showMessageDialog(this, "Préstamo realizado con éxito");
		break;
		case 1: JOptionPane.showMessageDialog(this, "El libro ya está prestado, pruebe más tarde");
		break;
		case 2: JOptionPane.showMessageDialog(this, "El libro no existe, introduzca un ID válido");
		break;
		case 3: JOptionPane.showMessageDialog(this, "Has alcanzado el límite de libros prestados, devuelve alguno");
		break;
		case 4: JOptionPane.showMessageDialog(this, "El usuario no se encuentra en la base de datos. introduzca un ID válido");
		break;
		}
	}

	/**
	 * Método encargado de enviarnos un mensaje según el parámetro tome un valor u otro.
	 * 
	 * @param x integer que será usado para la expresión del switch
	 */
	public void opcionesDevolver(int x) {
		switch(x) {
		case 0: JOptionPane.showMessageDialog(this, "Devolución realizada con éxito");
		break;
		case 1: JOptionPane.showMessageDialog(this, "Introduzca unos datos validos, puesto que los introducidos no son referentes a ningun prestamo");
		break;
		}
	}

	/**
	 * Método encargado de limpiar el contenido de la tabla.
	 * 
	 * @param tabla la tabla que se quiera limpiar
	 * @param modelo el modelo que se quiera limpiar
	 */
	public static void limpiarTabla(JTable tabla, DefaultTableModel modelo) {
		int filas = tabla.getRowCount()-1;
		for (int i = filas; i >= 0; i--)
			modelo.removeRow(i);
	}
}

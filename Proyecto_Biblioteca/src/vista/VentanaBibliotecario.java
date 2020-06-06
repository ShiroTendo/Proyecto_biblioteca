package vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

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
	private JLabel idSocio;
	private JLabel idLibro;
	private TextField escribirIdSocio;
	private TextField escribirIdLibro;
	private JButton prestar;
	private JButton devolver;
	
	//PANELES
	private JTabbedPane pestanas;
	private JPanel panelImprimir;
	private JPanel panelMostrar;
	private JPanel panelAnadirBorrar;
	private JPanel panelPrestarDevolver;
	private JScrollPane scrollImprimir;
	private JScrollPane scrollMostrar;
	private JScrollPane scrollAnadirBorrar;
	private JScrollPane scrollPrestarDevolver;
	
	private Bibliotecario bibliotecario;
	
	private JLabel algo;
	
	public VentanaBibliotecario(Bibliotecario b) {
		bibliotecario = new Bibliotecario(b);
		crearAlgo();
	}
	
	public void crearAlgo() {
		
		//CREAR PESTAÑA
		pestanas = new JTabbedPane();
		
		//CREAR PANEL IMPRIMIR
		panelImprimir = new JPanel();
		panelImprimir.setLayout(new MigLayout());
		
		//DATOS PANEL IMPRIMIR
		tituloImprimir = new JLabel("BIENVENIDO " + bibliotecario.getNombre());
		
		imprimirBibliotecarios = new JButton("Imprimir bibliotecarios");
		imprimirLibros = new JButton("Imprimir libros");
		imprimirPrestamos = new JButton("Imprimir préstamos");
		imprimirSocios = new JButton("Imprimir socios");
		
		imprimirBibliotecarios.addActionListener(this);
		imprimirLibros.addActionListener(this);
		imprimirPrestamos.addActionListener(this);
		imprimirSocios.addActionListener(this);
		
		panelImprimir.add(tituloImprimir, "align center, wrap");
		panelImprimir.add(imprimirBibliotecarios);
		panelImprimir.add(imprimirLibros, "skip, wrap");
		panelImprimir.add(imprimirPrestamos);
		panelImprimir.add(imprimirSocios, "skip");
		
		//AÑADIR PANEL IMPRIMIR A LA PESTAÑA
		pestanas.addTab("pestaña1", panelImprimir);
		
		//CREAR PANEL MOSTRAR
		panelMostrar = new JPanel();
		panelMostrar.setLayout(new MigLayout());
		
		//AÑADIR PANEL A PESTAÑA
		pestanas.addTab("pestaña2", panelMostrar);
		
		//DATOS PANEL MOSTRAR
		tituloMostrar = new JLabel("BIENVENIDO " + bibliotecario.getNombre());
		
		tablaMostrar = new JTable();
		scrollMostrar = new JScrollPane(tablaMostrar);
		
		mostrarBibliotecarios = new JButton("Mostrar bibliotecarios");
		mostrarLibros = new JButton("Mostrar libros");
		mostrarPrestamos = new JButton("Mostrar préstamos");
		mostrarSocios = new JButton("Mostrar socios");
		
		mostrarBibliotecarios.addActionListener(this);
		mostrarLibros.addActionListener(this);
		mostrarPrestamos.addActionListener(this);
		mostrarSocios.addActionListener(this);
		
		panelMostrar.add(tituloMostrar, "align center, wrap");
		panelMostrar.add(mostrarBibliotecarios);
		panelMostrar.add(mostrarLibros, "skip, wrap");
		panelMostrar.add(mostrarPrestamos);
		panelMostrar.add(mostrarSocios, "skip, wrap");
		panelMostrar.add(scrollMostrar, "span2, align center");
		
		//CREAR PANEL AÑADIR/BORRAR LIBRO
		panelAnadirBorrar = new JPanel();
		panelAnadirBorrar.setLayout(new MigLayout());
		
		//AÑADIR PANEL A PESTAÑA
		pestanas.addTab("pestaña3", panelAnadirBorrar);
		
		//DATOS PANEL MOSTRAR
		
		tituloAnadirBorrar = new JLabel("BIENVENIDO " + bibliotecario.getNombre());
		
		anadir = new JLabel("AÑADIR LIBROS");
		borrar = new JLabel("BORRAR LIBROS");
		tituloLibro = new JLabel("Título del libro: ");
		autorLibro = new JLabel("Autor del libro: ");
		generoLibro = new JLabel("Género del libro: ");
		idLibroBorrar = new JLabel("ID del libro: ");
		algo = new JLabel();
		
		escribirTitulo = new JTextField(30);
		escribirAutor = new JTextField(25);
		escribirGenero = new JTextField(15);
		borrarIdLibro = new JTextField(25);
		
		anadirLibro = new JButton("Añadir libro");
		borrarLibro = new JButton("Borrar libro");
		
		anadirLibro.addActionListener(this);
		borrarLibro.addActionListener(this);
		
		panelAnadirBorrar.add(tituloAnadirBorrar, "skip2, align center, wrap");
		panelAnadirBorrar.add(anadir, "span2, align center,");
		panelAnadirBorrar.add(borrar, "skip2, align center, wrap");
		panelAnadirBorrar.add(tituloLibro);
		panelAnadirBorrar.add(escribirTitulo, "wrap");
		panelAnadirBorrar.add(autorLibro);
		panelAnadirBorrar.add(escribirAutor);
		panelAnadirBorrar.add(idLibroBorrar, "skip");
		panelAnadirBorrar.add(borrarIdLibro, "wrap");
		panelAnadirBorrar.add(generoLibro);
		panelAnadirBorrar.add(escribirGenero, "wrap");
		panelAnadirBorrar.add(anadirLibro, "span2, align center");
		panelAnadirBorrar.add(borrarLibro, "skip2");
		
		//CREAR PANEL PRESTAR/DEVOLVER LIBRO
		panelPrestarDevolver = new JPanel();
		panelPrestarDevolver.setLayout(new MigLayout());
		
		//AÑADIR PANEL A PESTAÑA
		pestanas.addTab("pestaña4", panelPrestarDevolver);
		
		//DATOS PANEL PRESTAR DEVOLVER
		tituloPrestarDevolver = new JLabel("BIENVENIDO " + bibliotecario.getNombre());
		
		
		//XD
		this.add(pestanas);
		setVisible(true);
		setTitle("Imprimir");
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;
        this.setSize(width/3, height/2);
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(imprimirBibliotecarios)) {
			try {
				MainVentana.biblioteca.imprimirBibliotecarios();
				JOptionPane.showMessageDialog(this, "Bibliotecarios impresos con éxito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirLibros)) {
			try {
				MainVentana.biblioteca.imprimirLibros();
				JOptionPane.showMessageDialog(this, "Libros impresos con éxito");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirPrestamos)) {
			try {
				MainVentana.biblioteca.imprimirPrestamos();
				JOptionPane.showMessageDialog(this, "Préstamos impresos con éxito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource().equals(imprimirSocios)) {
			try {
				MainVentana.biblioteca.imprimirSocios();
				JOptionPane.showMessageDialog(this, "Socios impresos con éxito");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
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
		
		if(e.getSource().equals(anadirLibro)) {
			try {
				if(escribirTitulo.getText().equals("") && escribirAutor.getText().equals("") && escribirGenero.getText().equals("")) {
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
		if(e.getSource().equals(borrarLibro)) {
			if(borrarIdLibro.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Rellene el campo por favor");
			else {
				Libros aux = MainVentana.biblioteca.buscaLibro(Integer.parseInt(borrarIdLibro.getText()));
				try {
					MainVentana.biblioteca.borradoTotalLibro(aux);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Libro borrado con éxito");
			}
		}
		
	}
	
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
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base.");
	}
	
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
			JOptionPane.showMessageDialog(this, "No se ha encontrado nada en la base.");
	}
	
	public static void limpiarTabla(JTable tabla, DefaultTableModel modelo) {
        int filas = tabla.getRowCount()-1;
        for (int i = filas; i >= 0; i--)
            modelo.removeRow(i);
    }
	
}

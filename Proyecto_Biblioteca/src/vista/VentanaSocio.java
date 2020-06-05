package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modelo.Libros;
import modelo.Socio;
import net.miginfocom.swing.MigLayout;

/**
 * Clase dedicada a la interfaz de la ventana Socios.
 * 
 * @author Ivan, Luis, Sergio
 *
 */
public class VentanaSocio extends JFrame implements ActionListener, WindowListener{
	
	private JPanel panelSocio;
	private JLabel titulo;
	private JLabel libro;
	private JTextField escribirLibro;
	private JButton botonLibro;
	private JButton botonLibrosPrestados;
	private JButton botonLibrosBiblioteca;
	private DefaultTableModel modelo;
	private JScrollPane scrollTabla;
	private JTable tabla;
	
	private Socio socio;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param s un objeto de tipo Socio
	 */
	public VentanaSocio(Socio s) {
		socio = new Socio(s);
		crearAlgo();
	}
	
	/**
	 * Método encargado de crear la interfaz.
	 */
	public void crearAlgo() {
		
		panelSocio = new JPanel();
		panelSocio.setLayout(new MigLayout("align center"));
		
		titulo = new JLabel("BIENVENIDO " + socio.getNombre());
		libro = new JLabel("ID del libro: ");
		escribirLibro = new JTextField(10);
		
		botonLibro = new JButton("Introducir ID");
		botonLibrosPrestados = new JButton("Mostrar libros en pertenencia");
		botonLibrosBiblioteca = new JButton("Mostrar libros en biblioteca");
		
		tabla = new JTable();
		String lista[] = {"id", "titulo", "autor", "genero", "estado"};
		modelo = new DefaultTableModel(null, lista);
		tabla.setModel(modelo);
		scrollTabla = new JScrollPane(tabla);
		
		botonLibro.addActionListener(this);
		botonLibrosPrestados.addActionListener(this);
		botonLibrosBiblioteca.addActionListener(this);
		
		panelSocio.add(titulo, "align center, wrap");
		panelSocio.add(libro, "split3");
		panelSocio.add(escribirLibro, "align center");
		panelSocio.add(botonLibro, "wrap");
		panelSocio.add(botonLibrosPrestados);
		panelSocio.add(botonLibrosBiblioteca, "wrap");
		panelSocio.add(scrollTabla, "span2, align center");
		add(panelSocio);
		setVisible(true);
		setTitle("Socios");
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método encargado de hacer que los botones funcionen dependiendo del botón que hagamos click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(botonLibro)) {
			limpiarTabla(tabla, modelo);
			introducirLibro(Integer.parseInt(escribirLibro.getText()));
		}
		if(e.getSource().equals(botonLibrosPrestados)) {
			limpiarTabla(tabla, modelo);
			if(socio.getLibros_Tiene().size()!=0) {
				for (Libros i : socio.getLibros_Tiene()) {
					introducirLibro(i.getId_libro());
				}
			
			}
			else
				JOptionPane.showMessageDialog(this, "Este socio no tiene ningún libro prestado.");
		}
		if(e.getSource().equals(botonLibrosBiblioteca)) {
			limpiarTabla(tabla, modelo);
			if(MainVentana.biblioteca.getLista_libros().size()!=0) {
				ArrayList<Libros> orden= new ArrayList<Libros>(MainVentana.biblioteca.getLista_libros());
				Collections.sort(orden);
				for (Libros i : orden) {
					introducirLibro(i.getId_libro());
				}
			
			}
			else
				JOptionPane.showMessageDialog(this, "La biblioteca no tiene libros.");
		}
	}

	/**
	 * Método encargado de introducir un libro a la tabla.
	 * 
	 * @param id ID del libro
	 */
	public void introducirLibro(int id) {
		Libros aux = MainVentana.biblioteca.buscaLibro(id);
		if(aux!=null) {
			Object lista[] = new Object[5];
			lista [0] = aux.getId_libro();
			lista [1] = aux.getTitulo();
			lista [2] = aux.getAutor();
			lista [3] = aux.getGenero();
			if(aux.isPrestado())
				lista [4] = "prestado";
			else
				lista [4] = "disponible";
			modelo.addRow(lista);
		}
		else
			JOptionPane.showMessageDialog(this, "No se ha encontrado un libro con ese ID en la base de datos");
	}
	
	/**
	 * Método encargado de borrar la tabla.
	 * 
	 * @param tabla la tabla que le queramos pasar
	 * @param modelo el modelo que le queramos pasar
	 */
	public static void limpiarTabla(JTable tabla, DefaultTableModel modelo) {
        int filas = tabla.getRowCount()-1;
        for (int i = filas; i >= 0; i--)
            modelo.removeRow(i);
    }
}

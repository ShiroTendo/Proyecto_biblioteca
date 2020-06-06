package vista;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

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
import net.miginfocom.swing.MigLayout;

public class VentanaBibliotecario extends JFrame implements ActionListener, WindowListener{

	//PESTAÑA IMPRIMIR
	private JLabel tituloImprimir;
	private JButton imprimirBibliotecarios;
	private JButton imprimirLibros;
	private JButton imprimirPrestamos;
	private JButton imprimirSocios;
	private DefaultTableModel modeloImprimir;
	private JTable tablaImprimir;
	
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
	private TextField escribirTitulo;
	private TextField escribirAutor;
	private TextField escribirGenero;
	private JButton anadirLibro;
	private JButton borrarLibro;
	private DefaultTableModel modeloAnadirBorrar;
	private JTable tablaAnadirBorrar;
	
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
	private DefaultTableModel modeloPrestarDevolver;
	private JTable tablaPrestarDevolver;
	
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
	
	public VentanaBibliotecario(Bibliotecario b) {
		bibliotecario = new Bibliotecario(b);
		crearAlgo();
	}
	
	public void crearAlgo() {
		
		pestanas = new JTabbedPane();
		
		panelImprimir = new JPanel();
		panelImprimir.setLayout(new MigLayout());
		
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
		
		pestanas.addTab("pestaña1", panelImprimir);
		
		//
		panelMostrar = new JPanel();
		pestanas.addTab("pestaña2", panelMostrar);
		tablaMostrar = new JTable();
		
		tituloMostrar = new JLabel("BIENVENIDO " + bibliotecario.getNombre());
		
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
		panelMostrar.add(mostrarSocios, "skip");
		
		this.add(pestanas);
		setVisible(true);
		setTitle("Imprimir");
		setResizable(false);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			JOptionPane.showMessageDialog(this, "Funciona");
		}
		
	}
	
}

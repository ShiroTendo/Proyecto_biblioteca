package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Biblioteca;
import modelo.Bibliotecario;
import modelo.Socio;
import net.miginfocom.swing.MigLayout;

/**
 * Clase dedicada a la interfaz de la ventana principal.
 * 
 * @author Ivan, Luis y Sergio
 *
 */
public class VentanaInicio extends JFrame implements ActionListener, WindowListener {
	
	private JPanel panel;
	private JLabel idSocio;
	private JLabel idBibliotecario;
	private JLabel titulo;
	private JLabel socio;
	private JLabel bibliotecario;
	private JTextField escribirIdSocio;
	private JTextField escribirIdBibliotecario;
	private JButton botonSocio;
	private JButton botonBibliotecario;
	private JButton exit;
	
	private Biblioteca biblioteca;
	
	/**
	 * Constructor de la clase.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public VentanaInicio() throws ClassNotFoundException, SQLException {
		biblioteca = new Biblioteca();
		crearAlgo();
	}
	
	/**
	 * Método encargado de crear la interfaz.
	 */
	public void crearAlgo() {
		
		panel = new JPanel();
		panel.setLayout(new MigLayout("align center"));
		
		titulo = new JLabel("BIENVENIDO A LA BIBLIOTECA");
		socio = new JLabel("SOCIO");
		bibliotecario = new JLabel("BIBLIOTECARIO");
		idSocio = new JLabel("ID del socio: ");
		idBibliotecario = new JLabel("ID del bibliotecario: ");
		
		escribirIdSocio = new JTextField(10);
		escribirIdBibliotecario = new JTextField(10);
		botonSocio = new JButton("Acceso socio");
		botonBibliotecario = new JButton("Acceso bibliotecario");
		exit = new JButton("EXIT");
		
		botonSocio.addActionListener(this);
		botonBibliotecario.addActionListener(this);
		exit.addActionListener(this);
		
		panel.add(titulo, "skip, align center, wrap");
		panel.add(socio, "align center");
		panel.add(bibliotecario, "skip, align center, wrap");
		panel.add(idSocio, "split2");
		panel.add(escribirIdSocio);
		panel.add(idBibliotecario, "skip, split2");
		panel.add(escribirIdBibliotecario, "wrap");
		panel.add(botonSocio);
		panel.add(botonBibliotecario, "skip, align right, wrap");
		panel.add(exit, "skip, growx, pushx");
		add(panel);
		setVisible(true);
		setTitle("Biblioteca");
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

	/**
	 * Método encargado de hacer que los botones funcionen dependiendo del botoón que hagamos click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(botonSocio)) {
			if(escribirIdSocio.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduce un valor válido");
			}
			
			else {
				Socio aux = MainVentana.biblioteca.buscarSocio(Integer.parseInt(escribirIdSocio.getText()));
				if(aux!=null)
					new VentanaSocio(aux);
				else
					JOptionPane.showMessageDialog(this, "No se ha encontrado un socio con ese ID en la base de datos");
			}
				
		}
		
		if(e.getSource().equals(botonBibliotecario)) {
			if(escribirIdBibliotecario.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduce un valor válido");
			}
			else {
				Bibliotecario aux = MainVentana.biblioteca.buscarBibliotecario(Integer.parseInt(escribirIdBibliotecario.getText()));
				if(aux!=null)
					System.out.println("existe el bibliotecario");
				else
					JOptionPane.showMessageDialog(this, "No se ha encontrado un bibliotecario con ese ID en la base de datos");
			}
			
		}
		if(e.getSource().equals(exit)) {
			System.exit(0);
		}
		
	}
}

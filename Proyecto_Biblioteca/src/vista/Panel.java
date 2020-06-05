package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class Panel extends JPanel implements ActionListener{
	
	private JLabel idSocio;
	private JLabel idBibliotecario;
	private JTextField escribirIdSocio;
	private JTextField escribirIdBibliotecario;
	private JButton acceso;
	
	public Panel() {
		crearAlgo();
	}
	
	public void crearAlgo() {
		
		setLayout(new MigLayout("align center"));
		idSocio = new JLabel("id del socio: ");
		escribirIdSocio = new JTextField(10);
		acceso = new JButton("Acceder");
		acceso.addActionListener(this);
		
		add(idSocio);
		add(escribirIdSocio, "wrap");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

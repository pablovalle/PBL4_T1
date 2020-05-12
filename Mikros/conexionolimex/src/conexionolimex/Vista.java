package conexionolimex;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vista extends JFrame {

	JButton bEmpezar, bApagar, bSiguiente, bConectar;
	
	TwoWaySerialComm controlador;
	
	public Vista() {
		super("Controlador de conexión con placa Basys");		
		controlador = new TwoWaySerialComm();
		this.setSize(500, 400);
		this.setLocation(100, 80);
		this.setContentPane(crearPanelVentana());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);		
	}
	
	
	
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new GridLayout(2,1,10,10));
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		
		bEmpezar = new JButton();
		bApagar = new JButton();
		bSiguiente = new JButton();
		bConectar = new JButton();
		
		bConectar = crearBoton(bConectar, "conectar");
		bEmpezar = crearBoton(bEmpezar, "ok");
		bApagar = crearBoton(bApagar, "no");
		bSiguiente = crearBoton(bSiguiente, "siguiente");
		//activarDesactivar();
		
		
		
		panel.add(bConectar);
		panel.add(bEmpezar);
		panel.add(bSiguiente);
		panel.add(bApagar);
		
		return panel;
	}
	
	public JButton crearBoton(JButton boton, String comando) {
		boton.addActionListener(controlador);
		boton.setActionCommand(comando);
		boton.setText(comando);		
		return boton;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Vista programa = new Vista();
		
	}


	
}

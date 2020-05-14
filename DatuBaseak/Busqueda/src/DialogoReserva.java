import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class DialogoReserva extends JDialog implements ActionListener{
	JList<Reserva> lista;
	final String username;
	JButton btnSalir;
	public DialogoReserva(JFrame ventana, String titulo, boolean modo,String username) {
		super(ventana,titulo,modo);
		this.username=username;
		this.setLocationRelativeTo(ventana);
		this.setSize(500, 500);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
	}
	private Component crearPanelVentana() {
		JPanel panel=new JPanel(new BorderLayout(0,10));
		panel.add(crearListado(), BorderLayout.CENTER);
		panel.add(crearBoton(), BorderLayout.SOUTH);
		return panel;
	}
	private Component crearBoton() {
		JPanel panel = new JPanel();
		btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Salir");
		btnSalir.setActionCommand("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setBackground(new Color(255,255,255));
		panel.add(btnSalir);

		panel.setBackground(new Color(0,0,0));
		return panel;
	}
	private Component crearListado() {
		JScrollPane panel= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lista = new JList<Reserva>();
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setListData(DAOReservas.getReservas(username));
		panel.setViewportView(lista);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salir")) {
			this.dispose();
		}
	}
	
}

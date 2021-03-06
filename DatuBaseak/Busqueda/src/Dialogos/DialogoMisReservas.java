package Dialogos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.DAOReservas;
import Objetos.Reserva;
import Renderers.RectangleBorder;
import Renderers.RendererMisReservas;

@SuppressWarnings("serial")
public class DialogoMisReservas extends JDialog implements ActionListener, ListSelectionListener{
	JList<Reserva> lista;
	String username;
	JButton btnSalir;
	public DialogoMisReservas(JFrame ventana, String titulo, boolean modo,String username) {
		super(ventana,titulo,modo);
		this.username=username;
		this.setSize(1200, 800);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		
		this.getContentPane().add(crearPanelVentana(), BorderLayout.CENTER);
		this.setResizable(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		if(lista.getModel().getSize()==0) {
			JOptionPane.showMessageDialog(this, "�No tienes reservas!", "�ERROR!",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}else {
			this.setVisible(true);
		}
		
		
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
		panel.setBorder(new RectangleBorder(Color.WHITE));
		panel.add(btnSalir);

		panel.setBackground(new Color(0,0,0));
		return panel;
	}
	private Component crearListado() {
		JScrollPane panel= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		lista = new JList<Reserva>();
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setListData(DAOReservas.getReservas(username));
		lista.setCellRenderer(new RendererMisReservas());
		lista.addListSelectionListener(this);
		panel.setViewportView(lista);
		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salir")) {
			this.dispose();
		}
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int seleccionado = lista.getSelectedIndex();
		if(seleccionado!=-1) {
			if(lista.getSelectedValue().getCheckIn().compareTo(LocalDate.now().toString())<=0) {
				DialogoGestorClaves dialogo= new DialogoGestorClaves(this,"RECIBIR CLAVES",true,lista.getSelectedValue());
				dialogo.setVisible(true);
				lista.clearSelection();
			}
			else {
				DialogoGestorReservas dialogoReserva= new DialogoGestorReservas(this,"CANCELAR RESERVA",true,lista.getSelectedValue());
				dialogoReserva.setVisible(true);
				lista.setListData(DAOReservas.getReservas(username));
				lista.clearSelection();
			}
		}
		
		
			
		
		
		
	}
	
}

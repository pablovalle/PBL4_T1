package Dialogos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.DAOHabitacion;
import DAO.DAOHotel;
import Objetos.Trabajador;
import Renderer.MiPanelMapa;
import Renderer.RectangleBorder;
import Vistas.VistaPrincipal;


public class DialogoMapa extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	JComboBox comboBox;
	MiPanelMapa panelMapa;
	Trabajador trabajador;
	JLabel lbPiso ;
	public DialogoMapa(VistaPrincipal vistaPrincipal, Trabajador trabajador) {
		super(vistaPrincipal,"Visualizador de habitaciones");
		this.trabajador=trabajador;
		//setBounds(100, 100, 667, 535);
		this.setSize(520, 520);
		this.setBackground(Color.WHITE);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		lbPiso= new JLabel("Piso: 1");
		lbPiso.setHorizontalAlignment(SwingConstants.CENTER);
		lbPiso.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPanel.add(lbPiso, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.WEST);
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(11, 1, 0, 0));

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(DAOHotel.getPisos(trabajador.getIdHotel())));
		comboBox.addActionListener(this);
		panel.add(comboBox);
		comboBox.setActionCommand("ComboBox");
			
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(this);
		btnSalir.setActionCommand("Salir");
		JPanel panelBoton= new JPanel();
		panelBoton.setBackground(Color.BLACK);
		panelBoton.setBorder(new RectangleBorder(Color.WHITE));
		panelBoton.add(btnSalir);
		contentPanel.add(panelBoton, BorderLayout.SOUTH);
			
		panelMapa=new MiPanelMapa("img/Hotel.jpg", DAOHabitacion.getHabitacionesPiso(comboBox.getSelectedIndex()+1, trabajador.getIdHotel()));
		panelMapa.setBackground(Color.WHITE);
		contentPanel.add(panelMapa, BorderLayout.CENTER);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Salir":this.dispose();			
			break;
		case "ComboBox":
			String[] estadoPisos = DAOHabitacion.getHabitacionesPiso(comboBox.getSelectedIndex()+1,trabajador.getIdHotel());
			panelMapa.setEstados(estadoPisos);
			panelMapa.repaint();
			lbPiso.setText("Piso: "+(comboBox.getSelectedIndex()+1));
			//actualizar mapa
			break;
		}
	
		
		
		
	}

}

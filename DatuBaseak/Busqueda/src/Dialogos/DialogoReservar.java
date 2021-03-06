package Dialogos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DAOReservas;
import Objetos.Habitacion;
import Renderers.RectangleBorder;

public class DialogoReservar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String username;
	public DialogoReservar(JFrame ventana, String titulo, boolean modo,Habitacion habitacion, java.util.Date in, java.util.Date out, String username) {
		super(ventana,titulo,modo);
		this.username=username;
		this.setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(4, 1, 0, 0));
		DateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		String fechaIn=formatter.format(in);
		String fechaOut=formatter.format(out);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPanel.add(panel);
			
		JLabel lblHotel = new JLabel("HOTEL");
		lblHotel.setText(habitacion.getHotel());
		lblHotel.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.add(lblHotel);
				
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		contentPanel.add(panel1);
		panel1.setLayout(new GridLayout(0, 3, 0, 0));
		JLabel lbCiudad = new JLabel("   Ciudad: "+habitacion.getCiudad());
		lbCiudad.setFont(new Font("Tahoma", Font.PLAIN,16));
		panel1.add(lbCiudad);
		JLabel lbTipo = new JLabel("Tipo");
		lbTipo.setText("   "+habitacion.getCategoria().toUpperCase());
		lbTipo.setFont(new Font("Tahoma", Font.BOLD,18));
		lbTipo.setForeground(habitacion.getCategoria().toUpperCase().equals("ESTANDAR")? Color.BLACK: new Color(250,223,54));
		panel1.add(lbTipo);
		JLabel lbpersonas = new JLabel("   Personas: "+ habitacion.getAforo());
		lbpersonas.setFont(new Font("Tahoma", Font.PLAIN,16));
		panel1.add(lbpersonas);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		contentPanel.add(panel2);
		panel2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lbDesdetxt = new JLabel("   Desde: ");
		lbDesdetxt.setFont(new Font("Tahoma", Font.PLAIN,16));
		JLabel lbDesde = new JLabel();
		lbDesde.setForeground(new Color(21,130,9));
		lbDesde.setText(String.valueOf(fechaIn));
		panel2.add(lbDesdetxt);
		panel2.add(lbDesde);
		JLabel lbHasta = new JLabel();
		JLabel lbHastatxt = new JLabel("   Hasta:");
		lbHastatxt.setFont(new Font("Tahoma", Font.PLAIN,16));
		lbHasta.setText(String.valueOf(fechaOut));
		lbHasta.setForeground(Color.RED);
		panel2.add(lbHastatxt);
		panel2.add(lbHasta);
			
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		contentPanel.add(panel3);
		panel3.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lbNumhabitacion = new JLabel("   Habitacion: "+String.valueOf(habitacion.getNumhabitacion()));
		lbNumhabitacion.setFont(new Font("Tahoma", Font.BOLD,16));
		panel3.add(lbNumhabitacion);
		JLabel lbPrecio = new JLabel(String.valueOf(habitacion.getPrecio())+" �/noche");
		lbPrecio.setFont(new Font("Tahoma", Font.BOLD,16));
		panel3.add(lbPrecio);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.WHITE);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPane.setBackground(Color.BLACK);
		buttonPane.setBorder(new RectangleBorder(Color.WHITE));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				boolean result=DAOReservas.crearReserva(habitacion, fechaIn, fechaOut, username);
				if(!result) {
					JOptionPane.showMessageDialog(null, "�NO SE HA PODIDO REALIZAR LA RESERVA!");
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
			
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
			
		
	}

}

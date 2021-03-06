package Renderers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Objetos.Habitacion;

public class RendererBusqueda implements ListCellRenderer<Habitacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Habitacion habitacion;

	@Override
	public Component getListCellRendererComponent(JList<? extends Habitacion> list, Habitacion ha, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.habitacion=ha;
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setSize(150,75);
		panel.add(crearFotoHabitacion(), BorderLayout.WEST);
		panel.add(crearDatos(ha,index),BorderLayout.CENTER);
		
		if(isSelected) 	panel.setBackground(Color.BLACK);
		else panel.setBackground(Color.WHITE);
		
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
	        		BorderFactory.createLineBorder(Color.black)));
	    	       
	    panel.setOpaque(true);
	       

		return panel;
	}

	private Component crearDatos(Habitacion ha, int index) {
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		panel_1.setBackground(new Color(255,255,255));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(255,255,255));
		panel_2.setLayout(new BorderLayout(0, 0));
		
		
		JLabel lbNombre = new JLabel("New label");
		lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbNombre.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbNombre.setBackground(new Color(255, 255, 255));
		lbNombre.setText(ha.getHotel());
		panel_2.add(lbNombre, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setBackground(new Color(255,255,255));
		panel_3.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setBackground(new Color(255,255,255));
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lbCIudad = new JLabel("New label");
		lbCIudad.setBackground(new Color(255, 255, 255));
		lbCIudad.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbCIudad.setForeground(new Color(0, 0, 0));
		lbCIudad.setHorizontalAlignment(SwingConstants.CENTER);
		lbCIudad.setText(ha.getCiudad());
		panel_5.add(lbCIudad, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setBackground(new Color(255,255,255));
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTipo = new JLabel("New label");
		lbTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTipo.setBackground(new Color(255, 255, 255));
		lbTipo.setText(ha.getCategoria());
		if(ha.getCategoria().equals("estandar")) {
			lbTipo.setForeground(new Color(0,0,0));
		}
		else {
			lbTipo.setForeground(new Color(212,175,55));
		}
		panel_6.add(lbTipo);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setBackground(new Color(255,255,255));
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lbPrecio = new JLabel("New label");
		lbPrecio.setBackground(new Color(255, 255, 255));
		lbPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lbPrecio.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lbPrecio.setText(String.valueOf(ha.getPrecio())+"�/noche");
		panel_7.add(lbPrecio);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setBackground(new Color(255,255,255));
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		panel_8.setBackground(new Color(255,255,255));
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lbPersonas = new JLabel("New label");
		lbPersonas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbPersonas.setHorizontalAlignment(SwingConstants.CENTER);
		lbPersonas.setForeground(new Color(0, 0, 0));
		lbPersonas.setText(String.valueOf("Personas: "+ha.getAforo()));
		panel_8.add(lbPersonas, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255,255,255));
		panel_4.add(panel_9);
		
		JPanel pBoton = new JPanel();
		pBoton.setBorder(new EmptyBorder(5, 10, 5, 10));
		panel_4.add(pBoton);
		pBoton.setBackground(new Color(255,255,255));
		pBoton.setLayout(new BorderLayout(0, 0));
		
		MiBotonReservar btnNewButton = new MiBotonReservar(index);
		btnNewButton.setText("RESERVAR!");
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setForeground(new Color(0,0,0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 38));
		btnNewButton.setActionCommand(String.valueOf(index));
		//???????????btnNewButton.addActionListener(Ventana.this);
		pBoton.add(btnNewButton);
		return panel_1;
	}

	private Component crearFotoHabitacion() {
		JPanel panel= new JPanel();
		
		String url=elegir_foto();
		ImageIcon icon = new ImageIcon(url);
		JLabel label = new JLabel();
		label.setSize(50, 50);
		label.setIcon(icon);
		panel.add(label);
		return panel;
	}

	private String elegir_foto() {
		String ret;
		if(habitacion.getHotel().equals("Burj Khalifa")) {
			if(habitacion.getCategoria().equals("luxury")) {
				ret="img/luxury_burj.jpeg";
			}
			else {
				ret="img/estandar_burj.jpeg";
			}
		}
		else if(habitacion.getHotel().equals("President Wilson")) {
			if(habitacion.getCategoria().equals("luxury")) {
				ret="img/luxury_president.PNG";
			}
			else {
				ret="img/estandar_president.jpg";
			}
		}
		else {
			ret="img/rinconPepe.jpg";
		}
		return ret;
	}


}

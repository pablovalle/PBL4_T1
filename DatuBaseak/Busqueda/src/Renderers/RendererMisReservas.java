package Renderers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import Objetos.Reserva;

public class RendererMisReservas implements ListCellRenderer<Reserva> {
	Reserva reserva;
	@Override
	public Component getListCellRendererComponent(JList<? extends Reserva> list, Reserva re, int index, 
			boolean isSelected,	boolean cellHasFocus) {
		this.reserva=re;
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setSize(150,75);
		panel.add(crearFotoHabitacion(), BorderLayout.WEST);
		panel.add(crearDatos(re,index),BorderLayout.CENTER);
		panel.setBackground(isSelected? Color.BLUE: Color.WHITE);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
	        		BorderFactory.createLineBorder(Color.black)));
	    	       
	    panel.setOpaque(true);
	       

		return panel;
		
	}

	private Component crearDatos(Reserva re, int index) {
		JPanel panel = new JPanel(new GridLayout(2,1,0,0));
		panel.setBackground(new Color(255,255,255));
		
		JPanel panelNombre = new JPanel(new BorderLayout());
		panelNombre.setBackground(new Color(255,255,255));
		//Parte arriba
		JLabel lbNombre = new JLabel("Nombre Hotel");
		lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lbNombre.setFont(new Font("Tahoma", Font.BOLD, 40));
		lbNombre.setBackground(new Color(255, 255, 255));
		lbNombre.setText(re.getnombreHotel());
		
		panelNombre.add(lbNombre, BorderLayout.CENTER);
		panel.add(panelNombre);
		
		//Parte abajo
		JPanel panelAbajo = new JPanel(new GridLayout(1,4,0,0));
		panelAbajo.setBackground(new Color(255,255,255));
		JLabel lbIn = new JLabel("CheckIn");
		lbIn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbIn.setForeground(new Color(0, 255, 0));
		lbIn.setHorizontalAlignment(SwingConstants.CENTER);
		lbIn.setBackground(new Color(255,255,255));
		lbIn.setText(re.getCheckIn());
		
		panelAbajo.add(lbIn);
		
		JLabel lbOut = new JLabel("CheckOut");
		lbOut.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbOut.setForeground(new Color(255, 0, 0));
		lbOut.setBackground(new Color(255,255,255));
		lbOut.setHorizontalAlignment(SwingConstants.CENTER);
		lbOut.setText(re.getCheckOut());
		
		panelAbajo.add(lbOut);
		
		JLabel lbNumHabitacion = new JLabel("NumHabitacion");
		lbNumHabitacion.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbNumHabitacion.setForeground(new Color(0, 0, 0));
		lbNumHabitacion.setBackground(new Color(255,255,255));
		lbNumHabitacion.setHorizontalAlignment(SwingConstants.CENTER);
		lbNumHabitacion.setText(String.valueOf(re.getNumhabitacion()));
		
		panelAbajo.add(lbNumHabitacion);
		
		JButton btClave = new JButton();
		btClave.setText("CLAVES");
		btClave.setBackground(new Color(50, 205, 50));
		btClave.setForeground(new Color(0,0,0));
		btClave.setFont(new Font("Tahoma", Font.PLAIN, 38));
		
		JButton btnCancelar = new JButton();
		btnCancelar.setText("CANCELAR");
		btnCancelar.setBackground(new Color(205, 50, 50));
		btnCancelar.setForeground(new Color(0,0,0));
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 38));
		
		LocalDate today = LocalDate.now(); 

		
		if(re.getCheckIn().compareTo(String.valueOf(today))> 0 ) {
			panelAbajo.add(btnCancelar);
		}
		else panelAbajo.add(btClave);
		
		
		
		panel.add(panelAbajo);
		
		
		
		return panel;
	}

	private Component crearFotoHabitacion() {
		JPanel panel= new JPanel();
		String url=elegirFoto();
		ImageIcon icon = new ImageIcon(url);
		JLabel label = new JLabel();
		label.setSize(50, 50);
		label.setIcon(icon);
		panel.add(label);
		return panel;
	}

	private String elegirFoto() {
		
		String ret="";

		switch(reserva.getnombreHotel()) {
		case "President Wilson": ret="img/president.png"; break;
		case "Burj Khalifa": ret="img/khalifa.png";break;
		case "Rinc�n de Pepe": ret="img/pepe.png"; break;
		}
		return ret;
	}

}

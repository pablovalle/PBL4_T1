package Renderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import Objetos.Tarea;

public class Renderer implements ListCellRenderer<Tarea> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Tarea> list, Tarea tarea, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setSize(150,75);
		panel.add(crearDatos(tarea,index),BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(2,5,2,5),
        		BorderFactory.createLineBorder(Color.black)));
    	panel.setBackground(isSelected? Color.BLACK: Color.WHITE);       
		return panel;
	}

	private Component crearDatos(Tarea tarea, int index) {
		JPanel panel = new JPanel(new GridLayout(2,1,0,0));
		panel.setBackground(Color.WHITE);
		
		JPanel panelDescripcion = new JPanel();
		JLabel descripcion = new JLabel("Descripcion");
		descripcion.setText(tarea.getDescripcion()+" ");
		descripcion.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panelDescripcion.add(descripcion);
		panelDescripcion.setBackground(Color.WHITE);
		
		
		JPanel panelDatos = new JPanel(new GridLayout(1,2,0,0));
		JLabel numHabitacoin = new JLabel("Habitacion:");
		numHabitacoin.setText(" Habitación: "+String.valueOf(tarea.getNumHabitacion()));
		numHabitacoin.setFont(new Font("Tahoma", Font.BOLD, 20));
		panelDatos.add(numHabitacoin);
		JLabel fecha = new JLabel("Fecha");
		fecha.setHorizontalAlignment(SwingConstants.RIGHT);
		fecha.setForeground(Color.RED);
		fecha.setText(tarea.getFecha()+" ");
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelDatos.add(fecha);
		panelDatos.setBackground(Color.WHITE);
		panel.add(panelDescripcion);
		panel.add(panelDatos);
		
		
		return panel;
	}

}

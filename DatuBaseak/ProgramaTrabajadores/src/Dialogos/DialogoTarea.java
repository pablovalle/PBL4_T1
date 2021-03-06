package Dialogos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DAO.DAOHabitacion;
import DAO.DAOTarea;
import Objetos.Tarea;
import Objetos.Trabajador;
import Renderer.RectangleBorder;
import Vistas.VistaPrincipal;

public class DialogoTarea extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Tarea tarea;
	Trabajador trabajador;
	public DialogoTarea(VistaPrincipal vistaPrincipal, String string, boolean b, Tarea selectedValue, Trabajador trabajador) {
		super(vistaPrincipal,string,b);
		this.trabajador=trabajador;
		this.tarea=selectedValue;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		this.setSize(500,200);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setText("�Est� seguro que ha finalizado esta tarea?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel, BorderLayout.NORTH);
		panel.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		panel_2.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setText("HABITACION:"+tarea.getNumHabitacion());
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setText("Descripcion:"+tarea.getDescripcion());
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBorder(new RectangleBorder(Color.WHITE));
		JButton btnNewButton = new JButton("CANCELAR");
		btnNewButton.setActionCommand("Cancelar");
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("ACEPTAR");
		btnNewButton_1.setActionCommand("Aceptar");
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Aceptar")) {
			boolean resultado=DAOTarea.marcarToggleTarea(tarea);
			
			if(!resultado) {
				JOptionPane.showMessageDialog(null, "�NO SE HA PODIDO ACTUALIZAR LA TAREA!");
			}
			else {
				JOptionPane.showMessageDialog(null, "�TAREA ACTUALIZADA!");
				DAOHabitacion.cambiarEstadoHabitacion(tarea.getNumHabitacion(), trabajador.getIdHotel());
				this.dispose();
			}
			
		}
		else if(e.getActionCommand().equals("Cancelar")) {
			this.dispose();
		}
		
	}

}

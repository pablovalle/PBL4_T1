import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;

public class DialogoTarea extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Tarea tarea;
	public DialogoTarea(VistaPrincipal vistaPrincipal, String string, boolean b, Tarea selectedValue) {
		super(vistaPrincipal,string,b);
		this.tarea=selectedValue;
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		this.setSize(300,300);
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setText("�Est� seguro que ha finalizado esta tarea?");
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setText("HABITACION:"+tarea.getNumHabitacion());
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setText("Descripcion:"+tarea.getDescripcion());
		panel_2.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
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
				this.dispose();
			}
			
		}
		else if(e.getActionCommand().equals("Cancelar")) {
			this.dispose();
		}
		
	}

}
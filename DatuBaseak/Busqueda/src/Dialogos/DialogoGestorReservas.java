package Dialogos;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import DAO.DAOReservas;
import Objetos.Reserva;
import Renderers.RectangleBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class DialogoGestorReservas extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Reserva reserva;

	public DialogoGestorReservas(DialogoMisReservas dialogoMisReservas, String string, boolean b,Reserva selectedValue) {
		super(dialogoMisReservas,string,b);
		this.reserva=selectedValue;
		getContentPane().setLayout(new BorderLayout(0, 0));
		this.setSize(600, 250);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		JPanel panel = new JPanel(new BorderLayout(0,0));
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setText("�Seguro que desea cancelar esta reserva?");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel,BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setBackground(Color.BLACK);
		panel_1.setBorder(new RectangleBorder(Color.WHITE));
		JButton btnNewButton = new JButton("SI");
		btnNewButton.setActionCommand("SI");
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("NO");
		btnNewButton_1.setActionCommand("NO");
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("SI")) {
			boolean ret= DAOReservas.cancelarReserva(reserva);
			if(!ret) {
				JOptionPane.showMessageDialog(null, "�NO SE HA PODIDO CANCELAR LA RESERVA!");
			}
			this.dispose();
		}
		else if(e.getActionCommand().equals("NO")) {
			this.dispose();
		}
		
	}

	
}

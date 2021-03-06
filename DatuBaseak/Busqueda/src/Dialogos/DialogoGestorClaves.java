package Dialogos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Objetos.Reserva;
import Renderers.RectangleBorder;

public class DialogoGestorClaves extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Reserva reserva;
	public DialogoGestorClaves(DialogoMisReservas dialogoMisReservas, String string, boolean b, Reserva selectedValue) {
		super(dialogoMisReservas,string,b);
		this.reserva=selectedValue;
		this.setSize(300, 300);
		this.setBackground(Color.WHITE);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma",Font.BOLD, 20));
		lblNewLabel.setText("N�mero Habitaci�n: "+String.valueOf(reserva.getNumhabitacion()));
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma",Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.MAGENTA);
		Formatter fmt1= new Formatter();
		fmt1.format("%04d", reserva.getClaveHotel());
		lblNewLabel_1.setText("Clave Hotel: "+String.valueOf(fmt1));
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma",Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.BLUE);
		Formatter fmt2= new Formatter();
		fmt2.format("%04d", reserva.getClavehabita());
		lblNewLabel_2.setText("Clave Habitaci�n: "+String.valueOf(fmt2));
		panel_1.add(lblNewLabel_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBorder(new RectangleBorder(Color.WHITE));
		panel.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(this);
		panel_2.add(btnNewButton);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.dispose();
		
	}

}

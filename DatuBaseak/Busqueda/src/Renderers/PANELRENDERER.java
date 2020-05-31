package Renderers;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class PANELRENDERER extends JPanel {

	/**
	 * Create the panel.
	 */
	public PANELRENDERER() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panel_2.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lbCIudad = new JLabel("New label");
		lbCIudad.setBackground(new Color(255, 255, 255));
		lbCIudad.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lbCIudad.setForeground(new Color(0, 0, 0));
		lbCIudad.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lbCIudad, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lbTipo = new JLabel("New label");
		lbTipo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lbTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTipo.setBackground(new Color(255, 255, 255));
		panel_6.add(lbTipo);
		
		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lbPrecio = new JLabel("New label");
		lbPrecio.setBackground(new Color(255, 255, 255));
		lbPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		lbPrecio.setFont(new Font("Tahoma", Font.ITALIC, 24));
		panel_7.add(lbPrecio);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel lbPersonaspanel_8 = new JPanel();
		panel_4.add(lbPersonaspanel_8);
		lbPersonaspanel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel lbPersonas = new JLabel("New label");
		lbPersonas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbPersonas.setHorizontalAlignment(SwingConstants.CENTER);
		lbPersonas.setForeground(new Color(0, 0, 0));
		lbPersonaspanel_8.add(lbPersonas, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		
		JPanel pBoton = new JPanel();
		pBoton.setBorder(new EmptyBorder(20, 50, 20, 50));
		panel_4.add(pBoton);
		pBoton.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("RESERVAR!");
		btnNewButton.setBackground(new Color(50, 205, 50));
		btnNewButton.setForeground(new Color(0,0,0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 38));
		pBoton.add(btnNewButton);

	}

}

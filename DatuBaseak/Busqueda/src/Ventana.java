import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField tfCiudad;
	

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 952, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel Opciones = new JPanel();
		contentPane.add(Opciones, BorderLayout.WEST);
		Opciones.setLayout(new GridLayout(4, 1, 0, 0));
		
		JPanel panel = new JPanel();
		Opciones.add(panel);
		
		JButton btnReservas = new JButton("Mis Reservas");
		panel.add(btnReservas);
		
		JPanel panel_1 = new JPanel();
		Opciones.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("Ciudad:");
		panel_1.add(lblNewLabel);
		
		tfCiudad = new JTextField();
		panel_1.add(tfCiudad);
		tfCiudad.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		Opciones.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Personas");
		panel_2.add(lblNewLabel_1);
		
		JComboBox cbPersonas = new JComboBox();
		cbPersonas.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4}));
		panel_2.add(cbPersonas);
		
		JPanel panel_3 = new JPanel();
		Opciones.add(panel_3);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Conexion conn = new Conexion();
				conn.Conectar();
				conn.filtrar(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem());
				conn.desconectar();
				
			}
		});
		panel_3.add(btnFiltrar);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

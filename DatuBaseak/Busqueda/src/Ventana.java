import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JCalendar;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Ventana extends JFrame {

	private JPanel contentPane;
	private JTextField tfCiudad;
	
	Conexion conn = new Conexion();
	DAOHabitacion habitacionDao;
	

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel Opciones = new JPanel();
		panel.add(Opciones, BorderLayout.NORTH);
		Opciones.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		Opciones.add(panel_1);
		
		JButton btnReservas = new JButton("Mis Reservas");
		panel_1.add(btnReservas);
		
		JPanel panel_4 = new JPanel();
		Opciones.add(panel_4);
		
		JPanel panel_1_1 = new JPanel();
		Opciones.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Ciudad:");
		panel_1_1.add(lblNewLabel);
		
		tfCiudad = new JTextField();
		tfCiudad.setColumns(10);
		panel_1_1.add(tfCiudad);
		
		JPanel panel_5 = new JPanel();
		Opciones.add(panel_5);
		
		JPanel panel_2 = new JPanel();
		Opciones.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Personas");
		panel_2.add(lblNewLabel_1);
		
		JComboBox cbPersonas = new JComboBox();
		cbPersonas.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4}));
		panel_2.add(cbPersonas);
		
		JPanel panel_6 = new JPanel();
		Opciones.add(panel_6);
		
		JPanel panel_3 = new JPanel();
		Opciones.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCalendar calendar = new JCalendar();
		panel_3.add(calendar);
		
		JPanel panel_7 = new JPanel();
		Opciones.add(panel_7);
		
		JCalendar calendar_1 = new JCalendar();
		panel_7.add(calendar_1);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn.Conectar();
				//conn.filtrar(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem());
				habitacionDao.filtrarHabitaciones(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem());
				
				
                conn.desconectar();
			}
		});
		panel.add(btnFiltrar, BorderLayout.SOUTH);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setToolTipText("");
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		
	
		
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

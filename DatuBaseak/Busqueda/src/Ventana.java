import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import java.awt.Font;
import java.awt.Color;


public class Ventana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String COMMIT_ACTION = "commit";
	private static final String USUARIO="pablo_mutel";
	private JPanel contentPane;
	private JTextField tfCiudad;
	JList<Habitacion> list;
	Conexion conn = new Conexion();
	DAOHabitacion habitacionDao;
	List<String>nombreCiudades;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnReservas = new JButton("Mis Reservas");
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				DialogoReserva dialogoReserva = new DialogoReserva(Ventana.this,"MIS RESERVAS",true,USUARIO);
				//dialogoReserva.setVisible(true);
			}
		});
		btnReservas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_1.add(btnReservas);
		
		JPanel panel_4 = new JPanel();
		Opciones.add(panel_4);
		
		JPanel panel_1_1 = new JPanel();
		Opciones.add(panel_1_1);
		
		JLabel lblNewLabel = new JLabel("Ciudad:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_1_1.add(lblNewLabel);
		
		
		JPanel panel_5 = new JPanel();
		Opciones.add(panel_5);
		
		tfCiudad = new JTextField();
		tfCiudad.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_5.add(tfCiudad);
		aniadirAutocompletar();
		tfCiudad.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		Opciones.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Personas:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_6 = new JPanel();
		Opciones.add(panel_6);
		
		JComboBox cbPersonas = new JComboBox();
		cbPersonas.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_6.add(cbPersonas);
		cbPersonas.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4}));
		
		JPanel panel_3 = new JPanel();
		Opciones.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().setWeekOfYearVisible(false);
		calendar.getDayChooser().setDecorationBackgroundColor(new Color(0, 0, 0));
		calendar.getDayChooser().setWeekdayForeground(new Color(255, 255, 255));
		calendar.getDayChooser().setSundayForeground(new Color(255, 0, 0));
		panel_3.add(calendar);
		
		JPanel panel_7 = new JPanel();
		Opciones.add(panel_7);
		
		JCalendar calendar_1 = new JCalendar();
		calendar_1.getDayChooser().setWeekOfYearVisible(false);
		calendar_1.getDayChooser().setWeekdayForeground(new Color(255, 255, 255));
		calendar_1.getDayChooser().setSundayForeground(new Color(255, 0, 0));
		calendar_1.setDecorationBackgroundColor(new Color(0, 0, 0));
		panel_7.add(calendar_1);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn.Conectar();
				//conn.filtrar(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem());
				Habitacion[] listaHabitaciones=DAOHabitacion.filtrarHabitaciones(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem(),calendar.getDate(),calendar_1.getDate());
				list.setListData(listaHabitaciones);
                conn.desconectar();
                
			}
		});
		panel.add(btnFiltrar, BorderLayout.SOUTH);
		
		list = new JList<Habitacion>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new Renderer());
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		
		
	
		
	}
	private void aniadirAutocompletar() {
		nombreCiudades=DAOHotel.getCiudades();
		Autocomplete autoComplete = new Autocomplete(tfCiudad, nombreCiudades);
		tfCiudad.getDocument().addDocumentListener(autoComplete);
		tfCiudad.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		tfCiudad.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());		
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

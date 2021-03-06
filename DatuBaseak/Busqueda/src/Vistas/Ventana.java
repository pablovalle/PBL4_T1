package Vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;

import Controladores.Autocomplete;
import DAO.DAOHabitacion;
import DAO.DAOHotel;
import Dialogos.DialogoMisReservas;
import Dialogos.DialogoReservar;
import Excepciones.StringFormatException;
import Objetos.Conexion;
import Objetos.Habitacion;
import Renderers.RectangleBorder;
import Renderers.RendererBusqueda;


public class Ventana extends JFrame implements ListSelectionListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String COMMIT_ACTION = "commit";
	String usuario;
	private JPanel contentPane;
	private JTextField tfCiudad;
	private JTextField tfPrecioMin;
	private JTextField tfPrecioMax;
	JList<Habitacion> list;
	Conexion conn = new Conexion();
	DAOHabitacion habitacionDao;
	List<String>nombreCiudades;
	Habitacion[] listaHabitaciones;
	JCalendar calendarIn;
	JCalendar calendarOut;
	JComboBox<String> cbPersonas, cbTipo;
	

	/**
	 * Create the frame.
	 * @param useername 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ventana(String string, String username) {
		super(string);
		this.usuario=username;
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//Ventana frame = new Ventana();
		this.setVisible(true);
		ImageIcon ImageIcon = new ImageIcon("img/Logo_PBL4.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel Opciones = new JPanel();
		panel.add(Opciones, BorderLayout.NORTH);
		panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		Opciones.setLayout(new GridLayout(4, 2, 0, 0));
		
		JPanel panel_1 = new JPanel();
		Opciones.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(crearPanelAbajo(), BorderLayout.SOUTH);
		
		JButton btnReservas = new JButton("Mis Reservas");
		btnReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				DialogoMisReservas dialogoReserva = new DialogoMisReservas(Ventana.this,"MIS RESERVAS",true,usuario);
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
		cbTipo = new JComboBox<String>();
		cbTipo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"no importa","estandar","luxury"}));
		
		JLabel lblTipo = new JLabel("Tipo: ");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_5.add(lblTipo);
		panel_5.add(cbTipo);
		
		tfCiudad = new JTextField();
		tfCiudad.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_1_1.add(tfCiudad);
		aniadirAutocompletar();
		tfCiudad.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		Opciones.add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Personas:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_2.add(lblNewLabel_1);
		
		cbPersonas = new JComboBox();
		cbPersonas.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel_2.add(cbPersonas);
		cbPersonas.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3, 4}));
		
		JPanel panel_6 = new JPanel();
		tfPrecioMax = new JTextField(3);
		tfPrecioMin = new JTextField(3);
		JLabel lblmin = new JLabel("Precio minimo: ");
		lblmin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		JLabel lblmax = new JLabel(" Maximo :");
		lblmax.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPrecioMax.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPrecioMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_6.add(lblmin);
		panel_6.add(tfPrecioMin);
		panel_6.add(lblmax);
		panel_6.add(tfPrecioMax);
		
		Opciones.add(panel_6);
		
		JPanel panel_3 = new JPanel();
		Opciones.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		calendarIn = new JCalendar();
		calendarIn.getDayChooser().setWeekOfYearVisible(false);
		calendarIn.getDayChooser().setDecorationBackgroundColor(new Color(0, 0, 0));
		calendarIn.getDayChooser().setWeekdayForeground(new Color(255, 255, 255));
		calendarIn.getDayChooser().setSundayForeground(new Color(255, 0, 0));
		panel_3.add(calendarIn);
		
		JPanel panel_7 = new JPanel();
		Opciones.add(panel_7);
		
		calendarOut = new JCalendar();
		calendarOut.getDayChooser().setWeekOfYearVisible(false);
		calendarOut.getDayChooser().setWeekdayForeground(new Color(255, 255, 255));
		calendarOut.getDayChooser().setSundayForeground(new Color(255, 0, 0));
		calendarOut.setDecorationBackgroundColor(new Color(0, 0, 0));
		panel_7.add(calendarOut);
		

		panel.add(crearPanelFiltrar(), BorderLayout.SOUTH);
		
		list = new JList<Habitacion>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new RendererBusqueda());
		list.addListSelectionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		scrollPane.setViewportView(list);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
	}
	private Component crearPanelFiltrar() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(15,5,0,5));
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFiltrar.addActionListener(this);		
		panel.add(btnFiltrar);
		return panel;
	}
	private Component crearPanelAbajo() {
		JPanel panel = new JPanel(new BorderLayout(10,10));
		panel.setBackground(Color.BLACK);
		panel.setBorder(BorderFactory.createCompoundBorder(new RectangleBorder(Color.WHITE),BorderFactory.createEmptyBorder(20,20,20,20)));
		JLabel usuario = new JLabel();
		usuario.setText(this.usuario+"        ");
		usuario.setForeground(Color.WHITE);
		usuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblfecha = new JLabel("Fecha actual");		
		lblfecha.setText(String.valueOf(LocalDate.now()));
		lblfecha.setForeground(Color.WHITE);
		lblfecha.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		panel.add(lblfecha, BorderLayout.WEST);
		panel.add(usuario, BorderLayout.EAST);
		return panel;
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
					Ventana frame = new Ventana("B�sqeda MUTel", "benat_mutel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int seleccionado = list.getSelectedIndex();
		if(seleccionado != -1 ) {
			DialogoReservar confirmarReserva = new DialogoReservar(this, "Confirmar Reserva", true,list.getSelectedValue(),calendarIn.getDate(),calendarOut.getDate(), usuario);
			confirmarReserva.setVisible(true);
			try {
				listaHabitaciones=DAOHabitacion.filtrarHabitaciones(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem(),calendarIn.getDate(),calendarOut.getDate(), (String) cbTipo.getSelectedItem(), tfPrecioMin.getText(), tfPrecioMax.getText());
				list.setListData(listaHabitaciones);
				list.clearSelection();
			} catch (NumberFormatException | StringFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		else if (seleccionado==-1) {
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		conn.Conectar();
		//conn.filtrar(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem());
		try {
			listaHabitaciones=DAOHabitacion.filtrarHabitaciones(tfCiudad.getText(), (Integer)cbPersonas.getSelectedItem(),calendarIn.getDate(),calendarOut.getDate(), (String) cbTipo.getSelectedItem(), tfPrecioMin.getText(), tfPrecioMax.getText());
			list.setListData(listaHabitaciones);
	        conn.desconectar();
	        if(list.getModel().getSize()==0) {
	        	JOptionPane.showMessageDialog(this, "�No existe ninguna habitaci�n con esas caracter�sticas!", "�ERROR!",
						JOptionPane.ERROR_MESSAGE);
	        }
		} catch (NumberFormatException | StringFormatException e1) {
			JOptionPane.showMessageDialog(this, "�Los datos no son correctos!", "�ERROR!",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

}

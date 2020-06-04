import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	private JPanel panelPrincipal;
	private JSplitPane splitPanel;
	private JPanel panelIzda;
	private JScrollPane panelDcha;
	JTextArea areaTexto;
	
	JLabel labelPuerto;
	JComboBox<String> listaPuertos;
	JRadioButton rbRecepcion;
	JRadioButton rbHabitacion;
	ButtonGroup grupoBotones;
	JTextField numHotel;
	JTextField numHabitacion;
	JPanel panelHabitacion;
	
	ControladorPuerta controlador;
	Comunicador comunicador;
	
	JButton bConectar;
	JButton bDesconectar;
	JButton bEmpezar;
		
	public GUI() {
		super("Controlador MUTel");
		comunicador = new Comunicador(this);
		controlador = new ControladorPuerta(this, comunicador);
		comunicador.addListener(controlador);
		this.setSize(700, 500);
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2 - this.getSize().width/2),
				(Toolkit.getDefaultToolkit().getScreenSize().height/2) - this.getSize().height/2);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
		this.setContentPane(crearPanelPrincipal());
		comunicador.buscarPuertos();

	}
	
	private JPanel crearPanelPrincipal() {
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.add(crearSplitPanel());
		
		return panelPrincipal;
	}
	
	private JSplitPane crearSplitPanel() {
		splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, crearPanelIzda(), crearPanelDcha());
		splitPanel.setDividerLocation(230);
		return splitPanel;
	}

	private Component crearPanelIzda() {
		panelIzda = new JPanel();
		panelIzda.setLayout(new GridLayout(5,1));
		panelIzda.add(crearPanelPuertos());
		panelIzda.add(crearPanelHotel());
		panelIzda.add(panelRadioBoton());
		panelIzda.add(crearPanelHabitacion());
		panelIzda.add(crearPanelBotones()); 
		return panelIzda;
	}

	private Component crearPanelBotones() {
		JPanel panel =  new JPanel();
		bConectar = new JButton("Conectar");
		bConectar.addActionListener(controlador);
		bConectar.setActionCommand("conectar");
		
		bDesconectar = new JButton("Desconectar");
		bDesconectar.addActionListener(controlador);
		bDesconectar.setActionCommand("desconectar");
		bDesconectar.setEnabled(false);
		
		bEmpezar = new JButton("Empezar");
		bEmpezar.addActionListener(controlador);
		bEmpezar.setActionCommand("empezar");
		bEmpezar.setEnabled(false);
		
		panel.add(bConectar);
		panel.add(bDesconectar);
		panel.add(bEmpezar);
		
		return panel;
	}
		
	public boolean getEstadoRecepcion() {
		return rbRecepcion.isSelected();
	}

	private Component crearPanelHotel() {
		JPanel panel = new JPanel();
		JLabel labelHotel = new JLabel("ID hotel: ");
		numHotel = new JTextField(10);
		numHotel.setSize(10, 5);
		panel.add(labelHotel);
		panel.add(numHotel);
		
		return panel;
	}

	private Component panelRadioBoton() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		rbRecepcion = new JRadioButton();
		rbRecepcion.setText("Recepcion");
		rbRecepcion.setActionCommand("rbRecepcion");
		rbRecepcion.addActionListener(controlador);
		rbHabitacion = new JRadioButton();
		rbHabitacion.setText("Habitacion");
		rbHabitacion.setActionCommand("rbHabitacion");
		rbHabitacion.addActionListener(controlador);
		rbRecepcion.setSelected(true);
		grupoBotones = new ButtonGroup();
		grupoBotones.add(rbHabitacion);
		grupoBotones.add(rbRecepcion);

		panel.add(rbRecepcion);
		panel.add(rbHabitacion);
		return panel;
	}
	
	private Component crearPanelHabitacion() {
		panelHabitacion = new JPanel();
		JLabel label = new JLabel("Nº habitación: ");
		numHabitacion = new JTextField(10);
		panelHabitacion.add(label);
		panelHabitacion.add(numHabitacion);
		panelHabitacion.setVisible(false);
		return panelHabitacion;
	}

	public void setVisiblePanelHabitacion(boolean estado) {
		panelHabitacion.setVisible(estado);
	}
	
	private Component crearPanelPuertos() {
		JPanel panel = new JPanel();
		labelPuerto = new JLabel("Puerto: ");
		listaPuertos = new JComboBox<String>();
		panel.add(labelPuerto);
		panel.add(listaPuertos);
		return panel;
	}

	private Component crearPanelDcha() {
		panelDcha = new JScrollPane();
		areaTexto = new JTextArea();
		areaTexto.setColumns(20);
		areaTexto.setRows(5);
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		areaTexto.setFocusable(false);
		panelDcha.setViewportView(areaTexto);
		
		return panelDcha;
	}
	
	public void escribirLinea(String linea) {
		areaTexto.append(linea + '\n');
		areaTexto.setCaretPosition(areaTexto.getDocument().getLength());
	}
	
	public JComboBox<String> getComboBox() {
		return listaPuertos;
	}
	
	public String getComboBoxSeleccionado() {
		return (String)listaPuertos.getSelectedItem();
	}
	
	public JTextArea getTextArea() {
		return areaTexto;
	}

	public String getNumHotel() {
		return numHotel.getText();
	}
	
	public String getNumHabitacion() {
		return numHabitacion.getText();
	}
	
	public void setBotonEmpezar(boolean estado) {
		bEmpezar.setEnabled(estado);	
	}
	
	public void setBotonConectar(boolean estado) {
		bConectar.setEnabled(estado);
	}
	
	public void setbotonDesconectar(boolean estado) {
		bDesconectar.setEnabled(estado);
	}

	public void setEstadoTexfields(boolean estado) {
		numHabitacion.setEditable(estado);
		numHotel.setEditable(estado);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI ventana = new GUI();
					ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}




}

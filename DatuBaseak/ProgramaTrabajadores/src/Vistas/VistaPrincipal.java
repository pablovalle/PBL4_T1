package Vistas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.DAOTarea;
import DAO.DAOUsuario;
import Dialogos.DialogoMapa;
import Dialogos.DialogoTarea;
import Objetos.Tarea;
import Objetos.Trabajador;
import Renderer.RectangleBorder;
import Renderer.Renderer;

public class VistaPrincipal extends JFrame implements ListSelectionListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Tarea[] listaTareas;
	JList<Tarea> list ;
	Trabajador trabajador;
	JScrollPane spanel;
	public VistaPrincipal(String string, String usuario) {
		super(string);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		this.setVisible(true);
		ImageIcon ImageIcon = new ImageIcon("img/Logo_PBL4.png");
		Image image = ImageIcon.getImage();
		this.setIconImage(image);

		trabajador= DAOUsuario.getTrabajador(usuario);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		list = new JList();
		spanel= new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listaTareas=DAOTarea.getTareas(trabajador);
		list.addListSelectionListener(this);
		list.setListData(listaTareas);
		list.setCellRenderer(new Renderer());
		spanel.setViewportView(list);
		panel.add(spanel, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton btnNewButton = new JButton("VerMapa");
		btnNewButton.setActionCommand("Mapa");
		btnNewButton.addActionListener(this);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Fecha actual");		
		lblNewLabel.setText(String.valueOf(LocalDate.now()));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Cerrar Sesion");
		btnNewButton_1.addActionListener(this);
		panel_1.add(btnNewButton_1);
		
		JButton btnSalir= new JButton("Salir");
		btnSalir.addActionListener(this);
		JPanel panel2= new JPanel();
		panel2.setBackground(Color.BLACK);
		panel2.setBorder(new RectangleBorder(Color.WHITE));
		panel2.add(btnSalir);
		contentPane.add(panel2, BorderLayout.SOUTH);
	}
	/**
	 * Launch the application.
	 */
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int seleccionado=list.getSelectedIndex();
		if(seleccionado!=-1) {
			DialogoTarea dialogo= new DialogoTarea(this, "Terminar Tarea", true,list.getSelectedValue(), this.trabajador);
			dialogo.setVisible(true);
			listaTareas=DAOTarea.getTareas(trabajador);
			list.setListData(listaTareas);
			list.clearSelection();
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Mapa")) {
			DialogoMapa mapa = new DialogoMapa(this,trabajador);
			mapa.setVisible(true);
		}
		else {
			this.dispose();
		}
		
		
	}

}

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

public class VistaPrincipal extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Tarea[] listaTareas;
	JList<Tarea> list ;
	Trabajador trabajador;
	JScrollPane spanel;
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		trabajador= new Trabajador(1,1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DialogoMapa mapa = new DialogoMapa();
				mapa.setVisible(true);
			}
		});
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Fecha actual");		
		lblNewLabel.setText(String.valueOf(LocalDate.now()));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Cerrar Sesion");
		panel_1.add(btnNewButton_1);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaPrincipal frame = new VistaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int seleccionado=list.getSelectedIndex();
		if(seleccionado!=-1) {
			DialogoTarea dialogo= new DialogoTarea(this, "Terminar Tarea", true,list.getSelectedValue());
			dialogo.setVisible(true);
			listaTareas=DAOTarea.getTareas(trabajador);
			list.setListData(listaTareas);
			list.clearSelection();
			
		}
		
	}

}
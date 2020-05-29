import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class DialogoMapa extends JDialog implements ActionListener {

	private final int IDHOTEL=1;
	private final JPanel contentPanel = new JPanel();

	JComboBox comboBox;
	MiPanelMapa panelMapa;
	
	public DialogoMapa() {
		
	
		setBounds(100, 100, 667, 535);
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().width/2 - (int)(this.getSize().getWidth()/2), (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().height/2 - (this.getSize().getHeight()/2)));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		{
			JLabel lbPiso = new JLabel("Piso");
			lbPiso.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lbPiso, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(11, 1, 0, 0));

			{
				comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(DAOHotel.getPisos(IDHOTEL)));
				comboBox.addActionListener(this);
				panel.add(comboBox);
				comboBox.setActionCommand("ComboBox");
			}
		}
		
		{
			JButton btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			btnSalir.setActionCommand("Salir");
			contentPanel.add(btnSalir, BorderLayout.SOUTH);
			
		}
		panelMapa=new MiPanelMapa("img/Hotel.jpg", DAOHabitacion.getHabitacionesPiso(comboBox.getSelectedIndex()+1));
		contentPanel.add(panelMapa, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Salir":this.dispose();			
			break;
		case "ComboBox":
			String[] estadoPisos = DAOHabitacion.getHabitacionesPiso(comboBox.getSelectedIndex()+1);
			panelMapa.setEstados(estadoPisos);
			panelMapa.repaint();
			//actualizar mapa
			break;
		}
	
		
		
		
	}

}

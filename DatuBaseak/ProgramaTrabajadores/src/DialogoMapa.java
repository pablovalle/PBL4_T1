import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DialogoMapa extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	public DialogoMapa() {
		
		setBounds(100, 100, 667, 535);
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
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
				panel.add(comboBox);
			}
		}
		{
			JButton btnSalir = new JButton("Salir");
			btnSalir.addActionListener(this);
			contentPanel.add(btnSalir, BorderLayout.SOUTH);
			
		}
		MiPanelMapa panelMapa=new MiPanelMapa("img/Hotel.jpg");
		contentPanel.add(panelMapa, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		
	}

}

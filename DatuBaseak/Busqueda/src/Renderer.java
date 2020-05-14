import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class Renderer extends JLabel implements ListCellRenderer<Habitacion> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Habitacion> list, Habitacion ha, int index,
			boolean isSelected, boolean cellHasFocus) {
		if(isSelected) {
			setFont( new Font("Arial",Font.BOLD,16));
			setForeground(Color.WHITE);
			setBackground(Color.GREEN);
		}
		else {
			setFont( new Font("Arial",Font.BOLD,16));
			setForeground(Color.black);
			setBackground(Color.WHITE);
		}
		this.setHorizontalAlignment(JLabel.LEFT);
		this.setText(ha.toString());
		setOpaque(true);
		return this;
	}

}

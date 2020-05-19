import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MiPanelMapa extends JPanel {

	private Image img;

	public MiPanelMapa(String img) {
		this(new ImageIcon(img).getImage());
	}

	public MiPanelMapa(Image image) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		  g.drawImage(img, 0, 0, null);
	}

}

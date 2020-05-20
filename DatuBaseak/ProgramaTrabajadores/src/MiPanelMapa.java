import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MiPanelMapa extends JPanel {

	private Image img;
	private String[] estados;

	public MiPanelMapa(String image, String[] estados) {
	    try {
			this.img = ImageIO.read(new File(image));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	   this.estados=estados ;
	    
	}

	public void setEstados(String[] estados) {
		this.estados = estados;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		  g.drawImage(img, 0, 0, null);
		  g.setColor(Color.RED);
		  g.fillOval(33, 150, 15, 15);
		  g.setColor(Color.BLUE);
		  g.fillOval(33, 208, 15, 15);
		  g.setColor(Color.RED);
		  g.fillOval(108, 150, 15, 15);
		  g.fillOval(94, 208, 15, 15);
		  g.setColor(Color.BLUE);
		  g.fillOval(170, 208, 15, 15);
		  g.fillOval(155, 150, 15, 15);
		  g.setColor(Color.GREEN);
		  g.fillOval(217, 208, 15, 15);
		  g.fillOval(232, 150, 15, 15);
		  g.fillOval(292, 208, 15, 15);
		  g.fillOval(275, 150, 15, 15);
		  g.fillOval(355, 208, 15, 15);
		  g.fillOval(355, 150, 15, 15);
	}

}

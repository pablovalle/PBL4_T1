package Renderer;
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
		  g.setColor(seleccionarColor(0));
		  g.fillOval(33, 150, 15, 15);
		  g.setColor(seleccionarColor(1));
		  g.fillOval(33, 208, 15, 15);
		  g.setColor(seleccionarColor(2));
		  g.fillOval(108, 150, 15, 15);
		  g.setColor(seleccionarColor(3));
		  g.fillOval(94, 208, 15, 15);
		  g.setColor(seleccionarColor(4));
		  g.fillOval(170, 208, 15, 15);
		  g.setColor(seleccionarColor(5));
		  g.fillOval(155, 150, 15, 15);
		  g.setColor(seleccionarColor(6));
		  g.fillOval(217, 208, 15, 15);
		  g.setColor(seleccionarColor(7));
		  g.fillOval(232, 150, 15, 15);
		  g.setColor(seleccionarColor(8));
		  g.fillOval(292, 208, 15, 15);
		  g.setColor(seleccionarColor(9));
		  g.fillOval(275, 150, 15, 15);
		  g.setColor(seleccionarColor(10));
		  g.fillOval(355, 208, 15, 15);
		  g.setColor(seleccionarColor(11));
		  g.fillOval(355, 150, 15, 15);
	}

	private Color seleccionarColor(int i) {
		Color color= null;
		switch(estados[i]) {
		case "hecho": color=Color.GREEN; break;
		case "para hacer":color= Color.RED; break;
		case "vacio":color= Color.BLACK; break;
		}
		return color;
	}

}

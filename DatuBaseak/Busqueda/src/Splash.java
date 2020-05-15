import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class Splash extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Splash()  {
    	this.setBackground(new Color(0,0,0,0));
        JLabel labelImagen = new JLabel(new ImageIcon("img/Logo_PBL4.png"));
        labelImagen.setBorder(BorderFactory.createRaisedBevelBorder());
        getContentPane().add(labelImagen, BorderLayout.CENTER);
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = labelImagen.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
        setVisible(true);       
    }
	
}

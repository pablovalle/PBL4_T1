package Renderers;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class RectangleBorder extends AbstractBorder {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

protected Insets thickness;

  protected Color lineColor;

  protected Insets gap;

  public RectangleBorder(Color color) {
    this(color, new Insets(1, 1, 1, 1));
  }

  public RectangleBorder(Color color, Insets thickness) {
    this(color, thickness, thickness);
  }

  public RectangleBorder(Color color, Insets thickness, Insets gap) {
    lineColor = color;
    this.thickness = thickness;
    this.gap = gap;
  }

  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Color oldColor = g.getColor();
 

    g.setColor(lineColor);
    // top
    for (int i = 0; i < 2; i++) {
      g.drawLine(x+3, y + i+2, x + width-6, y + i+2);
    }
    // bottom
    for (int i = 0; i < 2; i++) {
      g.drawLine(x+3, y + height - i - 1-3, x + width-6, y + height - i - 1-3);
    }
    // right
    for (int i = 0; i < 2; i++) {
      g.drawLine(x + width - i - 1-4, y+3, x + width - i - 1-4, y + height-4);
    }
    // left
    for (int i = 0; i < 2; i++) {
      g.drawLine(x + i+3, y+2, x + i+3, y + height-4);
    }
    g.setColor(oldColor);
  }

  /**
   * Returns the insets of the border.
   * 
   * @param c
   *          the component for which this border insets value applies
   */
  public Insets getBorderInsets(Component c) {
    return gap;
  }

  public Insets getBorderInsets(Component c, Insets insets) {
    insets.left = gap.left;
    insets.top = gap.top;
    insets.right = gap.right;
    insets.bottom = gap.bottom;
    return insets;
  }

  /**
   * Returns the color of the border.
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Returns the thickness of the border.
   */
  public Insets getThickness() {
    return thickness;
  }

  /**
   * Returns whether or not the border is opaque.
   */
  public boolean isBorderOpaque() {
    return false;
  }

  public Insets getGap() {
    return gap;
  }

}

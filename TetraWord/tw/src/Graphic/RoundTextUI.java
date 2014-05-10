package Graphic;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalTextFieldUI;
 
/**
* A custom TextField UI based on Metal that paints
* text fields with a round border.
*/
public class RoundTextUI extends MetalTextFieldUI
{
 
	public static ComponentUI createUI(JComponent c)
	{
		return new RoundTextUI();
	}
 
	public void installUI(JComponent c)
	{
		super.installUI(c);
		c.setBorder(new RoundBorder());
		c.setOpaque(false);
	}
 
	protected void paintSafely(Graphics g)
	{
		JComponent c = getComponent();
		if (!c.isOpaque())
		{
			g.setColor(c.getBackground());
			g.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 20, 20);
		}
		super.paintSafely(g);
	}
 
 
	private static class RoundBorder extends AbstractBorder
	{
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
		{
			Color oldColor = g.getColor();
 
			g.setColor(Color.black);
			g.drawRoundRect(x, y, width - 1, height - 1, 20, 20);
			g.setColor(oldColor);
		}
 
		public Insets getBorderInsets(Component c)
		{
			return new Insets(4, 10, 4, 10);
		}
 
		public Insets getBorderInsets(Component c, Insets insets)
		{
			insets.left = insets.top = insets.right = insets.bottom = 4;
			return insets;
		}
 
	}
}
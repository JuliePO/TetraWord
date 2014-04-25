package Graphic;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class PanelBase extends JPanel {
	
	protected char state;
	
	protected int w, h;
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}
	 
	public char getState(){
		return state;
	}
}

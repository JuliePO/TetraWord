package Graphic;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class PanelBase extends JPanel {
	
	protected char state;
	
	protected int w, h;
	
	protected String mPath = ""; //ONLY FOR DEVELOPMENT
	
	protected PanelBase(){
	    
	    //mPath= "../";// A commenter sous Ecllipse
		
	    setFocusable(true);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(w, h);
	}
	 
	public char getState(){
		return state;
	}
	
	public void setState(char newS){
		state=newS;
	}
	
	abstract public void update();
}

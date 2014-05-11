package Graphic.tetra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import utility.Player;

public class KeyListenerTetra implements KeyListener {
	
	private Player P1;
	private Player P2;
	
	public KeyListenerTetra(Player P1 , Player P2){
		super();
		this.P1=P1;
		this.P2=P2;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		char tmp = arg0.getKeyChar();
		
		if(tmp == ' ')
			((PanelTetraWord)arg0.getComponent()).setPause();
		else if(tmp == P1.getInput("up"))
			System.out.println("lol");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

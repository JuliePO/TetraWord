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
			P1.getShape().rotate();
		else if(tmp == P1.getInput("left"))
			P1.getShape().goLeft();
		else if(tmp == P1.getInput("right"))
			P1.getShape().goRight();

		else if(tmp == P2.getInput("up"))
			P2.getShape().rotate();
		else if(tmp == P2.getInput("left"))
			P2.getShape().goLeft();
		else if(tmp == P2.getInput("right"))
			P2.getShape().goRight();
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
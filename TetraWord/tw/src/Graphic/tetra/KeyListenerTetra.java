package Graphic.tetra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameState.Game;
import utility.Player;

public class KeyListenerTetra implements KeyListener {
	
	private Game game1;
	private Game game2;
	
	public KeyListenerTetra(Game game1, Game game2){
		super();
		this.game1=game1;
		this.game2=game2;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		char tmp = arg0.getKeyChar();
		
		if(tmp == ' ')
			((PanelTetraWord)arg0.getComponent()).setPause();
		else{
			game1.getState().input(tmp);
			game2.getState().input(tmp);
		}
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

package Graphic.character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerCharacter implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		switch (arg0.getKeyChar()) {
		
		//player1
		case 'z':
			((PanelCharacter)arg0.getComponent()).selectP1(-2);
			break;
		case 's':
			((PanelCharacter)arg0.getComponent()).selectP1(2);
			break;
		case 'q':
			((PanelCharacter)arg0.getComponent()).selectP1(-1);
			break;
		case 'd':
			((PanelCharacter)arg0.getComponent()).selectP1(1);
			break;
		case 'e':
			((PanelCharacter)arg0.getComponent()).setP1();
			break;
			
		case 'a':
			((PanelCharacter)arg0.getComponent()).cancel1();
			break;
		
			//player2
		case '8':
			((PanelCharacter)arg0.getComponent()).selectP2(-2);
			break;
		case '5':
			((PanelCharacter)arg0.getComponent()).selectP2(2);
			break;
		case '4':
			((PanelCharacter)arg0.getComponent()).selectP2(-1);
			break;
		case '6':
			((PanelCharacter)arg0.getComponent()).selectP2(1);
			break;
		case '9':
			((PanelCharacter)arg0.getComponent()).setP2();
			break;
		case '7':
			((PanelCharacter)arg0.getComponent()).cancel2();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("key released :"+arg0.getKeyChar());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("key typed:"+arg0.getKeyChar());
	}

}

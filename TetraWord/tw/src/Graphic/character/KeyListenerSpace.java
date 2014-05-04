package Graphic.character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerSpace implements KeyListener {

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyChar()) {
		case ' ':
			((PanelCharacter)arg0.getComponent().getParent()).setState('g');
			break;
		case 'a':
			((PanelCharacter)arg0.getComponent().getParent()).cancel1();
			break;
		case '7':
			((PanelCharacter)arg0.getComponent().getParent()).cancel2();
			break;
		default:
			break;
		}
	}

}

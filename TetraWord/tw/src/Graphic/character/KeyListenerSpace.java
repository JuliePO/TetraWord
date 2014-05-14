package Graphic.character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Audio.Audio;

public class KeyListenerSpace implements KeyListener {
	
	Audio sound;
	
	public KeyListenerSpace() {
		sound = new Audio("sound/Bonus2.wav");
	}

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
			sound.start();
			break;
		case 'a':
			((PanelCharacter)arg0.getComponent().getParent()).cancel1();
			sound = new Audio("sound/b.wav");
			sound.start();
			break;
		case '7':
			((PanelCharacter)arg0.getComponent().getParent()).cancel2();
			sound = new Audio("sound/b.wav");
			sound.start();
			break;
		default:
			break;
		}
	}

}

package Graphic.tetra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerTetra implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyChar()) {
		case ' ':
			((PanelTetraWord)arg0.getComponent()).setPause();
			break;

		default:
			break;
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

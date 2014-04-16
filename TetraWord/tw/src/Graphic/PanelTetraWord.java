package Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelTetraWord extends PanelBase {
	
	public PanelTetraWord() {
		state = 'g';
		
		final ActionListener actionGame= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 's';
			}
		};
		
		JButton game = new JButton("Game");
		game.addActionListener(actionGame);
		add(game);
	}

}

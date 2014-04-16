package Graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelOption extends PanelBase {
	
	public PanelOption() {
		state = 'o';
		
		final ActionListener actionOption= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 's';
			}
		};
		
		JButton option = new JButton("Option");
		option.addActionListener(actionOption);
		add(option);
	}
}

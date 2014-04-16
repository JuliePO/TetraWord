package Graphic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelAccueil extends PanelBase {
	
	public PanelAccueil() {
		
		state = 's';
		
		final ActionListener actionGame= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'g';
			}
		};	
		final ActionListener actionOption= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'o';
			}
		};	
		final ActionListener actionExit= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'e';
				JFrame.getFrames()[0].dispose();
			}
		};
		
		JButton start = new JButton("Start");
		start.addActionListener(actionGame);
		add(start);
		
		JButton option = new JButton("Option");
		option.addActionListener(actionOption);
		add(option);
		
		JButton exit = new JButton("Exit");
		exit.addActionListener(actionExit);
		add(exit);
	}
}

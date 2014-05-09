package Graphic.option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphic.PanelBase;
import Graphic.tetra.BonusComponent;

public class PanelOption extends PanelBase {
	
	private char optionState = 'o';
	private OptionUnderPanel option;
	/*private ShapeUnderPanel shape;
	private LetterUnderPanel letter;
	private CreateUnderPanel create;*/
	
	public PanelOption() {
		state = 'o';
		
		w= 1024;
		h=700;
		
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelOption bonus = new PanelOption();
			
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}
}

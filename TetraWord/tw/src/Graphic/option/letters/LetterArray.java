package Graphic.option.letters;

import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;

import utility.Configuration;
import Graphic.TetraComponent;

public class LetterArray extends TetraComponent {
	
	LetterComponent[] letter;
	Configuration config;
	
	public LetterArray(Configuration config) {
		
		this.config=config;

		w = 1080;
		h= 322;
		
		setLayout(new GridLayout(5, 6));
		
		letter = new LetterComponent[26];
		
		for(char i = 0; i < 26; ++i){
			letter[i] = new LetterComponent((char)(i + 'A'), config.getCharFrequence((char)(i+'a')));
			add(letter[i]);
		}
	}

	@Override
	public void update() {
	
		for(int i = 0; i < 26; ++i){
			if(letter[i].upTxt){
				config.setCharFrequence((char)(i+'a'), letter[i].getValue());
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
	
	public static void main(String[] args) {
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LetterArray bonus = new LetterArray(new Configuration());
			
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
	}

}

package Graphic.option.option;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import utility.Player;
import Graphic.TetraComponent;
import Graphic.option.TextFieldTetra;

public class NameComponent extends TetraComponent{
	
	Player p;
	
	TextFieldTetra textField;
	
	public NameComponent(Player p, String playerNumber) {
		w = 354;
		h=41;
		
		this.p = p;
		
		setLayout(null);
		
		JLabel presentation = new JLabel(playerNumber+":");
		presentation.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		presentation.setForeground(Color.WHITE);
		presentation.setBounds(0, 0, 91, 41);
		add(presentation);
		
		textField = new TextFieldTetra(263, 30, p.getName(), new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 22), Color.BLACK);
		textField.setBounds(91, 0, 263, 41);
		add(textField);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
	
	@Override
	public void update() {
		
		if(textField.upTxt){
			textField.upTxt=false;
			String tmp = textField.getText();
			p.setName(tmp);
		}
	}
	
	public static void main(String[] args) {
		Player p = new Player(1, "kikou");
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NameComponent bonus = new NameComponent(p, "Player 1");	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		while(true){
			bonus.update();
		}
		
		
	}

}

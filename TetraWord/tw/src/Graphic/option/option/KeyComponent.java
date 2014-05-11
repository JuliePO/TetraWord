package Graphic.option.option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Player;
import Graphic.TetraComponent;
import Graphic.option.TextFieldTetra;

public class KeyComponent extends TetraComponent {
	
	private Player p;
	private TextFieldTetra input;
	private String key;

	public KeyComponent(String key, char value, Player p) {
		
		this.p=p;
		this.key=key;
		
		setLayout(null);
		
		w = 126;
		h=51;
		
		JLabel icone = new JLabel(new ImageIcon("texture/options/options/"+key+".png"));
		icone.setBounds(0, 0, 53, 51);
		add(icone);
		
		input = new TextFieldTetra(30, 30, ""+value, new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 22), Color.BLACK);
		input.setBounds(70, 3, 56, 42);
		add(input);
	}

	@Override
	public void update() {
		if(input.upTxt){
			p.setInput(key, input.getText().charAt(0));
		}

	}
	
	@Override
	public void paintComponent(Graphics g) {
	}
	

	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KeyComponent bonus = new KeyComponent("up", 'z', new Player(1));	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}

}

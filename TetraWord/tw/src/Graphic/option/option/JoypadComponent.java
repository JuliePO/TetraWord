package Graphic.option.option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Player;
import Graphic.TetraComponent;

public class JoypadComponent extends TetraComponent {
	
	Player p;
	
	JoypadComponent(String numP, Player p){
		this.p = p;
		h=212;
		w=479;
		
		setLayout(null);
		
		JLabel name = new JLabel(numP);
		name.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		name.setForeground(Color.BLACK);
		name.setBounds(38, 0, 90, 42);
		add(name);

		KeyComponent right = new KeyComponent("right", p.getInput("right"), p);
		right.setBounds(32, 61, right.getW(), right.getH());
		add(right);

		KeyComponent left = new KeyComponent("left", p.getInput("left"), p);
		left.setBounds(32, 130, left.getW(), left.getH());
		add(left);

		KeyComponent l = new KeyComponent("l", p.getInput("l"), p);
		l.setBounds(201, 0, l.getW(), l.getH());
		add(l);

		KeyComponent r = new KeyComponent("r", p.getInput("r"), p);
		r.setBounds(356, 0, r.getW(), r.getH());
		add(r);

		KeyComponent up = new KeyComponent("up", p.getInput("up"), p);
		up.setBounds(201, 61, up.getW(), up.getH());
		add(up);

		KeyComponent a = new KeyComponent("a", p.getInput("a"), p);
		a.setBounds(356, 61, a.getW(), a.getH());
		add(a);

		KeyComponent down = new KeyComponent("down", p.getInput("down"), p);
		down.setBounds(201, 130, down.getW(), down.getH());
		add(down);

		KeyComponent b = new KeyComponent("b", p.getInput("b"), p);
		b.setBounds(356, 130, b.getW(), b.getH());
		add(b);
	}

	@Override
	public void update() {
		for(int i = 1; i < 9; ++i){
			((KeyComponent)getComponent(i)).update();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JoypadComponent bonus = new JoypadComponent("Player 1", new Player(1, "georges"));	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}

}

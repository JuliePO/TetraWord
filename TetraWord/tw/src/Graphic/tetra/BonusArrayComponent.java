package Graphic.tetra;

import java.awt.Graphics;

import javax.swing.JFrame;

import utility.BonusCollection;
import utility.Player;
import Graphic.TetraComponent;

public class BonusArrayComponent extends TetraComponent {
	
	private BonusComponent[] bonusArray = new BonusComponent[3];
	private Player p;
	
	public BonusArrayComponent(Player p) {
		
		this.p=p;
		
		w= 50;
		h= 165;
		
		setLayout(null);
		
		for(int i = 0; i < 3; ++i){
			bonusArray[i] = new BonusComponent();
			bonusArray[i].setBounds(0, i * 55, bonusArray[i].getW(), bonusArray[i].getH());
			add(bonusArray[i]);
		}
		
	}
	
	@Override
	public void update() {
		
		BonusCollection tmp = p.getBonus();
		
		for(int i = 0; i < 3; ++i){
			bonusArray[i].setBonus(tmp.get(i));
			bonusArray[i].repaint();
		}
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
	
	public static void main(String[] args) {
		
		Player p = new Player();
		p.getBonus().add("exchange");
		p.getBonus().add("lapin");
		p.getBonus().add("lapin");
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BonusArrayComponent bonus = new BonusArrayComponent(p);	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		bonus.update();
		
	}

}

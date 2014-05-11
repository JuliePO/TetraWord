package Graphic.tetra;

import java.awt.Graphics;

import javax.swing.JFrame;

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
		

		String[] tmp = p.getBonus();
		for(int i = 0; i < 3; ++i){
			if(tmp[i] != null){
				bonusArray[i].setBonus(tmp[i]);
				bonusArray[i].repaint();
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
	
	public static void main(String[] args) {
		

		Player p = new Player(1);
		p.addBonus("exchange");
		p.addBonus("lapin");
		p.addBonus("lapin");
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BonusArrayComponent bonus = new BonusArrayComponent(p);	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		bonus.update();
		
	}

}

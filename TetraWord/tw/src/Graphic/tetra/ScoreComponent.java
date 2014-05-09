package Graphic.tetra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import Graphic.TetraComponent;
import utility.Player;


public class ScoreComponent extends TetraComponent {
	
	Player p;
	
	public ScoreComponent(Player player) {
		this.w = 80;
		this.h = 26;
		this.p = player;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("DOSIS-REGULAR", Font.BOLD, 20));
		g2.setColor(Color.WHITE);
		g2.drawString(new Integer(p.getScore()).toString(), w/4, (h*2)/3);
		
	}

	@Override
	public void update() {
		repaint();		
	}
}

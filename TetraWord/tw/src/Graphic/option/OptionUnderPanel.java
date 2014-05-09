package Graphic.option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

import Graphic.PanelBase;
import Graphic.tetra.BonusComponent;

public class OptionUnderPanel extends PanelBase {

	public OptionUnderPanel() {
		state = 'o';
		
		w = 1024;
		h = 700;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("DOSIS-BOLD", Font.BOLD, 45));
		g2.setColor(Color.WHITE);
		g2.drawString("OPTIONS", 442, 56);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionUnderPanel bonus = new OptionUnderPanel();	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}

}

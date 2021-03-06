package Graphic.character;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class SelecteurComponent extends SelecteurExtends {
	
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}
	
	public SelecteurComponent() {
		h = 343;
		w = 343;
		
		setLayout(null);
		
		PersoSelectComponent demon = new PersoSelectComponent("demon");
		demon.setBounds(0, 0, demon.getW(), demon.getH());
		add(demon);
		
		PersoSelectComponent ninja = new PersoSelectComponent("ninja");
		ninja.setBounds(193, 0, ninja.getW(), ninja.getH());
		add(ninja);
		
		PersoSelectComponent panda = new PersoSelectComponent("panda");
		panda.setBounds(0, 193, panda.getW(), panda.getH());
		add(panda);
		
		PersoSelectComponent robot = new PersoSelectComponent("robot");
		robot.setBounds(193, 193, robot.getW(), robot.getH());
		add(robot);
		
	}
	
	@Override
	public void update() {
		
		for(int i = 0; i < getComponentCount(); ++i){
			if(i == p1)
				((PersoSelectComponent) getComponent(i)).setP1(true);
			else
				((PersoSelectComponent) getComponent(i)).setP1(false);
			if(i == p2)
				((PersoSelectComponent) getComponent(i)).setP2(true);
			else
				((PersoSelectComponent) getComponent(i)).setP2(false);
		}	
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setContentPane(new SelecteurComponent());
		tmp.pack();
		tmp.setVisible(true);
	}
}

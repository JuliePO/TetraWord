package Graphic.character;

import java.awt.Graphics;

import javax.swing.JFrame;

public class PresentationComponent extends SelecteurExtends {
	
	@Override
	protected void paintComponent(Graphics g) {
		
	}
	
	public PresentationComponent() {
		h = 343;
		w = 343;
		
		setLayout(null);
		
		PersoSelectComponent panda = new PersoSelectComponent("panda");
		panda.setBounds(0, 0, panda.getW(), panda.getH());
		add(panda);
		
		PersoSelectComponent robot = new PersoSelectComponent("robot");
		robot.setBounds(193, 0, robot.getW(), robot.getH());
		add(robot);
		
		PersoSelectComponent ninja = new PersoSelectComponent("ninja");
		ninja.setBounds(0, 193, ninja.getW(), ninja.getH());
		add(ninja);
		
		PersoSelectComponent demon = new PersoSelectComponent("demon");
		demon.setBounds(193, 193, demon.getW(), demon.getH());
		add(demon);
		
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
		
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setContentPane(new SelecteurComponent());
		tmp.pack();
		tmp.setVisible(true);
	}
}

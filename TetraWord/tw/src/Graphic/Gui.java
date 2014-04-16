package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.Player;


public class Gui extends JPanel{
	
	Player players[];
	
	//@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int h = getHeight();
		int w = getWidth();
		
		char text[] = {'t'};
		
		g.drawChars(text, 0, 1, w/2, h/2);
	}
	
	public static void main(String[] args) {
		//1. Create the frame.
		final JFrame frame = new JFrame("Horloge");

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		
		final Gui h = new Gui();
		//h.setSize(500,500);
		//contentPane.add(h);
		

		//h.setTime(new java.util.Date());

		frame.setContentPane(h);

		//4. Size the frame.
		frame.setSize(500, 500);

		//5. Show it.
		frame.setVisible(true);	
		
		/*
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					h.setTime(new java.util.Date());
					frame.repaint();
					System.out.println("pouete");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();*/
		
	}
}

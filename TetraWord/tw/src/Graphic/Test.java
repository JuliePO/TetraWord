package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Test extends JPanel implements ActionListener, KeyListener {
	
	boolean move= true;
	int x;
	Timer time;
	ImageIcon img;
	
	public Test(){
		
		time= new Timer(5, this);
	
		time.start();
		addKeyListener(this);
		setFocusable(true);
		img = new ImageIcon("../texture/game/bonus/lapin.jpg");
	}
	
	@Override //JPanel
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//g.setColor(Color.RED);
		//g.fillRect(x, 20, 50, 30);
		img.paintIcon(this, g, x, 40);
	}

	@Override //KeyListener
	public void keyPressed(KeyEvent arg0) {
		int k= arg0.getKeyCode();
		
		switch( k ){
		
			case KeyEvent.VK_RIGHT:
				move = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//move= false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override //ActionListener
	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println("ActionPerformed");
		if( move )
			x = x + 1;
		
		repaint();
	}
	
	public static void main(String[] args){
		Test t= new Test();
		JFrame jf= new JFrame();
		jf.setTitle("test");
		jf.setSize(600, 400);
		jf.setVisible(true);
		jf.setLocation(200, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(t);
	}

}

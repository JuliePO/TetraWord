package Graphic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import utility.Player;

public class MouseListenerTetra implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Frame frame = ((Frame) e.getComponent());
		
		switch (frame.getPanelState()) {
		case 'g':
			clickAnagramme(frame, e.getX(), e.getY());
			break;

		default:
			break;
		}		
	}
	
	private void clickAnagramme(Frame frame, int x, int y){
		Player p;
		int insetsL;
		int insetsR;
		int insetsT = 120 + frame.getInsets().top;
		int insetsB = 670 + frame.getInsets().top;
		
		if(x < frame.getWidth()/2){
			p =  frame.getPlayer1();
			insetsL = (110 + frame.getInsets().left);
			insetsR = (385 + frame.getInsets().left);
		}
		else{
			p = frame.getPlayer2();
			insetsL = (640 + frame.getInsets().left);
			insetsR = (915 + frame.getInsets().left);
		}
		
		if(x > insetsL && x < insetsR && y > insetsT && y < insetsB){
			int xSquare = (x - insetsL)/25;
			int ySquare = (y - insetsT)/25;
			if(p.isSquareAt(xSquare, ySquare))
				System.out.println(p.getSquareAt(xSquare, ySquare).getChar());
		}
	}
}

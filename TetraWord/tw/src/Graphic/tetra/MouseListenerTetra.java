package Graphic.tetra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import GameState.Game;
import Graphic.Frame;
import utility.Player;

public class MouseListenerTetra implements MouseListener {
	
	private Game g1;
	private Game g2;

	public MouseListenerTetra(Game g1, Game g2) {
		this.g1 = g1;
		this.g2 = g2;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		PanelTetraWord tetra = ((PanelTetraWord) e.getComponent());
		
		clickAnagramme(tetra, e.getX(), e.getY());
		
	}
	
	private void clickAnagramme(PanelTetraWord tetra, int x, int y){
		Player p;
		int insetsL;
		int insetsR;
		int insetsT = 120 + tetra.getInsets().top;
		int insetsB = 670 + tetra.getInsets().top;
		
		if(x < tetra.getWidth()/2){
			p =  tetra.getPlayer1();
			insetsL = (110 + tetra.getInsets().left);
			insetsR = (385 + tetra.getInsets().left);
		}
		else{
			p = tetra.getPlayer2();
			insetsL = (640 + tetra.getInsets().left);
			insetsR = (915 + tetra.getInsets().left);
		}
		
		if(x > insetsL && x < insetsR && y > insetsT && y < insetsB){
			int xSquare = (x - insetsL)/25;
			int ySquare = (((y - insetsT)/-25)) + 21;
			
			System.out.println(ySquare);
			
			if(p.isSquareAt(xSquare, ySquare))
				System.out.println(p.getSquareAt(xSquare, ySquare).getChar());
		}
	}
}

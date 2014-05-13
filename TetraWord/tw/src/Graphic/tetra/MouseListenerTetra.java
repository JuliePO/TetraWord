package Graphic.tetra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import Graphic.Frame;
import utility.Player;

public class MouseListenerTetra implements MouseListener {
	
	String an= "";

	@Override
	public void mouseClicked(MouseEvent e) {
		
		PanelTetraWord tetra = ((PanelTetraWord) e.getComponent());
		
		clickAnagramme(tetra, e.getX(), e.getY());
		
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
			//(square.getY() - 21) * -25
			//System.out.println(ySquare);
			if(p.isSquareAt(xSquare, ySquare)){
				System.out.println( p.getSquareAt(xSquare, ySquare).getChar() );
				an += p.getSquareAt(xSquare, ySquare).getChar();
			}
			else{
				System.out.print(" Mot : " + an + "..." );
				if( p.getShape().getDico().contains(an) )
					System.out.println( "OK" );
				else
					System.out.println( "WRONG !" );
				
				System.out.print(">> Mots : ");
				p.getShape().getDico().findWith(an);
			}
				
		}
	}
}

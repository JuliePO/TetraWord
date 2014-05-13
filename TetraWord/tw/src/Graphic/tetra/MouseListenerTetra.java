package Graphic.tetra;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import GameState.Game;
import utility.Player;

public class MouseListenerTetra implements MouseListener {
	
	String an;

	public MouseListenerTetra(Game g1, Game g2) {
		String an= "";
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		PanelTetraWord tetra = ((PanelTetraWord) e.getComponent());
		
		clickAnagramme(tetra, e.getX(), e.getY());
		
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
	}
	
	private void clickAnagramme(PanelTetraWord tetra, int x, int y){
		Game g;
		int insetsL;
		int insetsR;
		int insetsT = 129 + tetra.getInsets().top;
		int insetsB = 669 + tetra.getInsets().top;
		
		if(x < tetra.getWidth()/2){
			g =  tetra.getGame1();
			insetsL = (109 + tetra.getInsets().left);
			insetsR = (379 + tetra.getInsets().left);
		}
		else{
			g = tetra.getGame2();
			insetsL = (644 + tetra.getInsets().left);
			insetsR = (914 + tetra.getInsets().left);
		}
		
		if(x > insetsL && x < insetsR && y > insetsT && y < insetsB){
			Player p = g.getPlayer();
			
			int xSquare = (x - insetsL)/27;
			int ySquare = (((y - insetsT)/-27))+19;
			
			System.out.println(ySquare);
			
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

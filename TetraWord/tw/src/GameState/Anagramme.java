package GameState;

import java.util.Vector;

import utility.Configuration;
import utility.Player;

public class Anagramme extends GameState {
	
	private Vector<Integer> fullLines;

	public Anagramme( Player p, Configuration c, Vector<Integer> l){
	
		super( p, c );
		fullLines= l;
		
		//b.colorLines(fullLines, "red");
		b.selectLines(fullLines);
	}
	
	@Override
	public GameState update(int tps) {
		
		//System.out.println("ANAGRAMME");	
	
		if( false ){
			//supprimer la ligne
			//deselectionner les lignes (fullLines)
			
			b.supprAt(0);
			b.colorLines(fullLines, "blue");
			return null;
		}
		
		return this;
	}


}

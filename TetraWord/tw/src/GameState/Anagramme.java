package GameState;

import java.util.Vector;

import utility.Configuration;
import utility.Player;
import utility.Square;

public class Anagramme extends GameState {
	
	private Vector<Integer> fullLines;
	private Vector<Square> currentWord;
	private boolean finish;

	public Anagramme( Player p, Configuration c, Vector<Integer> l){
	
		super( p, c );
		fullLines= l;
		
		//b.colorLines(fullLines, "red");
		b.selectLines(fullLines);
		
		currentWord = new Vector<>();
	}
	
	@Override
	public GameState update(int tps) {
		
		//System.out.println("ANAGRAMME");	
		if( finish )
			return null;
	
		if( false ){
			//supprimer la ligne
			//deselectionner les lignes (fullLines)
			
			b.supprAt(0);
			return null;
		}
		
		return this;
	}
	
	@Override
	public void input(int x, int y){
		if(j.isSquareAt(x, y)){
			Square selected = j.getSquareAt(x, y);
			
			switch (selected.getState()) {
			case 's':
				currentWord.add(selected);
				selected.setState('c');
				break;
			case 'c':
				currentWord.remove(selected);
				selected.setState('s');
				break;

			default:
				break;
			}
			
		}
	}
	
	@Override
	public void input(char input) {
				
		if(j.getInput("b") == input){
			
			String word = "";
			
			for(Square it : currentWord){
				word += it.getChar();
			}
			
			System.out.print(" Mot : " + word + "..." );
			
			if( j.getShape().getDico().contains(word) )
				System.out.println( "OK" );
			else
				System.out.println( "WRONG !" );
			
			System.out.print(">> Mots : ");
			j.getShape().getDico().findWith(word);
			b.supprLine(fullLines.elementAt(0));
			finish= true;
		}
	}


}

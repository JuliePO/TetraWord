package GameState;

import java.util.Vector;

import utility.Configuration;
import utility.Player;
import utility.Square;

public class Anagramme extends GameState {
	
	private Vector<Square> currentWord;
	private boolean finish;

	public Anagramme( Player p, Configuration c){
	
		super( p, c );
		
		b.setLinesTo(j.getLines(), 's');
		
		currentWord = new Vector<>();
	}
	
	@Override
	public GameState update(int tps) {
		
		//System.out.println("ANAGRAMME");	
		if( finish )
			return null;
		
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
			int currentLine= j.getLines().firstElement();
			
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
			b.setLineTo(currentLine, 'n');
			b.supprLine(currentLine);
			j.getLines().remove(0);
			
			finish= true;
		}
	}


}

package GameState;

import java.util.Vector;

import utility.Board;
import utility.Case;
import utility.Configuration;
import utility.Player;
import utility.Square;
import utility.IA.PlayerIA;

public class Anagramme extends GameState {
	
	private Vector<Square> currentWord;
	private boolean finish;
	private String bestWord;
	private int currentLine;

	public Anagramme( Player p, Configuration c, Game rival){
	
		super( p, c, rival );
		
		Board b = p.getBoard();
		
		b.setLinesTo(j.getLines(), 's');
		
		currentWord = new Vector<>();
		
		if(j instanceof PlayerIA){
			currentLine= j.getLines().firstElement();
			
			String strLine = j.getBoard().getLineString(currentLine);
			
			bestWord = j.getShape().getDico().bestWith( strLine );
		}
	}
	
	private void autoResolve(){
		
		if(bestWord.length() == 0){
			input(j.getInput("b"));
			return;
		}
		
		char tmp = bestWord.charAt(0);
		bestWord = bestWord.substring(1);
		
		for(Square cases : j.getBoard().getCases()){
			if(cases.getState() == 's' && cases.getChar() == tmp){
				cases.setState('c');
				currentWord.add(cases);
				return;
			}
		}
	}
	
	@Override
	public GameState update(int tps) {
		
		//System.out.println("ANAGRAMME");	
		if( finish ){
			rival.getPlayer().pause=false;
			j.setGame('t');
			return null;
		}
		
		if(j instanceof PlayerIA)
			autoResolve();
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
			
			Board b = j.getBoard();
			
			String word = "";
			int currentLine= j.getLines().firstElement();
			
			for(Square it : currentWord){
				word += it.getChar();
			}
			
			System.out.print(" Mot : " + word + "..." );
			
			boolean line = false;
			if( j.getShape().getDico().contains(word) ){
				System.out.println( "OK" );
				line = true;
			}
			else
				System.out.println( "WRONG !" );
			
			//j.getShape().getDico().findWith(j.getBoard().getLineString(currentLine));
			String strLine = j.getBoard().getLineString(currentLine);
			
			String best= j.getShape().getDico().bestWith( strLine );			
			System.out.println(">> Meilleur Mot : " + best);
			
			int rate= (word.length()*10) / best.length();
			
			b.setLineTo(currentLine, 'n');
			
			if( line && rate >= 4){
				b.supprLine(currentLine);
				j.getLines().remove(0);
			}
			
			finish= true;
		}
	}


}

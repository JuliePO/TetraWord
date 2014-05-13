package GameState;

import java.util.Vector;

import utility.Configuration;
import utility.Player;


public class Game {
	
	private GameState currentState;
	private Configuration config;
	Player j;
	Vector<GameState> stack;
	/*
	char state = 't';	// t : tetris, w : wordle, a : anagramme, m : menu
	char stateBack = 'g';
	*/
	
	public Game(Player j, Configuration config) {
		this.config = config;
		this.j=j;
		stack= new Vector<GameState>();
		GameState tetris= new Tetris(j, config); 
		currentState = tetris;
		stack.add( tetris );
	}
	
	public Player getPlayer(){
		return j;
	}

	public void update(int tps) {
		
		//currentState = currentState.update(tps);
		while( currentState == null ){
			stack.removeElementAt(stack.size()-1);
			currentState= stack.lastElement();
		}
		
		if( currentState != stack.lastElement() )
			stack.add(currentState);
		else	
			currentState= stack.lastElement().update(0);
	}
	
	public GameState getState(){
		return currentState;
	}
}

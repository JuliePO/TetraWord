package GameState;

import java.util.Vector;

import utility.Configuration;
import utility.Player;


public class Game {
	
	private GameState currentState;
	private Configuration config;
	Player j;
	Vector<GameState> stack;
	Game rival;
	/*
	char state = 't';	// t : tetris, w : wordle, a : anagramme, m : menu
	char stateBack = 'g';
	*/
	
	public Game(Player j, Configuration config) {
		this.config = config;
		this.j=j;
		stack= new Vector<GameState>();
	}
	
	public void startGame(Game rival){
		this.rival = rival;
		GameState tetris= new Tetris(j, config, rival); 
		currentState = tetris;
		stack.add( tetris );
	}
	
	public Player getPlayer(){
		return j;
	}

	public void update(int tps) {
		
		j.update();
		
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
	
	public boolean isEnd(){
		if(currentState == null)
			return false;
		
		return currentState.isEnd();
	}
	
	public void restart(){
		currentState.start();
		j.reset();
		currentState = new Tetris(j, config, rival);
	}
}

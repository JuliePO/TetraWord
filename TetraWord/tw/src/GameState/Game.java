package GameState;

import utility.Configuration;
import utility.Player;


public class Game {
	
	private GameState currentState;
	private Configuration config;
	Player j;
	/*
	char state = 't';	// t : tetris, w : wordle, a : anagramme, m : menu
	char stateBack = 'g';
	*/
	
	public Game(Player j, Configuration config) {
		this.config = config;
		this.j=j;
		currentState = new Tetris(j, config);
	}

	public void update(int tps) {
		
		currentState = currentState.update(tps);
	}
}

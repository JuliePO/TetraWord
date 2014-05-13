package GameState;

import utility.Board;
import utility.Configuration;
import utility.Player;


public abstract class GameState {
	
	protected Board b;
	protected Player j;
	protected Configuration config;
	
	public GameState(Player pl, Configuration cg){
		j= pl;
		b= j.getBoardTemp();
		config= cg;
	}

	
	public GameState update(int tps){
		return this;
	}
	
		
}

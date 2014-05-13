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
		b= j.getBoard();
		config= cg;
	}

	
	public GameState update(int tps){
		return this;
	}
	
	public void input(char input){}
	
	public void input(int x, int y){}
		
}

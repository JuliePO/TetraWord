package GameState;

import utility.Board;
import utility.Configuration;
import utility.Player;


public abstract class GameState {
	
	//protected Board b;
	protected Player j;
	protected Configuration config;
	
	protected boolean end;
	
	protected Game rival;
	
	
	public GameState(Player pl, Configuration cg, Game rival){
		j= pl;
		//b= j.getBoard();
		config= cg;
		end = false;
		this.rival=rival;
	}

	
	public GameState update(int tps){
		return this;
	}
	
	public void input(char input){}
	
	public void input(int x, int y){}
	
	public boolean isEnd(){
		return end;
	}
	
	public void start(){
		end =false;
	}
		
}

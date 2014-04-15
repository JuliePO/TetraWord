package GameState;


public class Game implements GameState{
	
	private GameState currentState;
	/*
	char state = 't';	// t : tetris, w : wordle, a : anagramme, m : menu
	char stateBack = 'g';
	*/
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		currentState = new Tetris();
		
	}

	@Override
	public void update() {
		
		currentState.update();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void input() {
		// TODO Auto-generated method stub
		
	}

}

package GameState;


public interface GameState {
	public GameState update(int tps);
	public void draw();
	public void input();	
}

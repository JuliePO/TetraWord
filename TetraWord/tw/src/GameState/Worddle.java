package GameState;

import utility.Configuration;
import utility.Player;

public class Worddle extends GameState {


	public Worddle(Player pl, Configuration cg, Game rival) {
		super(pl, cg, rival);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GameState update(int tps) {
		return this;
	}

}

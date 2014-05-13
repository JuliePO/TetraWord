package utility.Bonus;

import utility.Player;

public class MalusScore extends BonusTetra {

	public MalusScore(Player owner, Player rival) {
		super("malus", owner, rival, 1);
	}

	@Override
	public void apply() {
		rival.increaseScore(-500);
	}

	@Override
	public void remove() {		
	}

}

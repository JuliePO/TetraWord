package utility.Bonus;

import utility.Player;

public class MalusScore extends BonusTetra {

	public MalusScore(int tx, int ty, Player owner, Player rival) {
		super(tx, ty, "malus", owner, rival, 1);
	}

	@Override
	public void apply() {
		rival.increaseScore(-500);
	}

	@Override
	public void remove() {		
	}

}

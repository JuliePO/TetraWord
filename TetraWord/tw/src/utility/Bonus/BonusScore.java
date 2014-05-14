package utility.Bonus;

import utility.Player;

public class BonusScore extends BonusTetra {

	public BonusScore(int tx, int ty, Player owner, Player rival) {
		super(tx, ty, "bonus", owner, rival, 1);
	}

	@Override
	public void apply() {
		owner.increaseScore(1000);
	}

	@Override
	public void remove() {
	}

}

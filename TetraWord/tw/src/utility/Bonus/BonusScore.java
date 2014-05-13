package utility.Bonus;

import utility.Player;

public class BonusScore extends BonusTetra {

	public BonusScore(Player owner, Player rival) {
		super("bonus", owner, rival, 1);
	}

	@Override
	public void apply() {
		owner.increaseScore(1000);
	}

	@Override
	public void remove() {
	}

}

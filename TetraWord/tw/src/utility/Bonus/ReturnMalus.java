package utility.Bonus;

import utility.Player;

public class ReturnMalus extends BonusTetra{

	public ReturnMalus(int tx, int ty, Player owner, Player rival) {
		super(tx, ty, "return", owner, rival, 170);
	}

	@Override
	public void apply() {
		rival.getBoard().invert = !rival.getBoard().invert;
	}

	@Override
	public void remove() {
		rival.getBoard().invert = !rival.getBoard().invert;
	}

}

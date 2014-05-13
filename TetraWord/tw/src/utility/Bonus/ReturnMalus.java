package utility.Bonus;

import utility.Player;

public class ReturnMalus extends BonusTetra{

	public ReturnMalus(Player owner, Player rival) {
		super("return", owner, rival, 170);
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

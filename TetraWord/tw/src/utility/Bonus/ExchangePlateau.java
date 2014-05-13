package utility.Bonus;

import utility.Board;
import utility.Player;
import utility.Shape;

public class ExchangePlateau extends BonusTetra{

	public ExchangePlateau(Player owner, Player rival) {
		super("exchange", owner, rival, 1);
	}

	@Override
	public void apply() {
				
		Shape currentShape = owner.getShape();
		owner.setShape(rival.getShape());
		rival.setShape(currentShape);
	    
	    BonusTetra[] listBonus = owner.getBonus();
	    owner.setBonus(rival.getBonus());
	    rival.setBonus(listBonus);
	    
	    int nbBonus = owner.getNbBonus();
	    owner.setNbBonus(rival.getNbBonus());
	    rival.setNbBonus(nbBonus);

	    Board board = owner.getBoard();
	    owner.setBoard(rival.getBoard());
	    rival.setBoard(board);
	    
	    int score = owner.getScore();
	    owner.setScore(rival.getScore());
	    rival.setScore(score);
	}

	@Override
	public void remove() {
	}

}

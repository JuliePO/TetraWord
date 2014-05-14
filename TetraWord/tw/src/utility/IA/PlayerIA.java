package utility.IA;

import java.util.Random;

import utility.Dictionary;
import utility.Player;

public class PlayerIA extends Player {
	
	IAtetris iaTetris;
	ShapePosition pattern;

	public PlayerIA(int number, String name, String avatar, Random r) {
		super(number, name, avatar, r);
		iaTetris = new IAtetris();
	}
	
	@Override
	public void newShape(Dictionary dico) {
		super.newShape(dico);
		iaTetris.setShape(currentShape);
		pattern = iaTetris.getShapeIA();
		
		System.out.println("New Shape");
	}
	
	@Override
	public void update() {
		super.update();
		
		if(pattern.rotate > 0 && currentShape.getSquares()[0].y < 16){
			currentShape.rotate();
			--pattern.rotate;
		}
		else if(pattern.xPosition > 0){
			currentShape.goLeft();
			--pattern.xPosition;
		}
		else if(pattern.xPosition < 0){
			currentShape.goRight();
			++pattern.xPosition;
		}
	}

}

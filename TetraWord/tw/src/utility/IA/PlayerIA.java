package utility.IA;

import java.util.Random;

import utility.Dictionary;
import utility.Player;

public class PlayerIA extends Player {
	
	IAtetris iaTetris;
	ShapePosition pattern;
	boolean active;

	public PlayerIA(int number, String name, String avatar) {
		super(number, name, avatar);
		iaTetris = new IAtetris();
		active = true;
	}
	
	public void desactive(){
		active = false;
	}
	
	@Override
	public void newShape(Dictionary dico, int next) {
		super.newShape(dico, next);
		if(active){
			iaTetris.setShape(currentShape);
			pattern = iaTetris.getShapeIA();
		}
	}
	
	@Override
	public void update() {
		super.update();
		if(active){
			if(pattern.rotate > 0 ){
				System.out.println("rotate" + ","+ pattern.rotate);
				currentShape.rotate();
				--pattern.rotate;
			}
			else if(pattern.xPosition > 0){
				System.out.println("left," + pattern.xPosition);
				currentShape.goLeft();
				--pattern.xPosition;
			}
			else if(pattern.xPosition < 0){
				System.out.println("right," + pattern.xPosition);
				currentShape.goRight();
				++pattern.xPosition;
			}
		}
	}

}

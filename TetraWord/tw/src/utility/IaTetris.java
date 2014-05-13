package utility;

import java.util.Comparator;
import java.util.TreeSet;

class ShapePosition {
	int rotate; //compris entre 0 et 3
	int xPosition;
	int value;
	
	public ShapePosition(int rotate, int xPosition, int value) {
		this.rotate=rotate;
		this.xPosition=xPosition;
		this.value=value;
	}

}

class SolveTreeComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
		
		if(((ShapePosition)o1).value == ((ShapePosition)o2).value)
			if(((ShapePosition)o1).xPosition < ((ShapePosition)o2).xPosition)
				return 1;
			else
				return -1;
		
		else if(((ShapePosition)o1).value > ((ShapePosition)o2).value)
			return 1;
		else
			return -1;
	}
}

public class IaTetris {
	
	
	
	public static void main(String[] args) {
		
		//arbre de resolution
		TreeSet<ShapePosition> solveTree = new TreeSet<>(new SolveTreeComparator());

		solveTree.add(new ShapePosition(0, 0, 1));
		solveTree.add(new ShapePosition(1, 1, 2));
		solveTree.add(new ShapePosition(0, 0, 2));
		
		System.out.println(solveTree.last().rotate);
		
		Board b = new Board(11, 22);
		
		Shape shape = new Shape('T', new Dictionary("../franch", null), b);
		
		//on remplit l'arbre
		for(int i = 0; i < 4; ++i){
			for(int j = 0; j < 11; ++j){
				
					
			}
			shape.rotate();
		}
	}

}

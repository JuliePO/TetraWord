package utility;

import java.util.Comparator;
import java.util.Random;
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
	
	Shape shape;
	
	public IaTetris(Shape shape) {
		this.shape = shape;
		
		createSolveTree();
	}
	
	private void createSolveTree(){

		//arbre de resolution
		TreeSet<ShapePosition> solveTree = new TreeSet<>(new SolveTreeComparator());
		/*solveTree.add(new ShapePosition(0, 0, 1));
		solveTree.add(new ShapePosition(1, 1, 2));
		solveTree.add(new ShapePosition(0, 0, 2));
		
		System.out.println(solveTree.last().rotate);
		*/
		boolean[] fieal = shape.copyField();
		Case[][] shapeS = shape.copyShape();
		
		//on remplit l'arbre
		for(int i = 0; i < 4; ++i){
			System.out.println(shapeS[i][0].x +","+ shapeS[i][0].y +" "+shapeS[i][1].x +","+ shapeS[i][1].y +" "+shapeS[i][2].x +","+ shapeS[i][2].y +" "+shapeS[i][3].x +","+ shapeS[i][3].y);
			for(int j = 0; j < 11; ++j){
				
					
			}
		}
	}
	
	public static void main(String[] args) {
		Shape shape = new Shape('L', new Dictionary("../french.txt", new Random()), new Board());
		IaTetris ia = new IaTetris(shape);

		
	}

}

package utility.IA;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

import utility.Board;
import utility.Case;
import utility.Dictionary;
import utility.Shape;

class ShapePosition {
	int rotate; //compris entre 0 et 3
	int xPosition;
	int yPosition;
	int value;
	
	public ShapePosition(int rotate, int xPosition, int yPosition, int value) {
		this.rotate=rotate;
		this.xPosition=xPosition;
		this.yPosition=yPosition;
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
	
		boolean[] field = shape.copyField();
		Case[][] shapeS = shape.copyShape();
		
		//on remplit l'arbre
		for(int i = 0; i < 4; ++i){
			
			ShapeIA shape = new ShapeIA(shapeS[i], field);
			shape.setShape();
			
		//	System.out.println(shapeS[i][0].x +","+ shapeS[i][0].y +" "+shapeS[i][1].x +","+ shapeS[i][1].y +" "+shapeS[i][2].x +","+ shapeS[i][2].y +" "+shapeS[i][3].x +","+ shapeS[i][3].y);
			
			for(int j = 0; j < 10; ++j){
				int k = 0;
				while(!shape.collideDown()){
					shape.goDown();
					++k;
				}
				solveTree.add(new ShapePosition(i, j, k, rateField(shape.getCopyField())));
				shape.resetY();
			}
		}
	}
	
	int rateField(boolean[] field){
		int result = 0;
		
		//on parcourt toute la grille
		for(int y = 0; y < 20; ++y){
			
			boolean line = true;
			
			for(int x = 0; x < 10; ++x){
				
				if(!field[x+10*y]){
					//si la case est vide, il n'y a pas de ligne
					line = false;
					//et si la case du dessus est pleine, il y a un trou
					if(field[x+10*(y+1)])
						result -= 1;
				}
				else
					if(y == 19)
						result -= 2;
			}
			
			if(line)
				result +=3;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Shape shape = new Shape(6, new Dictionary("../french.txt", new Random()), new Board());
		IaTetris ia = new IaTetris(shape);

		
	}

}

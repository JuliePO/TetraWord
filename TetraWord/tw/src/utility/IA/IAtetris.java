package utility.IA;

import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

import utility.Board;
import utility.Case;
import utility.Dictionary;
import utility.Shape;




public class IAtetris {
	
	class SolveTreeComparator implements Comparator {
		@Override
		public int compare(Object o1, Object o2) {
			
			if(((ShapePosition)o1).value == ((ShapePosition)o2).value)
				if(((ShapePosition)o1).yPosition <  ((ShapePosition)o2).yPosition)
					return 1;
				else
					return -1;
			
			else if(((ShapePosition)o1).value > ((ShapePosition)o2).value)
				return 1;
			else
				return -1;
		}
	}
	
	Shape shape;
	TreeSet<ShapePosition> solveTree;
	
	public IAtetris() {
	}
	
	public IAtetris(Shape shape) {
		this.shape = shape;
		
		createSolveTree();
	}
	
	public void setShape(Shape shape){
		this.shape = shape;
		createSolveTree();
	}
	
	private void createSolveTree(){

		//arbre de resolution
		solveTree = new TreeSet<>(new SolveTreeComparator());
	
		boolean[] field = shape.copyField();
		Case[][] shapeS = shape.copyShape();
		
		//on remplit l'arbre
		for(int rotate = 0; rotate < 4; ++rotate){
			
			ShapeIA shapeIA = new ShapeIA(shapeS[rotate], field);
			int xPosition = shapeIA.setShape();
			
		//	System.out.println(shapeIAS[i][0].x +","+ shapeS[i][0].y +" "+shapeS[i][1].x +","+ shapeS[i][1].y +" "+shapeS[i][2].x +","+ shapeS[i][2].y +" "+shapeS[i][3].x +","+ shapeS[i][3].y);
			
			for(int j = 0; j < 10; ++j){
				int yPosition = 0;
				while(!shapeIA.collideDown()){
					shapeIA.goDown();
					++yPosition;
				}
				solveTree.add(new ShapePosition(rotate, xPosition, yPosition, rateField(shapeIA.getCopyField())));
				shapeIA.resetY();
				++xPosition;
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
					if(y < 19)
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
	
	public ShapePosition getShapeIA(){
		return solveTree.last();
	}
	
	public static void main(String[] args) {
		Shape shape = new Shape(6, new Dictionary("../french.txt", new Random()), new Board());
		IAtetris ia = new IAtetris(shape);

		
	}

}

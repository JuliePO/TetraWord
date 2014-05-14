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
			
			else if(((ShapePosition)o1).value < ((ShapePosition)o2).value)
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
		
for (int i = 0; i < 4; i++) {
	printSquare(shapeS[i]);
	
}
		
		cleanField(shapeS, field);
		
		//on remplit l'arbre
		for(int rotate = 0; rotate < 4; ++rotate){
			
			ShapeIA shapeIA = new ShapeIA(shapeS[rotate], field);
			int xPosition = shapeIA.setShape();
			
		//	System.out.println(shapeIAS[i][0].x +","+ shapeS[i][0].y +" "+shapeS[i][1].x +","+ shapeS[i][1].y +" "+shapeS[i][2].x +","+ shapeS[i][2].y +" "+shapeS[i][3].x +","+ shapeS[i][3].y);
			
			while(!shapeIA.collideRight()){
				int yPosition = 0;
				while(!shapeIA.collideDown()){
					shapeIA.goDown();
					++yPosition;
				}
			//	System.out.println(yPosition);
				solveTree.add(new ShapePosition(rotate, xPosition, yPosition, rateField(shapeIA.getCopyField()), shapeIA.getCopyField()));
				shapeIA.resetY();
				shapeIA.goRight();
				++xPosition;
			}
			//System.out.println("stop");
		}
	}
	
	int rateField(boolean[] field){
		int result = 0;
		
//printField(field);
		
		//on parcourt toute la grille
		for(int y = 0; y < 20; ++y){
			
			boolean line = true;
			
			for(int x = 0; x < 10; ++x){
				
				if(!field[x+10*y]){
					//si la case est vide, il n'y a pas de ligne
					line = false;
					//et si la case du dessus est pleine, il y a un trou
					if(y < 19)
						if(field[x+10*(y+1)]){
							result -= 1;
//System.out.println("trou");
						}
				}
				//perdu
				else
					if(y == 19){
						result -= 2;
//System.out.println("perdu");
					}
			}
			//ligne
			if(line){
				result +=3;
//System.out.println("line");
			}
		}
//System.out.println(result);
		return result;
	}
	
	public ShapePosition getShapeIA(){
		String tp = "";
		
		for(ShapePosition it : solveTree)
			tp += it.value + ","+it.yPosition+" ";
		
System.out.println(tp);
System.out.println(solveTree.first().value +","+solveTree.first().yPosition);
printField(solveTree.first().field);
		
		return solveTree.first();
	}
	
	void cleanField(Case[][] cases, boolean[] field){
		for(int i = 0; i < 4; ++i){
			for(int j = 0; j < 4; ++j){
				field[cases[i][j].x+10*cases[i][j].y] = false;
			}
		}
	}
//TMP	
	void printField(boolean[] field){
		for(int i = 0; i < 20; ++i){
			for(int j = 0; j < 10; ++j){
				if(field[j+10*(19-i)])
					System.out.print( "X ");
				else
					System.out.print("O ");
			}
			System.out.println("");
		}
	}
//TMP	
	void printSquare(Case[] cases){
		for(int i = 0; i < 20; ++i){
			for(int j = 0; j < 10; ++j){
				char tmp = 'O';
				for(int k = 0; k < 4; ++k)
					if(cases[k].x == j && cases[k].y == i)
						tmp = 'X';
				System.out.print(""+tmp);
			}
			System.out.println("");
		}

		System.out.println("");
		System.out.println("");
		
	}
	
	public static void main(String[] args) {
		Shape shape = new Shape(6, new Dictionary("../french.txt", new Random()), new Board());
		IAtetris ia = new IAtetris(shape);

		
	}

}

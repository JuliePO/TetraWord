package utility.IA;

import utility.Case;

class Pos{
	protected int x;
	protected int y;
	
	Pos(int x, int y){
		this.x=x;
		this.y=y;
	}
}

public class ShapeIA {
	
	protected Pos[] pos;
	private boolean[] field;
	private int[] yOrigine;
	
	
	public ShapeIA(Case[] cases, boolean[] field){
		
		this.field=field;
		
		yOrigine = new int[4];
		pos = new Pos[4];
		for(int i = 0; i < 4; ++i){
			pos[i] = new Pos(cases[i].x, cases[i].y);
			yOrigine[i] = cases[i].y;
		}		
	}
	
	public ShapeIA(ShapeIA shape){
		this.field = shape.field;
		
		for(int i =0; i < 4; ++i){
			this.pos[i].x = shape.pos[i].x;
			this.pos[i].y = shape.pos[i].y;			
		}
		
	}
	
	public void setShape(){
		while(!collideLeft()){
			goLeft();
		}
	}

	public boolean collideLeft(){
		return !(pos[0].x >0 && pos[1].x >0 && pos[2].x >0 && pos[3].x >0);
	}
	
	public boolean collideRight(){
		return !(pos[0].x <8 && pos[1].x <8 && pos[2].x <8 && pos[3].x <8);
	}
	
	public boolean collideDown(){
		for(int i = 0; i < 4; ++i)
			if(pos[i].y < 1 || field[pos[i].x + pos[i].y*10 -1])
				return true;
		return false;
	}
	
	public boolean isFree(int x, int y){
		if(x < 0 || x > 9 || y < 0 || y > 19 || field[x + 10*y])
			return false;
		return true;
	}
	
	public void goLeft()
	{
		for(int i = 0; i < 4; ++i)
			--pos[i].x;
	}
	
	public void goRight()
	{
		for(int i = 0; i < 4; ++i)
			++pos[i].x;
	}
	
	public void goDown(){
		for(int i = 0; i < 4; ++i)
			--pos[i].y;
	}
	
	public boolean[] getCopyField(){
		
		boolean[] result = new boolean[200];
		
		for(int i = 0; i < 200; ++i){
			result[i] = field[i];
		}
		
		for(int i = 0; i < 4 ; ++i){
			result[pos[i].x + 10*pos[i].y] = true;
		}
		
		return result;
	}
	
	public void resetY(){
		for(int i = 0; i < 4; ++i){
			pos[i].y = yOrigine[i];
		}
	}
}

package utility.Bonus;

import java.util.Random;

import utility.Board;
import utility.Case;
import utility.Player;

public abstract class BonusTetra  {
	
	private String name;
	protected Player owner;
	protected Player rival;
	private int ttl;
	private int x;
	private int y;
	
	public BonusTetra(int px, int py, String name, Player owner, Player rival, int duree) {
		
		this.setX(px);
		this.setY(py);
		
		this.name=name;
		this.owner = owner;
		this.rival = rival;
		this.ttl=duree;
	}
	
	
	
	public int getTtl(){
		return ttl;
	}
	
	abstract public void apply();
	
	abstract public void remove();
	
	public void update(){
		--ttl;
	}
	
	public String getName(){
		return name;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
}

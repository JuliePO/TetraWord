package utility.Bonus;

import utility.Player;

public abstract class BonusTetra {
	
	private String name;
	protected Player owner;
	protected Player rival;
	private int ttl;
	
	public BonusTetra(String name, Player owner, Player rival, int duree) {
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
}

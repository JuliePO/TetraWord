package utility;

public class Chrono {
	
	int currTime;
	int goal;
	
	public Chrono(int t){
		currTime= t;
		goal= t;
	}
	
	public Chrono(){
		this(0);
	}
	
	public void incr(){
		++currTime;
	}
	
	public void decr(){
		if( currTime > 0 )
			--currTime;
	}
	
	public int getValue(){
		return currTime;
	}
	
	public void reset(){
		currTime = goal;
	}
	
	public boolean isFinished(){
		if( currTime == 0 )
			return true;
		return false;
	}

}

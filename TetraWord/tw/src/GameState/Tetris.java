package GameState;

import java.util.Random;
import java.util.Vector;

import utility.Chrono;
import utility.Configuration;
import utility.Player;

public class Tetris extends GameState {
	

	private int speed;
	boolean[] fd;
	int t=0;
	boolean generate;
	private Chrono ch;

	public Tetris(Player j, Configuration config) {
		super( j, config );
		speed=30;
		
		
		b= j.getBoard();
		fd= b.getField();
		ch = new Chrono(4);
		j.newShape( config.getDico() );
	}
	
	public boolean isOver(){
		
		if( j.getShape().isTop() )
			return true;
		return false;
	}
	
	@Override
	public GameState update(int tps) {
		
		if(!j.pause && !end){
			
            // Calculate nextState ------------------------   
            for( int i= 0; i < b.size(); ++i )
                if( b.elmtAt(i).getNextState() == '?' )
                    b.elmtAt(i).becoming(j); 
            // --------------------------------------------
            
            //UPDATE------------------------  
            for( int i= 0; i < b.size(); ++i ){
                switch( b.elmtAt(i).getNextState() ){
                    case 's':  // b.elmtAt(i).setBusy(); 
                            break;
                
                case 'f':   b.elmtAt(i).fall();
                            break;
                            
                default : System.err.println( "Error : nextState of a Square is " +  b.elmtAt(i).getNextState() );
                            
                }
                
                b.elmtAt(i).setNextState('?');
            }               
            // ** DYNAMIC FREE & ALLOC
                

	        if( generate ){
	        	j.newShape( config.getDico() );
	        	ch.reset();
	        	generate = false;
	        }
	        
	        if( j.getShape().isArrived() ){
	        	
	        	if( isOver() )
	        		end=true;
	            
	        	Vector<Integer> lines= b.hasLines();
	            if( !lines.isEmpty() ){
	            	
	            	System.out.println("lignes pleines: " + lines);
	            	j.setFullLines(lines);
	            	return new Anagramme( j, config );
	            }
	            
	            ch.decr();
	            if( ch.isFinished() )
	            	generate = true;
	        }    
		}

        return this;
	}
	
	@Override
	public void input(char input) {
		super.input(input);
		
		if(input == j.getInput("up"))
			j.getShape().rotate();
		else if(input == j.getInput("left"))
			j.getShape().goLeft();
		else if(input == j.getInput("right"))
			j.getShape().goRight();
		else if(input == j.getInput("b"))
			j.useBonus();
	}
}
	

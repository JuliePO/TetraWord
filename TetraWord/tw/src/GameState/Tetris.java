package GameState;

import java.util.Random;
import java.util.Vector;

import utility.Chrono;
import utility.Configuration;
import utility.Player;

public class Tetris extends GameState {
	
	boolean[] fd;
	int t=0;
	boolean generate;
	private Chrono ch;
	private Chrono fall;
	private int g;

	public Tetris(Player j, Configuration config) {
		super( j, config );		
		
		g= 4;
		b= j.getBoard();
		fd= b.getField();
		ch = new Chrono(4);
		fall = new Chrono(g);
		
		j.newShape( config.getDico() );
	}
	
	public boolean isOver(){
		
		if( j.getShape().isTop() )
			return true;
		return false;
	}
	
	@Override
	public GameState update(int tps) {
		
		if(!j.pause){
			
			if( j.getShape().isArrived() ){
				
				fall.setGoal(g);
	        	
	        	//if( isOver() )
	        		//System.exit(0);
	        		//System.out.println("GAME OVER * GAME OVER * GAME OVER * GAME OVER *");
	            
	        	int lines= b.nbLines() ;
	        	System.out.println("NB Lines : " + lines);
	            if( lines > 0 ){
	            	
	            	j.setFullLines( b.hasLines() );
	            	return new Anagramme( j, config );
	            }
	            
	            ch.decr();
	            if( ch.isFinished() )
	            	generate = true;
	            
	        }
			
			fall.decr();
			if( fall.isFinished() ){
				fall.reset();
	            // Calculate nextState ------------------------   
	            for( int i= 0; i < b.size(); ++i )
	                if( b.elmtAt(i).getNextState() == '?' )
	                    b.elmtAt(i).becoming(j); 
	            // --------------------------------------------
	            
				
	            //UPDATE------------------------  
	            for( int i= 0; i < b.size(); ++i ){
	                switch( b.elmtAt(i).getNextState() ){
	                    case 's':   b.elmtAt(i).setBusy(); 
	                            	break;
	                
		                case 'f':   b.elmtAt(i).fall();
		                            break;
		                            
		                default : System.err.println( "Error : nextState of a Square is " +  b.elmtAt(i).getNextState() );
	                            
	                }
	                
	                b.elmtAt(i).setNextState('?');
	            }
	            
			}
            // ** DYNAMIC FREE & ALLOC
               
	        if( generate ){
	        	j.newShape( config.getDico() );
	        	ch.reset();
	        	generate = false;
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
		else if(input == j.getInput("down"))
			fall.setGoal(1);
	}
}
	

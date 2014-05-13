package GameState;

import utility.Board;
import utility.Configuration;
import utility.Player;

public class Tetris implements GameState {
	
	private Player j;
	private Configuration config;
	private int speed;
	Board b;
	boolean[] fd;
	int t=0;

	public Tetris(Player j, Configuration config) {
		this.j=j;
		this.config=config;
		speed=30;
		
		 b= j.getBoard();
		 fd= b.getField();	      
		 j.newShape( 'T', config.getDico() );
	}
	
	@Override
	public GameState update(int tps) {
		
		if(!j.pause){
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
	        // ** DYNAMIC FREE
	        
	        
	        // ** DYNAMIC FREE & ALLOC
	        
	        if( j.getShape().isArrived() ){
	            j.newShape( 'T', config.getDico() );
	            
	        }
		}
        
        return this;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void input() {
		// TODO Auto-generated method stub

	}

}

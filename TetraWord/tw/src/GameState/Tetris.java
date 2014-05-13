package GameState;

import utility.Board;
import utility.Configuration;
import utility.Player;

public class Tetris extends GameState {
	

	private int speed;
	boolean[] fd;
	int t=0;
	boolean generate;

	public Tetris(Player j, Configuration config) {
		super( j, config );
		speed=30;
		


		b= j.getBoard();
		fd= b.getField();	      
		j.newShape( 'T', config.getDico() );
	}
	
	public boolean isOver(){
		
		if( j.getShape().isTop() )
			return true;
		return false;
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
            // ** DYNAMIC FREE & ALLOC
                
	        if( generate ){
	        	j.newShape( 'I', config.getDico() );
	        	generate = false;
	        }
        
	        if( j.getShape().isArrived() ){
	        	            
	            if( !b.hasLines().isEmpty() ){
	            	
	            	System.out.println(b.hasLines());
	            	return new Anagramme( j, config, b.hasLines() );
	            }
	            
	            generate = true;
	        }        
		}

        return this;
	}
}
	

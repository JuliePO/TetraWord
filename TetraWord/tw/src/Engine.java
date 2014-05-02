import utility.*; //Sale
import GameState.*; //Sale
import Graphic.*; //Sale




public class Engine
{
    // instance variables - replace the example below with your own
    private Dictionary dicFile;
    
    /*private Start start = new Start();
    private Game game = new Game();
    private Option option = new Option();
    private char state = 's'; // s : start ; g : game ; o : option ;
  	*/
    
    private GameState currentState;
    
    void loadDictionary(String lang){
        
        if( lang.toLowerCase().equals("french") )
            dicFile = new Dictionary("../french.txt");
        
    }
    
    public void update(){
    	currentState.update();
    }
    
    public static void main(String[] args)
    {
        Engine e= new Engine();
        e.loadDictionary("french");
        
        for( int i= 0; i < 336531; ++i )
            e.dicFile.printNextWord();
            
        System.out.println(" -- END -- " );
        
    }
    
    public static void time(String[] args) {
        
        // * DECLARATIONS
        
        int speed= 30; //Entre 25 et 42 | 44 MAX
        
        boolean run= true;
        long beforeTime= 0, deltaTime, fps = 40;
        beforeTime = System.currentTimeMillis();
        System.out.println( beforeTime );
        
        // I N I T --------------------------
        int nb= 8;
        Square[] squares= new Square[nb];
        Dictionary dico;        
        Player J1= new Player("georges", "ninja");
        Player J2 = new Player("louis", "panda");
        Board b= J1.getBoardTemp();
        boolean[] fd= b.getField();

        if( args.length == 0 )
            dico= new Dictionary("../french.txt");
        else
            dico= new Dictionary("../../french.txt");

        //Creation des blocs
        squares[0] = new Square( 2, 19, dico.pickLetter(), b , "blue");
        squares[1] = new Square( 3, 19, dico.pickLetter(), b , "blue");
        squares[2] = new Square( 3, 18, dico.pickLetter(), b , "blue");
        squares[3] = new Square( 4, 19, dico.pickLetter(), b , "blue");
        
        //Mise en relation avec les blocs adjacents
        squares[0].setNeighbour( null, null, squares[1], null ); 
        squares[1].setNeighbour( null, squares[0], squares[3], squares[2] );
        squares[2].setNeighbour( squares[1], null, null, null );
        squares[3].setNeighbour( null, squares[1], null, null );
        
        //-----------------------------------------
        squares[4] = new Square( 1, 13, dico.pickLetter(), b , "green");
        squares[5] = new Square( 2, 13, dico.pickLetter(), b , "green");
        squares[6] = new Square( 3, 13, dico.pickLetter(), b , "green");
        squares[7] = new Square( 4, 13, dico.pickLetter(), b , "green");
        
        for(int i= 0; i < nb; ++i )
            b.addCase( squares[i] );
            
        squares= null;
        
        Frame tmp = new Frame(J1, J2);
        
        int ko= 0;
        
        // ** LOOP
        while(tmp.getPanelState() != 'e'){
            
            beforeTime= System.currentTimeMillis();
            ++ko;
            if( ko > 900 )
                ko = 0;
            
            // ** ERASE DISPLAY
            
            
            // ** UPDATES        
            tmp.update();
            
            if ( ko%(45-speed) == 0 ){
                
                // Calculate nextState ------------------------   
                for( int i= 0; i < nb; ++i )  
                    if( b.elmtAt(i).getNextState() == '?' )
                        b.elmtAt(i).becoming(J1);    
                // --------------------------------------------
                //Reset  
                b.freeAll();
                //UPDATE------------------------      
                for( int i= 0; i < nb; ++i ){
                    switch( b.elmtAt(i).getNextState() ){
                        case 's':   b.elmtAt(i).setBusy(); 
                                    break;
                        
                        case 'f':   b.elmtAt(i).fall();
                                    b.elmtAt(i).setBusy();
                                    break;
                                    
                        default : System.err.println( "Error : nextState of a Square is " +  b.elmtAt(i).getNextState() );
                                    
                    }
                    
                    b.elmtAt(i).setNextState('?');
                }
            }
            
            J2.increaseScore(1);
            
            // ** DYNAMIC FREE
            
            
            // ** DYNAMIC FREE & ALLOC
            
            
            deltaTime = System.currentTimeMillis() - beforeTime;
            System.out.println("D: " + deltaTime);
            try
            {
                if( deltaTime < fps ){
                    System.out.print(">> ");
                    System.out.println(fps - deltaTime);
                    Thread.sleep(fps - deltaTime);
                    
                }
            }
            catch(InterruptedException ie)
            {
               System.err.println("Error: Thread exception!");
            }
            //beforeTime = System.currentTimeMillis();
            
            
           
            //System.out.println(p.getScore());
        }        
        
        tmp.dispose();
        System.exit(0);
       
    }
}

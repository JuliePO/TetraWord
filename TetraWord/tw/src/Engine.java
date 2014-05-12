import utility.*; //Sale
import GameState.*; //Sale
import Graphic.*; //Sale




public class Engine
{
    // instance variables - replace the example below with your own
    

    private GameState currentState;
    
    private Player p1;
    private Player p2;
    
    int nbP; 
    
    //private Start start = new Start();
    private Game game1;
    private Game game2;
    //private Option option = new Option();
    private char state;
    
    Engine(Player p1, Player p2, Configuration config){

    	game1 = new Game(p1, config);
    	game2 = new Game(p2, config);
    }
    
    public void update(int tps){
        game1.update(tps);
        game2.update(tps);
    }
    
    public static void main(String[] args) {
// * DECLARATIONS

        long beforeTime= 0, deltaTime, fps = 60;
        beforeTime = System.currentTimeMillis();
        System.out.println( beforeTime );
        
        // I N I T --------------------------
        Configuration config = new Configuration();
              
        Player J1= new Player(1, "georges", "ninja");
        Player J2 = new Player(2, "louis", "panda");
        
       // Tetris tetris = new Tetris(J1, config);
        
        Engine engine = new Engine(J1, J2, config);

        Frame tmp = new Frame(J1, J2, config);
        
        int tps= 0;
        
        // ** LOOP
        while(tmp.getPanelState() != 'e'){
            
            beforeTime= System.currentTimeMillis();
            ++tps;
            
            if( tps%100 == 0 ){
                /*System.out.println( "Erase case" );
                b.supprAt(3);
                --nb;*/
            }
            if( tps > 900 )
                tps = 0;
            
            
            // ** ERASE DISPLAY
            
            
            // ** UPDATES        
            tmp.update();
            
            //tetra
            if(tmp.getPanelState() == 'g'){
	           engine.update(tps);
            }
            
            deltaTime = System.currentTimeMillis() - beforeTime;
            //System.out.println("D: " + deltaTime);
            try
            {
                if( deltaTime < fps ){
                    //System.out.print(">> ");
                    //System.out.println(fps - deltaTime);
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
    
    public static void oldMain(String[] args) {
        
        // * DECLARATIONS
        
        int speed= 30; //Entre 25 et 42 | 44 MAX
        
        boolean run= true;
        long beforeTime= 0, deltaTime, fps = 60;
        beforeTime = System.currentTimeMillis();
        System.out.println( beforeTime );
        
        // I N I T --------------------------
        Configuration config = new Configuration();
        int nb= 8;
        Square[] squares= new Square[nb];
        //Dictionary dico;        
        Player J1= new Player(1, "georges", "ninja");
        Player J2 = new Player(2, "louis", "panda");
        Board b= J1.getBoard();
        boolean[] fd= b.getField();

       /* if( args.length == 0 )
            dico= new Dictionary("../"+config.getLang()+".txt");
        else
            dico= new Dictionary("../../"+config.getLang()+".txt");
          */  
        //Shape shape= new Shape('T', dico, b);
        // J1.newShape( 'T', dico );
         J1.newShape( 'T', config.getDico() );
            
        squares= null;
        
        Frame tmp = new Frame(J1, J2, config);
        
        int tps= 0;
        
        // ** LOOP
        while(tmp.getPanelState() != 'e'){
            
            beforeTime= System.currentTimeMillis();
            ++tps;
            
            if( tps%100 == 0 ){
                /*System.out.println( "Erase case" );
                b.supprAt(3);
                --nb;*/
            }
            if( tps > 900 )
                tps = 0;
            
            
            // ** ERASE DISPLAY
            
            
            // ** UPDATES        
            tmp.update();
            
            if( tps == 185 )
                J1.getShape().rotate();
                
            if( tps== 100 ){
                J1.getShape().goRight();
                //J1.getShape().goRight();
                //J1.getShape().goRight();
            }
            
            if ( tps%(45-speed) == 0  ){
                
                // Calculate nextState ------------------------   
                for( int i= 0; i < b.size(); ++i )
                    if( b.elmtAt(i).getNextState() == '?' )
                        b.elmtAt(i).becoming(J1); 
                // --------------------------------------------
                //Reset  
                //b.freeAll();
                //UPDATE------------------------  
                for( int i= 0; i < b.size(); ++i ){
  
                    switch( b.elmtAt(i).getNextState() ){
                        case 's':   //b.elmtAt(i).setBusy(); 
                                    break;
                        
                        case 'f':   b.elmtAt(i).fall();
                                    break;
                                    
                        default : System.err.println( "Error : nextState of a Square is " +  b.elmtAt(i).getNextState() );
                                    
                    }
                    
                    b.elmtAt(i).setNextState('?');
                }
            }
            
            
            
            // ** DYNAMIC FREE
            
            
            // ** DYNAMIC FREE & ALLOC
            
            //if( tps == 200 + (int) (Math.random() * 100) )
            
            
            if( J1.getShape().isArrived() )
                J1.newShape( 'T', config.getDico() );
            
            
            deltaTime = System.currentTimeMillis() - beforeTime;
            //System.out.println("D: " + deltaTime);
            try
            {
                if( deltaTime < fps ){
                    //System.out.print(">> ");
                    //System.out.println(fps - deltaTime);
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

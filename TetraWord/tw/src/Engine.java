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
    
    /*public static void main(String[] args)
    {
        String[] tab= { "m" };
        time( tab );
        
    }*/
    
    public static void main(String[] args) {
        
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
        Dictionary dico;        
        Player J1= new Player(1, "georges", "ninja");
        Player J2 = new Player(2, "louis", "panda");
        Board b= J1.getBoardTemp();
        boolean[] fd= b.getField();

        if( args.length == 0 )
            dico= new Dictionary("../"+config.getLang()+".txt");
        else
            dico= new Dictionary("../../"+config.getLang()+".txt");
            
        //Shape shape= new Shape('T', dico, b);
        J1.newShape( 'T', dico );
            
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
                J1.newShape( 'T', dico );
            
            
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

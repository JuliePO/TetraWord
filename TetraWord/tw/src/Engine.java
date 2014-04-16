import utility.Dictionary;
import GameState.GameState;



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
}

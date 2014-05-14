import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import utility.Board;
import utility.Chrono;
import utility.Configuration;
import utility.Player;
import utility.Square;
import GameState.Game;
import GameState.GameState;
import Graphic.Frame;



public class Engine extends JPanel implements ActionListener
{
    // instance variables - replace the example below with your own
    private Timer time;

    private GameState currentState;
    
    private Player p1;
    private Player p2;
    
    private Frame fr;
    
    int nbP; 
    
    //private Start start = new Start();
    private Game game1;
    private Game game2;
    //private Option option = new Option();
    private char state;
    private Chrono freqBonus;
    
    Engine(Player p1, Player p2, Configuration config){

    	time= new Timer(17, this); //17
    	
    	game1 = new Game(p1, config);
    	game2 = new Game(p2, config);
    	
    	fr = new Frame(game1, game2, config);
    	freqBonus= new Chrono(new Random().nextInt(15) + 30);

    	time.start();
    }
    
    @Override //JPanel
	public void paintComponent(Graphics g){
    	super.paintComponent(g);
    }
    
    @Override //ActionListener
	public void actionPerformed(ActionEvent arg0) {
    	if(fr.getPanelState() == 'g')
    		update(0);

    	fr.update();
    	
    	if(fr.getPanelState() == 'e'){
    		fr.dispose();
    		time.stop();
    	}
    }
    
    public void update(int tps){
    	
    	//Decomenter pour relancer le jeu en fin de partie
    	if(/*game1.isEnd() || game2.isEnd()*/false){
    		System.out.println("gameover");
    		game1.restart();
    		game2.restart();
    		fr.restart();
    	}
    	else{
    		
	        game1.update(tps);
	        game2.update(tps);
	        
	        freqBonus.decr();
	        
	        	/*   Pas encore fonctionnel
    		if( freqBonus.isFinished()  ){
				
    			int tmp= new Random().nextInt(6);
				freqBonus.setGoal(tmp);
				
				if( tmp%2 == 0) 
					game1.getPlayer().getBoard().addBonus(game1.getPlayer(), game2.getPlayer());
				else
					game2.getPlayer().getBoard().addBonus(game2.getPlayer(), game1.getPlayer());
				
			}
				*/
	    }
    }
    
    public static void main(String[] args) {
    	// * DECLARATIONS
 
        long beforeTime= 0, deltaTime, fps = 60;
        beforeTime = System.currentTimeMillis();

        //Random alea = new Random();

        
        // I N I T --------------------------
        Configuration config = new Configuration();
              
        Player J1= new Player(1, "georges", "ninja");
        Player J2 = new Player(2, "louis", "panda");
                
        Engine engine = new Engine(J1, J2, config);
       
    }
}

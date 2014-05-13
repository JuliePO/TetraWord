import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import utility.*; //Sale
import GameState.*; //Sale
import Graphic.*; //Sale


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
    
    Engine(Player p1, Player p2, Configuration config){

    	time= new Timer(5, this);
    	
    	game1 = new Game(p1, config);
    	game2 = new Game(p2, config);
    	
    	fr = new Frame(game1, game2, config);

    	time.start();
    }
    
    @Override //JPanel
	public void paintComponent(Graphics g){
    	super.paintComponent(g);
    }
    
    @Override //ActionListener
	public void actionPerformed(ActionEvent arg0) {
    	
    	//System.out.println("ActionPerformed");
    	if(fr.getPanelState() == 'g')
    		update(0);
    	fr.update();
    }
    
    public void update(int tps){
        game1.update(tps);
        game2.update(tps);
    }
    
    public static void main(String[] args) {
    	// * DECLARATIONS
 
        long beforeTime= 0, deltaTime, fps = 60;
        beforeTime = System.currentTimeMillis();
        
        // I N I T --------------------------
        Configuration config = new Configuration();
              
        Player J1= new Player(1, "georges", "ninja");
        Player J2 = new Player(2, "louis", "panda");
        
       // Tetris tetris = new Tetris(J1, config);
        
        Engine engine = new Engine(J1, J2, config);
       
    }
}

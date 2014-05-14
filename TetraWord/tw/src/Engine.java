import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import utility.Board;
import utility.Configuration;
import utility.Player;
import utility.Square;
import GameState.Game;
import GameState.GameState;
import Graphic.Frame;
import Audio.Audio;



public class Engine extends JPanel implements ActionListener
{
    // instance variables - replace the example below with your own
    private Timer time;

    private GameState currentState;
    
    private Player p1;
    private Player p2;
    
    private Frame fr;
    
    private Audio son;
    boolean changeSound;
    
    int nbP; 
    
    //private Start start = new Start();
    private Game game1;
    private Game game2;
    //private Option option = new Option();
    private char state;
    
    Engine(Player p1, Player p2, Configuration config){


    	time= new Timer(17, this); //17
    	
    	game1 = new Game(p1, config);
    	game2 = new Game(p2, config);
    	
    	fr = new Frame(game1, game2, config);
    	
    	son = new Audio("sound/puzzle.wav");
    	son.setEnd(true);
        son.start();
        changeSound = false;

    	time.start();
    }
    
    @Override //JPanel
	public void paintComponent(Graphics g){
    	super.paintComponent(g);
    }
    
    @Override //ActionListener
	public void actionPerformed(ActionEvent arg0) {
    	if(fr.getPanelState() == 'g') {
    		update(0);
    		if(changeSound == false) {
    			changeSound = true;
    			son.setEnd(false);
    			son.stopSound();
    			son = new Audio("sound/phoenix.wav");
    	    	son.setEnd(true);
    	        son.start();
    		}
    	}
    	
    	if(fr.getPanelState() != 'g' && changeSound == true) {
    		changeSound = false;
			son.setEnd(false);
			son.stopSound();
			son = new Audio("sound/puzzle.wav");
	    	son.setEnd(true);
	        son.start();
    	}

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
	        //game2.update(tps);
	    }
    	
    }
    
    public static void main(String[] args) {
    	// * DECLARATIONS
 
        long beforeTime= 0, deltaTime, fps = 60;
        beforeTime = System.currentTimeMillis();

        Random alea = new Random();

        
        // I N I T --------------------------
        Configuration config = new Configuration();
              
        Player J1= new Player(1, "georges", "ninja", alea);
        Player J2 = new Player(2, "louis", "panda", alea);
                
        Engine engine = new Engine(J1, J2, config);
       
    }
}

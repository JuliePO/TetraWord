package Graphic;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameState.Game;
import Graphic.character.PanelCharacter;
import Graphic.option.PanelOption;
import Graphic.start.PanelAccueil;
import Graphic.tetra.PanelTetraWord;
import utility.*; // Evidemment temporaire
import utility.Bonus.BonusScore;
import utility.Bonus.MalusScore;


public class Frame extends JFrame {

	
	private int width = 1024, height = 700;
	JPanel contentPane = new JPanel();
	Game G1, G2;
	Configuration config;
	
	private char panelState = 'Z';
	
	public Frame(Game G1, Game G2, Configuration config){		
		
		this.G1 = G1;
		this.G2 = G2;
		this.config = config;
		
		//1. Create the frame.
		setTitle("TetraWord");
	
		//2. Optional: What happens when the frame closes?
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//3. Create components and put them in the frame.
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		//JPanel menu = new JPanel();

		contentPane.add(new PanelAccueil());
	
		setContentPane(contentPane);
		
		//4. Size the frame.
		onSize();
	
		//5. Show it.
		setVisible(true);	
	}
	
	public char getPanelState(){
		return panelState;
	}
	
	public void setPanelState(char newState){
		panelState = newState;
	}
	
	public void onSize(){
		pack();
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Player getPlayer1(){
		return G1.getPlayer();
	}
	
	public Player getPlayer2(){
		return G2.getPlayer();
	}
	
	public void restart(){
		contentPane.removeAll();
		contentPane.add(new PanelAccueil());
		onSize();
		setPanelState('s');
	}
	
	public void update(){
				
		char tmpState = ((PanelBase)contentPane.getComponent(0)).getState();
		if(panelState != tmpState){
			switch (tmpState) {
			
			//le jeu
			case 'g':
				contentPane.removeAll();
				PanelTetraWord tetra = new PanelTetraWord(G1, G2);
				contentPane.add(tetra);
				onSize();
				setPanelState('g');
				tetra.requestFocusInWindow();
				break;
				
			//le menu de slection
			case 'c':
				contentPane.removeAll();
				PanelCharacter chara = new PanelCharacter(G1.getPlayer(), G2.getPlayer());
				contentPane.add(chara);
				onSize();
				setPanelState('c');
				chara.requestFocusInWindow();
				break;
				
			//option
			case 'o':
				contentPane.removeAll();
				contentPane.add(new PanelOption(G1.getPlayer(), G2.getPlayer(), config));
				onSize();
				setPanelState('o');
				break;
				
			//ecran start
			case 's':
				contentPane.removeAll();
				contentPane.add(new PanelAccueil());
				onSize();
				setPanelState('s');
				break;
				
			//exit
			case 'e':
				setPanelState('e');
				break;

			default:
				break;
			}
		}
		else{

			((PanelBase)contentPane.getComponent(0)).update();
		}
	}

    
    public static void main(String[] args) {
        
        int nb= 8;
        Square[] squares= new Square[nb];
        
        Configuration config = new Configuration();
        Dictionary dico = config.getDico();        
        Player J1= new Player(1, "georges", "ninja", null);
        Player J2 = new Player(2, "louis", "panda", null);
        Board b= J1.getBoard();
        boolean[] fd= b.getField();


    	J1.addBonus(new BonusScore(J1, J2));
		J1.addBonus(new BonusScore(J1, J2));
		
    	J2.addBonus(new MalusScore(J2, J1));
		J2.addBonus(new MalusScore(J2, J1));
		J2.addBonus(new MalusScore(J2, J1));

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
       /* 
        Frame tmp = new Frame(J1, J2, config);
        
        int ko= 0;
        
        while(tmp.getPanelState() != 'e'){
            
            ++ko;
            
            tmp.update();
           
            if( ko%500000 == 0  ){
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
            
            J1.increaseScore(1);
            J2.increaseScore(1);
           
            //System.out.println(p.getScore());
        }
        
        tmp.dispose();
       
    }

       */
    }


}

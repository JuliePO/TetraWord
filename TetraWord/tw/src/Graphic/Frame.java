package Graphic;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphic.character.PanelCharacter;
import Graphic.option.PanelOption;
import Graphic.start.PanelAccueil;
import Graphic.tetra.PanelTetraWord;
import utility.*; // Evidemment temporaire


public class Frame extends JFrame {

	
	private int width = 1024, height = 700;
	JPanel contentPane = new JPanel();
	Player P1, P2;
	
	private char panelState = 'Z';
	
	Frame(Player P1, Player P2){		
		
		this.P1 = P1;
		this.P2 = P2;
		
		//1. Create the frame.
		setTitle("TetraWord");
	
		//2. Optional: What happens when the frame closes?
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//3. Create components and put them in the frame.
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		JPanel menu = new JPanel();

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
		//setSize(width, height);
		pack();
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public Player getPlayer1(){
		return P1;
	}
	
	public Player getPlayer2(){
		return P2;
	}
	
	public void update(){
		
		//repaint();
		
		char tmpState = ((PanelBase)contentPane.getComponent(0)).getState();
		if(getPanelState() != tmpState){
			switch (tmpState) {
			
			//le jeu
			case 'g':
				contentPane.removeAll();
				PanelTetraWord tetra = new PanelTetraWord(P1, P2);
				contentPane.add(tetra);
				//setContentPane(contentPane);
				onSize();
				setPanelState('g');
				tetra.requestFocusInWindow();
				break;
				
			//le menu de slection
			case 'c':
				contentPane.removeAll();
				PanelCharacter chara = new PanelCharacter(P1, P2);
				contentPane.add(chara);
				//setContentPane(contentPane);
				onSize();
				setPanelState('c');
				chara.requestFocusInWindow();
				break;
				
			//option
			case 'o':
				contentPane.removeAll();
				contentPane.add(new PanelOption());
				//setContentPane(contentPane);
				onSize();
				setPanelState('o');
				break;
				
			//ecran start
			case 's':
				contentPane.removeAll();
				contentPane.add(new PanelAccueil());
				//setContentPane(contentPane);
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
			//onSize();
			//setContentPane(contentPane);
		}
	}

    
    public static void main(String[] args) {
        
        int nb= 8;
        Square[] squares= new Square[nb];
        
        Dictionary dico;        
        Player J1= new Player("georges", "ninja");
        Player J2 = new Player("louis", "panda");
        Board b= J1.getBoardTemp();
        boolean[] fd= b.getField();

    	J1.getBonus().add("exchange");
		J1.getBonus().add("lapin");
		
    	J2.getBonus().add("exchange");
		J2.getBonus().add("lapin");
		J2.getBonus().add("lapin");
        
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
        
        while(tmp.getPanelState() != 'e'){
            
            ++ko;
            
            tmp.update();
           
            if( ko%500000 == 0  ){
                // Calculate nextState ------------------------   
                for( int i= 0; i < nb; ++i )  
                    if( b.elmtAt(i).getNextState() == '?' )
                        b.elmtAt(i).becoming();    
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
            
            J1.increaseScrore(1);
            J2.increaseScrore(1);
           
            //System.out.println(p.getScore());
        }
        
        tmp.dispose();
       
    }
}

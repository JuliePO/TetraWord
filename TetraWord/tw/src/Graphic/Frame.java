package Graphic;

import java.awt.Component;
import java.awt.event.MouseWheelListener;

import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphic.character.PanelCharacter;
import Graphic.start.PanelAccueil;
import Graphic.tetra.PanelTetraWord;
import utility.Letter;
import utility.Player;
import utility.Square;


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
		
		repaint();
		
		char tmpState = ((PanelBase)contentPane.getComponent(0)).getState();
		if(getPanelState() != tmpState){
			switch (tmpState) {
			
			//le jeu
			case 'g':
				contentPane.removeAll();
				contentPane.add(new PanelTetraWord(P1, P2));
				setContentPane(contentPane);
				onSize();
				setPanelState('g');
				break;
				
			//le menu de slection
			case 'c':
				contentPane.removeAll();
				PanelCharacter chara = new PanelCharacter(P1, P2);
				contentPane.add(chara);
				setContentPane(contentPane);
				onSize();
				setPanelState('c');
				chara.requestFocusInWindow();
				break;
				
			//option
			case 'o':
				contentPane.removeAll();
				contentPane.add(new PanelOption());
				setContentPane(contentPane);
				onSize();
				setPanelState('o');
				break;
				
			//ecran start
			case 's':
				contentPane.removeAll();
				contentPane.add(new PanelAccueil());
				setContentPane(contentPane);
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
			//setContentPane(contentPane);
		}
	}
	
	
	public static void main(String[] args) {

		Player p = new Player("georges", "ninja");
		Player p2 = new Player("louis", "panda");
		p.increaseScrore(12);
		p2.increaseScrore(452);

		p.getBoardTemp().addCase(new Square(10, 21, new Letter((short) 1, 'k'), null, "blue"));
		p.getBoardTemp().addCase(new Square(2, 2, new Letter((short) 1, 'w'), null, "green"));
		
		Frame tmp = new Frame(p, p2);
		
		/*new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 200; ++i){
					p.increaseScrore(200);
					System.out.println(p.getScore());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();*/
		while(tmp.getPanelState() != 'e'){
			tmp.update();
			/*p.increaseScrore(1);
			p2.increaseScrore(1);*/
			//System.out.println(p.getScore());
		}
		
		tmp.dispose();
	}
}

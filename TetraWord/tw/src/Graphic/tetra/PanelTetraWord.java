package Graphic.tetra;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Graphic.PanelBase;
import Graphic.TetraComponent;
import utility.Letter;
import utility.Player;
import utility.Square;

public class PanelTetraWord extends PanelBase {

    BufferedImage grillage, background;
    TexturePaint grillageP, backgroundP;
    
    Player P1, P2;
    
    private boolean pause = false;
    
    //grille de 11*22
    //private Vector<Square> cases = null;
    
    
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		

		g2.setPaint(backgroundP);
        g2.fillRect(0, 0, w, h);
		g2.setPaint(grillageP);
        g2.fillRect(0, 0, w, h);
	}
	
	private void loadImage(){
		try {
            grillage = ImageIO.read(new File(mPath+"texture/game/field/blue.png"));
            background = ImageIO.read(new File(mPath+"texture/game/background/bamboo.jpg"));

        } catch (IOException ex) {

        	System.out.println("Error 404: File not Found !");
        }
		
		w = grillage.getWidth();
		h = grillage.getHeight();

		grillageP= new TexturePaint(grillage, new Rectangle(0, 0, w, h));
		backgroundP= new TexturePaint(background, new Rectangle(0, 0, w, h));
	}
	
	//Le constructeur en premier c'est plus cool
	public PanelTetraWord(Player player1, Player player2) {	
		
		super();
		
		state = 'g';
		
		P1 = player1;
		P2 = player2;
		
		loadImage();		
		
		setLayout(null);
		
		if(P1 != null){
			FieldComponent fieldP1 = new FieldComponent(P1);
			fieldP1.setBounds(110, 120, fieldP1.getW(), fieldP1.getH());
			add(fieldP1);
			
			ScoreComponent scoreP1 = new ScoreComponent(P1);
			scoreP1.setBounds(395, 648, scoreP1.getW(), scoreP1.getH());
			add(scoreP1);
			
			BonusArrayComponent bonusArrayP1 = new BonusArrayComponent(P1);
			bonusArrayP1.setBounds(29, 197, bonusArrayP1.getW(), bonusArrayP1.getH());
			add(bonusArrayP1);
			
		}
		
		if(P2 != null){
			FieldComponent fieldP2 = new FieldComponent(P2);
			fieldP2.setBounds(640, 120, fieldP2.getW(), fieldP2.getH());
			add(fieldP2);
			
			ScoreComponent scoreP2 = new ScoreComponent(P2);
			scoreP2.setBounds(507, 648, scoreP2.getW(), scoreP2.getH());
			add(scoreP2);
			
			BonusArrayComponent bonusArrayP2 = new BonusArrayComponent(P2);
			bonusArrayP2.setBounds(945, 197, bonusArrayP2.getW(), bonusArrayP2.getH());
			add(bonusArrayP2);
		}
		
		VersusComponent versus = new VersusComponent(P1, P2);
		versus.setBounds(405, 448, versus.getW(), versus.getH());
		add(versus);
		
		addMouseMotionListener(new MouseMotionListenerTetra());
		addMouseListener(new MouseListenerTetra());
		
		addKeyListener(new KeyListenerTetra(P1, P2));
	}
	
	
	
	/*public void update(Vector<Square> casesP1, Vector<Square>casesP2){
		//this.cases = cases;
		
	}*/
	
	@Override
	public void update() {
		
		for(Component tmp : getComponents()){
			((TetraComponent)tmp).update();
		}
		repaint();
	}
	

	public static void main(String[] args) {
		

		Vector<Square> square = new Vector<Square>();
		square.add(new Square(11, 1, new Letter((short)5, 'a', 0), null, "blue"));
		square.add(new Square(1, 22, new Letter((short)5, 'z', 0), null, "yellow"));
		
		final Player p = new Player(1, "georges", "ninja");
		p.increaseScore(8000);
		
		final JFrame tmp = new JFrame("lol");
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*/!\*/		
		tmp.setContentPane(new PanelTetraWord(p, null));
	
		//4. Size the frame.
		tmp.setSize(1000, 700);
		//setSize(200, 200);
	
		//5. Show it.
		tmp.setVisible(true);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 200; ++i){
					p.increaseScore(200);
					tmp.removeAll();
					tmp.setContentPane(new PanelTetraWord(p, null));
					tmp.setSize(1000, 700);
					tmp.repaint();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}

	public Player getPlayer2() {
		// TODO Auto-generated method stub
		return P2;
	}

	public Player getPlayer1() {
		// TODO Auto-generated method stub
		return P1;
	}
	
	public void setPause(){
		if(pause)
			remove(0);
		else{
			PauseComponent pause = new PauseComponent();
			pause.setBounds(1, 1, pause.getW(), pause.getH());
			addImpl(pause, null, 0);
		}
			
		pause = !pause;
	}
}
package Graphic.option;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import utility.Configuration;
import utility.Player;
import Graphic.PanelBase;
import Graphic.character.PanelCharacter;
import Graphic.option.letters.LetterUnderPanel;
import Graphic.option.option.OptionUnderPanel;
import Graphic.start.PanelAccueil;
import Graphic.tetra.PanelTetraWord;

public class PanelOption extends PanelBase {
	
	private char optionState = 'o';
	private Player p1;
	private Player p2;
	private Configuration config;

	//private ShapeUnderPanel shape;
	//private CreateUnderPanel create;*/
	
	TexturePaint background;
	
	public PanelOption(Player p1, Player p2, Configuration config) {
		state = 'o';
		
		w= 1024;
		h=700;
		
		this.p1=p1;
		this.p2=p2;
		this.config=config;
		
		loadImages();
		
		OptionUnderPanel option = new OptionUnderPanel(p1, p2, config);
		
		add(option);
	}
	
	private void loadImages(){
		
		String path= mPath + "texture/options/background.jpg";
		
		try {
            BufferedImage tmp = ImageIO.read(new File(path));
            background = new TexturePaint(tmp, new Rectangle(0, 0, tmp.getWidth(), tmp.getHeight()));

        } catch (IOException ex) {

        	System.out.println("Error 404: '"+path+"' not found !");
        }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(background);
		g2.fillRect(0, 0, w, h);
	}

	@Override
	public void update() {
		
		char tmpState = ((PanelBase)getComponent(0)).getState();
		if(optionState != tmpState){
			
			switch (tmpState) {
			
			//option de base
			case 'o':
				removeAll();
				OptionUnderPanel option = new OptionUnderPanel(p1, p2, config);
				add(option);
				optionState = 'o';
				break;
				
			//creation de shape
			case 'c':
				System.out.println("create shape");
				break;
				
			//configuration des shapes
			case 's':
				System.out.println("config shape");
				break;
				
			//configuration des lettres
			case 'l':
				removeAll();
				LetterUnderPanel letter = new LetterUnderPanel(config);
				add(letter);
				optionState = 'l';
				break;
				
			//exit
			case 'r':
				state = 's';
				break;

			default:
				break;
			}
			validate();
		}
		else{

			((PanelBase)getComponent(0)).update();
			repaint();
		}
		
	}
	
	public static void main(String[] args) {
		Player p1 = new Player(1, "Peter");
		Player p2 = new Player(2, "Alfred");
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelOption bonus = new PanelOption(p1, p2, new Configuration());
			
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}
}

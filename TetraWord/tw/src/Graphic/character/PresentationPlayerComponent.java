package Graphic.character;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Graphic.TetraComponent;

public class PresentationPlayerComponent extends TetraComponent {

	CharacterLabel persoTop;;
	CharacterLabel persoBot;
	CharacterLabel persoName;
	TexturePaint[] j1 = new TexturePaint[3];
	TexturePaint[] j2 = new TexturePaint[3];
	
	int player;
	int select;
	
	@Override
	protected void paintComponent(Graphics g) {
		
		
		
	}
	
	public PresentationPlayerComponent(int player, int select) {
		h = 700;
		w = 387;
		this.player = player;
		this.select = select;
		loadImages();
		
		setLayout(null);
		
		int decalage = 0;
		
		if(player == 2)
			decalage = 1;
		
		persoName = new CharacterLabel(select, 104, 27, "persoName"+player);
		persoName.setBounds(w/2 - 80 + (decalage * 50), 323, 104, 27);
		add(persoName);
		
		JLabel bandeau = new JLabel(new ImageIcon(mPath+ "texture/selection/j"+player+"/player"+player+"-name.png"));
		bandeau.setBounds(0, 288, 386, 69);
		add(bandeau);
		
		JLabel persoPlayer = new JLabel(new ImageIcon(mPath+"texture/selection/j"+player+"/player"+player+".png"));
		persoPlayer.setBounds(215 + (decalage * - 190), 25, 153, 33);
		add(persoPlayer);
		
		persoTop = new CharacterLabel(select, 368, 378, "persoTop");
		persoTop.setBounds(10, 10, persoTop.getW(), persoTop.getH());
		add(persoTop);
		
		persoBot = new CharacterLabel(select, 398, 333, "persoBot");
		persoBot.setBounds(w/2 - 199, 378, persoBot.getW(), persoBot.getH());
		add(persoBot);

		JLabel topBack = new JLabel(new ImageIcon(mPath+"texture/selection/j"+player+"/background-player"+player+".jpg"));
		topBack.setBounds(0, 0, 387, 387);
		add(topBack);
	}
	
	private TexturePaint[] loadDir(String path){
		
		File repertoire = new File(mPath+"texture/selection/"+path);
		String [] listefichiers; 
		listefichiers=repertoire.list();

		TexturePaint[] tmp = new TexturePaint[listefichiers.length];
		
		for(int i= 0; i < listefichiers.length; i++){ 
			try {
	            BufferedImage image = ImageIO.read(new File(mPath+"texture/selection/"+path+"/"+ listefichiers[i]));
	    		tmp[i] = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
	
	        } catch (IOException ex) {
	        	System.out.println("Error 404: 'texture/selection/"+path +"/"+ listefichiers[i]+"' not Found !");
	        }
		} 
		return tmp;
	}
	
	private void loadImages() {

		j1 = loadDir("j1");
		j2 = loadDir("j2");
	}

	@Override
	public void update() {
		persoTop.select(select);
		persoTop.update();
		persoBot.select(select);
		persoBot.update();
		persoName.select(select);
		persoName.update();
	}
	
	public void setSelect(int i){
		select = i;
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setContentPane(new PresentationPlayerComponent(2, 0));
		tmp.pack();
		tmp.setVisible(true);
	}
}

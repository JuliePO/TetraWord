package Graphic.character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Graphic.TetraComponent;

public class CharacterLabel extends TetraComponent {
	
	TexturePaint[] textures = new TexturePaint[4];
	int select;
	
	public void paintComponent(java.awt.Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(textures[select]);
		g2.fillRect(0, 0, w, h);
	};
	
	CharacterLabel(int select, int w, int h, String path){
		this.select = select;
		loadImages(path);
		this.w = w;
		this.h = h;
	}
	
	private void loadImages(String path){
		
		File repertoire = new File("texture/selection/"+path);
		String [] listefichiers; 
		listefichiers=repertoire.list();

		//TexturePaint[] tmp = new TexturePaint[listefichiers.length];
		
		for(int i= 0; i < listefichiers.length; i++){ 
			try {
	            BufferedImage image = ImageIO.read(new File("texture/selection/"+path +"/"+ listefichiers[i]));
	    		textures[i] = new TexturePaint(image, new Rectangle(0, 0, image.getWidth(), image.getHeight()));
	
	        } catch (IOException ex) {
	        	System.out.println("Error 404: 'texture/selection/"+path +"/"+ listefichiers[i]+"' not Found !");
	        }
		}
	}
	
	
	public void select(int i){
		select = i;
	}
	
	@Override
	public void update() {
		repaint();

	}

	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setContentPane(new CharacterLabel(0, 10, 10, "lol"));
		tmp.pack();
		tmp.setVisible(true);
	}

}

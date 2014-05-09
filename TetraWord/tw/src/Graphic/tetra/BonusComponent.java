package Graphic.tetra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Graphic.TetraComponent;

public class BonusComponent extends TetraComponent {
	
	private TexturePaint[] bonus;
	private TexturePaint lueur;
	
	private String current = null;
	
	private HashMap<String, Integer> index = new HashMap<String, Integer>();
	
	private int top = 13, left = 13;
	
	BonusComponent(){
		loadImages();
	}
	
	private void loadImages(){
		
		
		String path = "texture/game/";
		
		File repertoire = new File(mPath+path + "bonus/");
		String [] listefichiers; 
		listefichiers=repertoire.list();

		this.bonus = new TexturePaint[listefichiers.length];
		
		for(int i= 0; i < listefichiers.length; i++){ 
			try {
	            BufferedImage image = ImageIO.read(new File(mPath+path + "bonus/"+ listefichiers[i]));
	    		this.bonus[i] = new TexturePaint(image, new Rectangle(left, top, image.getWidth(), image.getHeight()));
	    		index.put(listefichiers[i].substring(0, listefichiers[i].length() - 4), i);
	        } catch (IOException ex) {
	        	System.out.println("Error 404: '"+mPath+path + "bonus/"+ listefichiers[i]+"' not Found !");
	        }
		}
		
		try {
            BufferedImage image = ImageIO.read(new File(mPath+path +"lueur-bonus.png"));
            h = image.getHeight();
            w = image.getWidth();
    		lueur = new TexturePaint(image, new Rectangle(0, 0, w, h));

        } catch (IOException ex) {
        	System.out.println("Error 404: '"+mPath+path +"lueur-bonus.png' not Found !");
        }
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		Integer currentBonus = index.get(current);
		if(currentBonus != null){
			int tmp = currentBonus.intValue();
			
			g2.setPaint(lueur);
			g2.fillRect(0, 0, lueur.getImage().getWidth(), lueur.getImage().getHeight());
			
			g2.setPaint(bonus[tmp]);
			g2.fillRect(left, top, bonus[tmp].getImage().getWidth(), bonus[tmp].getImage().getHeight());
		}
	}
	
	@Override
	public void update() {
		repaint();
	}
	
	public void setBonus(String nBonus){
		current = nBonus;
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BonusComponent bonus = new BonusComponent();
		bonus.setBonus(null);		
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}
}

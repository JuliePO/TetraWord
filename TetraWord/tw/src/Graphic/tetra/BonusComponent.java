package Graphic.tetra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Graphic.TetraComponent;

public class BonusComponent extends TetraComponent {
	
	private TexturePaint bonus;
	private TexturePaint lueur;
	
	private int top = 16, left = 13;
	
	BonusComponent(String bonus){
		loadImages(bonus);
	}
	
	private void loadImages(String bonus){
		
		String path = "texture/game/";
		
		try {
            BufferedImage image = ImageIO.read(new File(mPath+path +"bonus/"+ bonus+".jpg"));
    		this.bonus = new TexturePaint(image, new Rectangle(left, top, image.getWidth(), image.getHeight()));

        } catch (IOException ex) {
        	System.out.println("Error 404: '"+mPath+path +"bonus/"+ bonus+".jpg' not Found !");
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
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, lueur.getImage().getWidth(), lueur.getImage().getHeight());
		
		g2.setPaint(lueur);
		g2.fillRect(0, 0, lueur.getImage().getWidth(), lueur.getImage().getHeight());
		
		g2.setPaint(bonus);
		g2.fillRect(left, top, bonus.getImage().getWidth(), bonus.getImage().getHeight());
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tmp.setContentPane(new BonusComponent("exchange"));
		tmp.pack();
		tmp.setVisible(true);
	}
}

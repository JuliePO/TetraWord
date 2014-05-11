package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public class ButtonTetra extends JButton {
	
	private BufferedImage texture, hover;
	private TexturePaint paint;
	private int w; 
	private int h;
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(paint);
		g2.fillRect(0, 0, w, h);
	}
	
	private void loadImage(String path1, String path2){
		try {
	           texture = ImageIO.read(new File(path1));
	           hover = ImageIO.read(new File(path2));
       } catch (IOException ex) {
    	   System.out.println("Error 404: '"+path1+"' and '"+path2+"' not Found !");
       }
		
		w = texture.getWidth();
		h = texture.getHeight();
	}
	
	public ButtonTetra(String path1, String path2){
		loadImage(path1, path2);
		loadButton(path1, path2);		
	}
	
	private void loadButton(String path1, String path2){
	
		setBorder(BorderFactory.createEmptyBorder());
		
		paint= new TexturePaint(texture, new Rectangle(0, 0, w, h));
		
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	paint= new TexturePaint(hover, new Rectangle(0, 0, w, h));
		    	getParent().repaint();
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	paint= new TexturePaint(texture, new Rectangle(0, 0, w, h));
		    	getParent().repaint();
		    }
		});
	}

	public int getHeightTexture(){
		return h;
	}
	
	public int getWidthTexture(){
		return w;
	}
	
	public static void main(String[] args) {
		ButtonTetra start = new ButtonTetra("texture/start/button-solo.png", "texture/start/hoover-button-solo.png");
		//start.addActionListener(actionGame);
		start.setBounds(322, 246, start.getWidthTexture(), start.getHeightTexture());
		//add(start);
		
	}
}

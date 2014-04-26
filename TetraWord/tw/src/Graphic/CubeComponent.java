package Graphic;

import java.awt.Color;
import java.awt.Font;
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

public class CubeComponent extends JButton {
	
	private BufferedImage texture, hover;
	private TexturePaint paint;
	private int scale = 24;
	private int w; 
	private int h;
	private char letter;
		
	public CubeComponent(String path1, String path2, char letter){
		this.letter = letter;
		loadImage(path1, path2);
		loadButton(path1, path2);	
	}
	
	private void loadImage(String path1, String path2){
		try {
	           texture = ImageIO.read(new File(path1));
	           hover = ImageIO.read(new File(path2));
       } catch (IOException ex) {
    	   System.out.println("Error 404: '"+path1+"' and '"+path2+"' not Found !");
       }

        texture.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
        hover.getScaledInstance(scale, scale, Image.SCALE_DEFAULT);
        
    	w = scale;
		h = scale;
	}
	
	private void loadButton(String path1, String path2){
	
		setBorder(BorderFactory.createEmptyBorder());
		
		paint= new TexturePaint(texture, new Rectangle(0, 0, w, h));
		
		addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	paint= new TexturePaint(hover, new Rectangle(0, 0, w, h));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	paint= new TexturePaint(texture, new Rectangle(0, 0, w, h));
		    }
		});
	}

	public int getHeightTexture(){
		return h;
	}
	
	public int getWidthTexture(){
		return w;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(paint);
		g2.fillRect(0, 0, w, h);
		char[] tmp = new char[1];
		tmp[0] = letter;
		
		g2.setFont(new Font("Serif", Font.BOLD, 20));
		g2.setColor(Color.WHITE);
		g2.drawChars(tmp, 0, 1, w/3, (h*2)/3);
	}
}

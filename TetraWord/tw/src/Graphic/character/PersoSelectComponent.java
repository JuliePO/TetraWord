package Graphic.character;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Graphic.TetraComponent;

public class PersoSelectComponent extends TetraComponent {
	private TexturePaint texture, p1, p2;
	
	private boolean isP1 = false, isP2 =false;
	
	protected void paintComponent(java.awt.Graphics g) {
		
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(texture);
		g2.fillRect(0, 0, w, h);

		if(isP1){
			g2.setPaint(p1);
			g2.fillRect(0, 0, w, h);
		}
		if(isP2){
			g2.setPaint(p2);
			g2.fillRect(0, 0, w, h);
		}
	};
	
	PersoSelectComponent(String path){
		loadImages(path);
	}
	
	private void loadImages(String path){
		try {
            BufferedImage tmp = ImageIO.read(new File(mPath+"texture/selection/icon-"+path+".png"));
    		texture = new TexturePaint(tmp, new Rectangle(0, 0, tmp.getWidth(), tmp.getHeight()));
    		w = tmp.getWidth();
    		h = tmp.getHeight();

        } catch (IOException ex) {
        	System.out.println("Error 404: 'texture/selection/icon-"+path+".png' not Found !");
        }
		
		try {
            BufferedImage tmp = ImageIO.read(new File(mPath+"texture/selection/icon-player1.png"));
    		p1 = new TexturePaint(tmp, new Rectangle(0, 0, tmp.getWidth(), tmp.getHeight()));
        } catch (IOException ex) {
        	System.out.println("Error 404: 'texture/selection/icon-player1.png' not Found !");
        }
		

		try {
            BufferedImage tmp = ImageIO.read(new File(mPath+"texture/selection/icon-player2.png"));
    		p2 = new TexturePaint(tmp, new Rectangle(0, 0, tmp.getWidth(), tmp.getHeight()));

        } catch (IOException ex) {
        	System.out.println("Error 404: 'texture/selection/icon-player2.png' not Found !");
        }
	}

	public void setP1(boolean value){
		isP1 = value;
	}
	
	public void setP2(boolean value){
		isP2 = value;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

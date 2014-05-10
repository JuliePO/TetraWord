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

import utility.Player;
import Graphic.PanelBase;
import Graphic.option.option.OptionUnderPanel;

public class PanelOption extends PanelBase {
	
	private char optionState = 'o';
	private OptionUnderPanel option;
	/*private ShapeUnderPanel shape;
	private LetterUnderPanel letter;
	private CreateUnderPanel create;*/
	
	TexturePaint background;
	
	public PanelOption(Player p1, Player p2) {
		state = 'o';
		
		w= 1024;
		h=700;
		
		loadImages();
		
		option = new OptionUnderPanel(p1, p2);
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
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Player p1 = new Player(1, "Peter");
		Player p2 = new Player(2, "Alfred");
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelOption bonus = new PanelOption(p1, p2);
			
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}
}

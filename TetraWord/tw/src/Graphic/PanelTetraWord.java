package Graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTetraWord extends PanelBase {

    BufferedImage grillage;
    TexturePaint grillageP;
    	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		grillageP= new TexturePaint(grillage, new Rectangle(0, 0, 960, 656));
		
		g2.setPaint(grillageP);
        g2.fillRect(0, 0, 960, 656);
	}
	
	private void loadImage(){
		try {
            grillage = ImageIO.read(new File("../grillage.jpg"));

        } catch (IOException ex) {

        	System.out.println("Error 404: File not Found !");
        }
	}
	
	public PanelTetraWord() {
		
		state = 'g';
		
		loadImage();
		
		final ActionListener actionGame= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 's';
			}
		};
		
		JButton game = new JButton("Game");
		game.addActionListener(actionGame);
		//game.setSize(960, 656);
		add(game);
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame("lol");
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tmp.setContentPane(new PanelTetraWord());
	
		//4. Size the frame.
		tmp.setSize(1000, 700);;
		//setSize(200, 200);
	
		//5. Show it.
		tmp.setVisible(true);
	}
}

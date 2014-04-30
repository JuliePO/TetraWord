package Graphic;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelAccueil extends PanelBase {
	
	 BufferedImage accueil;
	 TexturePaint accueilP;
	 
	 String path; //ONLY FOR DEVELOPMENT
	 	 
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponents(g);
				
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(accueilP);
	    g2.fillRect(0, 0, w, h);
	    
	}
	
	private void loadImage(){
		try {
            accueil = ImageIO.read(new File( path + "texture/start/background-start.jpg"));

        } catch (IOException ex) {

        	System.out.println("Error 404: 'texture/start/background-start.jpg' not Found !");
        }
		
		h = accueil.getHeight();
		w = accueil.getWidth();
		
		accueilP= new TexturePaint(accueil, new Rectangle(0, 0, w, h));
	}

	public PanelAccueil() {
	    
	    //path= "../";// A commenter sous Ecllipse
		
	    
		state = 's';
		loadImage();
				
		final ActionListener actionGame= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'g';
			}
		};	
		final ActionListener actionOption= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'o';
			}
		};	
		final ActionListener actionExit= new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				state = 'e';
				JFrame.getFrames()[0].dispose();
			}
		};
		
		setLayout(null);
		
		ButtonTetra start = new ButtonTetra(path+"texture/start/button-solo.png", path+"texture/start/hoover-button-solo.png");
		start.addActionListener(actionGame);
		start.setBounds(322, 246, start.getWidthTexture(), start.getHeightTexture());
		add(start);

		ButtonTetra multi = new ButtonTetra(path+"texture/start/button-multi.png", path+"texture/start/hoover-button-multi.png");
		multi.addActionListener(actionGame);
		multi.setBounds(322, 342, multi.getWidthTexture(), multi.getHeightTexture());
		add(multi);
		
		ButtonTetra option = new ButtonTetra(path+"texture/start/button-options.png", path+"texture/start/hoover-button-option.png");
		option.addActionListener(actionOption);
		option.setBounds(322, 443, option.getWidthTexture(), option.getHeightTexture());
		add(option);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

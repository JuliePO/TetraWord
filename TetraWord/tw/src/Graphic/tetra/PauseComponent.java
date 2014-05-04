package Graphic.tetra;

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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Player;
import Graphic.ButtonTetra;
import Graphic.TetraComponent;
import Graphic.character.PanelCharacter;

public class PauseComponent extends TetraComponent {
	
	TexturePaint background;
	
	public PauseComponent() {
		
		loadImage();
		
		String path = mPath + "texture/game/pause/";
		
		setLayout(null);
		
		JLabel titre = new JLabel(new ImageIcon(path+"pause.png"));
		titre.setBounds(464, 124, 102, 26);
		add(titre);
		
		ButtonTetra reprendre = new ButtonTetra(path+"reprendre.png", path+"reprendre-hover.png");
		reprendre.setBounds(397, 203, reprendre.getWidthTexture(), reprendre.getHeightTexture());
		reprendre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((PanelTetraWord)getParent()).setPause();
			}
		});
		add(reprendre);
		
		ButtonTetra personnages = new ButtonTetra(path+"personnages.png", path+"personnages-hover.png");
		personnages.setBounds(397, 279, personnages.getWidthTexture(), personnages.getHeightTexture());
		personnages.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				((PanelTetraWord)getParent()).setState('c');				
			}
		});
		add(personnages);
		
		ButtonTetra configuration = new ButtonTetra(path+"configuration.png", path+"configuration-hover.png");
		configuration.setBounds(397, 356, configuration.getWidthTexture(), configuration.getHeightTexture());
		configuration.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		add(configuration);
		
		ButtonTetra menu = new ButtonTetra(path+"menu.png", path+"menu-hover.png");
		menu.setBounds(397, 432, menu.getWidthTexture(), menu.getHeightTexture());
		menu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((PanelTetraWord)getParent()).setState('s');
			}
		});
		add(menu);
		
		ButtonTetra quitter = new ButtonTetra(path+"quitter.png", path+"quitter-hover.png");
		quitter.setBounds(397, 508, quitter.getWidthTexture(), quitter.getHeightTexture());
		quitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((PanelTetraWord)getParent()).setState('e');
			}
		});
		add(quitter);
	}
	
	private void loadImage(){
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/pause/background-pause.png"));
	          this.background =  new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight()));  
	          w = texture.getWidth();
	          h = texture.getHeight();
	   } catch (IOException ex) {
		   System.out.println("Error 404: '"+mPath+"texture/game/pause/background-pause.png' not Found !");
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
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PauseComponent prout = new PauseComponent();
		prout.update();
		tmp.setContentPane(prout);
		tmp.pack();
		tmp.setVisible(true);
		
		while(true){
			((PauseComponent)tmp.getComponent(0)).update();
		}
	}

}

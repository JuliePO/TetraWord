package Graphic.character;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Graphic.PanelBase;
import utility.Player;

public class PanelCharacter extends PanelBase{
	
	 TexturePaint background;
	 Player p1, p2;
	 int select1 = 0, select2 = 1;
	 boolean set1 = false, set2 = false;
	 SelecteurComponent selecteur;
	 PresentationPlayerComponent j1;
	 PresentationPlayerComponent j2;
	 	 
	@Override 
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.paintComponents(g);
				
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(background);
	    g2.fillRect(0, 0, w, h);
	
	}
	
	private void loadImages(){
		
		String path = mPath +  "texture/selection/background-characters.jpg";
		try {
            BufferedImage tmp = ImageIO.read(new File(path));
    		h = tmp.getHeight();
    		w = tmp.getWidth();
    		background = new TexturePaint(tmp, new Rectangle(0, 0, tmp.getWidth(), tmp.getHeight()));

        } catch (IOException ex) {
        	System.out.println("Error 404: '"+path+"' not Found !");
        }
		
	}
	
	
	public PanelCharacter(Player p1, Player p2) {
		
		super();
		
		state = 'c';
		
		this.p1 = p1;
		this.p2 = p2;
		
		loadImages();
		
		setLayout(null);
		
		selecteur = new SelecteurComponent();
		selecteur.setBounds(w / 2 - selecteur.getW() / 2, 320, selecteur.getW(), selecteur.getH());
		add(selecteur);

		j1 = new PresentationPlayerComponent(1, 0);
		j1.setBounds(0, 0, j1.getW(), j1.getH());
		add(j1);
		
		j2 = new PresentationPlayerComponent(2, 0);
		j2.setBounds(w - j2.getW(), 0, j2.getW(), j2.getH());
		add(j2);
		
		addKeyListener(new KeyListenerCharacter());
		
	}
	
	private void space(){
	
		JLabel space = new JLabel(new ImageIcon(mPath+"texture/selection/lancer-partie.jpg"));
		space.setBounds(0, h/2, 1024, 71);
		space.addKeyListener(new KeyListenerSpace());
		addImpl(space, null, 0);
		space.requestFocusInWindow();
	}
	
	public void selectP1(int move){
		if(!set1){
			select1 += move;
			if(select1 == 4)
				select1 = 0;
			else if(select1 == 5)
				select1 = 1;
			else if(select1==-1)
				select1 = 3;
			else if(select1 == -2)
				select1 = 2;
		}
	}
	
	public void selectP2(int move){
		if(!set2){
			select2 += move;
			if(select2 == 4)
				select2 = 0;
			else if(select2 == 5)
				select2 = 1;
			else if(select2==-1)
				select2 = 3;
			else if(select2 == -2)
				select2 = 2;
		}
	}
	
	public void setP1(){
		switch (select1) {
		case 0:
			p1.setAvatar("demon");
			break;
		case 1:
			p1.setAvatar("ninja");
			break;
		case 2:
			p1.setAvatar("panda");
			break;
		case 3:
			p1.setAvatar("robot");
			break;

		default:
			break;
		}
		
		if(!set1 && set2){
			space();
		}
		
		set1=true;
	}
	
	public void setP2(){
		switch (select2) {
		case 0:
			p2.setAvatar("demon");
			break;
		case 1:
			p2.setAvatar("ninja");
			break;
		case 2:
			p2.setAvatar("panda");
			break;
		case 3:
			p2.setAvatar("robot");
			break;

		default:
			break;
		}
		
		if(set1 && !set2){
			space();
		}
		set2=true;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
		 selecteur.select(select1, select2);
		 selecteur.update();
		 j1.setSelect(select1);
		 j1.update();
		 j2.setSelect(select2);
		 j2.update();
		 
		 repaint();			
	}


	public void cancel1() {
		if(set1){
			set1=false;
			if(set2)
				remove(0);
		}
		
		else
			state = 's';
		
	}
	
	public void cancel2() {
		if(set2){
			set2=false;
			if(set1)
				remove(0);
		}
		else
			state = 's';
		
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelCharacter prout = new PanelCharacter(new Player(1, "georges"), new Player(2, "louis"));
		prout.update();
		tmp.setContentPane(prout);
		tmp.pack();
		tmp.setVisible(true);
	}

	

}

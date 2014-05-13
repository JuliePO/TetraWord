package Graphic.tetra;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Graphic.TetraComponent;
import utility.Letter;
import utility.Player;
import utility.Square;

public class VersusComponent extends TetraComponent {

	TexturePaint avatar1, avatar2, cadre, versus, separator;
	String name1, name2;
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	private void loadAvatar1(String avatar1){
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/vs/"+avatar1+"-left.png"));
	          this.avatar1 =  new TexturePaint(texture, new Rectangle(3, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/"+avatar1+"-left.png' not Found !");
	   }
	}
	

	private void loadAvatar2(String avatar2){
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/vs/"+avatar2+"-right.png"));
	          this.avatar2 =  new TexturePaint(texture, new Rectangle(92, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/"+avatar2+"-right.png' not Found !");
	   }
	}
	

	private void loadRessources(){
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/vs/cadre.png"));
	          this.cadre =  new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight())); 
	          h = texture.getHeight();
	          w = texture.getWidth();
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/cadre.png' not Found !");
	   }
		
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/vs/vs.png"));
	          this.versus =  new TexturePaint(texture, new Rectangle(70, 30, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/vs.png' not Found !");
	   }
		
		try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/vs/separation.png"));
	          this.separator =  new TexturePaint(texture, new Rectangle(91, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/separation.png' not Found !");
	   }
	}

	public VersusComponent(Player p1, Player p2) {


		if(p1 != null){
			loadAvatar1(p1.getAvatar());
			name1 = p1.getName();
		}
		else
			name1 = null;
		
		if(p2 != null){
			loadAvatar2(p2.getAvatar());
			name2 = p2.getName();
		}
		else
			name2 = null;
		
		loadRessources();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		//dessin du cadre
		g2.setPaint(cadre);
		g2.fillRect(0, 0, w, h);
		
		//dessin avatar1
		if(avatar1 != null){
			g2.setPaint(avatar1);
			g2.fillRect(3, 3, avatar1.getImage().getWidth(), avatar1.getImage().getHeight());
		}
		
		//dessin avatar2
		if(avatar2 != null){
			g2.setPaint(avatar2);
			g2.fillRect(92, 3, avatar2.getImage().getWidth(), avatar2.getImage().getHeight());
		}
		
		//dessin du separator
		g2.setPaint(separator);
		g2.fillRect(91, 3, separator.getImage().getWidth(), separator.getImage().getHeight());
		
		//dessin du versus
		g2.setPaint(versus);
		g2.fillRect(70, 30, versus.getImage().getWidth(), versus.getImage().getHeight());
		
		//ecriture des pseudo
		if(name1 != null){
			g2.setFont(new Font("DOSIS-REGULAR", Font.BOLD, 18));
			g2.setColor(Color.WHITE);
			g2.drawString(name1, 10, 145);
		}
		
		if(name2 != null){
			g2.setFont(new Font("DOSIS-REGULAR", Font.BOLD, 18));
			g2.setColor(Color.WHITE);
			g2.drawString(name2, 118, 145);
		}
		
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//3. Create components and put them in the frame.
		//frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		frame.setContentPane(new VersusComponent(new Player(1, "georges", "ninja", null), new Player(2, "louis", "panda", null)));
	
		//4. Size the frame.
		frame.pack();
	
		//5. Show it.
		frame.setVisible(true);	
	}
}

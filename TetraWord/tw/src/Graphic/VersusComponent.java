package Graphic;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import utility.Letter;
import utility.Player;
import utility.Square;

public class VersusComponent extends TetraComponent {

	TexturePaint avatar1, avatar2, cadre, versus, separator;
	
	String path; //ONLY FOR DEVELOPMENT
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	private void loadAvatar1(String avatar1){
		try {
	          BufferedImage texture = ImageIO.read(new File(path+"texture/game/vs/"+avatar1+"-left.png"));
	          this.avatar1 =  new TexturePaint(texture, new Rectangle(3, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/"+avatar1+"-left.png' not Found !");
	   }
	}
	

	private void loadAvatar2(String avatar2){
		try {
	          BufferedImage texture = ImageIO.read(new File(path+"texture/game/vs/"+avatar2+"-right.png"));
	          this.avatar2 =  new TexturePaint(texture, new Rectangle(92, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/"+avatar2+"-right.png' not Found !");
	   }
	}
	

	private void loadRessources(){
		try {
	          BufferedImage texture = ImageIO.read(new File(path+"texture/game/vs/cadre.png"));
	          this.cadre =  new TexturePaint(texture, new Rectangle(0, 0, texture.getWidth(), texture.getHeight())); 
	          h = texture.getHeight();
	          w = texture.getWidth();
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/cadre.png' not Found !");
	   }
		
		try {
	          BufferedImage texture = ImageIO.read(new File(path+"texture/game/vs/vs.png"));
	          this.versus =  new TexturePaint(texture, new Rectangle(70, 30, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/vs.png' not Found !");
	   }
		
		try {
	          BufferedImage texture = ImageIO.read(new File(path+"texture/game/vs/separation.png"));
	          this.separator =  new TexturePaint(texture, new Rectangle(91, 3, texture.getWidth(), texture.getHeight()));           
	   } catch (IOException ex) {
		   System.out.println("Error 404: 'texture/game/vs/separation.png' not Found !");
	   }
	}

	public VersusComponent(Player p1, Player p2) {

	    //path= "../";// A commenter sous Ecllipse


		if(p1 != null)
			loadAvatar1(p1.getAvatar());
		if(p2 != null)
			loadAvatar2(p2.getAvatar());
		loadRessources();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;

		g2.setPaint(cadre);
		g2.fillRect(0, 0, w, h);
		
		if(avatar1 != null){
			g2.setPaint(avatar1);
			g2.fillRect(3, 3, avatar1.getImage().getWidth(), avatar1.getImage().getHeight());
		}
		
		if(avatar2 != null){
			g2.setPaint(avatar2);
			g2.fillRect(92, 3, avatar2.getImage().getWidth(), avatar2.getImage().getHeight());
		}
		
		g2.setPaint(separator);
		g2.fillRect(91, 3, separator.getImage().getWidth(), separator.getImage().getHeight());
		
		g2.setPaint(versus);
		g2.fillRect(70, 30, versus.getImage().getWidth(), versus.getImage().getHeight());
		
		
	}
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//3. Create components and put them in the frame.
		//frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		frame.setContentPane(new VersusComponent(new Player("georges", "ninja"), new Player("louis", "panda")));
	
		//4. Size the frame.
		frame.pack();
	
		//5. Show it.
		frame.setVisible(true);	
	}
}

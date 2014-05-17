package Graphic.tetra;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import utility.Letter;
import utility.Player;
import utility.Square;
import GameState.Game;
import GameState.Tetris;
import Graphic.TetraComponent;

public class NextComponent extends TetraComponent {
	
	private int next;
	Game game;
	private HashMap<String, TexturePaint> paints = new HashMap<String, TexturePaint>(7);
	
	public NextComponent(Game game) {
		this.game = game;
		if(game.getState() instanceof Tetris){
			next = ((Tetris)game.getState()).getNextShape();
		}
		h=84;
		w=84;
		
		loadImage();
	}
	
	private void loadImage(){
		String[] paths= {"blue", "dark_gray", "gray", "green", "orange", "pink", "purple", "red", "yellow"};
	      
		for(int i= 0; i < paths.length; ++i ){   
	        try {
	          BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/"+paths[i]+".jpg"));
	          texture.getScaledInstance(21, 21, Image.SCALE_DEFAULT);
	          paints.put(paths[i], new TexturePaint(texture, new Rectangle(0, 0, 21, 21)));      
	       } catch (IOException ex) {
	           System.out.println("Error 404: color missing !" + paths[i]);
	       }   
       }
	}
	
	@Override
	public void update() {
		if(game.getState() instanceof Tetris){
			next = ((Tetris)game.getState()).getNextShape();
		}

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		switch(next){
		 case 0:
         	shapeI("yellow", g2);
             break;
         case 1:
         	shapeO("blue", g2);
             break;
         case 2:
         	shapeT("purple", g2);
             break;
         case 3:
         	shapeL("green", g2);
             break;
         case 4:
         	shapeJ("red", g2);
             break;
         case 5:
         	shapeZ("orange", g2);
             break;
         case 6:
         	shapeS("pink", g2);
             break;
			default: break;
		}
	}

	private void shapeS(String color, Graphics2D g2){

		g2.setPaint(paints.get(color));

		g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);
		g2.fillRect(21*1, 21*2, 21, 21);
		g2.fillRect(21*0, 21*2, 21, 21);
    }
    
    private void shapeZ(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*0, 21*1, 21, 21);
		g2.fillRect(21*1, 21*2, 21, 21);
		g2.fillRect(21*2, 21*2, 21, 21);
    }
    
    private void shapeJ(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);
		g2.fillRect(21*0, 21*1, 21, 21);
		g2.fillRect(21*2, 21*2, 21, 21);
    }
    
    private void shapeL(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);
		g2.fillRect(21*0, 21*1, 21, 21);
		g2.fillRect(21*0, 21*2, 21, 21);
    }
    
    private void shapeO(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*2, 21, 21);
		g2.fillRect(21*2, 21*2, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);
		g2.fillRect(21*1, 21*1, 21, 21);
    }
    
    private void shapeI(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*0, 21*1, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);
		g2.fillRect(21*3, 21*1, 21, 21);
    }
    
    private void shapeT(String color, Graphics2D g2){

    	g2.setPaint(paints.get(color));

    	g2.fillRect(21*1, 21*1, 21, 21);
		g2.fillRect(21*0, 21*1, 21, 21);
		g2.fillRect(21*1, 21*2, 21, 21);
		g2.fillRect(21*2, 21*1, 21, 21);     
    }
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //3. Create components and put them in the frame.
        //frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
        //frame.setContentPane(new NextComponent(null, 6));
    
        //4. Size the frame.
        frame.pack();
    
        //5. Show it.
        frame.setVisible(true); 
	}
}

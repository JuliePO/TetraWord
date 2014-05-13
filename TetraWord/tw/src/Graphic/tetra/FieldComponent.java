package Graphic.tetra;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.bind.Marshaller.Listener;

import Graphic.TetraComponent;
import utility.Board;
import utility.Letter;
import utility.Player;
import utility.Square;

public class FieldComponent extends TetraComponent {
    
	int insetH = 0;
	int insetW = 0;
	
    private Player p;
    private HashMap<String, TexturePaint> paints = new HashMap<String, TexturePaint>(7);
      
    
    private void loadImage(){
        
       String[] paths= {"blue", "green", "orange", "pink", "purple", "red", "yellow"};
              
        try {
            for(int i= 0; i < paths.length; ++i ){
              BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/"+paths[i]+".jpg"));
          //    texture.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
              paints.put(paths[i], new TexturePaint(texture, new Rectangle(insetW, insetH, texture.getWidth(), texture.getHeight())));
            }
       } catch (IOException ex) {
           System.out.println("Error 404: color missing !" );
       }        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponents(g);
        
        Graphics2D g2 = (Graphics2D)g;
        
        //affichage des cubes
       
        if(p.getCases() != null){
            for(Square square : p.getCases()){
            	TexturePaint tmp = paints.get(square.getColor());
                g2.setPaint(tmp);
                
                if(p.getBoard().invert)
                	g2.fillRect(square.getX() * tmp.getImage().getWidth() + insetW, tmp.getImage().getHeight() * square.getY() + insetH, tmp.getImage().getWidth(), tmp.getImage().getHeight());
                else
                	g2.fillRect(square.getX() * tmp.getImage().getWidth() + insetW, 532 - tmp.getImage().getHeight() * square.getY() + insetH , tmp.getImage().getWidth(), tmp.getImage().getHeight());
                
                char[] chartmp = new char[1];
                chartmp[0] = square.getChar();
                g2.setFont(new Font("Serif", Font.BOLD, 20));
                g2.setColor(Color.WHITE);
                
                if(p.getBoard().invert)
                	g2.drawChars(chartmp, 0, 1, square.getX() * 25 + 5, 18 + 25 * square.getY());
                else
                	g2.drawChars(chartmp, 0, 1, square.getX() * 25 + 5, 543 - 25 * square.getY());   
            }
        }
    }
    
    FieldComponent(Player player){
        
        this.w = 275;
        this.h = 550;
        this.p = player;
        
        loadImage();
    }

    @Override
    public void update() {
    }
    
    public static void main(String[] args) {
        Player p = new Player(1, "georges", "ninja");
        p.increaseScore(8000);

        p.getBoard().addCase(new Square(10, 21, new Letter((short) 1, 'k', 0), null, "blue"));
        p.getBoard().addCase(new Square(0, 0, new Letter((short) 1, 'w', 0), null, "green"));
        
        JFrame frame = new JFrame("test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //3. Create components and put them in the frame.
        //frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
        frame.setContentPane(new FieldComponent(p));
    
        //4. Size the frame.
        frame.pack();
    
        //5. Show it.
        frame.setVisible(true); 
    }
}

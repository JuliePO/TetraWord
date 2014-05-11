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
    
    private int xSize = 11;
    private int ySize = 22;
    private Player p;
    private HashMap<String, TexturePaint> paints = new HashMap<String, TexturePaint>(7);
      
    
    private void loadImage(){
        
       String[] paths= {"blue", "green", "orange", "pink", "purple", "red", "yellow"};
              
        try {
            for(int i= 0; i < paths.length; ++i ){
              BufferedImage texture = ImageIO.read(new File(mPath+"texture/game/"+paths[i]+".jpg"));
          //    texture.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
              paints.put(paths[i], new TexturePaint(texture, new Rectangle(0, 0, 25, 25)));
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
        Vector<Square> cases = p.getCases();
        if(cases != null){
            for(Square square : cases){
                g2.setPaint(paints.get(square.getColor()));
                g2.fillRect(square.getX() * 25, 525 - 25 * square.getY(), 25, 25);
                
                char[] tmp = new char[1];
                tmp[0] = square.getChar();
                g2.setFont(new Font("Serif", Font.BOLD, 20));
                g2.setColor(Color.WHITE);
                g2.drawChars(tmp, 0, 1, square.getX() * 25 + 5, 543 - 25 * square.getY());   
            }
        }
    }
    
    FieldComponent(Player player){
        
        this.w = 275;
        this.h = 550;
        this.p = player;
        
        loadImage();
        
        /*this.setLayout(null);
        
        //update();
        Vector<Square> cases = p.getCases();
        
        if(cases != null){
            for(Square square : cases){
                CubeComponent tmp = new CubeComponent("texture/game/"+square.getColor() + ".jpg", "texture/game/"+square.getColor() + ".jpg", square.getChar());
                tmp.setEnabled(false);
                tmp.setBounds(square.getX() * 25, square.getY() * 25, tmp.getWidthTexture(), tmp.getHeightTexture());
                add(tmp);
            }
        }*/
    }

    @Override
    public void update() {
    }
    
    public static void main(String[] args) {
        Player p = new Player(1, "georges", "ninja");
        p.increaseScore(8000);

        p.getBoardTemp().addCase(new Square(10, 21, new Letter((short) 1, 'k', 0), null, "blue"));
        p.getBoardTemp().addCase(new Square(0, 0, new Letter((short) 1, 'w', 0), null, "green"));
        
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

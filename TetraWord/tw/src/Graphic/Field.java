package Graphic;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utility.Board;
import utility.Letter;
import utility.Square;

public class Field extends JPanel {
	
	private int xSize = 11;
	private int ySize = 22;
	
	private JComponent[][] panelHolder = new JComponent[ySize][xSize];
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1100, 2200);
	}
	
	Field(Vector<Square> cases){
		
		this.setLayout(new GridLayout(ySize, xSize));
		

		for(Square square : cases){
			ButtonTetra tmp= new ButtonTetra("texture/game/"+square.getColor() + ".jpg", "texture/game/"+square.getColor() + ".jpg");
			tmp.setEnabled(false);			
			panelHolder[square.getY() - 1][square.getX() - 1] = tmp;
		}
		
		for(int i = 0; i < ySize; ++i){
			for(int j = 0; j < xSize; ++j){
				if(panelHolder[i][j] == null)
					panelHolder[i][j] = new JPanel();
				add(panelHolder[i][j]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Vector<Square> square = new Vector<Square>();
		square.add(new Square(11, 8, new Letter((short)5, 'a'), null, "blue"));
		square.add(new Square(4, 20, new Letter((short)5, 'z'), null, "yellow"));
		
		JFrame tmp = new JFrame("lol");
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tmp.setContentPane(new Field(square));
	
		//4. Size the frame.
		tmp.pack();
		//setSize(200, 200);
	
		//5. Show it.
		tmp.setVisible(true);
	}
}

package Graphic.option.letters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import utility.Configuration;
import Graphic.TetraComponent;
import Graphic.option.PanelOption;
import Graphic.option.TextFieldTetra;

public class LetterComponent extends TetraComponent implements ActionListener{
	private char letter;
	private TextFieldTetra text;
	public boolean upTxt;
	
	public LetterComponent(char letter, int frequence) {
		
		this.letter = letter;
		
		h = 42;
		w = 110;
		
		setLayout(null);
		
		text = new TextFieldTetra(50, 30, ""+frequence, new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 22), Color.BLACK);
		text.setBounds(60, 0, 56, 42);
		add(text);
		
		PlainDocument doc = (PlainDocument) text.getDocument();
	    doc.setDocumentFilter(new MyIntFilter());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("DOSIS-BOLD", Font.BOLD, 40));
		g2.setColor(Color.WHITE);
		g2.drawString(""+letter, 20, 32);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		upTxt = true;
		
	}

	public int getValue(){
		return Integer.parseInt(text.getText());
	}
	

	class MyIntFilter extends DocumentFilter {
	   @Override
	   public void insertString(FilterBypass fb, int offset, String string,
	         AttributeSet attr) throws BadLocationException {
	
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.insert(offset, string);
	
	      if (test(sb.toString())) {
	         super.insertString(fb, offset, string, attr);
	      } else {
	         // warn the user and don't allow the insert
	      }
	   }
	
	   private boolean test(String text) {
	      try {
	         Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	   }
	
	   @Override
	   public void replace(FilterBypass fb, int offset, int length, String text,
	         AttributeSet attrs) throws BadLocationException {
	
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.replace(offset, offset + length, text);
	
	      if (test(sb.toString())) {
	         super.replace(fb, offset, length, text, attrs);
	      } else {
	         // warn the user and don't allow the insert
	      }
	
	   }
	
	   @Override
	   public void remove(FilterBypass fb, int offset, int length)
	         throws BadLocationException {
	      Document doc = fb.getDocument();
	      StringBuilder sb = new StringBuilder();
	      sb.append(doc.getText(0, doc.getLength()));
	      sb.delete(offset, offset + length);
	
	      if (test(sb.toString())) {
	         super.remove(fb, offset, length);
	      } else {
	         // warn the user and don't allow the insert
	      }
	
	   }
	}
	
	public static void main(String[] args) {
		
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LetterComponent bonus = new LetterComponent('C', 3);
			
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
	}

}

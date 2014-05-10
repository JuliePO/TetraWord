package Graphic.option;

import javax.swing.*; 

import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldTetra extends JPanel implements ActionListener{ 
	JTextField tf;
	public boolean upTxt =false;
	
	public TextFieldTetra(int w, int h, String text, Font f, Color c) { 
		
		tf= new JTextField(); 
		tf.setUI(new RoundTextUI()); 
		tf.setPreferredSize(new Dimension(w,h));
		tf.setForeground(c);
		tf.setFont(f);
		tf.setText(text); 
		tf.addActionListener(this);
		add(tf); 
	} 
	
	public void setText(String txt){
		tf.setText(txt);
	}
	
	public String getText(){
		return tf.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		upTxt = true;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	}
}

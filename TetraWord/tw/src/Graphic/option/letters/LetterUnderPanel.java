package Graphic.option.letters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Configuration;
import utility.Player;
import Graphic.ButtonTetra;
import Graphic.PanelBase;
import Graphic.TetraComponent;
import Graphic.option.TetraComboBox;
import Graphic.option.option.JoypadComponent;
import Graphic.option.option.NameComponent;
import Graphic.option.option.OptionUnderPanel;

public class LetterUnderPanel extends PanelBase {
	

	public LetterUnderPanel(Configuration config) {
		state = 'l';
		
		w = 1024;
		h = 700;
		
		setLayout(null);
		
		LetterArray letterArray = new LetterArray(config);
		letterArray.setBounds(0, 233, letterArray.getW(), letterArray.getH()); 
		add(letterArray);
		
		
		String path = mPath + "texture/options/buttons/";
		
		ButtonTetra option = new ButtonTetra(path + "options.png", path +"options_hover.png");
		option.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state = 'o';
			}
		});
		option.setBounds(71, 610, option.getWidthTexture(), option.getHeightTexture());
		add(option);
		
		ButtonTetra shapes = new ButtonTetra(path + "shapes.png", path +"shapes_hover.png");
		shapes.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state = 's';
			}
		});
		shapes.setBounds(416, 610, shapes.getWidthTexture(), shapes.getHeightTexture());
		add(shapes);
		
		ButtonTetra exit = new ButtonTetra(path + "return.png", path +"return_hover.png");
		exit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state = 'r';
			}
		});
		exit.setBounds(760, 610, exit.getWidthTexture(), exit.getHeightTexture());
		add(exit);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("DOSIS-BOLD", Font.BOLD, 45));
		g2.setColor(Color.WHITE);
		g2.drawString("CONFIGURATION", 380, 38);
		g2.drawString("LETTERS", 439, 88);
		

		g2.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		g2.drawString("Rate letters apparition :", 42, 158);
		
		g2.drawLine(40, 169, 260, 169);
	}
	
	@Override
	public void update() {
		((TetraComponent)getComponent(0)).update();
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LetterUnderPanel bonus = new LetterUnderPanel(new Configuration());	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}


}

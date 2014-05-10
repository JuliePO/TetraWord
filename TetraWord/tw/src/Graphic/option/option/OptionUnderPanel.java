package Graphic.option.option;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.Player;
import Graphic.PanelBase;
import Graphic.option.TetraComboBox;
import Graphic.tetra.BonusComponent;

public class OptionUnderPanel extends PanelBase {

	public OptionUnderPanel(Player p1, Player p2) {
		state = 'o';
		
		w = 1024;
		h = 700;
		
		setLayout(null);
		
		NameComponent name1 = new NameComponent(p1, "Player 1");
		name1.setBounds(70, 113, name1.getW(), name1.getH());
		add(name1);
		
		NameComponent name2 = new NameComponent(p2, "Player 2");
		name2.setBounds(607, 113, name2.getW(), name2.getH());
		add(name2);
		
		String[] array = {"Slow", "Normal", "Fast"};
		TetraComboBox<String> speed = new TetraComboBox<>("Speed", array, 91, 263);
		speed.setBounds(70, 187, speed.getWidth(), speed.getHeight());
		add(speed);
		
		TetraComboBox<String> rateBonus = new TetraComboBox<>("Rate bonus", array, 127, 263);
		rateBonus.setBounds(570, 187, rateBonus.getWidth(), rateBonus.getHeight());
		add(rateBonus);
		
		String[] dicoArray = {"Fançais"};
		TetraComboBox<String> dico = new TetraComboBox<>("Dictionnary", dicoArray, 140, 263);
		dico.setBounds(70, 261, dico.getWidth(), dico.getHeight());
		add(dico);
		
		JLabel joypad = new JLabel("Configure joypad :");
		joypad.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		joypad.setForeground(Color.BLACK);
		joypad.setBounds(70, 327, 175, 42);
		add(joypad);
		
		JoypadComponent inputPlayer1 = new JoypadComponent("Player 1", p1);
		inputPlayer1.setBounds(0, 398, inputPlayer1.getW(), inputPlayer1.getH());
		add(inputPlayer1);
		
		JoypadComponent inputPlayer2 = new JoypadComponent("Player 2", p2);
		inputPlayer2.setBounds(511, 398, inputPlayer2.getW(), inputPlayer2.getH());
		add(inputPlayer2);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setFont(new Font("DOSIS-BOLD", Font.BOLD, 45));
		g2.setColor(Color.WHITE);
		g2.drawString("OPTIONS", 442, 56);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionUnderPanel bonus = new OptionUnderPanel(new Player(1, "georges"), new Player(2, "Louis"));	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}

}

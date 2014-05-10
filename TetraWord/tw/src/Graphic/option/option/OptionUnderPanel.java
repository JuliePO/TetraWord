package Graphic.option.option;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utility.Configuration;
import utility.Player;
import Graphic.ButtonTetra;
import Graphic.PanelBase;
import Graphic.option.TetraComboBox;

public class OptionUnderPanel extends PanelBase {
	
	private Configuration config;
	private NameComponent name1;
	private NameComponent name2;
	private TetraComboBox<String> speed;
	private TetraComboBox<String> rateBonus;
	private TetraComboBox<String> dico;
	private JoypadComponent inputPlayer1;
	private JoypadComponent inputPlayer2;

	public OptionUnderPanel(Player p1, Player p2, Configuration config) {
		state = 'o';
		
		w = 1024;
		h = 700;
		
		this.config=config;
		
		setLayout(null);
		
		name1 = new NameComponent(p1, "Player 1");
		name1.setBounds(70, 113, name1.getW(), name1.getH());
		add(name1);
		
		name2 = new NameComponent(p2, "Player 2");
		name2.setBounds(607, 113, name2.getW(), name2.getH());
		add(name2);
		
		String[] array = {"Slow", "Normal", "Fast"};
		speed = new TetraComboBox<>("Speed", array, 91, 263);
		speed.setBounds(70, 187, speed.getWidth(), speed.getHeight());
		add(speed);
		
		rateBonus = new TetraComboBox<>("Rate bonus", array, 127, 263);
		rateBonus.setBounds(570, 187, rateBonus.getWidth(), rateBonus.getHeight());
		add(rateBonus);
		
		dico = new TetraComboBox<>("Dictionnary", config.lang, 140, 263);
		dico.setBounds(70, 261, dico.getWidth(), dico.getHeight());
		add(dico);
		
		JLabel joypad = new JLabel("Configure joypad :");
		joypad.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		joypad.setForeground(Color.WHITE);
		joypad.setBounds(70, 327, 175, 42);
		add(joypad);
		
		inputPlayer1 = new JoypadComponent("Player 1", p1);
		inputPlayer1.setBounds(0, 398, inputPlayer1.getW(), inputPlayer1.getH());
		add(inputPlayer1);
		
		inputPlayer2 = new JoypadComponent("Player 2", p2);
		inputPlayer2.setBounds(511, 398, inputPlayer2.getW(), inputPlayer2.getH());
		add(inputPlayer2);
		
		String path = mPath + "texture/options/buttons/";
		
		/*ButtonTetra letter = new ButtonTetra(path + "letters.png", path +"letters_hover.png");
		letter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				state = 'l';
			}
		});
		letter.setBounds(71, 610, letter.getWidthTexture(), letter.getHeightTexture());
		add(letter);*/
		
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
		g2.drawString("OPTIONS", 442, 56);
	}
	
	@Override
	public void update() {
		name1.update();
		name2.update();
		inputPlayer1.update();
		inputPlayer2.update();


		if(speed.select){
			config.setSpeedGame(speed.getSelectItem());
			speed.select = false;
		}
		if(rateBonus.select){
			config.setRateBonus(rateBonus.getSelectItem());
			rateBonus.select = false;
		}
		if(dico.select){
			config.setLang(dico.getSelectItem());
			dico.select = false;
		}
		
		repaint();
	}
	
	public static void main(String[] args) {
		JFrame tmp = new JFrame();
		
		tmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionUnderPanel bonus = new OptionUnderPanel(new Player(1, "georges"), new Player(2, "Louis"), new Configuration());	
		tmp.setContentPane(bonus);
		tmp.pack();
		tmp.setVisible(true);
		
		
	}

}

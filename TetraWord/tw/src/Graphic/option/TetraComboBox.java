package Graphic.option;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;


public class TetraComboBox<E> extends JLabel {
	
	private JComboBox<E> list;
	public boolean select = false;
	
	public TetraComboBox(String name, E[] elmnt, int lenghtTitre, int lenghtList) {

		JLabel titre = new JLabel(name+" :");
		titre.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		titre.setForeground(Color.BLACK);
		titre.setBounds(0, 0, lenghtTitre, 41);
		add(titre);
		
		
		list = new JComboBox<E>(elmnt);
		list.setFont(new Font("DOSIS-REGULAR", Font.TRUETYPE_FONT, 25));
		list.setForeground(Color.BLACK);
		list.setPreferredSize(new Dimension(lenghtList, 41));
		list.setBounds(lenghtTitre, 0, lenghtList, 41);
		list.addActionListener(new ItemAction());
		add(list);
		
		setPreferredSize(new Dimension(lenghtList + lenghtTitre, 41));
		setSize(new Dimension(lenghtList + lenghtTitre, 41));
	}
	
	class ItemAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			select = true;
		}               
	}
	
	public int getSelectItem(){
		return list.getSelectedIndex();
	}

}

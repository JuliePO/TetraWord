package Graphic;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {
	
	JPanel contentPane = new JPanel();
	
	private char panelState = 's';
	
	Frame(){		
		
		//1. Create the frame.
		setTitle("TetraWord");
	
		//2. Optional: What happens when the frame closes?
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//3. Create components and put them in the frame.
	    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		JPanel menu = new JPanel();

		contentPane.add(new PanelAccueil());
	
		setContentPane(contentPane);
	
		//4. Size the frame.
		pack();
		//setSize(200, 200);
	
		//5. Show it.
		setVisible(true);	
	}
	
	public char getPanelState(){
		return panelState;
	}
	
	public void setPanelState(char newState){
		panelState = newState;
	}
	
	public void update(){
		char tmpState = ((PanelBase)contentPane.getComponent(0)).getState();
		if(getPanelState() != tmpState){
			switch (tmpState) {
			case 'g':
				contentPane.removeAll();
				contentPane.add(new PanelTetraWord());
				setContentPane(contentPane);
				pack();
				setPanelState('g');
				break;

			case 'o':
				contentPane.removeAll();
				contentPane.add(new PanelOption());
				setContentPane(contentPane);
				pack();
				setPanelState('o');
				break;

			case 's':
				contentPane.removeAll();
				contentPane.add(new PanelAccueil());
				setContentPane(contentPane);
				pack();
				setPanelState('s');
				break;

			case 'e':
				setPanelState('e');
				break;

			default:
				break;
			}
		}
	}
	
	
	public static void main(String[] args) {
		
		Frame tmp = new Frame();

		while(tmp.getPanelState() != 'e'){
			tmp.update();
		}
		
		tmp.dispose();
	}
}

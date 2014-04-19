package Graphic;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame {
	
	private int width = 976, height = 695;
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
		onSize();
	
		//5. Show it.
		setVisible(true);	
	}
	
	public char getPanelState(){
		return panelState;
	}
	
	public void setPanelState(char newState){
		panelState = newState;
	}
	
	private void onSize(){
		setSize(width, height);
		//pack();
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void update(){
		

		repaint();
		
		char tmpState = ((PanelBase)contentPane.getComponent(0)).getState();
		if(getPanelState() != tmpState){
			switch (tmpState) {
			case 'g':
				contentPane.removeAll();
				contentPane.add(new PanelTetraWord());
				setContentPane(contentPane);
				onSize();
				setPanelState('g');
				break;

			case 'o':
				contentPane.removeAll();
				contentPane.add(new PanelOption());
				setContentPane(contentPane);
				onSize();
				setPanelState('o');
				break;

			case 's':
				contentPane.removeAll();
				contentPane.add(new PanelAccueil());
				setContentPane(contentPane);
				onSize();
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

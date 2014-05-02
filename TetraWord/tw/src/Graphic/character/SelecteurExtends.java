package Graphic.character;

import Graphic.TetraComponent;
import utility.Player;

public class SelecteurExtends extends TetraComponent{
	
	int p1 = 0;
	int p2 = 1;

	void select(int i, int y){
		p1 = i;
		p2 = y;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}

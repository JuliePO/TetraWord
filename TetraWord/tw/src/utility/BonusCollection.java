package utility;

import java.util.ArrayList;

public class BonusCollection extends ArrayList<String>{
	
	private int sizeMax;
	
	BonusCollection(int size){
		super(size);
		sizeMax = size;
	}
	
	public boolean add(String nBonus){
		
		boolean result = super.add(nBonus);
		
		if(result && size() > sizeMax){
			remove(0);
		}
		
		return result;
	}
}

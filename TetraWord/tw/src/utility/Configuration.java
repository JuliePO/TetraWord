package utility;

import java.util.HashMap;

public class Configuration {
	
	public String[] lang = {"french"};

    private int speedGame;
    private int rateBonus;
    private int dicoLang;
    
    public Configuration() {
		speedGame = 0;
		rateBonus = 0;
		dicoLang = 0;
	}
    
    public void setSpeedGame(int i){
    	speedGame = i;
    }
    
    public void setRateBonus(int i){
    	rateBonus = i;
    }
    
    public void setLang(int i){
    	dicoLang = i;
    }
    
    public int getSpeedGame(){
    	return speedGame;
    }
    
    public int getRateBonus(){
    	return rateBonus;
    }
    
    public String getLang(){
    	
    	return lang[dicoLang];
    }
}

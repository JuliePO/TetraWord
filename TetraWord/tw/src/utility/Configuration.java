package utility;

import java.io.File;

import java.util.HashMap;

public class Configuration {
	
	public String[] lang = {"french"};

    private int speedGame;
    private int rateBonus;
    private int dicoLang;
    private Dictionary dico;
    
    public Configuration() {
		speedGame = 0;
		rateBonus = 0;
		dicoLang = 0;
        dico= new Dictionary("../../"+getLang()+".txt");
        
        File directoryToScan = new File("./"); 
    	System.out.println(directoryToScan.listFiles()[1]);
    	
    	
    	
	}
    
    public void setSpeedGame(int i){
    	speedGame = i;
    }
    
    public void setRateBonus(int i){
    	rateBonus = i;
    }
    
    public void setLang(int i){
    	dicoLang = i;
    	
    	dico = new Dictionary("../"+getLang()+".txt");
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

	public Dictionary getDico() {
		return dico;
	}
	
	public void setCharFrequence(char letter, int frequence){
		dico.setCharFrequence(letter, frequence);
	}
	
	public int getCharFrequence(char letter){
		return dico.getCharFrenquence(letter);
	}
}

package utility;

import java.util.HashMap;
import java.util.Vector;


/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Board board;
    private int score = 0;
    private String name;
    private String avatar;
    private HashMap<String, Character> inputs;

    private Shape currentShape;
    
    private String[] listBonus;
    private int nbBonus;

    /**
     * Constructor for objects of class Player
     */
    public Player(int number)
    {
        board= new Board();
        
        inputs = new HashMap<>(8);
        switch(number){
        case 1:
        	inputs.put("up", new Character('z'));
        	inputs.put("down", new Character('s'));
        	inputs.put("left", new Character('q'));
        	inputs.put("right", new Character('d'));
        	inputs.put("a", new Character('a'));
        	inputs.put("b", new Character('e'));
        	inputs.put("l", new Character('r'));
        	inputs.put("r", new Character('f'));
        	break;
        	
        case 2:
        	inputs.put("up", new Character('8'));
        	inputs.put("down", new Character('5'));
        	inputs.put("left", new Character('4'));
        	inputs.put("right", new Character('6'));
        	inputs.put("a", new Character('7'));
        	inputs.put("b", new Character('9'));
        	inputs.put("l", new Character('-'));
        	inputs.put("r", new Character('+'));
        	break;
        	
        default:break;
        }
        
        listBonus= new String[3];
        nbBonus= 0;
    }
    
    public Player(int number, String name)
    {
        this(number);
        this.name = name;        
    }
    
    public Player(int number, String name, String avatar)
    {
        this(number, name);
        this.avatar = avatar;        
    }
    
    public void newShape(char shape, Dictionary dico){
        currentShape= new Shape(shape, dico, board);
    }
    
    public Shape getShape(){
        return currentShape;
    }
    
    public Board getBoardTemp(){
        return board;
    }
    
    public Vector<Square> getCases(){
        return board.getCases();
    }
    
    public void increaseScore(int i){
        score +=i;
    }
    
    public int getScore(){
        return score;
    }
    
    public void resetScore(){
        score = 0;
    }
    
    public void setName(String newName){
        name = newName;
    }
    
    public String getName(){
        return name;
    }
    
    public void setAvatar(String newAvatar){
        avatar = newAvatar;
    }
    
    public String getAvatar(){
        return avatar;
    }

    public Square getSquareAt(int x, int y){
        return board.getSquareAt(x, y);
    }
    
    public boolean isSquareAt(int x, int y){
        return board.isSquareAt(x, y);
    } 
    
    public void addBonus(String nB){
        if(nbBonus == 3){
            String tmp= listBonus[1];
            listBonus[0]= tmp;
            tmp= listBonus[2];
            listBonus[1]= tmp;
            listBonus[2]= nB;
        }
        else{
            listBonus[nbBonus]= nB;
            ++nbBonus;
        }
    }
    
    public String[] getBonus(){
    	return listBonus;
    }
    
    public void setInput(String input, char key){
    	inputs.put(input, new Character(key));
    }
    
    public char getInput(String input){
    	return inputs.get(input).charValue();
    }
}

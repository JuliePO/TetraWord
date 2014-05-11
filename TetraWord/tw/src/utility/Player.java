package utility;

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

    private Shape currentShape;
    
    private String[] listBonus;
    private int nbBonus;


    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        board= new Board();
        listBonus= new String[3];
        nbBonus= 0;
    }
    
    public Player(String name)
    {
        this();
        this.name = name;        
    }
    
    public Player(String name, String avatar)
    {
        this(name);
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
    
    public int getNbBonus(){
        return nbBonus;
    }
    
    public String getBonus(int i){
        return listBonus[i];
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
    
}

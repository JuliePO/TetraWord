package utility;

import java.awt.Color;


/**
 * Write a description of class Case here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Case
{
    protected int x;
    protected int y;
    
    protected char nextState;
    //Bonus effect
    protected String color;
    
    protected Board field;    

    /**
     * Constructor for objects of class Square
     */
    public Case(int pX, int pY, Board f, String color)
    {
        nextState= '?';
        x = pX;
        y = pY;
        field= f;
        this.color = color;
    }    

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public String getColor(){
        return color;
    }
    
    public char getNextState(){
        return nextState;
    }
    
    public void setNextState(char n){
        //if( f s r )
            nextState= n;
        /*else
            nextState= '?';*/
    }
        
}

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
    public int x;
    public int y;
    
    protected char nextState;
    //Bonus effect
    protected String color;
    
    protected Board field;    

    /**
     * Constructor for objects of class Square
     */
    public Case( int pX, int pY ){
    	x = pX;
    	y = pY;
    }
    public Case(int pX, int pY, Board f, String color)
    {
    	this(pX, pY);
        nextState= '?';
        field= f;
        this.color = color;
    }    
    
    public void setPosition( int x, int y ){
        this.x = x;
        this.y = y;
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
    
    public void changeColor( String color ){
    	//verif
    	this.color= color; 
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

package utility;

/**
 * Write a description of class Letter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Letter
{
    private short value;
    private char character;

    /**
     * Constructor for objects of class Letter
     */
    public Letter(short v, char c)
    {
        value= v;
        character= c;
    }
    
    public char getChar(){
        return character;
    }
    
    public short getValue(){
        return value;
    }

}

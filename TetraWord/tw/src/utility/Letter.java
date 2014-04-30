package utility;

/**
 * Write a description of class Letter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Letter implements Comparable
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
    
    public boolean endChar(){
        if( character == '\0' )
            return true;
            
        return false;
    }
    
    public char getChar(){
        return character;
    }
    
    public short getValue(){
        return value;
    }
    
    @Override
    public boolean equals(Object o){
        if ( o == null )
            return false;
            
        Letter L = (Letter) o;
        if( value ==  L.value  && character == L.character )
            return true;
        return false;
    }
    
    @Override 
    public String toString(){
        String vs= "L: ";
        vs += character + value;
        return vs;
    }
    
    @Override 
    public int compareTo(Object o){
        Letter L = (Letter) o;
      
        if( character == L.character )
            return 0;
        if ( character < L.character )
            return -1;
        return 1;
    }

}

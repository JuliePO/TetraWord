import java.util.Vector;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Vector<Square> cases;
    private boolean[] field; //Maybe Field field;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        cases= new Vector<Square>(0);
        field= new boolean[200];
    }

    public void addCase(Square s)
    {
        cases.add( s );
    }
    
    public Square elmtAt( int index ){
        return cases.elementAt(index);
    }
    
    public boolean[] getField(){
        return field;
    }
}

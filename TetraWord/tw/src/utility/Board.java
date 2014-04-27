package utility;

import java.util.Vector;
/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
    
    private boolean[] field;
    private Vector<Square> cases;
    //Les limites du terrain ?

    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        cases= new Vector<Square>(0);
        field= new boolean[200];
    }
    
    public Board(int width, int height)
    {
        cases= new Vector<Square>(0);
        field= new boolean[width * height];
    }
    
    /**
     * Add an Square object to the list of existing squares
     * 
     * @param  s   a new Square 
     */
    public void addCase(Square s)
    {
        cases.add( s );
    }
    
    /**
     * Return the Square at the position defined
     * 
     * @param  index   the position in the list
     * @return     the Square 
     */
    public Square elmtAt( int index ){
        return cases.elementAt(index);
    }
    
    public boolean[] getField(){
        return field;
    }
    
    public Vector<Square> getCases(){
    	return cases;
    }
    
    public Square getSquareAt(int x, int y){
    	for(Square square : cases){
    		if( square.getX() == x && square.getY() == y)
    			return square;
    	}
    	return null;
    }
    
	public boolean isSquareAt(int x, int y) {
		for(Square square : cases){
    		if( square.getX() == x && square.getY() == y)
    			return true;
    	}
    	return false;
	}
    
    /**
     * Return the state of the given coordinates 
     * 
     * @param  x, y   Coordinates
     * @return     true if there is a Square here 
     */
    boolean isBusy( int x, int y ){
        
        if( x < 0 || x > 9 || y < 0 || y > 19 ) //limite (attribut)
            return true;
        
        return field[ x + y*10 ];
    }
    
    void busyAt( int x, int y ){
        
        field[ x + y*10 ] = true;
    }
    
    void freeAll(){
        
        for( int i= 0; i < 200; ++i ) // limite
                field[i] = false;
    }

}

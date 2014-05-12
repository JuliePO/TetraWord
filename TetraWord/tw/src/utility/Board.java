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
    private int width;
    private int height;
    public boolean invert;
    //Les limites du terrain ?

    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        this( 10, 20 );// 22 apparement
        invert = false;
    }
    
    public int size(){
        return cases.size();
    }
    
    public Board(int width, int height)
    {
        this.width= width;
        this.height= height;
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
    
    public boolean outside( Square s ){//Case?
        return outside(s.getX(), s.getY() );
    }
    
    public boolean outside( int x, int y ){
        if( x >= width || x < 0 )
            return true;
        if( y >= height || y < 0 )
            return true;    
        
        return false;
    }
    
    public void supprAt( int index ){
        Square sq= cases.elementAt(index);
        freeAt( sq.getX(), sq.getY() );
        cases.removeElementAt(index);
    }
    
    public boolean[] getField(){
        return field;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
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
    public boolean isBusy( int x, int y ){
        
        if( x < 0 || x > 9 || y < 0 || y > 19 ) //limite (attribut)
            return true;
        
        return field[ x + y*10 ];
    }
    
    void busyAt( int x, int y ){
        
        field[ x + y*10 ] = true;
    }
    
    public void freeAt( int x, int y ){
        
        field[ x + y*10 ] = false;
    }
    
    public void freeAll(){
        
        for( int i= 0; i < 200; ++i ) // limite
                field[i] = false;
    }

}

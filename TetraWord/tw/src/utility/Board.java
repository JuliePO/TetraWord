package utility;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import utility.Bonus.BonusScore;
import utility.Bonus.BonusTetra;
import utility.Bonus.ExchangePlateau;
import utility.Bonus.MalusScore;
import utility.Bonus.ReturnMalus;
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
    private Vector<BonusTetra> bonus;
    private int width;
    private int height;
    public boolean invert;
    private Random alea;
    //Les limites du terrain ?

    /**
     * Constructor for objects of class Board
     */
    public Board()
    {
        this( 10, 20 );// 22 apparement
        invert = false;
    }
    
    public Board(int width, int height)
    {
        this.width= width;
        this.height= height;
        cases= new Vector<Square>(0);
        field= new boolean[width * height];
        alea= new Random();
        bonus= new Vector<BonusTetra>();
    }
    
    public int nbLines(){
    	
    	int nb= 0;
    	
    	for(int i= 0; i < height; ++i ){
    		int k= 0;
    		for( int j= 0; j < width; ++j ){
    			if( !this.isBusy(j, i) )
    				break;
    			
    			++k;
    		}
    		
    		if( k == width )
    			++nb;
    	}
    			
    	return nb;
    	
    }
    
    public void applyBonus(Player owner, Player rival){
    	
    	Iterator<BonusTetra> it = bonus.iterator();
    	
    	while( it.hasNext() ){
    		BonusTetra bon = it.next();
    		if( bon.ttlBoard <= 0 )
    			bon.remove();
    		else
	    		if( isBusy( bon.getX(), bon.getY() ) ){
	    			bon.addToPlayer();
	    			it.remove();
	    		}
    		--bon.ttlBoard;
    	}
    	
    	int appear = alea.nextInt(300);
    	if(appear <= 1){
    		addBonus(owner, rival);
    	}
    }
    
    public BonusTetra getBonus(int x, int y){
    	for(BonusTetra tmp : bonus){
    		if(tmp.getX() == x && tmp.getY() == y)
    			return tmp;
    	}
    	return null;
    }
    
    public void addBonus(Player J, Player E){
    	
		int tx;
		int ty;
		
		do{
			tx= alea.nextInt(getWidth()-1);
			ty= alea.nextInt(getHeight()-1);
		}
		while( isBusy(tx, ty) );
		
		//System.out.println("Bonus Here -> (" + tx +", " + ty  + ")");
    	
    	switch( alea.nextInt(3) ){
    	
	    	case 0:
	    		bonus.add( new BonusScore( tx, ty, J, E) );
	    		break;
	    	case 1:
	    		bonus.add( new ExchangePlateau( tx, ty, J, E) );
	    		break;
	    	case 2:
	    		bonus.add( new MalusScore( tx, ty, J, E) );
	    		break;
	    	case 3:
	    		bonus.add( new ReturnMalus( tx, ty, J, E) );
	    		break;
    		default:
    			System.err.println("Impossible case (addBonus)");
    			break;
    	}
    	
    }
    
    public Vector<BonusTetra> getBonus(){
    	return bonus;
    }
    
    //Retourne un tableau contenant les numeros des lignes pleines
    public Vector<Integer> hasLines(){
    	
    	Vector<Integer> vect= new Vector<Integer>();    	
    	
    	for(int i= 0; i < height; ++i ){
    		int k= 0;
    		for( int j= 0; j < width; ++j ){
    			if( !this.isBusy(j, i) )
    				break;
    			
    			++k;
    		}
    		
    		if( k == width )
    			vect.add(i);
    	}
    			
    	return vect;
    }
    
    //OBSOLETE
    public void colorLines( Vector<Integer> v, String col ){
    	
    	for(int i= 0; i < v.size(); ++i)
	    	for(int j= 0; j < width; ++j)
	    		if( getSquareAt( j, v.elementAt(i) ) != null)
	    			getSquareAt( j, v.elementAt(i) ).changeColor(col);
    }
    
    public void setLineTo( int line, char newState ){
    	
    	for(int j= 0; j < width; ++j)
    		if( getSquareAt( j, line ) != null)
    			getSquareAt( j, line ).setState(newState);
    }
    
    public void setLinesTo( Vector<Integer> v, char newState ){
    	
    	if( newState == 's' || newState == 'c' || newState == 'n' )
	    	for(int i= 0; i < v.size(); ++i)
	    		setLineTo(v.elementAt(i), newState );		    	
    }
    
    public String getLineString(int line){
    	
    	String vs= "";
    	
    	for(int j= 0; j < width; ++j)
    		if( getSquareAt( j, line ) != null)
    			vs += getSquareAt( j, line ).getChar();
    	
    	return vs;
    }
    
    
    public boolean[] copyField(){
    	
    	boolean[] vf= new boolean[width * height];
    	
    	for(int i= 0; i < width * height; ++i)
    		vf[i] = field[i];
    	
    	return vf;
    }
    
    public int size(){
        return cases.size();
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
        if( x >= width || x < 0 || y >= height || y < 0 )
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
    
    private void fallLine( int line ){
    	
    	for(int i= 0; i < cases.size(); ++i){
    		Square tmp = elmtAt(i);
    		
            if( tmp.getY() == line )
                tmp.fall();
    	}
    	
    }
    
    public void fallFromLine( int line ){
    	
    	for( int i= line; i < height; ++i )
    		fallLine(i);
    }
    
    public void supprLine( int line ){
    	
    	
    	for(int i= 0; i < cases.size(); ++i)
            if( elmtAt(i).getY() == line ){
                supprAt(i);
                --i;
            }
	
    	fallFromLine(line+1);
    }
    
    //ONLY FOR DEVELOPMENT
    public void checkLine(int line){
    	
    	for(int i= 0; i < width; ++i)
    		System.out.print( this.isBusy(i, line) + "(" + i + ")  \t" );
    	
    	System.out.println();
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
        
        if( this.outside(x, y) )
            return true;
        
        return field[ x + y*10 ];
    }
    
    void busyAt( int x, int y ){
    	
    	if( this.outside(x, y) )
        	return;
    	
        field[ x + y*10 ] = true;
    }
    
    public void freeAt( int x, int y ){
    	
    	if( this.outside(x, y) )
        	return;
        field[ x + y*10 ] = false;
    }
    
    public void freeAll(){
        
        for( int i= 0; i < width*height; ++i )
                field[i] = false;
    }

}

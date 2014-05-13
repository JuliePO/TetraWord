package utility;

import java.awt.Color;


/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square extends Case
{
    //private int x;
    //private int y;
    
    Square up;
    Square left;
    Square right;
    Square down;
    
    //char nextState;
    Letter letter;
    //String color;
    
    //boolean[] fieldTemp;
    //private Board field;
    boolean newBloc;
    private char state; // 'n'ormal , 's'elected , 'c'licked 
    

    /**
     * Constructor for objects of class Square
     */
    public Square(int pX, int pY, Letter pL, Board f, String color)
    {
        super( pX, pY, f, color ); 
        //nextState= '?';
        //x = pX;
        //y = pY;
        letter= pL;
        //field= f;
        //this.color = color;
        
        newBloc= true;
        state= 'n';
        setBusy();
    }
    
    public void setBusy(){
        if(field != null)
            field.busyAt( x , y );
        else
            System.out.println("Error : field null");
    }
    
    public void unBusy(){
        if(field != null)
            field.freeAt( x , y );
        else
            System.out.println("Error : field null");
    }
    
    /*boolean isBusyTemp( int x, int y ){
        
        if( x < 0 || x > 9 || y < 0 || y > 19 ) //field.bornes
            return true;
        
        return fieldTemp[ x + y*10 ];
    }*/
    
    public void fall(){
        if( 0 == y )
            System.err.println("Error: Overfall");
        else
            setY( y-1 );
    }

    private boolean isBlocked(){
            
        Square tmp= isNeighbour(x, y-1);
        
        if( tmp != null )
            return tmp.isBlocked();

        if( field.isBusy(x, y-1) )
            return true;
        
    
        return false;
    }
    
    public Square isNeighbour(int x, int y){
        
        if( down != null && x == down.x && y == down.y )
            return down;
        if( left != null && x == left.x && y == left.y )
            return left;
        if( right != null && x == right.x && y == right.y )
            return right;
        if( up != null && x == up.x && y == up.y )
            return up;
            
        return null;
            
    }
    
    public void setState(char s){    	
    	state= s;
    }
    
    public int getState(){
    	return state;
    }
    
    public void setNeighbour( Square up, Square left, Square right, Square down ){
        this.up= up;
        this.left= left;
        this.right= right;
        this.down= down;
    }
    
    public boolean isRightBusy(){
        if( field.isBusy( x+1, y ) && isNeighbour(x+1,y) == null )
            return true;
        return false;
    }
    
    public boolean isLeftBusy(){
        if( field.isBusy( x-1, y ) && null == isNeighbour(x-1,y) )
            return true;
        return false;
    }

    int stopThemAll(){
        
        int pts= 0;
        this.nextState = 's';
        setBusy();
        
        if( newBloc ) // Seuls les nouveaux bloc rapportent des pts
            //System.out.println ("  >> SCORE + 5 << ");
            pts += 5;
            
        newBloc= false;  
    
        if( this.up != null && this.up.nextState != 's' )       
            pts += this.up.stopThemAll();
    
        if( this.left != null && this.left.nextState != 's' )       
            pts += this.left.stopThemAll();
    
        if( this.right != null && this.right.nextState != 's' )     
            pts += this.right.stopThemAll();
    
        if( this.down != null && this.down.nextState != 's' )       
            pts += this.down.stopThemAll();
    
        return pts;
    }


    public boolean becoming(Player J){
    
        this.nextState = 'f'; 
    
        if( this.isBlocked() ){
            J.increaseScore( this.stopThemAll() );
            return true;
        }
    
        if( this.up != null && this.up.nextState == '?'  )
            if( this.up.becoming(J) )
                return true;
    
        if( this.left != null && this.left.nextState == '?'  )
            if( this.left.becoming(J) )
                return true;
    
        if( this.right != null && this.right.nextState == '?'  )
            if( this.right.becoming(J) )
                return true;
    
        if( this.down != null && this.down.nextState == '?'  )
            if( this.down.becoming(J) )
                return true;
    
        return false;
    }
    
    @Override
    public String toString(){
        return "( " + x + ", " + y + " ) " + "| next: " + nextState + " - { " + Character.toUpperCase(letter.getChar()) + " }";
    }
    
    public static void printFieldTemp(boolean[] fd, int range){
        
        for( int j= 0; j < range; ++j )
            if( (j+1)%10 == 0 )
                System.out.println( fd[j] + "\t" );
            else
                System.out.print( fd[j] + "\t" );
                
        System.out.println();        
        
    }
    
    public boolean isNew(){
        return newBloc;
    }

    /*public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }*/
    
    public void setX(int pX){
        unBusy();
        this.x= pX;
        setBusy();
    }
    
    public void setY(int pY){
        unBusy();
        this.y= pY;
        setBusy();
    }
    
    public void translateX( int tx ){
        
        if( x+tx < 0 ){ //a verifier : utiliser plutot fd
            x = 0; 
            return;
        }
        if( x+tx > field.getWidth()-1 ){
            x = field.getWidth()-1;
            return;
        }
        setX( x+tx );
    }
    
    @Override //Case
    public void setPosition( int x, int y ){
        setX(x);
        setY(y);
    }
    
    public char getChar(){
        return letter.getChar();
    }
    
    public static void switchLetters( Square sq1, Square sq2 ){
    	
    	if(sq1 == null || sq2 == null)
    		return;
    	
    	Letter tmp= sq1.letter;	
    	sq1.letter = sq2.letter;
    	sq2.letter= tmp;
    }
    
    /*public String getColor(){
        return color;
    }
    
    public char getNextState(){
        return nextState;
    }
    
    public void setNextState(char n){
        //if( f s r )
            nextState= n;
        else
            nextState= '?';
    }*/
    
    public static void main(String[] args){ 
        
        int nb= 8;
        Square[] squares= new Square[nb];
        
        Player J1= new Player(1);
        Board b= J1.getBoard();
        boolean[] fd= b.getField();
        Dictionary dico= new Dictionary("../../french.txt", null);
        
        
        //printFieldTemp(fd, 40);
        
        //Creation d'une brique -------------------
        squares[0] = new Square( 2, 3, dico.pickLetter(), b , "blue");
        squares[1] = new Square( 3, 3, dico.pickLetter(), b , "blue");
        squares[2] = new Square( 3, 2, dico.pickLetter(), b , "blue");
        squares[3] = new Square( 4, 3, dico.pickLetter(), b , "blue");
        
        squares[0].right= squares[1];
        squares[1].left= squares[0];
        squares[1].down= squares[2];
        squares[1].right= squares[3];
        squares[2].up= squares[1];
        squares[3].left= squares[1];
        //-----------------------------------------
        squares[4] = new Square( 1, 0, dico.pickLetter(), b , "blue");
        squares[5] = new Square( 2, 0, dico.pickLetter(), b , "blue");
        squares[6] = new Square( 3, 0, dico.pickLetter(), b , "blue");
        squares[7] = new Square( 4, 0, dico.pickLetter(), b , "blue");
        
        for(int i= 0; i < nb; ++i )
            b.addCase( squares[i] );
            
        squares= null;
        
        printFieldTemp(fd, 40);
        
        for( int i= 0; i < nb; ++i ) 
            System.out.println( J1.getBoard().elmtAt(i).toString() );
        
        // Calculate nextState ------------------------   
        for( int i= 0; i < nb; ++i )  
            if( b.elmtAt(i).nextState == '?' )
                b.elmtAt(i).becoming(J1);    
        // --------------------------------------------
            
        System.out.println();               
        for( int i= 0; i < nb; ++i ) 
            System.out.println( b.elmtAt(i).toString() );
            
        //UPDATE------------------------  
        
            //Reset  
            b.freeAll();
                
            for( int i= 0; i < nb; ++i ){
                switch( b.elmtAt(i).nextState ){
                    case 's':   b.elmtAt(i).setBusy();
                                break;
                    
                    case 'f':   b.elmtAt(i).fall();
                                b.elmtAt(i).setBusy();
                                break;
                                
                    default : System.err.println( "Error : nextState of a Square is " +  b.elmtAt(i).nextState );
                                
                }
                
                b.elmtAt(i).nextState= '?';
            }
        
        //------------------------------
        
        System.out.println();
        printFieldTemp(fd, 40);
        
    }

    
}


/**
 * Write a description of class Square here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Square
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    
    Square up;
    Square left;
    Square right;
    Square down;
    
    char nextState;
    Letter letter;
    
    boolean[] fieldTemp;// Field field;
    boolean newTemp;
    

    /**
     * Constructor for objects of class Square
     */
    public Square(int pX, int pY, Letter pL, boolean[] fT)
    {
        nextState= '?';
        x = pX;
        y = pY;
        letter= pL;
       
        fieldTemp= fT; // Joueur master;
        
        newTemp= true;
        busyTemp();
    }
    
    void busyTemp(){
        fieldTemp[ x + y*10 ] = true;
    }
    
    boolean isBusyTemp( int x, int y ){
        
        if( x < 0 || x > 9 || y < 0 || y > 19 ) //field.bornes
            return true;
        
        return fieldTemp[ x + y*10 ];
    }
    
    void fall(){
        if( 0 == y ) //field.bornes 
            System.err.println("Error: Overfall");
        else
            --this.y;
    }

    boolean isBlocked(){
    
        if( down != null )
            return down.isBlocked();

        if( isBusyTemp(this.x, this.y-1) )
            return true;
    
        return false;
    }


    void stopThemAll(){
        
        this.nextState = 's';
        
        if( newTemp )
            System.out.println ("  >> SCORE + 5 << ");
        newTemp= false;  
    
        if( this.up != null && this.up.nextState != 's' )       
            this.up.stopThemAll();
    
        if( this.left != null && this.left.nextState != 's' )       
            this.left.stopThemAll();
    
        if( this.right != null && this.right.nextState != 's' )     
            this.right.stopThemAll();
    
        if( this.down != null && this.down.nextState != 's' )       
            this.down.stopThemAll();
    
    }


    boolean becoming(){
    
        this.nextState = 'f'; 
    
        if( this.isBlocked() ){
            this.stopThemAll();
            return true;
        }
    
        if( this.up != null && this.up.nextState == '?'  )
            if( this.up.becoming() )
                return true;
    
        if( this.left != null && this.left.nextState == '?'  )
            if( this.left.becoming() )
                return true;
    
        if( this.right != null && this.right.nextState == '?'  )
            if( this.right.becoming() )
                return true;
    
        if( this.down != null && this.down.nextState == '?'  )
            if( this.down.becoming() )
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

    public static void main(String[] args){ 
        
        int nb= 8;
        Square[] squares= new Square[nb];
        
        Player J1= new Player();
        boolean[] fd= J1.getField();
        Dictionary dico= new Dictionary("../french.txt");
        
        //printFieldTemp(fd, 40);
        
        //Creation d'une brique -------------------
        squares[0] = new Square( 2, 3, dico.pickLetter(), fd );
        squares[1] = new Square( 3, 3, dico.pickLetter(), fd );
        squares[2] = new Square( 3, 2, dico.pickLetter(), fd );
        squares[3] = new Square( 4, 3, dico.pickLetter(), fd );
        
        squares[0].right= squares[1];
        squares[1].left= squares[0];
        squares[1].down= squares[2];
        squares[1].right= squares[3];
        squares[2].up= squares[1];
        squares[3].left= squares[1];
        //-----------------------------------------
        squares[4] = new Square( 1, 0, dico.pickLetter(), fd );
        squares[5] = new Square( 2, 0, dico.pickLetter(), fd );
        squares[6] = new Square( 3, 0, dico.pickLetter(), fd );
        squares[7] = new Square( 4, 0, dico.pickLetter(), fd );
        
        for(int i= 0; i < nb; ++i )
            J1.addCase( squares[i] );
            
        squares= null;
        
        printFieldTemp(fd, 40);
        
        for( int i= 0; i < nb; ++i ) 
            System.out.println( J1.elmtAt(i).toString() );
        
        // Calculate nextState ------------------------   
        for( int i= 0; i < nb; ++i )  
            if( J1.elmtAt(i).nextState == '?' )
                J1.elmtAt(i).becoming();    
        // --------------------------------------------
            
        System.out.println();               
        for( int i= 0; i < nb; ++i ) 
            System.out.println( J1.elmtAt(i).toString() );
            
        //UPDATE------------------------  
        
            //Reset  
            for( int j= 0; j < 40; ++j )
                fd[j] = false;
                
            for( int i= 0; i < nb; ++i ){
                switch( J1.elmtAt(i).nextState ){
                    case 's':   J1.elmtAt(i).busyTemp();
                                break;
                    
                    case 'f':   J1.elmtAt(i).fall();
                                J1.elmtAt(i).busyTemp();
                                break;
                                
                    default : System.err.println( "Error : nextState of a Square is " +  J1.elmtAt(i).nextState );
                                
                }
                
                J1.elmtAt(i).nextState= '?';
            }
        
        //------------------------------
        
        System.out.println();
        printFieldTemp(fd, 40);
        
    }

    
}

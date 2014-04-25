package utility;

import java.awt.Color;


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
    String color;
    
    //boolean[] fieldTemp;
    private Board field;
    boolean newTemp;
    

    /**
     * Constructor for objects of class Square
     */
    public Square(int pX, int pY, Letter pL, Board f, String color)
    {
        nextState= '?';
        x = pX;
        y = pY;
        letter= pL;
        field= f;
        this.color = color;
        
        newTemp= true;
        setBusy();
    }
    
    void setBusy(){
    	if(field != null)
    		field.busyAt( x , y );
    	else
    		System.out.println("Error : field null");
    }
    
    /*boolean isBusyTemp( int x, int y ){
        
        if( x < 0 || x > 9 || y < 0 || y > 19 ) //field.bornes
            return true;
        
        return fieldTemp[ x + y*10 ];
    }*/
    
    void fall(){
        if( 0 == y ) //field.bornes 
            System.err.println("Error: Overfall");
        else
            --this.y;
    }

    boolean isBlocked(){
    
        if( down != null )
            return down.isBlocked();

        if( field.isBusy(x, y-1) )//OLD: if( isBusyTemp(this.x, this.y-1) )
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

    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public String getColor(){
    	return color;
    }
    
    public static void main(String[] args){ 
        
        int nb= 8;
        Square[] squares= new Square[nb];
        
        Player J1= new Player();
        Board b= J1.getBoardTemp();
        boolean[] fd= b.getField();
        Dictionary dico= new Dictionary("../../french.txt");
        
        
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
            System.out.println( J1.getBoardTemp().elmtAt(i).toString() );
        
        // Calculate nextState ------------------------   
        for( int i= 0; i < nb; ++i )  
            if( b.elmtAt(i).nextState == '?' )
                b.elmtAt(i).becoming();    
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

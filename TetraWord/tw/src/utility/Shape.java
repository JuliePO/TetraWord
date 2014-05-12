package utility;


/**
 * Write a description of class Shape here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shape
{
    
    private int minos;
    private Square[] blocs;
    private Dictionary dico;
    private Board bd;

    /**
     * Constructor for objects of class Shape
     */
    public Shape(char shp, Dictionary d, Board b)
    {
        minos= 4;
        blocs= new Square[minos];
        dico= d;
        bd= b;
        
        switch( shp ){
            case 'I':
            case 'O':
            case 'T':
                shapeT("blue");
                break;
            case 'L':
            case 'J':
            case 'Z':
            case 'S':
            default: 
                System.err.println( "Unknown shape !" );
        }
    }
    
    /**
     * Constructor for objects of class Shape
     */
    public Shape(char shp, int m)
    {
        minos= m;
        blocs= new Square[minos];
       
    }
    
    public boolean isArrived(){
        return !blocs[0].isNew();
    }
    
    public void printBlocs(){
    	
    	String vs= "BLOC :";
    	
    	for( int i= 0; i < minos; ++i )
    		vs += "(" + blocs[i].getX() + ", " + blocs[i].getY() + ")";  
    		
    	System.out.println(vs + "***********************************");
    }
    
    private void shapeT(String col){
        //4 - 19
        //int midX= bd.getWidth()/2;
        //int maxY= bd.getHeight() -1;
        
    	int midX= 4;
    	int maxY= 19;
    	
        //Creation des blocs
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX-1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX, maxY-1, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        
        //Mise en relation avec les blocs adjacents
        blocs[0].setNeighbour( null, blocs[1], blocs[3], blocs[2] );
        blocs[1].setNeighbour( null, null, blocs[0], null ); 
        blocs[2].setNeighbour( blocs[0], null, null, null );
        blocs[3].setNeighbour( null, blocs[0], null, null );
        
        //Ajout au plateau
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
        
    }
    
    public void rotate(){
        
        //Génération de vecteurs relatifs
        //blocs[0] for (les autres)
        int xref= blocs[0].getX();
        int yref= blocs[0].getY();
        
        //Square pivot = blocs[0];
        
        int[] vectx= new int[minos-1];
        int[] vecty= new int[minos-1];
        
        for( int i= 0; i < minos-1; ++i )
        	if( bd.outside(blocs[i+1].getY() - yref + xref, yref - blocs[i+1].getX() + xref ) )
        		return;

        
        for( int i= 0; i < minos-1; ++i ){
        	
            vectx[i]= blocs[i+1].getX() - xref;  
            vecty[i]= blocs[i+1].getY() - yref;

            blocs[i+1].setPosition(  xref + vecty[i], yref - vectx[i] );

        } 

    }
    
    public void goRight(){
        
        for( int i= 0; i < minos; ++i )
            if( bd.outside(blocs[i]) || blocs[i].isRightBusy() )
                return;    

        for( int i= 0; i < minos; ++i )
            blocs[i].translateX(1);
        
    }
    
    public void goLeft(){
        
        for( int i= 0; i < minos; ++i )
            if( bd.outside(blocs[i]) || blocs[i].isLeftBusy() )
                return;    

        for( int i= 0; i < minos; ++i )
            blocs[i].translateX(-1);
        
    }

}

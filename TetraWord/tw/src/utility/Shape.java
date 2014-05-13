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
    private boolean cube;

    /**
     * Constructor for objects of class Shape
     */
    public Shape(int shp, Dictionary d, Board b)
    {
        minos= 4;
        blocs= new Square[minos];
        dico= d;
        bd= b;
        
        switch( shp ){
            case 0:
            	shapeI("yellow");
                break;
            case 1:
            	shapeO("blue");
            	cube = true;
                break;
            case 2:
                shapeT("purple");
                break;
            case 3:
            	shapeL("green");
                break;
            case 4:
            	shapeJ("red");
                break;
            case 5:
            	shapeZ("orange");
                break;
            case 6:
            	shapeS("pink");
                break;
            default: 
                System.err.println( "Unknown shape !" );
        }
    }
    
    //OBSOLETE
    public Shape(char shp, Dictionary d, Board b)
    {
        minos= 4;
        blocs= new Square[minos];
        dico= d;
        bd= b;
        
        switch( shp ){
            case 'I':
            	shapeI("yellow");
                break;
            case 'O':
            	shapeO("blue");
                break;
            case 'T':
                shapeT("purple");
                break;
            case 'L':
            	shapeL("green");
                break;
            case 'J':
            	shapeJ("red");
                break;
            case 'Z':
            	shapeZ("orange");
                break;
            case 'S':
            	shapeS("pink");
                break;
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
    
    public boolean isTop(){
    	
    	for( int i= 0; i < minos; ++i )
    		if( blocs[i].getY() >= bd.getHeight()-1 )
    			return true;
    	
    	return false;		
    }
    
    public boolean[] copyField(){
    	
    	return bd.copyField();
    }
    
    public Case[][] copyShape(){
    	
    	Case[][] vc= new Case[4][minos];
    	
    	for(int i= 0; i < 4; ++i){	
	    	for(int j= 0; j < minos; ++j)
	    		vc[3-i][j]= new Case( blocs[j].getX(), blocs[j].getY() );
	    	
	    	for(int j = 0; j < i; ++j){
	    		System.out.println(vc[3-j][0].x +","+ vc[3-j][0].y +" "+vc[3-j][1].x +","+ vc[3-j][1].y +" "+vc[3-j][2].x +","+ vc[3-j][2].y +" "+vc[3-j][3].x +","+ vc[3-j][3].y);
			
	    		rotate( vc[3 - j] );
	    		System.out.println(vc[3-j][0].x +","+ vc[3-j][0].y +" "+vc[3-j][1].x +","+ vc[3-j][1].y +" "+vc[3-j][2].x +","+ vc[3-j][2].y +" "+vc[3-j][3].x +","+ vc[3-j][3].y);
				
	    	}
    	}
    	return vc;
    }
    
    public void printBlocs(){
    	
    	String vs= "BLOC :";
    	
    	for( int i= 0; i < minos; ++i )
    		vs += "(" + blocs[i].getX() + ", " + blocs[i].getY() + ")";  
    		
    	System.out.println(vs + "***********************************");
    }
    
    private void shapeS(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX, maxY-1, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX-1, maxY-1, dico.pickLetter(), bd , col);
        
        blocs[0].setNeighbour( null, null, blocs[1], blocs[2] );
        blocs[1].setNeighbour( null, blocs[0], null, null );
        blocs[2].setNeighbour( blocs[0], blocs[3], null, null );
        blocs[3].setNeighbour( null, null, blocs[2], null );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
    }
    
    private void shapeZ(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX-1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX, maxY-1, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX+1, maxY-1, dico.pickLetter(), bd , col);
        
        blocs[0].setNeighbour( null, blocs[1], null, blocs[2] );
        blocs[1].setNeighbour( null, null, blocs[0], null );
        blocs[2].setNeighbour( blocs[0], null, blocs[3], null );
        blocs[3].setNeighbour( null, blocs[2], null, null );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
    }
    
    private void shapeJ(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX-1, maxY, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX+1, maxY-1, dico.pickLetter(), bd , col);
        
        
        blocs[0].setNeighbour( null, blocs[2], blocs[1], null );
        blocs[1].setNeighbour( null, blocs[0], null, blocs[3] );
        blocs[2].setNeighbour( null, null, blocs[0], null );
        blocs[3].setNeighbour( blocs[1], null, null, null );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
    }
    
    private void shapeL(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX-1, maxY, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX-1, maxY-1, dico.pickLetter(), bd , col);
        
        
        blocs[0].setNeighbour( null, blocs[2], blocs[1], null );
        blocs[1].setNeighbour( null, blocs[0], null, null );
        blocs[2].setNeighbour( null, null, blocs[0], blocs[3] );
        blocs[3].setNeighbour( blocs[2], null, null, null );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
    }
    
    private void shapeO(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY-1, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX+1, maxY-1, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        
        
        blocs[0].setNeighbour( null, null, blocs[1], null );
        blocs[1].setNeighbour( blocs[2], blocs[0], null, null );
        blocs[2].setNeighbour( null, blocs[3], null, blocs[1] );
        blocs[3].setNeighbour( null, null, blocs[2], blocs[0] );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
    }
    
    private void shapeI(String col){
        
    	int midX= 4;
    	int maxY= 19;
    	
        blocs[0] = new Square( midX, maxY, dico.pickLetter(), bd , col);
        blocs[1] = new Square( midX-1, maxY, dico.pickLetter(), bd , col);
        blocs[2] = new Square( midX+1, maxY, dico.pickLetter(), bd , col);
        blocs[3] = new Square( midX+2, maxY, dico.pickLetter(), bd , col);
        
        
        blocs[0].setNeighbour( null, blocs[1], blocs[2], null );
        blocs[1].setNeighbour( null, null, blocs[0], null );
        blocs[2].setNeighbour( blocs[0], null, null, blocs[3] );
        blocs[3].setNeighbour( null, blocs[2], null, null );
        
        for(int i= 0; i < minos; ++i )
            bd.addCase( blocs[i] );
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
    
    public boolean inShape(int x, int y) {
    	
    	for( int i= 0; i < minos-1; ++i )
    		if( blocs[i].getX() == x && blocs[i].getY() == y )
    			return true;
    	
    	return false;
        
    }
    
    public void rotate( Case[] blocs ){
    	
    	//Génération de vecteurs relatifs
        int xref= blocs[0].getX();
        int yref= blocs[0].getY();
        
        int[] vectx= new int[minos-1];
        int[] vecty= new int[minos-1];
        
        for( int i= 0; i < minos-1; ++i ){
        	int nX= blocs[i+1].getY() - yref + xref;
        	int nY= yref - blocs[i+1].getX() + xref;
        	
        	if( bd.outside( nX, nY ) || ( bd.isBusy(nX, nY) && !inShape(nX,nY) ) )
        		return;
        }

        
        for( int i= 0; i < minos-1; ++i ){
        	
            vectx[i]= blocs[i+1].getX() - xref;  
            vecty[i]= blocs[i+1].getY() - yref;

            blocs[i+1].setPosition(  xref + vecty[i], yref - vectx[i] );

        }
    	
    }
    
    public Dictionary getDico(){
    	
    	return dico;
    }
    
    public void rotate(){
    	
    	if( cube ){
    		Square.switchLetters(blocs[0], blocs[1]);
    		Square.switchLetters(blocs[2], blocs[3]);
    		Square.switchLetters(blocs[1], blocs[3]);
    	}
    	else
    		rotate( blocs );
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
    
    public Square[] getSquares(){
    	return blocs;
    }
    
    public int getMinos(){
    	return minos;
    }

}

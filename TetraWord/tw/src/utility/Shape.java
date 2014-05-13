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
    private int oriX;
    private int oriY;
    private int shpNum;

    /**
     * Constructor for objects of class Shape
     */
    public Shape(int shp, Dictionary d, Board b)
    {
        minos= 4;
        oriX = 4;
        oriY= 19;
        dico= d;
        bd= b;
        shpNum= shp;
        
        switch( shp ){
            case 0:
            	blocs= shapeI("yellow", oriX, oriY);
                break;
            case 1:
            	blocs= shapeO("blue", oriX, oriY);
                break;
            case 2:
            	blocs= shapeT("purple", oriX, oriY);
                break;
            case 3:
            	blocs= shapeL("green", oriX, oriY);
                break;
            case 4:
            	blocs= shapeJ("red", oriX, oriY);
                break;
            case 5:
            	blocs= shapeZ("orange", oriX, oriY);
                break;
            case 6:
            	blocs= shapeS("pink", oriX, oriY);
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
    
    public Square[][] copyShape(){
    	
    	Square[][] vc= new Square[4][minos];
    	
    	for(int i= 0; i < 4; ++i){	
	    	for(int j= 0; j < minos; ++j){
	    		int tx= blocs[j].getX();
	    		int ty= blocs[j].getY();
	    		
	    		switch( shpNum ){
		            case 0:
		            	vc[i]= shapeI("green", tx, ty ); 
		                break;
		            case 1:
		            	vc[i]= shapeO("green", tx, ty );
		                break;
		            case 2:
		            	vc[i]= shapeT("green", tx, ty );
		                break;
		            case 3:
		            	vc[i]= shapeL("green", tx, ty );
		                break;
		            case 4:
		            	vc[i]= shapeJ("green", tx, ty );
		                break;
		            case 5:
		            	vc[i]= shapeZ("green", tx, ty );
		                break;
		            case 6:
		            	vc[i]= shapeS("green", tx, ty );
		                break;
		            default: 
		                System.err.println( "Unknown shape !" );
	    		}
	    	}
	    		
	    	//System.out.println(vc[i][0].x +","+ vc[i][0].y +" "+vc[i][1].x +","+ vc[i][1].y +" "+vc[i][2].x +","+ vc[i][2].y +" "+vc[i][3].x +","+ vc[i][3].y);
	    	rotate( vc[i] );
	    	//System.out.println(vc[i][0].x +","+ vc[i][0].y +" "+vc[i][1].x +","+ vc[i][1].y +" "+vc[i][2].x +","+ vc[i][2].y +" "+vc[i][3].x +","+ vc[i][3].y);
    	}
    	return vc;
    }
    
    public void printBlocs(){
    	
    	String vs= "BLOC :";
    	
    	for( int i= 0; i < minos; ++i )
    		vs += "(" + blocs[i].getX() + ", " + blocs[i].getY() + ")";  
    		
    	System.out.println(vs + "***********************************");
    }
    
    private Square[] shapeS(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeZ(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeJ(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeL(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeO(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeI(String col, int midX, int maxY){
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
    }
    
    private Square[] shapeT(String col, int midX, int maxY){
        //4 - 19
        //int midX= bd.getWidth()/2;
        //int maxY= bd.getHeight() -1;
        
    	//int midX= 4;
    	//int maxY= 19;
    	
    	Square[] blocs = new Square[minos];
    	
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
        
        return blocs;
        
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
    	
    	if( shpNum == 1 ){
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
    
    public static void main(String[] args){
    	
    	
    }

}

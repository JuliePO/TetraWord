package utility;


/**
 * Write a description of class AlphaTree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlphaTree
{
    // instance variables - replace the example below with your own
    private Letter L;
    private AlphaTree fg;
    private AlphaTree fd;
    private AlphaTree f;
    
    private Letter[] alphabet;
    /**
     * Constructor for objects of class AlphaTree
     */
    public AlphaTree(Letter l, Letter[] a)
    {
        this.L= l;
        alphabet= a;
    }
    
    public AlphaTree(Letter[] a)
    {
        this.L= null;
        alphabet= a;
    }
    
    //ONLY FOR DEVELOPMENT
    public AlphaTree( boolean t ){
        
        Letter a= new Letter((short)0, 'a');
        Letter z= new Letter((short)0, 'z');
        Letter r= new Letter((short)0, 'r');
        Letter o= new Letter((short)0, 'o');
        Letter m= new Letter((short)0, 'm');
        Letter end= new Letter((short)0, '\0');
        
        Letter[] tab= new Letter[3];
        tab[0] = a;
        tab[1] = r;
        tab[2] = end;
        
        Letter[] tab2= new Letter[4];
        tab2[0] = a;
        //tab2[0] = m;
        tab2[1] = o;
        tab2[2] = z;
        tab2[3] = end;
        
        System.out.println( "true" );
        
        add( tab );
        add( tab2 );
        
    }

    public int add(String word){
        char[] tab= word.toCharArray();
        Letter[] l= new Letter[tab.length+1];
        
        for(int i=0; i < tab.length; ++i){

            if( tab[i]-'a' < 0 || tab[i]-'a' > 26 ){             //Problème des tirets...
                 l[i]= alphabet[0];
                 System.err.println( "ERROR : character '"+ tab[i] +"' unknown" );
            }
            else
                l[i]= alphabet[tab[i]-'a'];
        }
            
        l[tab.length] = alphabet[26];
        
        return add( l );
    }
    
    public int add(Letter[] ls)
    {
        if ( L == null ){
            //System.out.println( "+" + ls[0] );
            L= ls[0];
            
            if( ls[0].endChar() )
                return 1;
            else{
                System.arraycopy(ls, 1, ls, 0, ls.length-1);
                if( f == null )
                    f= new AlphaTree(alphabet);
                return f.add( ls );
            }
                
        }
        else
            if( L.endChar() && ls[0].endChar() )
                return 1;
            else{
                if( L.equals(ls[0]) ){
                    System.arraycopy(ls, 1, ls, 0, ls.length-1);
                    if( f == null )
                        f= new AlphaTree(alphabet);
                    return f.add( ls );
                }
                else
                    if( L.compareTo( ls[0] ) == 1 ){
                        if( fg == null )
                            fg= new AlphaTree(alphabet);
                        return fg.add( ls );
                    }
                    else{
                        if( fd == null )
                            fd= new AlphaTree(alphabet);
                        return fd.add( ls );
                    } 
                }
    }
    
    public int search(String word){
        char[] tab= word.toCharArray();
        Letter[] l= new Letter[tab.length+1];
        
        for(int i=0; i < tab.length; ++i)
            l[i]= alphabet[tab[i]-'a'];
            
        l[tab.length] = new Letter( (short) 0, '\0' );
        
        return search( l );
    }
    
    // -1 Introuvable | 0 prefixe | 1 mot du dico
    public int search( Letter[] ls ){
        
        if( L == null )
            return -1;
            
        else    if( ls[0].endChar() )
                    if( L.endChar() )
                        return 1;
                    else{
                        if( fg == null )
                            return 0;
                        return fg.search( ls );
                    }
                else
                    if( L.equals(ls[0]) ){
                        System.arraycopy(ls, 1, ls, 0, ls.length-1);
                        if( f == null )
                            return -1;
                        return f.search( ls );
                    }
                    else
                        if( L.compareTo( ls[0] ) == 1 ){
                            if( fg == null )
                                return -1;
                            return fg.search( ls );
                        }
                        else{
                            if( fd == null )
                                return -1;
                            return fd.search( ls );
                        }  
        
    }
    
    //ONLY FOR DEVELOPMENT
    public static void findAll( String prefix, char[] lets ){
        
        
        for( int i= 0; i < lets.length; ++i ){
                System.out.print( prefix );
                System.out.print( lets[i] + "\t" );
           
            char[] ls= new char[lets.length-1];
            int j= 0;
            int k= 0;
            while( j < lets.length-1 ){
                
                    ls[j] = lets[k];
                    if( i != k )
                        ++j;
                    ++k;
            }
              
                findAll( prefix + lets[i], ls);
        }
        
    }
    
    public void findWith( String prefix, char[] lets ){
        
        
        for( int i= 0; i < lets.length; ++i ){
                /*   Décommenter pour voir le détail de la recherche
            System.out.print( prefix );
            System.out.print( lets[i] + "\t" );
                */
               
            char[] ls= new char[lets.length-1];
            int j= 0;
            int k= 0;
            while( j < lets.length-1 ){
                
                    ls[j] = lets[k];
                    if( i != k )
                        ++j;
                    ++k;
            }
            
            int tmp= search( prefix + lets[i] );
            if(  tmp != -1 ){
                if( tmp == 1 )
                    System.out.print( prefix + lets[i] + "\t" );
                findWith( prefix + lets[i], ls );
            }
        }
        
    }
    
    public static void main(String[] args){
        
        Dictionary dic= new Dictionary( "../../french.txt" );
        
        
        AlphaTree aT= new AlphaTree( dic.getAlphabet() );
        System.out.println( "go" );
        //aT.add( tab );
        aT.add( "hello" );
        aT.add( "roza" );
        aT.add( "hero" );
        
        //System.out.println( aT.search( tabz ) ) ;
        System.out.println( aT.search( "hello" ) ) ;
        System.out.println( aT.search( "roz" ) ) ;
        System.out.println( aT.search( "hell" ) ) ;
        System.out.println( aT.search( "hel" ) ) ;
        System.out.println( aT.search( "he" ) ) ;
        System.out.println( aT.search( "ho" ) ) ;
        System.out.println( aT.search( "" ) ) ;
        System.out.println( aT.search( "her" ) ) ;
        
        
        char[] randLets= {'h', 'o', 'l', 'r', 'e', 'l'};
        
        //findAll( "", randLets ); 
        System.out.println();
        aT.findWith( "", randLets ); 
        
        
    
    }
}

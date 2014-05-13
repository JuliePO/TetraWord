package utility;


/**
 * Write a description of class AlphaTree here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AlphaTree //Arbre ternaire lexicographique
{
    private Letter L;   //Lettre du noeud courant
    private AlphaTree fg;   //fils gauche
    private AlphaTree fd;   //fils droit
    private AlphaTree f;    //fils direct
    private int nbWords;
    
    private Letter[] alphabet;
    /**
     * Constructor for objects of class AlphaTree
     */
    public AlphaTree(Letter let, Letter[] alph)
    {
        this.L= let;
        alphabet= alph;
    }
    
    public AlphaTree(Letter[] alph)
    {
        L= null;
        alphabet= alph;
    }
    
    //ONLY FOR DEVELOPMENT
    public AlphaTree( boolean t ){
        
        Letter a= new Letter((short)0, 'a', 9);
        Letter z= new Letter((short)0, 'z', 1);
        Letter r= new Letter((short)0, 'r', 1);
        Letter o= new Letter((short)0, 'o', 5);
        Letter m= new Letter((short)0, 'm', 5);
        Letter end= new Letter((short)0, '\0', 0);
        
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

    //0 mot ajoute | 1 mot ignore
    public int add(String word){
        char[] chars= word.toCharArray();
        Letter[] letters= new Letter[chars.length+1];
        
        for(int i=0; i < chars.length; ++i){
            
            if ( !(chars[i] < 'A' || chars[i] > 'Z') )
                chars[i] = Character.toLowerCase(chars[i]);
                
            if( chars[i]-'a' < 0 || chars[i]-'a' > 26 ) // Si caractere special   
                 return 1;
           
            letters[i]= alphabet[chars[i]-'a'];
        }
            
        letters[chars.length] = alphabet[26]; //On rajoute un caractere d'arret a la fin
        
        return add( letters );
    }
    
    public int getNbWords(){
        return nbWords;
    }
    
    public int add(Letter[] ls)
    {
        ++nbWords;
        
        if ( L == null ){
            //System.out.println( "+" + ls[0] );
            L= ls[0];
            
            if( ls[0].endChar() )
                return 0;
            else{
                System.arraycopy(ls, 1, ls, 0, ls.length-1);
                if( f == null )
                    f= new AlphaTree(alphabet);
                return f.add( ls );
            }
                
        }
        else
            if( L.endChar() && ls[0].endChar() )
                return 0;
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
    
    //Traduction d'un mot String en un mot Letter[]
    public int search(String word){
        char[] chars= word.toCharArray();
        Letter[] letters= new Letter[chars.length+1];
        
        for(int i=0; i < chars.length; ++i)
            letters[i]= alphabet[chars[i]-'a'];
            
        letters[chars.length] = new Letter( (short) 0, '\0' , 0);
        
        return search( letters );
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
                    if( L.equals(ls[0]) ){ //Si la 1ere lettre du mot a chercher = la 1ere lettre de l'arbre
                        System.arraycopy(ls, 1, ls, 0, ls.length-1); //On continue la recherche dans le fils direct sans la 1ere lettre
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
    
    public int findWith( String prefix, char[] nextLets ){
        
        int founds= 0;
        
        for( int i= 0; i < nextLets.length; ++i ){
                /*   Decommenter pour voir le detail de la recherche
            System.out.print( prefix );
            System.out.print( lets[i] + "\t" );
                */
            char[] ls= new char[nextLets.length-1];
            int j= 0;
            int k= 0;
            while( j < nextLets.length-1 ){ //On remplit les futures prochaines lettres sans celle juste apres
                
                    ls[j] = nextLets[k];
                    if( i != k )
                        ++j;
                    ++k;
            }
            
            int tmp= search( prefix + nextLets[i] );
            if(  tmp != -1 ){
                if( tmp == 1 ){
                    System.out.print( prefix + nextLets[i] + "\t" );
                    ++founds;
                }
                
                findWith( prefix + nextLets[i], ls ); //On concatene la juste apres avec le prefix deja existant
            }
        }
        
        return founds;
        
    }
    
    public static void main(String[] args){
        
        //Dictionary dic= new Dictionary( "../../french.txt" );
        
        AlphaTree aT= new AlphaTree( null );
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

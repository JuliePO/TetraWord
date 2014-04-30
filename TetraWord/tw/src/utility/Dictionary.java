package utility;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.text.Normalizer;

/**
 * Write a description of class File here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dictionary
{
    private Scanner sc;
    private Letter letters[];
    private Letter alphabet[];
    private AlphaTree tree;
    
    /**
     * Constructor for objects of class File
     */
    public Dictionary(String path)
    {
        generateLetters();
        
        try{
            sc= new Scanner( new FileReader(path) );
        }
        catch( FileNotFoundException e ){
            System.err.println("Error 404: File \""+ path + "\" not Found !");
        }
        
        tree= new AlphaTree( alphabet );
        
        generateTree();
    }
    
    public Letter[] getAlphabet(){
        
        return alphabet;
    }
    
    public void generateTree(){
        int ignored= 0;
        String str= getNextWord();
        while ( str != null ){
            ignored += tree.add( str );
            str= getNextWord();
        }
        System.out.println( tree.getNbWords() + " words added !" );
        System.err.println( "Caution: " + ignored + " words ignored (special character)" );
    }
    
    public void generateLetters(){
        
        int it= -1;
        
        alphabet= new Letter[27];
        
        alphabet[0]= new Letter( (short) 1, 'a' );
        alphabet[1]= new Letter( (short) 3, 'b' );
        alphabet[2]= new Letter( (short) 3, 'c' );
        alphabet[3]= new Letter( (short) 1, 'd' );
        alphabet[4]= new Letter( (short) 1, 'e' );
        alphabet[5]= new Letter( (short) 4, 'f' );
        alphabet[6]= new Letter( (short) 1, 'g' );
        alphabet[7]= new Letter( (short) 4, 'h' );
        alphabet[8]= new Letter( (short) 1, 'i' );
        alphabet[9]= new Letter( (short) 8, 'j' );
        alphabet[10]= new Letter( (short) 10, 'k' );
        alphabet[11]= new Letter( (short) 1, 'l' );
        alphabet[12]= new Letter( (short) 1, 'm' );
        alphabet[13]= new Letter( (short) 1, 'n' );
        alphabet[14]= new Letter( (short) 1, 'o' );
        alphabet[15]= new Letter( (short) 3, 'p' );
        alphabet[16]= new Letter( (short) 8, 'q' );
        alphabet[17]= new Letter( (short) 1, 'r' );
        alphabet[18]= new Letter( (short) 1, 's' );
        alphabet[19]= new Letter( (short) 1, 't' );
        alphabet[20]= new Letter( (short) 1, 'u' );
        alphabet[21]= new Letter( (short) 4, 'v' );
        alphabet[22]= new Letter( (short) 10, 'w' );
        alphabet[23]= new Letter( (short) 10, 'x' );
        alphabet[24]= new Letter( (short) 10, 'y' );
        alphabet[25]= new Letter( (short) 10, 'z' );
        alphabet[26]= new Letter( (short) 0, '\0' );
        
        letters= new Letter[100];
        
        for(int ic= 0; ic < 15; ++ic)
            letters[++it]= alphabet[4];
        for(int ic= 0; ic < 9; ++ic)
            letters[++it]= alphabet[0];
        for(int ic= 0; ic < 8; ++ic)
            letters[++it]= alphabet[8];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[13];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[14];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[17];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[18];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[19];
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= alphabet[20];
        for(int ic= 0; ic < 5; ++ic)
            letters[++it]= alphabet[11];
        for(int ic= 0; ic < 3; ++ic)
            letters[++it]= alphabet[3];
        for(int ic= 0; ic < 3; ++ic)
            letters[++it]= alphabet[12];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[6];   
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[1];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[2];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[15];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[5];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[7];
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= alphabet[21];    
        
        letters[++it]= alphabet[9];
        letters[++it]= alphabet[16];
        letters[++it]= alphabet[10];
        letters[++it]= alphabet[22];
        letters[++it]= alphabet[23];
        letters[++it]= alphabet[24];
        letters[++it]= alphabet[25];
         
    }
  
    public Letter pickLetter(){
  
        int r = (int) (Math.random() * 100);
                /* Décommenter pour voir les lettres random
        if( r%5 == 0 )
            System.out.println( Character.toUpperCase(letters[r].getChar()) + " " );
        else
            System.out.print( Character.toUpperCase(letters[r].getChar()) + " " );
                */
            
        return letters[r];
    }
    
    public int findWith( String s ){
        
        return tree.findWith( "", s.toCharArray() ); 
    }

    public void printNextWord()
    {
        try{
            if( sc.hasNext() )
                
                System.out.println(
                    Normalizer.normalize(sc.next(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                );
            else
                System.err.println( "# No more word #" );
        }
        catch( NullPointerException e ){
            System.err.println("Error: Dictionary cannot be read ! It may not be initialized.");
        }
    }
    
    public String getNextWord(){
        
        try{
            if( sc.hasNext() )
                return Normalizer.normalize(sc.next(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
                
        }
        catch( NullPointerException e ){
            System.err.println("Error: Dictionary cannot be read ! It may not be initialized.");
        }
        
        return null;
    }
    
    public static void main(String[] args){
        
        int nb= 0;
        Dictionary dico;
        System.out.print( "loading..." );    
        
        if( args.length != 0 ) 
            dico= new Dictionary("../../french.txt");
        else
            dico= new Dictionary("../french.txt");
        
        System.out.println( "DONE !" );
        
        Scanner scanner;
        String str;
      
        System.out.println( "Tapez l'ensemble de lettres... " );
        scanner=new Scanner(System.in);
        str=scanner.next();
        
        if( str.isEmpty() )
            return;
        else
            nb = dico.findWith( str );
            
        System.out.println( "\n"+ nb + " words found(s) !" );    
       
    }
}

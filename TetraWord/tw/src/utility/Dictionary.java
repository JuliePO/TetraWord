package utility;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
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
    private Random alea;
    
    /**
     * Constructor for objects of class File
     */
    public Dictionary(String path, Random r)
    {	
    	alea= r;
    	generateAlphabet();
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
    
    private int getSommeFrequence(){
    	int tmp = 0;
    	for(int i = 0; i < 26 ; ++i)
    		tmp += alphabet[i].getFrequence();
    	return tmp;
    }
    
    private void generateAlphabet(){
    	 alphabet= new Letter[27];
         
         alphabet[0]= new Letter( (short) 1, 'a' , 9);
         alphabet[1]= new Letter( (short) 3, 'b' , 2);
         alphabet[2]= new Letter( (short) 3, 'c' , 2 );
         alphabet[3]= new Letter( (short) 1, 'd' , 3);
         alphabet[4]= new Letter( (short) 1, 'e' , 15);
         alphabet[5]= new Letter( (short) 4, 'f' , 2);
         alphabet[6]= new Letter( (short) 1, 'g' , 2);
         alphabet[7]= new Letter( (short) 4, 'h' , 2);
         alphabet[8]= new Letter( (short) 1, 'i' , 8);
         alphabet[9]= new Letter( (short) 8, 'j' , 1);
         alphabet[10]= new Letter( (short) 10, 'k', 1);
         alphabet[11]= new Letter( (short) 1, 'l' , 5);
         alphabet[12]= new Letter( (short) 1, 'm' , 3);
         alphabet[13]= new Letter( (short) 1, 'n' , 6);
         alphabet[14]= new Letter( (short) 1, 'o' , 6);
         alphabet[15]= new Letter( (short) 3, 'p' , 2);
         alphabet[16]= new Letter( (short) 8, 'q' , 1);
         alphabet[17]= new Letter( (short) 1, 'r' , 6);
         alphabet[18]= new Letter( (short) 1, 's' , 6);
         alphabet[19]= new Letter( (short) 1, 't' , 6);
         alphabet[20]= new Letter( (short) 1, 'u' , 6);
         alphabet[21]= new Letter( (short) 4, 'v' , 2);
         alphabet[22]= new Letter( (short) 10, 'w' , 1);
         alphabet[23]= new Letter( (short) 10, 'x' , 1);
         alphabet[24]= new Letter( (short) 10, 'y' , 1);
         alphabet[25]= new Letter( (short) 10, 'z' , 1);
         alphabet[26]= new Letter( (short) 0, '\0' , 0);
    }
    
    private void generateLetters(){
        
        int it= -1;
        
        int sommeFrequence = getSommeFrequence();
        
        letters= new Letter[sommeFrequence];
        
        for(int i = 0; i < 26; ++i){
        	for(int y = 0; y < alphabet[i].getFrequence(); ++y)
        		letters[++it] = alphabet[i];
        }
    }
    
    
    public void setCharFrequence(char letter, int nFrequence){
    	alphabet[letter - 'a'].setFrequence(nFrequence);
    	generateLetters();
    }
    
    public int getCharFrenquence(char letter){
    	return alphabet[letter -'a'].getFrequence();
    }
  
    public Letter pickLetter(){

        return letters[alea.nextInt(getSommeFrequence()-1)];
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
    
    public boolean contains( String ps ){
    	if( tree.search(ps) == 1 )
    		return true;
    	return false;
    }
    
    public static void main(String[] args){
        
        int nb= 0;
        Dictionary dico;
        System.out.print( "loading..." );    
        
        if( args.length != 0 ) 
            dico= new Dictionary("../../french.txt", null);
        else
            dico= new Dictionary("../french.txt", new Random());
        
        System.out.println( "DONE !" );
        
        Scanner scanner;
        String str;
      
        System.out.println( "Tapez l'ensemble de lettres... " );
        scanner=new Scanner(System.in);
        str=scanner.next();
        
        if( str.isEmpty() )
            return;
        else{
            nb = dico.findWith( str );
        	System.out.println( dico.contains(str) );
        }
            
        System.out.println( "\n"+ nb + " words found(s) !" );    
       
    }
}

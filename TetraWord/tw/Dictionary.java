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
    private char[] index;

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
    }
    
    public void generateLetters(){
        
        index= new String( "eeeeeeeeeeeeeeeaaaaaaaaaiiiiiiiinnnnnnoooooorrrrrrssssssttttttuuuuuullllldddmmmggbbccppffhhvvjqkwxyz" ).toCharArray(); 
        System.out.println( index.length );        
    }
    
    public void pickLetter(){
        while(true){
            int r = (int) Math.random() * 100;
            System.out.println(r);
        }
        
        //return index[r];
        //return '?';
    }
    
    public void generate(){
        
        
        sc= null;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
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
}

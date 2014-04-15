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
    private Letter letters[];

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
        
        int it= -1;
        
        Letter a= new Letter( (short) 1, 'a' );
        Letter b= new Letter( (short) 3, 'b' );
        Letter c= new Letter( (short) 3, 'c' );
        Letter d= new Letter( (short) 1, 'd' );
        Letter e= new Letter( (short) 1, 'e' );
        Letter f= new Letter( (short) 4, 'f' );
        Letter g= new Letter( (short) 1, 'g' );
        Letter h= new Letter( (short) 4, 'h' );
        Letter i= new Letter( (short) 1, 'i' );
        Letter j= new Letter( (short) 8, 'j' );
        Letter k= new Letter( (short) 10, 'k' );
        Letter l= new Letter( (short) 1, 'l' );
        Letter m= new Letter( (short) 1, 'm' );
        Letter n= new Letter( (short) 1, 'n' );
        Letter o= new Letter( (short) 1, 'o' );
        Letter p= new Letter( (short) 3, 'p' );
        Letter q= new Letter( (short) 8, 'q' );
        Letter r= new Letter( (short) 1, 'r' );
        Letter s= new Letter( (short) 1, 's' );
        Letter t= new Letter( (short) 1, 't' );
        Letter u= new Letter( (short) 1, 'u' );
        Letter v= new Letter( (short) 4, 'v' );
        Letter w= new Letter( (short) 10, 'w' );
        Letter x= new Letter( (short) 10, 'x' );
        Letter y= new Letter( (short) 10, 'y' );
        Letter z= new Letter( (short) 10, 'z' );
        
        letters= new Letter[100];
        
        for(int ic= 0; ic < 15; ++ic)
            letters[++it]= e;
        for(int ic= 0; ic < 9; ++ic)
            letters[++it]= a;
        for(int ic= 0; ic < 8; ++ic)
            letters[++it]= i;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= n;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= o;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= r;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= s;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= t;
        for(int ic= 0; ic < 6; ++ic)
            letters[++it]= u;
        for(int ic= 0; ic < 5; ++ic)
            letters[++it]= l;
        for(int ic= 0; ic < 3; ++ic)
            letters[++it]= d;
        for(int ic= 0; ic < 3; ++ic)
            letters[++it]= m;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= g;   
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= b;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= c;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= p;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= f;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= h;
        for(int ic= 0; ic < 2; ++ic)
            letters[++it]= v;    
        
        letters[++it]= j;
        letters[++it]= q;
        letters[++it]= k;
        letters[++it]= w;
        letters[++it]= x;
        letters[++it]= y;
        letters[++it]= z;
        
        //index= new String( "eeeeeeeeeeeeeeeaaaaaaaaaiiiiiiiinnnnnnoooooorrrrrrssssssttttttuuuuuullllldddmmmggbbccppffhhvvjqkwxyz" ).toCharArray(); 
               
    }
    
    public Letter pickLetter(){
            /*
        int r= 0;
            
        while(index[r] != 't' ){    
            r = (int) (Math.random() * 100);
            if( r%5 == 0 )
                System.out.println( Character.toUpperCase(index[r]) + " " );
            else
                System.out.print( Character.toUpperCase(index[r]) + " " );
        }
            */
         
        int r = (int) (Math.random() * 100);
        
        if( r%5 == 0 )
            System.out.println( Character.toUpperCase(letters[r].getChar()) + " " );
        else
            System.out.print( Character.toUpperCase(letters[r].getChar()) + " " );
            
        return letters[r];
        
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

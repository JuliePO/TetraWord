
/**
 * Write a description of class Engine here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Engine
{
    // instance variables - replace the example below with your own
    private Dictionary dicFile;

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    void loadDictionary(String lang){
        
        if( lang.toLowerCase().equals("french") )
            dicFile = new Dictionary("../frenich.txt");
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void main(String[] args)
    {
        Engine e= new Engine();
        e.loadDictionary("french");
        
        for( int i= 0; i < 330000; ++i )
            e.dicFile.printNextWord();
            
        System.out.println(" -- END -- " );
        
    }
}

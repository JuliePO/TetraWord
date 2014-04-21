package utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class Anagramme {
	
	//taille minimum pour le resultat d'un anagramme
	private int limit;
	
	//� chacun des mots du dictionnaire est associ� une valeur d�pendant des lettres qui le compose "mot" => m*o*t.
	private TreeMap<Long, TreeSet<String>> dictionnaire = new TreeMap<Long, TreeSet<String>>();
	
	//chaque lettre de l'alphabet a un nombre premier qui lui est attribu�. Ainsi tout les mots qui ont les m�mes lettres ont la m�me valeurs.
	//et une valeur diff�rente des mots n'ayant pas les m�mes lettres
	private short lettersAnagramme[] = {	2,
								            3,
								            5,
								            7,
								            11,
								            13,
								            17,
								            19,
								            23,
								            29,
								            31,
								            37,
								            41,
								            43,
								            47,
								            53,
								            59,
								            61,
								            67,
								            71,
								            73,
								            79,
								            83,
								            89,
								            97,
								            101};
	
	//comparateur pour classer les resultats par taille
	class MyComp implements Comparator<String>{
		 
	    @Override
	    public int compare(String str1, String str2) {
	    	
	    	if(str1.length() > str2.length())
	    		return -1;
	    	else if(str1.length() < str2.length() )
	    		return 1;
	    	else
	    		return str1.compareTo(str2);	    	
	    }
	     
	}
	
	public Anagramme(Scanner sc, int i) {
		importDictionnary(sc);
		this.limit = i;
	}
	
	//on importe le dictionnaire
	private void importDictionnary(Scanner sc){
		
		Boolean test = true;

		while(test){
			try{
	            if( sc.hasNext() ){
	                String tmp = Normalizer.normalize(sc.next(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	                Long value = new Long(getWordValue(tmp));
	                
	                if(dictionnaire.get(value) == null){
	                	TreeSet<String> TreeSet = new TreeSet<String>(); // ne nomme pas des var locales avec des majuscules !! (surtout avec un nom de classe !)
	                	TreeSet.add(tmp); 
	                	dictionnaire.put(value,TreeSet);
	                }
	                else{
	                	dictionnaire.get(value).add(tmp); 
	                }
	            }
	            else
	                test = false;
	        }
	        catch( NullPointerException e ){
	            System.err.println("Error: Dictionary cannot be read ! It may not be initialized.");
	        }
		}
	}
	
	//� partir d'une lettre on retrouve sa valeur dans le tableau de valeurs
	private long getCharValue(char letter){
		if( letter >= 'a' && letter <= 'z'){
			//on d�cale de 97 car 'a' = 97, ainsi on retrouve la case 0 du tableau
			return  lettersAnagramme[letter - 97];}
		else
			return 0;
	}
	
	//calcule de la valeur d'un mot.
	public long getWordValue(String word){
		
		if(word == null)
			return 0;
		
		long result = 1;
		
		for(int i = 0 ; i < word.length(); ++i)
			result *= getCharValue(word.charAt(i));
		
		return result;
	}
	
	//on r�cup�re l'ensemble des mots ayant une valeur commune
	public TreeSet<String> getWords(long value){
		return dictionnaire.get(new Long(value));
	}
	
	//� partir d'un ensemble de lettre, on r�cup�re tout les mots ayant la m�me valeur
	public TreeSet<String> resolve(String word){
		if(word.length() >= limit)
			return getWords(getWordValue(word));
		else
			return null;
	}
	
	//on r�cup�re tout les mots possible � partir d'une combinaison de lettre
	public TreeSet<String> allCombination(String word){
		if(word.isEmpty())
			return null;
		else{
			TreeSet<String> result = new TreeSet<String>(new MyComp());
			TreeSet<String> resolve = resolve(word);
			if(resolve != null)
				result.addAll(resolve);
			//on recommence l'op�ration en retirant chacune des lettres
			for(int i = 0; i < word.length(); ++i){
				StringBuilder tmp = new StringBuilder(word);
				tmp.deleteCharAt(i);
				TreeSet<String>resolveIt = allCombination(tmp.toString());
				if (resolveIt != null)
					result.addAll(resolveIt);
			}
			
			return result;
		}
	}
	
	public static void main(String[] args) {
		
		Scanner sc = null;
		try {
			sc = new Scanner( new FileReader("../french.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(sc != null){
			Anagramme ana = new Anagramme(sc, 5);
			System.out.println(ana.allCombination("gnlrbeuoa"));
		}
	}
    
}

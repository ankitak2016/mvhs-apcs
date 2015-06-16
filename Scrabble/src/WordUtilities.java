
import java.util.Scanner;

/**
 *  Finds all the words that can be formed with a list of letters,
 *  then finds the word with the highest Scrabble score.
 *
 */
 
public class WordUtilities
{
	public static void main (String [] args)
	{
		String input = getInput();
		String [] word = findAllWords(input);
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoretable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoretable);
		System.out.println("\n\n\n" + best + "\n\n\n");
	}
	
	/**
	 *  Enter a list of 3 to 12 letters. It also checks
	 *  all letters to insure they fall between 'a' and 'z'.
	 *
	 *  @return  A string of the letters
	 */
	public static String getInput ( )
	{
		String letters ="";
		boolean done;
		do
		{
			done = true;
			letters = Prompt.getString("List of 3 to 12 letters for search:\t");
			if(letters.length()<3||letters.length() >12){
			done = false;}
		
		//check each letter
		
		for(int cnt = 0;cnt<letters.length();cnt++){
			char c = letters.charAt(cnt);
			if(c<'a' || c>'z'){ 
			done = false;}
			}
	    }
	
	while(!done);
	return letters;
		
	}
	
	/**
	 *  Find all words that can be formed by a list of letters.
	 *
	 *  @param letters  String list of letters
	 *  @return   An array of strings with all words found.
	 */
	public static String [] findAllWords (String letters)
	{		
        String word;
        String [] words = new String[1000];
        int count = 0;
		Scanner infile = OpenFile.openToRead("wordlist.txt.html");
		while(infile.hasNext()){
			word = infile.next();
	
			int len = word.length();
			if(matchletters(word, letters.toLowerCase())){
				if(len>=4){
					
			   words[count++] = word;
				}
			}
		   }
		   String [] found = new String[count];
		   for(int i =0; i<count;i++){
			   found[i]=words[i];
			   
		   }
		
		   return found;  
	}
	
	/**
	 *  Determines if a word can be formed by a list of letters.
	 *
	 *  @param temp  The word to be tested.
	 *  @param letters  A string of the list of letter
	 *  @return   True if word can be formed, false otherwise
	 */
	public static boolean matchletters (String word, String letters)
	{
		
		for(int i=0;i<word.length();i++){
                int ind = letters.indexOf(word.charAt(i));
                if(ind == -1)
                        return false;
                letters = letters.substring(0,ind) + letters.substring(ind+1);
        }
        return true;

	}
	
	/**
	 *  Print the words found to the screen.
	 *
	 *  @param words  The string array containing the words.
	 */
	public static void printWords (String [] words)
	{
		System.out.println();
		for(int i = 0;i < words.length;i++){
				System.out.printf("%s\t", words[i]);
				if((i+1)%10==0) System.out.println();
		}
	

	}
	
	/**
	 *  Finds the highest scoring word according to Scrabble rules.
	 *
	 *  @param word  An array of words to check.
	 *  @param scoretable  An array of 26 integer scores in letter order.
	 *  @return   The word with the highest score.
	 */
	public static String bestWord (String [] word, int [] scoretable)
	{
		String MaxScoreWord = "";
		int max = 0;
		for(int i = 0;i< word.length;i++){
			int score = getScore(word[i], scoretable);
			if(max<score){
				MaxScoreWord = word[i];
			}	
		}
		return MaxScoreWord;
    }
	
	/**
	 *  Calculates the score of a word according to Scrabble rules.
	 *
	 *  @param word  The word to score
	 *  @param scoretable  The array of 26 scores for alphabet.
	 *  @return   The integer score of the word.
	 */
	public static int getScore (String word, int [] scoretable)
	{
		int s = 0;
		int ASCII = 0;
		for(int i = 0;i<word.length();i++){
			char c = word.charAt(i);
			if(Character.isUpperCase(c)){
				ASCII = (int)c - 65;}
			if(Character.isLowerCase(c)){
				ASCII = (int)c-97;}
			s = s + scoretable[ASCII];
		}
		return s;
						

	}
}

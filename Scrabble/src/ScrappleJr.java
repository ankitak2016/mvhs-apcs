import java.util.Scanner;

	/**
	 *  
	 *
	 *  @author Ankita Koratkar
	 *  @version September 23 2014
	 */

	public class ScrappleJr {
		public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
						 1, 1, 1, 1, 4, 4, 8, 4, 10};
		private String tilesRemaining = "AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIII" +
						"JKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
		int PlayerScore = 0;
		int ComputerScore = 0;	
		String letters_selectedUser = "";
		String letters_selectedComp = "";	
		int NUMTILES = 8;
		String userWord ="";
		String compWord = "";
		boolean check = false;
		int FirstTurn = 1;
		
		public static void main(String [] args) {
			ScrappleJr sj = new ScrappleJr();
			System.out.println("Entering the game");
			sj.oneGame();
		}
		
		public void oneGame() {
			boolean userTurn = true;
			boolean endOfGame = false;
			//Letters selected for the computer
			letters_selectedComp =  letterSelection(tilesRemaining, compWord, letters_selectedComp);
			letters_selectedUser = letterSelection(tilesRemaining, userWord, letters_selectedUser);
			
			printIntroduction();
			
			while (! endOfGame) {
			
			    //User's Turn
				if (userTurn) {
					
					//Output Tiles remaining
					printTilesRemaining(tilesRemaining);
					
					//Print the score of the players
					printScore(PlayerScore, ComputerScore);
					
					//Print the letters chosen
					printLetter(letters_selectedUser, letters_selectedComp);
					
					//Ask for the word
					userWord = Prompt.getString("Please enter a word created from your current set of tiles ->");
					
					//Check if word exists in the file
					check = wordCheck(userWord,letters_selectedUser);
					if(check==false) break;
                    
					//Remove tiles used
					//System.out.println("Word found");
					tilesRemaining = changeString(tilesRemaining, userWord);
					
					// Show tiles remaining
					printTilesRemaining(tilesRemaining);
			
			        ///Calculate the Score
					PlayerScore = PlayerScore + scoreCalculation(userWord, scores);
					
					//Show the score
					printScore(PlayerScore, ComputerScore);
					
					//Get tiles for User
					letters_selectedUser = letterSelection(tilesRemaining, userWord, letters_selectedUser);
					if(letters_selectedUser.length()<4) break;
				
			
				}
				// Computer's turn
				else {
					
					//Show Tiles in your hand and computers hand
					printLetter(letters_selectedUser, letters_selectedComp);
					 
					//Its the computers turn : Hit enter
					Prompt.getString("It's the computer's turn. Hit ENTER on the keyboard to continue");
					
					//Find best word that can be made by the letters.
					String [] ALLwords = WordUtilities.findAllWords(letters_selectedComp);
				    if(ALLwords==null) break;
					// Score table in alphabetic order according to Scrabble
					String best = WordUtilities.bestWord(ALLwords,scores);
					compWord = best.toUpperCase();
					
					//Show word chosen by computer
					System.out.println("The computer chose:" + compWord);
					
					//Calculate score of computer
					ComputerScore = ComputerScore + scoreCalculation(compWord, scores);
					
					//Take out letters from the remaining tiles
					tilesRemaining = changeString(tilesRemaining, compWord);
			
					//Get other tiles in Computers hand
					letters_selectedComp =  letterSelection(tilesRemaining, compWord, letters_selectedComp);
					
					if(letters_selectedComp.length()<4) break;
			
				}
				userTurn = ! userTurn;
			}
			
			//Print ending of the game
			if(check==false){
			System.out.println("The typed in word is incorrect");	
			}
			else{
				
			System.out.println("No more words can be created.");
			}
			System.out.println();
			System.out.println();
			printScore(PlayerScore, ComputerScore);
			System.out.println();
			System.out.println("Thank you for playing ScrappleJr!");
			
			
		}
		
		/**
		 *  Print the introduction screen for Scrapple.
		 */
		public void printIntroduction() {
			System.out.print(" _______     _______     ______     ______    ");
			System.out.println(" ______    ______   __          _______");
			System.out.print("/\\   ___\\   /\\  ____\\   /\\  == \\   /\\  __ \\   ");
			System.out.println("/\\  == \\  /\\  == \\ /\\ \\        /\\  ____\\");
			System.out.print("\\ \\___   \\  \\ \\ \\____   \\ \\  __<   \\ \\  __ \\  ");
			System.out.println("\\ \\  _-/  \\ \\  _-/ \\ \\ \\_____  \\ \\  __\\");
			System.out.print(" \\/\\______\\  \\ \\______\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\ ");
			System.out.println(" \\ \\_\\     \\ \\_\\    \\ \\______\\  \\ \\______\\");
			System.out.print("  \\/______/   \\/______/   \\/_/ /_/   \\/_/\\/_/ ");
			System.out.println("  \\/_/      \\/_/     \\/______/   \\/______/ TM");
			System.out.println();
			System.out.print("This game is a \"scaled down\" version of Scrabble. ");
			System.out.println("The game starts with a pool of letter tiles, with");
			System.out.println("the following group of 100 tiles:\n");
			
			for (int i = 0; i < tilesRemaining.length(); i ++) {
				System.out.printf("%c ", tilesRemaining.charAt(i));
				if (i == 49) System.out.println();
			}
			System.out.println("\n");
			System.out.printf("The game starts with %d tiles being chosen at ran", NUMTILES);
			System.out.println("dom to fill the player's hand. The player must");
			System.out.printf("then create a valid word, with a length from 4 to %d ", NUMTILES);
			System.out.println("letters, from the tiles in his/her hand. The");
			System.out.print("\"word\" entered by the player is then checked. It is first ");
			System.out.println("checked for length, then checked to make ");
			System.out.print(" sure it is made up of letters from the letters in the current hand, ");
			System.out.println("and then it is checked against");
			System.out.print("the word text file. If any of these tests fail, the game");
			System.out.println(" terminates. If the word is valid, points");
			System.out.print("are added to the player's score according to the following table ");
			System.out.println("(These scores are taken from the");
			System.out.println("game of Scrabble):");
			
			// Print line of letter scores
			char c = 'A';
			for (int i = 0; i < 26; i++) {
				System.out.printf("%3c", c);
				c = (char)(c + 1);
			}
			System.out.println();
			for (int i = 0; i < scores.length; i++) System.out.printf("%3d", scores[i]);
			System.out.println("\n");
			
			System.out.print("Once the player's score has been updated, more tiles are ");
			System.out.println("chosen at random from the remaining pool");
			System.out.printf("of letters, to fill the player's hand to %d letters. ", NUMTILES);
			System.out.println("The player again creates a word, and the process");
			System.out.print("continues. The game ends when the player enters an invalid ");
			System.out.println("word, or the letters in the pool and");
			System.out.println("player's hand run out. Ready? Let's play!\n");
			
			Prompt.getString("HIT ENTER on the keyboard to continue:");

		}
		
		 /**
		 *  Calculate the score
		 *  
		 *  @param word, scoretable
		 *  @return The score of the word
		 */
		 
		 public int scoreCalculation(String word, int scoretable[]){
			 
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
		 
		 /**
		 *  Selection of the Letters
		 *  @param the pool of tiles, word, set of tiles
		 *  @return selected tiles
		 */
		 
		 
		 public String letterSelection(String Alltiles, String word, String tiles){
			 
			 if(Alltiles.equals("")){
				for(int i =0;i<word.length();i++){
					tiles = tiles.replaceFirst(Character.toString(word.charAt(i)),"");
				
				}
			 }
			 else{
				 
			 
			 
			 //Letter selection for the first time
			 if(word ==""){
				 
			 int randomIndex = 0;
			 int randomIndexCheck[] = new int[8];
			 for(int i =0; i<8;i++){
				 
				 randomIndex =(int)(Alltiles.length()*Math.random());
				 randomIndexCheck[i] = randomIndex;
			 }
			 
			
			 for(int i = 0; i<8;i++){
				 
				tiles = tiles + Alltiles.charAt(randomIndexCheck[i]);
			}
			 }
			 else{
				 
				 int length = word.length();
				 for(int i = 0; i<length;i++){
					 
					 char c = word.charAt(i);
					 String character = Character.toString(c);
					 char d = Alltiles.charAt((int)(Alltiles.length()*Math.random()));
					 String dchar = Character.toString(d);
					 tiles = tiles.replaceFirst(character,dchar);
					 
				 }
				 
			 }
			 }
				 
			return tiles;	 
		 }
		 
		/**
		 *  To print the remaining tiles
		 *  @param the pool of tiles
		 */
		 public void printTilesRemaining(String tiles){
			 
		     System.out.println("The tiles remaining are:");
		     for(int i = 0; i<tiles.length();i++){
		    	 if(i%19==0){
		    		 System.out.println();
		    	 }
		    	 System.out.print(" " + tiles.charAt(i));
		     }
		     System.out.println();
			 
		 }
		 
		 /**
		 *  To change the tiles in the pool by deleting the used tiles
		 *  @param all tiles, word used
		 *  @return the refreshed pool of tiles
		 */
		public String changeString(String tiles , String word){
			
			 String newstr = tiles;
			 
			 for(int i = 0; i < word.length(); i++){
				 
				char c = word.charAt(i);
				String character = Character.toString(c);
				//int LastIndex = tiles.lastIndexOf(c);
				newstr = newstr.replaceFirst(character, "");
				
				
				}
			 
			
			  return newstr;
		}
		
		/**
		 *  Printing Scores
		 *  
		 *  @param user score, computer score
		 */		
		 
		 public void printScore(int uscore, int cscore){
			 
			System.out.println("Player Score:     " +uscore);
			System.out.println("Computer Score:   " +cscore);
		}
		
		 /**
			 *  Displaying the tiles in each players hand
			 *  
			 *  @param user's tiles, comp tiles
			 */	
		public void printLetter (String letter1, String letter2){
			
			System.out.println("THE TILES IN YOUR HAND ARE:         " + letter1);
			System.out.println();
			System.out.println("THE TILES IN THE COMPUTER HAND ARE: " +letter2);
			System.out.println();
		}
		
		/**
		 *  To check the word given by the user
		 *  
		 *  @param user's word, tiles in the users hand
		 *  @return true or false if the word is correct or not
		 */	
		public boolean wordCheck(String wordgiven,String letters){
			
			//returns false if word is not found to end the game
			boolean found = false;
			int k =0;
			String word;
			Scanner infile = OpenFile.openToRead("wordlist.txt.html");
			while(infile.hasNext()){
				word = infile.next();
				if(word.equalsIgnoreCase(wordgiven)){
					found = true;	
				}
			}
			if(found==true){
				
				for(int i =0;i<wordgiven.length();i++){
					for(int j=0;j<letters.length();j++){
						if(wordgiven.charAt(i)==letters.charAt(j)){
							k=k+1;
							break;
						}
							
					}
				}
				if(k==wordgiven.length()) found = true;
				
				if(found==true){
					if(wordgiven.length()<4)
						found = false;
					else
						found = true;
				}
					
				
			}
					
			return found;		
		}
	
		 
	}


/**
 *  This is a two dice game which two player compete
 *  Each player is given a game board which they have to clear
 *  They are given a set of tiles
 *
 *  @author Ankita Koratkar
 *  @version  10/5/2014
 */
import java.util.Scanner;
public class TileBreak 
{
	TileBreakPlayer player1, player2;
	boolean player1turn;
	boolean winner = false;
	public TileBreak(){
	player1 = new TileBreakPlayer();
	player2 = new TileBreakPlayer();
	}
	public static void main(String args[]){
		
		TileBreak tb = new TileBreak();
		tb.play();
		
	}
	
	/**
	 * Plays the game
	 */
	public void play(){
		
		printInstructions();
		enterNames();
		player1turn = startFind();
		printGame();
		int turn = 1;
		
		while(turn<13){
			
			if(player1turn)
			{
				System.out.println();
				System.out.println("Turn "+turn);
				takePlayerTurn(player1);
				winner = checkWin(player1);
				if(winner)
					break;
				
			}
			else{
				
				System.out.println();
				System.out.println("Turn "+turn);
				takePlayerTurn(player2);
				winner = checkWin(player2);
				if(winner)
					break;
			}
			
			player1turn = !(player1turn);
			turn++;
		}
		
		printGame();
		System.out.println();
		if(winner){
			if(player1turn==true)
				System.out.println("Congratulations "+ player1.getName() + ", you WON!!!");
				else
					System.out.println("Congratulations "+ player2.getName() + ", you WON!!!");
		}
		else{
			getWinner();
		}
		
	}
	/**
	 * Prints the instructions to the game.
	 */
	public void printInstructions() {
		System.out.println("+-----------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA TILEBREAK!                                                 |");
		System.out.println("|                                                                                   |");
		System.out.println("| TileBreak is a dice game played between two players. Each player is given a board |");
		System.out.println("| of tile pieces numbered 2 through 12, with one random tile piece eliminated. Each |");
		System.out.println("| player takes turns rolling the dice and eliminating tile pieces that add up to    |");
		System.out.println("| the same value. For example, if the player rolls a 5 and 6 which adds to 11, the  |");
		System.out.println("| player can eliminate tile(s) 2-9, 3-8, 4-7, 5-6, 2-3-6, or 2-4-5. If they can     |");
		System.out.println("| successfully eliminate tiles, then they get to roll again. If none of these tile  |");
		System.out.println("| combinations are available or if the player enters a combination that add to      |");
		System.out.println("| another sum, the player loses his/her turn. Play ends when one player eliminates  |");
		System.out.println("| all their tiles or after 12 turns, whichever happens first. The player with the   |");
		System.out.println("| least tiles when the game ends is the winner. If both players have the same       |");
		System.out.println("| number of tiles, then it is declared a tie.                                       |");
		System.out.println("|                                                                                   |");
		System.out.println("| LET'S PLAY TILEBREAK!                                                             |");
		System.out.println("+-----------------------------------------------------------------------------------+");
		}
		
		//To enter the names of the players
		public void enterNames() {
		Scanner kb = new Scanner(System.in);
		System.out.print("Player 1, please enter your first name : ");
		player1.setName(kb.nextLine());
		System.out.print("\nPlayer 2, please enter your first name : ");
		player2.setName(kb.nextLine());
	}
	/**
	 * Method to determine who goes first
	 *  @return Returns true if player1 starts.
	 */
	public boolean startFind() {
		boolean found = false;
		int score1 = 0, score2 = 0;
		DiceGroup dg = new DiceGroup();
		Scanner kb = new Scanner(System.in);
		while (! found) {
			System.out.printf("\nLet's see who will go first. %s, please hit enter" +
					" to roll the dice : ", player1.getName());
			kb.nextLine();
			dg.rollDice();
			dg.printDice();
			score1 = dg.getTotal();
			System.out.printf("\n%s, it's your turn. Please hit enter to roll the " +
							"dice : ", player2.getName());
			kb.nextLine();
			dg.rollDice();
			dg.printDice();
			score2 = dg.getTotal();
			if (score1 == score2)
				System.out.printf("Whoops, we have a tie (both rolled %d). Looks like" +
							" we'll have to try that again . . .\n", score1);
			else found = true;
		}
		System.out.printf("\n%s, you rolled a sum of %d, and %s, you rolled a sum " +
							"of %d.\n", player1.getName(), score1, player2.getName(),
							score2);
		if (score1 > score2) System.out.print(player1.getName());
		else System.out.print(player2.getName());
		System.out.println(", since your sum was higher, you'll roll first.");
		if (score1 > score2) return true;
		return false;
	}
	
	/**
	 *  Print out the Gameboard
	 */
	public void printGame() {
		player1.getGameBoard().printGameBoard(player1);
		player2.getGameBoard().printGameBoard(player2);
	}
	
	/**
	 * Takes the players turn
	 * @param player   the player
	 */
	public void takePlayerTurn(TileBreakPlayer player){
		
		boolean comb;
		Scanner kb = new Scanner(System.in);
		DiceGroup dg = new DiceGroup();
		System.out.printf("\n%s, it's your turn to play. Please hit enter to roll" +
						" the dice : ", player.getName());
		kb.nextLine();
		boolean takeTurn = true;
		while(takeTurn){
		dg.rollDice();
		dg.printDice();
		int total = dg.getTotal();
		player.getGameBoard().printGameBoard(player);
		//To check whether a tile combination can be made
		comb = tileCombinationCheck(player,total);
		if(comb==false){
			takeTurn = false;
			break;
			
		}
			
		System.out.println("Enter the pieces you would like to remove" + 
		" For example, if you want to remove 2 and 10, enter 210 : ");
		String tile = kb.nextLine();
		int [] tiles_entered = getTiles(tile);
		takeTurn = checkTiles(player, total, tiles_entered);
		if(takeTurn){
			player.getGameBoard().changeGameBoard(tiles_entered);
		}
		else
			break;
	}
		System.out.println("Uh oh! Looks like you lose your turn");
		
	}
	
	/**
	 * Checks the tiles which are entered by the user
	 * @param player,t,te  the player, the total of the dice, tiles entered
	 * @return Tile_Check  tiles are entered correctly
	 */
	public boolean checkTiles(TileBreakPlayer player, int t, int[]te){
		
		boolean Tile_Check = true;
		int sum = 0;
		for(int i = 0; i < te.length; i++){
			
			int num1 = te[i];
			int num2 = player.getGameBoard().getTile(num1);
			if(num2 == -1){
				Tile_Check = false;
				break;
			}
			else
			sum = sum + num1;	
			
		}
		if(Tile_Check==true && (sum==t))
			return true;
		else
			return false;
		
	}
	
	/**
	 * Gets the tiles entered by the user into integers
	 * @param t tiles entered
	 * @return finaltile the final tile array
	 */	
	public int[] getTiles(String t){
		int[] tiles = new int[10];
		int k = 0;
		for(int i = 0; i<t.length();i++){
			char c = t.charAt(i);
			String cstring = Character.toString(c);
			int num1 = Integer.parseInt(cstring);
			if(num1 == 1){
			   
				char d = t.charAt(i+1);
				String dstring = Character.toString(d);
				int num2 = Integer.parseInt(dstring);
				tiles[k] = 10 + num2;
				i = i+1;
			}
			else{
				tiles[k] = num1;
			}
			k++;
				
		}
		int [] finalTile = new int [k];
		for(int i = 0; i<k;i++){
			finalTile[i]= tiles[i];
		}
		return finalTile;
	}
	
	/**
	 * To check whether any combinations can be made
	 * @param player, t the player, total of dice
	 */
	public boolean tileCombinationCheck(TileBreakPlayer player, int t){
		
		boolean combination = false;
			
			for(int i = 2; i<13;i++){
				if(player.getGameBoard().getTile(i) == t){
				
					combination = true;
					break;
				}
			}
				
			for(int i = 2; i<13; i++){
				
				if(player.getGameBoard().getTile(i) == -1)
					continue;
					
				for(int j = 2; j<13;j++){
					
					if(i==j)
						continue;
					if(player.getGameBoard().getTile(j) == -1)
						continue;
					if(((player.getGameBoard().getTile(i))+(player.getGameBoard().getTile(j))) == t){
						combination = true;
						break;
					}
					
				}
				if(combination)	
				break;
			}
		return combination;
}
	
	/**
	 * Checks to see whether there is an immediate winner
	 * @param player 
	 * @return true if the GameBoard is empty
	 */
	public boolean checkWin(TileBreakPlayer player){
		
		
		int k = 0;
		for(int i = 2; i<13;i++){
			
			if(player.getGameBoard().getTile(i) == -1){
				k = k+1;
			}
		}
		if(k==11)
			return true;
		else
			return false;
	}
	
	/**
	 * To get the winner
	 */
	public void getWinner(){
		
		int p1, p2;
		p1=0;
		p2=0;
		for(int i = 2; i< 13;i++){
			if(player1.getGameBoard().getTile(i) == -1){
				p1 = p1+1;
		}
			if(player2.getGameBoard().getTile(i) == -1){
				p2 = p2+1;
			}
	}
		if(p1>p2)
				System.out.println("Congratulations "+ player1.getName() + ", you WON!!!");
		if(p2>p1)
			System.out.println("Congratulations "+ player2.getName() + ", you WON!!!");
		if(p1==p2)
			System.out.println("It's a TIE!");
			
	}
}
	
	

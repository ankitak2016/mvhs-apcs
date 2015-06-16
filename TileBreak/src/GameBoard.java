/**
 *  Contains the game board of the players
 *  Contains methods which modify the game board
 *
 *  @author Ankita Koratkar
 *  @version  10/5/14
 */
public class GameBoard
{
	
	private String tiles = "23456789";
	private int[] tilesPieces;
	
	//Constructor
	public GameBoard(){
		
		int randomTile;
		tilesPieces = new int[13];
	    randomTile = (int)(Math.random()*(12-2)) + 2;
	    //System.out.println(randomTile);
		for(int i = 2 ; i <13;i++){
			if(i==randomTile)
			tilesPieces[i] = -1;
			else
			tilesPieces[i] = i;
		}
			
	  
}
	
	/**
	 * To print the gameboard of the player
	 * @player player
	 */
    public void printGameBoard(TileBreakPlayer player) {
		
		
		int num = 2;
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"-----------------+\n");
						System.out.printf("| %-12s |", player.getName());
		for (int i = 2; i < 13; i++) {
			if (getTile(i) == -1)
			 System.out.printf("    |");
			 else
			 System.out.printf(" %2d |", getTile(i));
			
			num++;
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"-----------------+\n");
		
	}
    
    /**
	 * To get the tile of a particular position
	 * @param i    position
	 * @return tiles at that position
	 */
	 public  int getTile(int i) {
		return tilesPieces[i];
	}
	 
	 /**
	  * To change the game board
	  * @param t array of tiles
	  */
	 public void changeGameBoard(int []t){
		 
		 for(int i = 0; i< t.length;i++){
			 int num1 = t[i];
			 tilesPieces[num1] = -1;
		 }
	 }
}

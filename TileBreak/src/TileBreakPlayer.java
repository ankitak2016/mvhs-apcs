/**
 * Contains methods to maintain the player
 * Contains the name and game board of the player
 *
 *  @author Ankita Koratkar
 *  @version  10/5/2014
 */
public class TileBreakPlayer
{
	private GameBoard gameboard;
	private String name;
	//constructor
	public TileBreakPlayer ()
	{
		gameboard = new GameBoard();
	}
	
	/**
	 * To set the name of the player
	 */
	public void setName(String n){
		name = n;
	}
	
	/**
	 * Return the name of the player
	 * @return name   name of the player
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the gameboard of the player
	 * @return gameboard
	 */
	public GameBoard getGameBoard(){
		return gameboard;
	}
}
	
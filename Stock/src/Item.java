/**
 *  Each Item of the store list
 *
 *  @author Ankita Koratkar
 *  @version  1/8/15
 */

public class Item{
	
  private int myId;
  private int myInv;

	public Item(int id, int inv){
		myId = id;
		myInv = inv;
	}
	
	/**
	 * To return the ID number
	 * 
	 * @return myId
	 */
	public int getId(){
		
		return myId;
	}
	
	/**
	 * To return the number of items
	 * 
	 * @return myInv
	 */
	public int getInv(){
		
		return myInv;
	}
	
	/**
	 * The compareTo method
	 * 
	 * @return -1 if lesser 
	 *          1 if greater
	 */
	public int compareTo(Item other){
		
		if(myId-other.getId()>0)
		return 1;
		else
		return -1;
		
	 }
	
	/**
	 * To check if IDs are equal
	 * 
	 * @return true if equal else false
	 */
	public boolean equals(Item other){ 
		
		if(myId==other.getId())
		return true;
		else
		return false;
		}
	
	/**
	 * To return the Id and number of items
	 * 
	 * @param st
	 */
	public String toString(){
		
		String st = myId + "         " + myInv;
		return st;
	}
}

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
//import info.gridworld.actor.Egg;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Actor;
import java.util.ArrayList;
public class Fox extends Critter{
	
	/**
     * A critter acts by getting a list of other actors, processing that list,
     * getting locations to move to, selecting one of them, and moving to the
     * selected location.
     */
	
	public Fox(){
	setColor(null);
	}
	
	/**
	 * Overriding the act method of the critter class
	 */
    public void act()
    {
        if (getGrid() == null)
            return;

        ArrayList<Location> Chicken_Egg = getAllChickenOrEgg();
        if(getLocation().getRow()==0&&getLocation().getCol()==0&&Chicken_Egg.size()==0){
        	setDirection(0);
        	return;
        }
        if(Chicken_Egg.size()==0){
        	moveToZero();
        	return;
        }
        ArrayList<Actor> actors = getActors();
        boolean eggfound = eatAdjacentEggs(actors);
        if(eggfound)
        return;
        boolean chickenfound = eatAdjacentChickens(actors);
        if(chickenfound)
        return;
        ArrayList<Actor> Grid_Actors = getGridFiveActors();
        boolean grideggfound = eatAdjacentEggs(Grid_Actors);
        if(grideggfound)
        return;
        boolean gridchickenfound = eatAdjacentChickens(Grid_Actors);
        if(gridchickenfound)
        return;
        Location close_Actor = getClosestLocation();
        if(close_Actor==null)
        	return;
        MoveTowardsActor(close_Actor);
        
        
        
        //processActors(actors);
        //ArrayList<Location> moveLocs = getMoveLocations();
        //Location loc = selectMoveLocation(moveLocs);
        //makeMove(loc);
    }
    /**
     * A method to eat a random egg from a list of actors 
     * found in the adjacent locations
     * Returns true if an egg is eaten
     * @param actors a list of the adjacent actors
     * @return true if egg is eaten 
     */
    public boolean eatAdjacentEggs(ArrayList<Actor> actors){
		
		for(int i = 0; i<actors.size();i++){
			
			Actor a = actors.get(i);
			if(a instanceof Egg){
				Location l = a.getLocation();
				int ang = getLocation().getDirectionToward(l);
				setDirection(ang);
				a.removeSelfFromGrid();
				moveTo(l);
				return true;
			}

		}
		return false;
	}
	
    /**
     * Method to eat a random chicken from
     * a given list of actors
     * 
     * @param actors list of adjacent actors
     * @return true if a chicken is eaten 
     *         else false
     */
	public boolean eatAdjacentChickens(ArrayList<Actor> actors){
		
				for(int i = 0; i<actors.size();i++){
			
			Actor a = actors.get(i);
			if(a instanceof Chicken){
				Location l = a.getLocation();
				int ang = getLocation().getDirectionToward(l);
				setDirection(ang);
				a.removeSelfFromGrid();
				moveTo(l);
				return true;
			}

		}
		return false;
		
	}
	
	/**
	 * A method to retrieve the actors in
	 * the five by five grid
	 * 
	 * @return gridactor a list of the actors
	 */
	public ArrayList<Actor> getGridFiveActors(){
		
		ArrayList<Actor> gridactor = new ArrayList<Actor>();
		int col = getLocation().getCol();
		int row = getLocation().getRow();
		int colmin = col - 2;
		int colmax = col+2;
		int rowmin = row - 2;
		int rowmax = row + 2;
		if(colmin<0)
		colmin = 0;
		if(rowmin<0)
		rowmin = 0;
		if(rowmax>=getGrid().getNumRows())
		rowmax = getGrid().getNumRows() - 1;
		if(colmax>=getGrid().getNumCols())
		colmax = getGrid().getNumCols() - 1;
		for(int i = rowmin;i<=rowmax;i++){
			for(int j = colmin; j<=colmax; j++){
				if(i==row&&j==col)
				continue;
				Actor a = getGrid().get(new Location(i,j));
				if(a==null)
				continue;
				gridactor.add(a);
				
			}
		}
		return gridactor;
	}
	

	/**
	 * A method to create a list of all
	 * chickens and eggs in the entire grid
	 * 
	 * @return chickenegg list of all chickens and eggs
	 */
	public ArrayList<Location> getAllChickenOrEgg(){
		
	ArrayList<Location> chickenegg = new ArrayList<Location>();
	ArrayList<Location> all = getGrid().getOccupiedLocations();
	for(int i = 0; i < all.size();i++){
			
		      Actor a = getGrid().get(all.get(i));
		      if(a instanceof Chicken || a instanceof Egg)
		      chickenegg.add(all.get(i));
			}
		
	return chickenegg;
	}
	
	/**
	 * Method to move the fox to the (0,0)
	 * position
	 */
	public void moveToZero(){
		Location loc = getLocation();
		int direction = loc.getDirectionToward(new Location(0,0));
		Location loc2 = loc.getAdjacentLocation(direction);
		if(getGrid().isValid(loc2)&&getGrid().get(loc2)==null)
		{
			setDirection(direction);
			moveTo(loc2);
		}
			else{
			setDirection(getDirection() + Location.HALF_RIGHT);
			if(getGrid().isValid(loc.getAdjacentLocation(getDirection())))
			moveTo(loc.getAdjacentLocation(getDirection()));
			else
	        setDirection(getDirection()+Location.HALF_RIGHT);
			
			}
	}
	
	/**
	 * Method to recieve the closest location 
	 * to the fox
	 * 
	 * @return loc the closest location
	 */
	public Location getClosestLocation(){
		
		ArrayList<Location> act = getAllChickenOrEgg();
		int minimum = 0;
		Location loc = null;
		int row = getLocation().getRow();
		int col = getLocation().getCol();

		
		for(int i = 0; i<act.size();i++){
			if(act.get(i)==null)
			continue;
			int r = act.get(i).getRow();
			int c = act.get(i).getCol();
			int difr = Math.abs(row-r);
			int difc = Math.abs(col-c);
			int distsq = (difr*difr) + (difc*difc);
			int dist = (int)Math.sqrt(distsq);

			if(i==0){
				minimum = dist;
				loc=act.get(0);
				continue;
			}
			if(dist<minimum){
				loc = act.get(i);
				minimum = dist;
			}
			
			
		}
		return loc;
		

	}
	
	/**
	 * Method to move towards the closest
	 * location
	 * 
	 * @param a the closest location
	 */
	public void MoveTowardsActor(Location a){
		
		Location loc = getLocation();
		int direction = loc.getDirectionToward(a);
		Location loc2 = loc.getAdjacentLocation(direction);
		if(getGrid().isValid(loc2)&&getGrid().get(loc2)==null){
			setDirection(direction);
			moveTo(loc2);
		}
		else{
			setDirection(getDirection() + Location.HALF_RIGHT);
		}
		
	}
}
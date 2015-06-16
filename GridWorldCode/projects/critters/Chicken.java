import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
//import info.gridworld.actor.Egg;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import info.gridworld.actor.Actor;
import java.util.ArrayList;
public class Chicken extends Critter{
	
	    int incubation = 0;
	    int probability = 0;
	    int numOfMoves = 0;

		
		public Chicken(int prob , int inc){
			
			setColor(null);
			probability = prob;
			incubation = inc;
		}
		
		 /**
		  * Overridden method of the act method
		  */
		public void act()
		{
			
			if (getGrid() == null)
			return;
			numOfMoves++; 
			int egglay = (int)(Math.random()*incubation);
			if(egglay == 1){
				Location loc = getLocation();
				Location loc1 = loc.getAdjacentLocation(getDirection());
				if(getGrid().isValid(loc1)&&getGrid().get(loc1)==null){
					moveTo(loc1);
					Egg eg = new Egg(probability, incubation);
					//getGrid().put(new Location(loc.getRow(),loc.getCol()), new Egg(probability, incubation));
					eg.putSelfInGrid(getGrid(), loc);
					int r = loc.getRow();
					int c = loc.getCol();
					//System.out.println(r+c);
					
				}
				
			}
			if(numOfMoves>=1200&&numOfMoves<1320){
			}
			else if(numOfMoves == 1320){
				
				Location loc = getLocation();
				removeSelfFromGrid();
				
			}
            else{
			ArrayList<Actor> actors = getActors();
			//processActors(actors);
			//ArrayList<Location> moveLocs = getMoveLocations();
			int n = (int)(Math.random()*9);
			switch(n){
			
			case 0:{
				Location loc = getLocation();
				Location loc2 = loc.getAdjacentLocation(getDirection());
				if(getGrid().isValid(loc2)&&getGrid().get(loc2)==null&&!(getGrid().get(loc2) instanceof Chicken) && !(getGrid().get(loc2) instanceof Egg))
					moveTo(loc2);
				else
					setDirection(getDirection() + Location.HALF_RIGHT);
				break;
			}
			case 1:
				setDirection(Location.EAST);
				break;
			case 2:
				setDirection(Location.WEST);
				break;
			case 3:
				setDirection(Location.NORTH);
				break;
			case 4:
				setDirection(Location.NORTHEAST);
				break;
			case 5:
				setDirection(Location.SOUTHEAST);
				break;
			case 6:
				setDirection(Location.NORTHWEST);
				break;
			case 7:
				setDirection(Location.SOUTH);
				break;
			case 8:
				setDirection(Location.SOUTHWEST);
				break;
			}
			
		
		}
		}
		 public void processActors(ArrayList<Actor> actors)
		{
		}
	}

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import java.awt.Color;
import info.gridworld.actor.Actor;


public class Egg extends Actor{
	
	private static final double DARKENING_FACTOR = 0.05;
    int numCalls = 0;
    int incub = 0;
    int numCallsAfterRed = 0;
    int probability2 = 0;

    public Egg(int prob, int inc)
    {
        setColor(Color.WHITE);
        incub = inc;
        probability2 = prob;
	}
	
        /**
         * Overidden act method
         */
	    public void act()
	    {
	    	numCalls++;
	    	if(numCalls< (int)((90.0/100)*incub))
	    	{
				Color c = getColor();
				int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
				int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
				int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

				setColor(new Color(red, green, blue));  
	    	}
	    	if(numCalls == (int)((90.0/100)*incub))
	    		setColor(Color.RED);

       
	    	if(numCalls == incub){
	    		Location loc = getLocation();
	    		getGrid().remove(loc);
	    		Chicken chic = new Chicken(probability2,incub);
	    		chic.putSelfInGrid(getGrid(), loc);
		   //getGrid().put(loc, new Chicken(probability2,incub));
	    		}
       
    
    
	    }
}
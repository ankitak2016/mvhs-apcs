
/**
 * 
 * To print and roll dice
 *  
 *
 *  @author Ankita Koratkar
 *  @version  8/20/2014
 */
 public class DiceGroup {
	 Dice[]die;
	 	
	 	// Create the seven different line images of a die
	String [] line = {	" _______ ",
						"|       |",
						"| O   O |",
						"|   O   |",
						"|     O |",
						"| O     |",
						"|_______|" };
	
	
	 
	 public DiceGroup() {
		 die = new Dice[2];
		 for(int i =0;i<2;i++){
			 die [i] = new Dice();
		 }
	 }
		 public static void main(String args[]){
			 DiceGroup dg = new DiceGroup();
			 dg.rollDice();
			 dg.printDice();
		 }
		 
		 /**
         *  Roll all dice
         *
         */
		 public void rollDice() {
			 for(int i = 0; i<die.length;i++)
				 die[i].roll();
			 
		 }
		 
		 /**
         *  Roll only selected die
         *
         */
		 public void rollDice(String rawHold){
			 for(int i = 0;i< die.length;i++){
				 String c = (i+1) +"";
				 if(rawHold.indexOf(c) == -1)
				    die[i].roll();
			 }
		 }
		 
		 public Dice getDie(int i){
			 return die[i];
		 }
		 
		  
		 /**
         *  Computes the sum of the Dice Group
         *
         */
         
         public int getTotal(){
			 int sum = 0;
			 for(int i =0; i < die.length;i++)
			 sum+= die[i].getValue();
			 return sum;
		 }
		 
		 
		 
/**
	 *  Prints out the images of the dice
	 */
	public void printDice() {
		printDiceHeaders();
		for (int i = 0; i < 6; i++) {
			System.out.print(" ");
			for (int j = 0; j < die.length; j++) {
				printDiceLine(die[j].getValue() + 6 * i);
				System.out.print("     ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 *  Prints the first line of the dice.
	 */
	public void printDiceHeaders() {
		System.out.println();
		for (int i = 1; i < 3; i++) {
			System.out.printf("   # %d        ", i);
		}
		System.out.println();
	}
	
	/**
	 *  Prints one line of the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public void printDiceLine(int value) {
		System.out.print(line[getDiceLine(value)]);
	}
	
	/**
	 *  Gets the index number into the ASCII image of the dice.
	 *
	 *  @param value The index into the ASCII image of the dice.
	 */
	public int getDiceLine(int value) {
		if (value < 7) return 0;
		if (value < 14) return 1;
		switch (value) {
			case 20: case 22: case 25:
				return 1;
			case 16: case 17: case 18: case 24: case 28: case 29: case 30:
				return 2;
			case 19: case 21: case 23:
				return 3;
			case 14: case 15:
				return 4;
			case 26: case 27:
				return 5;
			default:	// value > 30
				return 6;
		}
	}
	 }
			 

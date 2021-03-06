/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
		//Deck deck1 = new Deck(
		String [] ranks1 = {"king","queen","jack"};
		String [] suits1 = {"orange","green"};
		int [] val1 = {13,12,11};
		String [] ranks2 = {"two", "three", "four", "five","six", "seven","eight","nine","ten","jack","queen","king","ace"};
		String [] suits2 = {"spades", "hearts", "diamonds","clubs"};
		int [] val2 = {2,3,4,5,6,7,8,9,10,10,10,10,11};
		System.out.println("SMALL DECK");
		System.out.println("----------");
		System.out.println();
		System.out.println("Original Deck");
		Deck deck1 = new Deck(ranks1,suits1,val1);
        System.out.println(deck1);
        System.out.println();
        System.out.println();
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println();
        System.out.println();
        System.out.println("After 4 deals");
        System.out.println(deck1);
        //System.out.println();
        //System.out.println();
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println("*** Deal Card *** " + deck1.deal());
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("After 6 deals");
        System.out.println(deck1);
        System.out.println("Large Deck");
        System.out.println("---------------");
        System.out.println();
        System.out.println("Original Deck");
        Deck deck2 = new Deck(ranks2,suits2,val2);
        System.out.println(deck2);

        System.out.println("*** Deal Card *** " + deck2.deal());
        System.out.println("*** Deal Card *** " + deck2.deal());
        System.out.println("*** Deal Card *** " + deck2.deal());
        System.out.println();
        System.out.println();
        System.out.println("After 3 deals:");
        System.out.println(deck2);
        for(int i = 1; i<=50;i++){
        	System.out.println("*** Deal Card *** " + deck2.deal());
        }
        
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("After 52 deals");
        System.out.println(deck2);
	}
}

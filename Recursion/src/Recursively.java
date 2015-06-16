import java.util.Scanner;
public class Recursively{
	

	int arrowValue = 1;
	int result;
	String strBinary;
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		Recursively call = new Recursively();
		char c = 'a';
		while(c!='q'&&c!='Q'){
			System.out.println();
			System.out.println("(1) Arrow");
			System.out.println("(2) Decimal to Binary");
			System.out.println("(3) Pascal's Triangle");
			System.out.println();
			System.out.println("Make a choice from the menu above, q or Q to quit");
			c = in.next().charAt(0);
			if(c=='q'||c=='Q')
				break;
			switch(c){
				
				case '1':
				call.arrow();
				break;
				
				case '2':
				call.binary();
				break;
				
				case '3':
				call.pascalsTriangle();
				break;
				
				
			}
			
		}
	}
	
	/*
	 * To print the directions for printing an arrow
	 * Prompting the user for the number of astrix
	 */
	public void arrow(){
		Scanner in = new Scanner(System.in);		
		while(arrowValue>0&&arrowValue<=80){
			
			System.out.println();
			System.out.println("Create an arrow by entering an integer, 0 to quit (0 to 80)");
			arrowValue = in.nextInt();
			if(arrowValue==0)
			break;
			System.out.println();
			printArrow(arrowValue, "*");
				
		}
	}
	
	/*
	 * Printing direction for decimal to binary conversion
	 * Prompting user for the number
	 */
	public void binary(){
		int bi = 1;
		Scanner in = new Scanner(System.in);		
		while(bi>0&&bi<=1000){
            System.out.println();
			System.out.println("Enter an integer to convert to Binary, 0 to quit (0 to 1000) : ");
			bi = in.nextInt();
			if(bi<0||bi>1000)
			continue;
			if(bi==0)
			break;
			System.out.println();
			System.out.println(bi + " : " + toBinary(bi));
			}
		
	}
	
	/*
	 * Printing directions for pascal's triangle
	 * Prompting user for the number of rows
	 */
	public void pascalsTriangle(){
		Scanner in = new Scanner(System.in);		
		int pt = 1;
		while(pt>0){
		System.out.println("Create a pascal's triangle by entering an integer, 0 to quit (0 to 16) : ");
		pt = in.nextInt();
		if(pt>16)
			continue;
		System.out.println();
        pascalRow(0,pt);
		}
		
	}
	
	/*
	 * Recursive method to go through each row 
	 * in the Pascal's triangle
	 * 
	 * @param row the current row number of the triangle
	 * @param max the row number given by the user
	 */
	   public static void pascalRow(int row, int max) {
	       if (row == max) {
	    	   System.out.println();
	       } 
	       else
	       {
	    	   

	    	pascalColumn(0, row);
	    	row++;
	       	pascalRow(row, max);
	       	  
	       }
	       //System.out.println();
	   }
	
	   /*
	    * Recursive method to go through each column
	    * in the given row
	    * 
	    * @param column the current column being printed
	    * @param row the row number
	    */
	public static void pascalColumn(int column, int row){
		
		if(column>row){
			System.out.println();
			
	}
		else{
			
			//System.out.println("row-" + row + " column-" + column);
			//System.out.print(pascal(row,column) + " ");
			System.out.printf("%6d", pascal(row,column));
			column++;
			pascalColumn(column,row);
		    
		}
	      

	   }
	
	/*
	 * Recursive method to return the number
	 * at a given row and column of the pascal's triangle
	 * 
	 * @param i the row number
	 * @param j the column number
	 */
	   public static int pascal(int i, int j) {
	       if (j == 0) {
	           return 1;
	       } else if (j == i) {
	           return 1;
	       } else {
	           return pascal(i - 1, j - 1) + pascal(i - 1, j);
	       }

	   }
	   
    /*
     * Recursive method to convert decimal 
     * into binary
     * 
     * @param num the current number in each recursion
     */
	public String toBinary(int num) { 
		   if(num < 2) { 
			   strBinary = "" + num; 
			   return strBinary; }
		   else { 
			   if(num != 0) { 
				   toBinary(num/2); 
				   strBinary += ""+num%2; }
			   } 
		   return strBinary; 
		   } 
	
    /*
     * Recursive method to print the arrow
     * 
     * @param n the max number of astrix
     * @param s the astrix
     */
	public void printArrow(int n, String s){
		
	    if (s.length() > n) return;
	    System.out.println(s);
	    printArrow(n, s + "*");
	    if(s.length()<n)
	    System.out.println(s);
				
	}
	

}

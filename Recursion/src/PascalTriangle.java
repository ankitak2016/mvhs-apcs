import java.util.Scanner;

public class PascalTriangle {
	
   public void print(int a,int b, int n) {
	
	  /* if(a==n){
	   }
	   else if(b==a){
		   print(a++,0,n);
		   System.out.println();
		     
	   }
	   else{
		   print(a,b++,n);
		   System.out.print(pascal(a,b) + " ");
	   }
	   */
	   
	   

   }

   public static int pascal(int i, int j) {
       if (j == 0) {
           return 1;
       } else if (j == i) {
           return 1;
       } else {
           return pascal(i - 1, j - 1) + pascal(i - 1, j);
       }

   }

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.print("Enter the row number upto which Pascal's triangle has to be printed: ");
	   int n = scanner.nextInt();
	   
	   //System.out.println(pascal(3,2));
	   pascalRow(0, n);
	   
       /*for (int i = 0; i < n; i++) {
           for (int j = 0; j <= i; j++) {
               System.out.print(pascal(i, j) + " ");
           }
           System.out.println();
       }
       */
    
   }
   
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
       System.out.println();
   }
      
public static void pascalColumn(int column, int row){
	
	if(column>row){
		System.out.println();
		
}
	else{
		
		//System.out.println("row-" + row + " column-" + column);
		System.out.print(pascal(row,column) + " ");
		column++;
		pascalColumn(column,row);
		

		
	    
	}
      

   }
   
  
   
}

import java.util.Scanner;
import java.util.ArrayList;


public class Partitions { 

    public static void partition(int n, int num1) {
        partition(num1, n, n, "", new ArrayList<ArrayList<Integer>>(), new ArrayList<Integer>());
    }
    public static void partition(int num1, int n, int max, String temp, ArrayList<ArrayList<Integer>> allCombination, ArrayList<Integer> Combination ) {
        if (n == 0) {
            ArrayList<Integer> temp1 = new ArrayList<Integer>();
            for (int i = 0; i < Combination.size(); i++) {
                temp1.add(Combination.get(i));
            }
            //To add combinations
            allCombination.add(temp1);
            //Print the combination
            System.out.print(num1 + " =");
            System.out.print(temp);
            System.out.println();
        }
       //Go through the options
        for (int i = Math.min(max, n); i >= 1; i--) {
            Combination.add(i);
            //If temp is empty
            if(temp.length()==0)
            	partition(num1, n - i, i, temp + " " + i, allCombination, Combination);
            else
            	//if temp is not empty
            	partition(num1, n - i, i, temp + " + " + i, allCombination, Combination);
            //Delete the last added number
            Combination.remove(Combination.size() - 1);
        }
    }
    


    public static void main(String[] args) { 
    	Scanner in = new Scanner(System.in);
    	System.out.println("Please enter a number");
        int N = in.nextInt();
        int num = N;
        partition(N,num);
    }

}

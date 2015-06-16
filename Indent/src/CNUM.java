  
	/** 
	* This program creates ten random numbers from 1 to 200, puts them in an 
	* array, and sorts them. 
	* 
	* @author Mr Greenstein 
	* @version November 24, 2013 
	*/
public class CNUM
{
	 final int ARRSIZE = 10;
	 final int RANGE = 200;
	 int [] arr;
 	public CNUM() 
	 {
		 arr = new int[ARRSIZE];
		 
	 }
	 public static void main(String [] args) 
	 {
		 CNUM cn = new CNUM();
		 cn.Run();
		 
	 }
	 public void Run() 
	 {
		 FillArray(arr);
		 System.out.println("Before sorting");
		 for (int i = 0; i < ARRSIZE; i++)
		 {
			 System.out.print(arr[i] + " ");
			 
		 }
		 System.out.println();
		 BubbleSort(arr);
		 System.out.println("\nAfter sorting");
		 for (int i = 0; i < ARRSIZE; i++)
		 {
			 System.out.print(arr[i] + " ");
			 
		 }
		 System.out.println();
		 
	 }
	 public void FillArray(int[] a) 
	 {
		 int i = 0;
		 while (i < a.length) 
		 {
			 a[i] = (int)(Math.random() * RANGE) + 1;
			 boolean found = false;
			 for (int j = 0; j < i; j++)
			 {
				 if (a[j] == a[i]) found = true;
				 
			 }
			 if (! found) i++;
			 
		 }
		 
	 }
	 public void BubbleSort(int[] a) 
	 {
		 for (int j = 0; j < a.length-1; j++)
		 {
			 for (int i = j+1; i < a.length; i++)
			 {
				 if (a[i] < a[j]) 
				 {
					 int temp = a[i];
					 a[i] = a[j];
					 a[j] = temp;
					 
				 }
				 
			 }
			 
		 }
		 
	 }
	 
 }
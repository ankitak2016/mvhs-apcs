import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * To print a list for the availabilities of the store
 * To search for a given id number
 *
 *  @author Ankita Koratkar
 *  @version  1/8/15
 */
public class Store{
	
  
	private ArrayList <Item> myStore; // = new ArrayList <Item>(100);
    public static final int NOT_FOUND = -1;
    public Item[] temp;
    public static int steps;
    String fileName;
    
	public Store(String fName){
		
		fileName = fName;
		
	}

	public Store(){
		
		myStore = new ArrayList<Item>(100);
   }
   
   public static void main(String args[]){
	   Scanner sc = new Scanner(System.in);
	   String st = sc.next();
	   Store ob = new Store();
	   ob.loadFile(st);
	   //System.out.println("Done loading file");
	   ob.Sort();
	   ob.displayStore();
	   
   }
   
	/**
	 * To load the file into the array list
	 * 
	 * @param inFileName
	 */
	private void loadFile(String inFileName){
	  
	    int id1 = 0;
	    int inv1 = 0;
	    String i1 = "";
	    String in2 = "";
		Scanner readFile = openToRead(inFileName); 
		//Going through each line
		while(readFile.hasNextLine()){
			
			id1 = readFile.nextInt();
            //i1 = readFile.next();
            //id1 = Integer.parseInt(i1);
            
            inv1 = readFile.nextInt();
			myStore.add(new Item(id1,inv1));
			readFile.nextLine();
		}
				 
		readFile.close();		 
    }
	
	/**
	 * To display the store after sorting
	 * 
	 */ 
	public void displayStore(){
	
			int k = 1;
			System.out.println("+-----------------+");
			System.out.println("| Store Inventory |");
			System.out.println("+-------------------------------+");
			System.out.println("|                               |");
			System.out.println("|        ID Number   # Of Items |");
			for(int i = 0; i<myStore.size();i++){
				if(k%10==0){
					System.out.println("|                               |");
				}
				System.out.print("|  ");
				System.out.printf( "%3d", k);
				System.out.print("      ");
				System.out.printf( "%4d", myStore.get(i).getId());
				System.out.print("       ");
				System.out.printf(" %3d", myStore.get(i).getInv());
				//System.out.println(myStore.get(i).toString());
				System.out.print("     |");
				System.out.println();
				k++;
			}
			search(myStore);
		
		} 
	  
	public String toString(){
	  
	  return "-----------";
	}
  
	/**
	 * To sort the given arraylist
	 *
	 */
	public void Sort(){
	  
	  System.out.println("In sort");
	  temp = new Item[myStore.size()];
	 // System.out.println("Created temp arraylist");

	  mergeSort(myStore, 0, myStore.size()-1);
	  
	} 
	/**
	 * To merge the lists in a merge sort
	 * 
	 * @param a, first, mid, last
	 */
	private void merge(ArrayList <Item> a, int first, int mid, int last){

                //ArrayList<Integer> temp = new ArrayList<Integer>(list.size());
                int i = first, j = mid+1, k = first;
               
                while( i <= mid && j <= last){
                        
                        if(a.get(i).getId() < a.get(j).getId()){
                                //temp.set(k,a.get(i));
                        	    temp[k] = a.get(i);
                                i++;
                                
                        }
                        else
                        {
                                //temp.set(k, a.get(j));
                        	    temp[k] = a.get(j);
                                j++;
                        }
                        k++;
                }

                while (i <= mid){
                        //temp.set(k, a.get(i));
                		temp[k] = a.get(i);
                        i++;
                        k++;
                        
                }
                

                while(j<=last){
                        //temp.set(k, a.get(j));
                	    temp[k] = a.get(j);
                        j++;
                        k++;
                }

                for(k = first; k<=last;k++){
                a.set(k, temp[k]);
        }
	  
	}
	/**
	 * To perform a merge sort
	 * 
	 * @param a, first, last
	 */
	public void mergeSort(ArrayList <Item> a, int first, int last) {
  
                //to = to - 1;
                if(last - first < 2){
                        
                        if(last>first && (a.get(last).getId() < a.get(first).getId()))
                        {
							swap(a, first, last);

                        }
                }
                else
                {
                        int middle = (first + last) / 2;

                        mergeSort(a, first, middle);
                        mergeSort(a, middle + 1, last);
                        merge(a, first, middle, last);
                }
                
	  
	}
  
	/**
	 * To swap two given items in the
	 * arraylist
	 * 
	 * @param list, a, b
	 */
	private void swap(ArrayList <Item> list, int a, int b){
	  
            Item aTemp = list.get(b);
            Item bTemp = list.get(a);
            list.set(b, bTemp);
            list.set(a, aTemp);	  
	  
	}
	/**
	 * To output the search mechanism
	 * 
	 * @param a
	 */
	public void search(ArrayList <Item> a){
		
		Scanner in = new Scanner(System.in);
		int i = 1;
		int num = 0;
		while(num>=0){
			System.out.println("---------------------------------------------------------------");
			System.out.println("Please enter an Id number to look for (22 to 9983, -1 to exit) : ");
			System.out.println();
			num = in.nextInt();
			if(num==-1)
				break;
			int n = binarySearch(a, num);
			if(n==-1)
			System.out.println("The binary search took " + steps + " steps to determine that this inventory item does not exist");
			else{
			System.out.println("The binary search took " + steps + " steps to find this inventory item");
			System.out.println();
			System.out.println("The inventory item was found at index " + (n+1) + " out of 100 items");
			System.out.println();
			System.out.println("ID number    Number of Items");
			System.out.println(a.get(n));
		}
			
			
		}
		System.out.println("Goodbye!");
	}
	
	/**
	 * To perform a binart search
	 * 
	 * @param a, x
	 */
	    public static int binarySearch( ArrayList <Item> a, Integer x )
	    {
        int low = 0;
        int high = a.size() - 1;
        int mid;
        steps = 0;

        while( low <= high )
        {
            mid = ( low + high ) / 2;
            steps++;

            if( a.get(mid).getId()-x< 0 )
                low = mid + 1;
            else if( a.get(mid).getId()-x> 0 )
                high = mid - 1;
            else
                return mid;
        }

        return NOT_FOUND;    
    }
    	/**
       *  This method opens a file to be read.
       *
       *  @param fileName file to be read
       *  @return   the opened file
       */
	public static Scanner openToRead (String fileName) {
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e ) {
			System.err.println("ERROR : Cannot open " + fileName +" to read");
			System.exit(1);
		}
		return input;
	}
	
}

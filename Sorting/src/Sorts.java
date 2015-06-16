import java.util.ArrayList;

public class Sorts
{
	private static int temp[];
        public static int bubbleSort (ArrayList <Integer> list)
        {
        	//System.out.println("IN BUBBLE SORT");
                int steps = 0;
                int n = list.size();

                 for(int outter = 0; outter < n-1;outter++){
                        //printArray(arr);
                        for(int iner = 0;iner < n-outter-1;iner++){

                                if(list.get(iner)>list.get(iner+1)){
                                        int temp1 = list.get(iner);
                                        int temp2 = list.get(iner+1);
                                        //arr[iner] = arr[iner+1];
                                        list.set(iner, temp2);
                                        //arr[iner+1] = temp;
                                        list.set(iner+1, temp1);
                                        steps++;
                                }
                                steps++;
                        }
                 }
                 //System.out.println(list);
                return steps;
        }

        public static int selectionSort (ArrayList <Integer> list)
        {
                int steps = 0;
                int length = list.size();
                for(int n = length; n>1; n--){
                        int iMax = 0;
                        for(int i = 1; i < n; i++){
                                if(list.get(i)>list.get(iMax))
                                        iMax = i;
                                        steps++;
                                }
                                int aTemp = list.get(iMax);
                                int bTemp = list.get(n-1);
                                list.set(iMax, bTemp);
                                list.set(n-1, aTemp);
                                steps++;
                        }
                return steps;
        }

        public static int insertionSort (ArrayList <Integer> list)
        {
                int steps = 0;

                for(int n = 1; n<list.size();n++){

                        int aTemp = list.get(n);

                        int i = n;

                        while(i>0 && aTemp < list.get(i-1)){
                                list.set(i, list.get(i-1));
                                i--;
                                steps++;
                        }

                        list.set(i, aTemp);
                        steps++;
                }
                return steps;
        }

        public static int mergeSort(ArrayList<Integer> list, int from , int to){
        	int n = list.size();
        	temp = new int [n];
        	int s = Sort(list, 0 , n-1);
        	return s;
        	
        }
        public static int Sort (ArrayList<Integer> list, int from, int to)
        {
                int steps = 0;
                //to = to - 1;
                if(to - from < 2){
                        steps++;
                        if(to>from && (list.get(to) < list.get(from)))
                        {
                                int aTemp = list.get(to);
                                int bTemp = list.get(from);
                                list.set(to, bTemp);
                                list.set(from, aTemp);
                                steps++;
                        }
                }
                else
                {
                        int middle = (from + to) / 2;

                        steps += Sort(list, from, middle);
                        steps += Sort(list, middle + 1, to);
                        steps += merge(list, from, middle, to);
                }
                return steps;
        }

        public static int merge (ArrayList<Integer> list, int from, int middle, int to)
        {
                //ArrayList<Integer> temp = new ArrayList<Integer>(list.size());
                int i = from, j = middle+1, k = from;
                int steps = 0;
                while( i <= middle && j <= to){
                        steps++;
                        if(list.get(i) < list.get(j)){
                                //temp.set(k,list.get(i));
                        	    temp[k] = list.get(i);
                                i++;
                                steps++;
                        }
                        else
                        {
                                //temp.set(k, list.get(j));
                        	    temp[k] = list.get(j);
                                j++;
                        }
                        k++;
                }

                while (i <= middle){
                        //temp.set(k, list.get(j));
                		temp[k] = list.get(i);
                        i++;
                        k++;
                        steps++;
                }
                steps++;

                while(j<=to){
                        //temp.set(k, list.get(j));
                	    temp[k] = list.get(j);
                        j++;
                        k++;
                        steps++;
                }
                steps++;

                for(k = from; k<=to;k++){
                list.set(k, new Integer(temp[k]));
                steps++;
        }


                return steps;
        }
}

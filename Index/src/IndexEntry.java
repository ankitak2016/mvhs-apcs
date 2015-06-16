import java.util.ArrayList;

public class IndexEntry{

	private String word;
	private ArrayList<Integer> numsList;

	public IndexEntry(String w){
	
		word = w.toUpperCase();
		numsList = new ArrayList<Integer>();
	}
	
	public void add(int num){
		
		if(numsList.contains(new Integer(num))==false)
		numsList.add(new Integer(num));
	}
	
	public String getWord(){
		
		return word;
	}
	
	public String toString(){
		
		String st = word + " ";
		for(int i = 0; i<numsList.size();i++){
			
			if(i == numsList.size()-1){
				st+= numsList.get(i);
				continue;
			}
			st+= numsList.get(i) + ", ";
		}
		return st;
	}
}

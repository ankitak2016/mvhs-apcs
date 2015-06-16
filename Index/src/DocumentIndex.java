import java.util.ArrayList;

public class DocumentIndex extends ArrayList<IndexEntry>{
	
	
	ArrayList<IndexEntry> documentlist;
	public DocumentIndex(){
		
		documentlist = new ArrayList<IndexEntry>();
	}
	
	public DocumentIndex(int c){
		
		documentlist = new ArrayList<IndexEntry>(c);
	}
	
	void addWord(String word, int num){
		
		//System.out.println(word);
		int location = foundorInserted(word);
		documentlist.get(location).add(num);
		
	}
	
	void addAllWords(String str, int num){
		
		String word = "";
		for(int i = 0; i<str.length();i++){
			char c = str.charAt(i);
			if(i==str.length()-1){
				if(c=='!'||c=='.'||c=='?'||c==',')
					addWord(word,num);
				else{
					word = word + c;
					addWord(word,num);
				}
				word="";
				continue;
			}
			
			if(c=='!'||c=='.'||c=='?'||c==',')
			continue;
			if(Character.toString(c).equals(" ")){
			addWord(word, num);
			word = "";
			continue;
		}
			
			word = word + c;
			
		}
		
	}
	
	private int foundorInserted(String word){
		
		//System.out.println(word);
		int index = 0;
		
		if(documentlist.size()== 1||documentlist.size()==0){
				//||documentlist.size()==2)
			
			   if(documentlist.size()==1){
				char c1 = Character.toUpperCase(word.charAt(0));
				char c2 = Character.toUpperCase(documentlist.get(0).getWord().charAt(0));
				if(c1>c2){
					documentlist.add(1, new IndexEntry(word));
					index = 1;
					//System.out.println(documentlist.get(index));
				}
				else{
					documentlist.add(0, new IndexEntry(word));
					index = 0;
				}	
			}
			if(documentlist.size()==0){
				documentlist.add(new IndexEntry(word));
				index = 0;
			}
			
		 }
		 
         else{
              char c1 = Character.toUpperCase(word.charAt(0));
        	  for(int i = 0;i<documentlist.size()-1;i++){
			
        		 	String word2 = documentlist.get(i).getWord();
        		 	String word3 = documentlist.get(i+1).getWord();	
        		 	
        		 	int k = 0;
        		 	
        		 	//Check for existing word
        		 	for(int h = 0; h<documentlist.size();h++){
        		 	if(word.equalsIgnoreCase(documentlist.get(h).getWord())){
        		 		k=1;
        		 		index = h;
        		 		break;
        		 	}
        		 	}
        		 	
        		 	if(k==1)
        		 		break;
        		 	if(c1<Character.toUpperCase(documentlist.get(0).getWord().charAt(0))){
        		 		//System.out.println(word);
        		 		index = 0;
        		 		documentlist.add(index, new IndexEntry(word));
        		 		break;
        		 	}
        		 	
        		 	
        		 	if((c1>Character.toUpperCase(word2.charAt(0)))&&(c1<Character.toUpperCase(word3.charAt(0)))){
        		 		//System.out.println(word);
        		 		index = i+1;
        		 		documentlist.add(index, new IndexEntry(word));
        		 		break;
        		 	}
        		 	if(c1==Character.toUpperCase(word2.charAt(0))){
        		 		if(word.length()>word2.length()){
        		 			documentlist.add(i+1, new IndexEntry(word));
        		 			index = i+1;
        		 			break;
        		 		}
        		 		for(int j = 1; j<word.length();j++){
        		 			if(j<word2.length()==false){
        		 				//documentlist.add(i+1, new IndexEntry(word));
        		 				break;
        		 			}
        		 			char cword = Character.toUpperCase(word.charAt(j));
        		 			char ccomp = Character.toUpperCase(word2.charAt(j));
        		 			//if(cword==ccomp)
        		 				//continue;
        		 			if(cword>ccomp){
        		 				index = i+1;
        		 				documentlist.add(i+1, new IndexEntry(word));
        		 				break;
        		 			}
        		 			if(cword<ccomp){
        		 				index = i;
        		 				documentlist.add(i, new IndexEntry(word));
        		 				break;
        		 			}
        		 			
        		 		}
        		 		break;
        		 	}
        		 	if(c1>Character.toUpperCase(documentlist.get(documentlist.size()-1).getWord().charAt(0))){
        		 		index = documentlist.size();
        		 		documentlist.add(new IndexEntry(word));
        		 		break;
        		 	}
		}
		
	}
		//System.out.println(documentlist.get(index));
		return index;
					   
	}
	
	public ArrayList<IndexEntry> getList(){
		return documentlist;
	}
				   
}

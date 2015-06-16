import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * 
 * To format a given program in
 * Allman's style
 * @author Ankita Koratkar
 * @version October 19, 2014
 */
public class Indent{
	
	
	public static void main(String args[]){
		
		Scanner in = new Scanner(System.in);
		String entire_File= "";
		String fileName = in.next();
		String program_Name = "";
		program_Name = getProgramName(fileName);
		entire_File = setFile(fileName);
		formatOutput(entire_File, program_Name);
		printFile(program_Name);
		
	}
	/**
	 * To print the final formatted file
	 * 
	 * @param name the name of the file
	 */
	public static void printFile(String name){
		
		Scanner readFile = openFile(name);
		while(readFile.hasNextLine()){
			System.out.println(readFile.nextLine());
		}
		readFile.close();
			
	}
	
	    /**
	     * To get the name of the program to be
	     * formatted
	     * 
	     * @param file the file to be read
	     * @return name the name of the program
	     */
	    public static String getProgramName(String file){
		String name = "";
		Scanner readFile = openFile(file);
		while(readFile.hasNextLine()){
			String st = readFile.next();
	      if(st.equals("class"))
	      {
		  name = readFile.next()+".java";
		  break;
	  }
	  }
	  return name;
  }
	/**
	 * To set the file into one string
	 * @param file
	 * @return entire the complete file in one string
	 */
	public static String setFile(String file){
	  
	  int im = 0;
	  String entire = "";
	  Scanner readFile = openFile(file);
	  while(readFile.hasNextLine()){
		  
		  String st = readFile.next();
		  if(st.equals("</PRE>"))
			  break;
		  if(st.equals("import")||st.equals("/**"))
			  im = im+1;
		  if(im>0)
			  if(st.equals("import")||st.equals("final"))
			  		entire = entire + st;
			  else
				  	entire = entire + " " + st;
	  }
	 
	readFile.close();
	return entire;  
		  
	}
	
	/**
	 * To open a file to be read
	 * @param file
	 * @return input the opened file
	 */
	public static Scanner openFile(String file){
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e ) {
			System.err.println("ERROR : Cannot open " + file +" to read");
			System.exit(1);
		}
		return input;
	}
		
	/**
	 * To format the given file
	 * @param st the file in one string
	 * @param name the program name
	 */
	public static void formatOutput(String st, String name){
		
		PrintWriter writeFile = openToWrite(name);
		int pos,i;
		int c1=0;
		int p1=0;
	    for(i = 0; i<st.length();i++){
		
			char c = st.charAt(i);
			if(st.length()-i>6){
				if(getPublic(c, st.charAt(i+1), st.charAt(i+2)))
					p1++;
				if(p1>1&&(getPublic(c, st.charAt(i+1), st.charAt(i+2)))){
					i = formatIndent(st, i, writeFile);
					continue;
				}
			}
				if(st.length()-i>4)
			
				if(checkComment(c, st.charAt(i+1), st.charAt(i+2))){
				pos = printComment(i, st, writeFile,c1);
				c1++;
				i = pos;
				continue;
				}
			
			
			if(checkBracket(c, writeFile)){
				writeFile.print('\t');
				continue;
			}
		        if(newLine(c,writeFile)){
		    	if(st.charAt(i+2) != 'p'&&st.charAt(i+1)!='i')
		    	writeFile.print('\t');
		    	continue;
		    }
		    	
		    writeFile.print(c);
		  }
		
		writeFile.close();
		
	}
	/**
	 * A method to print a set given number 
	 * of indents
	 * @param num the number of indents
	 * @param File
	 */
	public static void setIndent(int num, PrintWriter File){
	
		for(int i = 1;i<=num;i++){
    		File.print('\t');
    	}
	}
	
	/**
	 * A method the get the number of indents
	 * @param c1
	 * @param File
	 * @param i
	 * @return i the number of indents
	 */
	public static int getIndent(char c1, PrintWriter File, int i){
		
		if(c1=='{'){
			File.print('\n');
			setIndent(i,File);
			File.print(" " +c1);
			File.print('\n');
			i++;
			setIndent(i,File);
		}
		if(c1=='}'){
			File.print('\n');
			i--;
			setIndent(i,File);
			File.print(" " +c1);
			File.print('\n');
			setIndent(i,File);
			
		}
			
		return i;
	}
	
	/**
	 * Method to format the indented areas(methods)
	 * @param st
	 * @param p
	 * @param File
	 * @return j the position ended at
	 */
	public static int formatIndent(String st, int p, PrintWriter File){
		
		//No of indents for {
		int indent1 = 1;
		int j;
		setIndent(indent1, File);
	    	for(j = p; j<st.length();j++){
	    		char c = st.charAt(j);
	    		if(st.length()-j>4&&checkLoop(c, st.charAt(j+1),st.charAt(j+2),st.charAt(j+4))){
					j = printLoop(j,st,File);
					continue;
				}
			
	    		if(c=='/'&&st.charAt(j+1)=='*'){
	    			j=j-1;
	    	        break;
	    		}
	    		if(newLine(c,File)){
	    			setIndent(indent1, File);
	    			continue;
	    		}
	    		int temp = indent1;
	    		indent1 = getIndent(c, File, indent1);
	    		if(indent1!=temp){
	    			
	    			continue;
	    		}
	    		
	    		File.print(c);
	    	}
	    
	    return j;
		
		
	}
	/**
	 * To check for the existence of "public"
	 * @param c1
	 * @param c2
	 * @param c3
	 * @return false if public is not found
	 * 		   true if public is found
	 */
	public static boolean getPublic(char c1, char c2, char c3){
		
		if(c1=='p'&&c2=='u'&&c3=='b')
			return true;
		else
			return false;
	}

	/**
	 * Method to check whether a new line is needed
	 * @param c1
	 * @param File
	 * @return true if line is needed
	 */
	public static boolean newLine(char c1, PrintWriter File){
		if(c1==';'){
			File.print(';');
			File.print('\n');
			return true;
		}
		else
			return false;
	}

	/**
	 * Method to check for the existence of for loops
	 * @param c1
	 * @param c2
	 * @param c3
	 * @param c4
	 * @return true if loop is found
	 */
	public static boolean checkLoop(char c1, char c2, char c3,char c4){
		
		if(c1=='f'&&c2=='o'&&c3=='r'&&c4=='(')
		return true;
		else
		return false;
	}
	
	/**
	 * Method to print for loops
	 * @param j
	 * @param f
	 * @param File
	 * @return k the position stopped at
	 */
	public static int printLoop(int j, String f, PrintWriter File){
		
		int k = j;
		for(int a = j; a<f.length();a++){
			char c = f.charAt(a);
			if(c==')'){
				
				File.print(')');
				k = a+1;
				break;
		}
		File.print(c);
	}
	return k;
}
	
	/**
	 * To check the existence of brackets
	 * @param c1
	 * @param file
	 * @return true if found
	 */
	public static boolean checkBracket(char c1, PrintWriter file){
		
		if(c1 == '{' || c1 == '}'){
		file.print('\n');
		file.print(c1);
		file.print('\n');
		return true;
	}
	else
	return false;
	}
	
	/**
	 * Method to check for comments
	 * @param c1
	 * @param c2
	 * @param c3
	 * @return true if comment found else false
	 */
	public static boolean checkComment(char c1, char c2, char c3){
		
		if(c1=='/'&&c2=='*'&&c3=='*')
		return true;
		else
		return false;
	
	}
	
	/**
	 * To print comments
	 * @param i
	 * @param f
	 * @param File
	 * @param com the comment number
	 * @return pos the position ended at
	 */
	public static int printComment(int i, String f, PrintWriter File, int com){
		
		int pos = 0;
		File.print('\n');
		if(com>0)
		File.print('\t');
		
		File.print("/**");
		for(int j = i+3; j<f.length();j++){
			
			char c = f.charAt(j);
			if( c == '/'){
				File.print('/');
				File.print('\n');
				if(f.charAt(j+1)=='p'||f.charAt(j+1)=='i')
					pos=j;
				else
					pos = j+1;
				break;
			}
	
			if(c=='*'){
			File.print('\n');
			if(com>0)
			File.print('\t');
			
			File.print('*');
			continue;
		}
	    File.print(c);	
			
		}
		
		return pos;
	}
	
	/**
	 * To open a file to write
	 * @param fileName
	 * @return output the new file
	 */
	public static PrintWriter openToWrite (String fileName) {
		PrintWriter output = null;
		try {
			output = new PrintWriter(new File(fileName));
		} catch (IOException e) {
			System.err.println("ERROR : Cannot open" + fileName + " for writng");
			System.exit(1);
		}
		return output;
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cipher {

	public static void main(String[] args) {
		
		Scanner inSystem = new Scanner(System.in);
		//Ask the user for the name of the file to be encrypted or decrypted
		
		System.out.println("Enter the name of the file to be used as the key");
		String keyfileName = inSystem.next();
		
		System.out.println("Enter the name of the file to be encrypted or decrypted");
		String infileName = inSystem.next(); 
		
		//Enter 1 or 2 to encrypted or decrypted using switch case
		System.out.println("Enter 1 to encrypt and 2 to decrypt");
		int choice = inSystem.nextInt();
		
		//Open the Key File
		Scanner keyFiletoRead =openToRead(keyfileName);
		
		//Making the array for encryption or decryption
		char decryptionValues[] = new char[26];
		char encryptionValues[] = new char[26];
		
		int i=0;
		
		while(keyFiletoRead.hasNextLine())
		{
			
			//Populate the arrays
			decryptionValues[i]=keyFiletoRead.next().charAt(0);
			encryptionValues[i]=keyFiletoRead.next().charAt(0);
			
		
			//go to next line in the file
			i=i+1;
			keyFiletoRead.nextLine();
				
			
		}
		
		// Close the file
		keyFiletoRead.close();
		
		switch(choice)
		{
		case 1:
		    encrypt(infileName,encryptionValues,decryptionValues, choice);
			break;
			
		case 2:
			encrypt(infileName,decryptionValues,encryptionValues, choice);
			break;
			
		  
		}
	}
	
	
	//Method for Encryption
	public static void encrypt(String File, char encyptionValues[], char decryptionValues[], int choice){
	
		
		//open the file
		Scanner readFile = openToRead(File) ;
		
		String outputFile=null;
		
		switch(choice)
		{
		case 1:
			
		    outputFile = File +  "_encrypted.txt";
		    System.out.println("Encrypting to file " + outputFile);
		    break;
			
		case 2:
			
			outputFile = File + "_decrypted.txt";
			System.out.println("Decrypting to file " + outputFile);
			break;
			
		  
		}
		
		
		
		PrintWriter writeFile= openToWrite (outputFile);
		
		//readFile.useDelimiter("\r");
		String currentString="";
		
		//start scanning the file
		while(readFile.hasNextLine()){
			
			currentString = readFile.nextLine();
			
			
			int lengthofString = currentString.length();
			
			//Start scanning through the word
			for(int j =0;j<lengthofString;j++){
				
				
				
			    //Character at a particular position
				char current_char = currentString.charAt(j);
				char encrytedChar= current_char;
			
				//Find subscript of the letter
				int s, subscript=0;
				
				for(s=0;s<26;s++){
					
					if(Character.toLowerCase(current_char) == decryptionValues[s]){
				    //Saving value of subscript
					subscript = s;
					encrytedChar=encyptionValues[subscript];
					break;
					}
				}
		        if(Character.isUpperCase(current_char))
		        {
				writeFile.print(Character.toUpperCase(encrytedChar));
		        }
		        else
		        writeFile.print(encrytedChar);
				//System.out.print(encrytedChar);
			}
			
			
			
			writeFile.print("\r");
	
			
			}
		writeFile.close();
		readFile.close();
			
		}
		
		//Save the encrypted file into a text file

	
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

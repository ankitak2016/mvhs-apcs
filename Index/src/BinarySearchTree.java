import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class BinarySearchTree{
	
	
	private TreeNode roots;
	public BinarySearchTree(){
		 roots = null;
	}
	
	public void insert(Item next){
		
			
		roots=insertHelper(roots,next);
		
	}
	
	public TreeNode insertHelper(TreeNode root, Item next){
		
		if(root == null)
			root = new TreeNode(next);
		else
		{
			int diff = next.compareTo((Item)root.getValue());
			if(diff < 0)
				root.setLeft(insertHelper(root.getLeft(), next));
			else
				root.setRight(insertHelper(root.getRight(), next));
		}
		return root;
	}
	
	
	
	public void printInOrder(){
		printInOrderHelper(roots);
	}
	
	public void printInOrderHelper(TreeNode root){
		if (root != null) {
			printInOrderHelper (root.getLeft());
			System.out.println(root.getValue());
			printInOrderHelper(root.getRight());
		}
		
	}
	
	public int countNodes(){
		
		return countNodesHelper(roots);
	}
	
	public int countNodesHelper(TreeNode root){
		
		if(root==null)
		return 0;
		else
		return 1 + countNodesHelper(root.getLeft())+countNodesHelper(root.getRight());
		
	}
    /**
	* Method to load given data 
	* to a singly linked list
	*/		
	public void loadData(){
		
		Scanner readInput = new Scanner(System.in);
		System.out.println("Enter the file to be processed");
		String file = readInput.next();
		Scanner readFile = openToRead(file);
		//ArrayList <Item> items = new ArrayList<Item>();
		int i = 1;
		while(readFile.hasNextLine()){
			int id = Integer.parseInt(readFile.next());
			int iv = Integer.parseInt(readFile.next());
			insert(new Item(id,iv));
			readFile.nextLine();
		}
		
		
   }
   
    /**
	* Opens a given file
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
	
	public void testDelete(){
		Scanner in = new Scanner(System.in);
		int choice;
		do{
			System.out.println("Enter id value to delete (-1 to quit)");
			choice = in.nextInt();
			if(choice == -1)
				break;	
		     Object found = find(choice);
			 if(found!=null)
				 delete((Item)found);
			
		}
		while(choice!=-1);
	}
	public void delete(Item target){
		roots=deleteHelper(roots,target);
	}
	
	private TreeNode deleteHelper(TreeNode node, Item target){
		
		int diff = target.compareTo(node.getValue());
		if(diff==0)
			node = removeRoot(node);
		else if(diff<0)
			node.setLeft(deleteHelper(node.getLeft(),target));
		else
			node.setRight(deleteHelper(node.getRight(), target));
		return node;
		
	}
	
	private TreeNode removeRoot(TreeNode target){
		  if (target.getRight() == null) {
		    return target.getLeft();
		  }
		  else if (target.getLeft() == null) {
		    return target.getRight();
		  }
		  else if (target.getLeft().getRight() == null) {
		    target.setValue(target.getLeft().getValue());
		    target.setLeft(target.getLeft().getLeft());
		    return target;
		  }
		  else{ 

		    TreeNode marker = target.getLeft();
		    while (marker.getRight().getRight() != null)
		      marker = marker.getRight();
		    target.setValue(marker.getRight().getValue());
		    marker.setRight(marker.getRight().getLeft());
		    return target;
		  }
		}
	
	
	public boolean contains(int value){
		return contains(roots,value);
	
	}
	public boolean contains(TreeNode node, int value){
		
		if(node==null)
			return false;
		else
		{
			Item val = (Item)node.getValue();
			int valid = val.getId();
			int diff = value-valid;
			if(diff==0)
				return true;
			else if(diff<0)
				return contains(node.getRight(),value);
			else
				return contains(node.getRight(),value);
		}
	}
	
	public void testFind(){
		Scanner in = new Scanner(System.in);
		int choice;
		do{
			System.out.println("Enter id value to search for (-1 to quit)");
			choice = in.nextInt();
			if(choice == -1)
				break;	
		     Object found = find(choice);
			 if(found == null){
				 System.out.print("Id : " + choice);
				 System.out.print("No such id in stock");
			 }
			 else{
				 System.out.println((Item)found);
			 }
			System.out.println();
			
		}
		while(choice!=-1);
	}
	
	public Object find(int target){
		
		return findHelper(roots,target);
	}
	
	private Object findHelper(TreeNode root, int target){
		
	    if(root == null)
		return null;
	    
		Item value = (Item)root.getValue();
		int valueid = value.getId();
		int result = target - valueid;
		if(result==0)
			return root.getValue();
		else if (result < 0)
			return findHelper(root.getLeft(),target);
		else 
			return findHelper(root.getRight(),target);
			
	}
	
	
	

		
		
	
	public void clear(){
		
		roots = null;
	}
}

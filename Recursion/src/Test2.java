
public class Test2 {
	private static void myMethod(int n, String s) {
	    if (s.length() > n) return;
	    System.out.println(s);
	    myMethod(n, s + "*");
	    if(s.length()<n)
	    System.out.println(s);
	    }
	
	public static void main(String args[]){
		//System.out.println("")
		myMethod(5, "*");
	}
}

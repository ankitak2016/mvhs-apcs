
public class NewSong extends Song {
public void sing ( ) {
	System.out.println("In new song sing");
super.sing(); System.out.print("la la ");
}
public void chorus ( )
{
	System.out.println("in new song chorus");
	super.chorus(); System.out.print("woo woo ");
}
}


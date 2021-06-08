import java.util.Comparator;


//This class implements the Comparator interface, which helps us sort collections of objects
public class CustomComparator implements Comparator<Result> {
	@Override  //Overrides the compare method to alter the way the objects are ordered
	public int compare(Result a, Result b) {
		double distance1 = a.distance;			
		double distance2 = b.distance;
		
		if(distance1 < distance2) {
			return -1;
		}
		else if(distance1 == distance2) {
			return 0;
		}
		else return 1;
	}
}

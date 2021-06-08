import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
 
public class Utility {
	//Calculates the distance between 2 samples
	public static double euclideanDistance(double[] element1, double[] element2) {
		double sum= 0;														
		for(int i = 0;i < element1.length - 1;i++) {
			sum += Math.pow((element1[i] - element2[i]), 2);
		}
		return Math.sqrt(sum);
	}
	
	
	//Searches for a target value in a list, and increments a counter variable for every instance of that value
	static int countOccurrences(double[] list, double targetValue) {
		int count = 0;
		for(int i = 0; i < list.length; i++) {
			if (list[i] == targetValue) {
				count++;
			}
		}
		return count;
	}
	
	//Takes a list and returns most frequent Value(a double)
	static double getMostFrequentValue(double[] list) {
		int mostFrequentCount = 0;
		double mostFrequentValue = 0;
		for(int i = 0;i < list.length; i++) {				//searches the list through every index
			double value = list[i];
			int count = countOccurrences(list, value);		//The target value in the countOcurrences method is going to be every iteration of the list
			if( count > mostFrequentCount) {		//If the count variable hits a new high point, a new value from the list is assigned to the most frequent value variable 
				mostFrequentCount = count;
				mostFrequentValue = value;
			}
		}
		return mostFrequentValue;
	}
	
	
	
	
	
}
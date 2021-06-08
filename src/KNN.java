
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KNN {
	
	
	public static void main(String[] args) throws IOException {
		String line = "";
		int numOfRows1 = 0;		//Row incrementor to find out number of Rows
		int numOfRows2 = 0;		//Row incrementor to find out number of Rows
		int rowInc1 = 0;		//Row incrementor for for loops
		int rowInc2 = 0;		//Row incrementor
	
		File file1 = new File("C:/Users/cw2DataSet1.csv/"); 
		Scanner sc1_dataset1 = new Scanner(file1);			//creating scanners to read the file
		Scanner sc2_dataset1 = new Scanner(file1);
		
		// Creates array with the training data set
		double[][] trainingData;
		
		//Counts the number of Rows on the training dataset, so the array can be initialized properly
		while(sc1_dataset1.hasNextLine())
		{
			numOfRows1++;
			sc1_dataset1.nextLine();
		}
		sc1_dataset1.close();
		
		//initializes the array
		trainingData = new double[numOfRows1][65];
		//fills the array
		while(sc2_dataset1.hasNextLine()) {
			line = sc2_dataset1.nextLine();
			
			String[] InArray = line.split(",");			//InArray stands for Input array, it is used to store the tokens after a line is split
			
			//Copies the content of the InArray to array
			for(int i = 0;i < InArray.length;i++) {
				trainingData[rowInc1][i] = Double.parseDouble(InArray[i]);
			}
			
			rowInc1++;
		}
		sc2_dataset1.close();
		
		File file2 = new File("C:/Users/cw2DataSet2.csv/"); //Reading the file
		Scanner sc1_dataset2 = new Scanner(file2);	
		Scanner sc2_dataset2 = new Scanner(file2);
		
		//Creates the test dataset array
		double[][] testData;
		
		//Counts the number of Rows on the test dataset, so the array can be initialized properly
		while(sc1_dataset2.hasNextLine())
		{
			numOfRows2++;
			sc1_dataset2.nextLine();
		}
		sc1_dataset2.close();
		
		//initializes the array
		testData = new double[numOfRows2][65];
		//fills the array
		while(sc2_dataset2.hasNextLine()) {
			line = sc2_dataset2.nextLine();
			
			String[] InArray = line.split(",");				
			
			//Copies the content of the InArray to array
			for(int i = 0;i < InArray.length;i++) {
				testData[rowInc2][i] = Double.parseDouble(InArray[i]);
			}
			
			rowInc2++;
		}
		sc2_dataset2.close();
		
		//Declares value of K
		int k = 11; 
		
		//Creates a list containing the object Result(it merges each distance with the respective classCode)
		ArrayList<Result> resultList = new ArrayList<Result>();
		
		double[] bufferList = new double[k]; //Creates a buffer list that stores class codes in ascendent order
		double successCounter = 0;   //An incrementor
		
		
		//Training algorithm
		for(int x = 0; x < testData.length; x++ )
			for(int i = 0; i < trainingData.length; i++) {
																	
				double distance = 0;													
				distance += Utility.euclideanDistance(trainingData[i], testData[x]);	//Calculates the distance between one sample of the training set and one from the test set
				resultList.add(new Result(distance, trainingData[i][64]));			//Adds a Result object to the result list which contains both the distance and the classCode
				Collections.sort(resultList, new CustomComparator());			//Sorts the list in an ascending order, so the first indexes are the closest neighbors and their respective class codes
				if(i == trainingData.length - 1) {							
					for(int y = 0;y < k; y++) {								//When all the training samples have been iterated through, the buffer list is created with only the class codes of the closestNeighbors in ascending order
						bufferList[y] = resultList.get(y).classCode;
					}
					if(Utility.getMostFrequentValue(bufferList) == testData[x][64]) { //Then it checks for the most frequent class in the neighbors, and if the
						successCounter++;
					}
					resultList.clear();
					
				}
			}
		
		//Cross-validation
		double successCounter_2ndFold = 0;
		
		for(int x = 0; x < trainingData.length; x++) {
			for(int i = 0; i < testData.length; i++) {
			
				double distance = 0;
				distance += Utility.euclideanDistance(testData[i], trainingData[x]);
				resultList.add(new Result(distance, testData[i][64]));
				Collections.sort(resultList, new CustomComparator());
				if(i == testData.length - 1) {
					for(int y = 0;y < k; y++) {
						bufferList[y] = resultList.get(y).classCode;
					}
					if(Utility.getMostFrequentValue(bufferList) == trainingData[x][64]) {
						successCounter_2ndFold++;
					}
					resultList.clear();
					
				}
				
			}
		}
		
		
		
		
		//Prints out the accuracy of the training Algorithm
		System.out.println((successCounter/trainingData.length) * 100);
		//Prints out the accuracy of the 2-fold test
		System.out.println((successCounter_2ndFold/testData.length) * 100);
		
		
	}
	
	
}
			


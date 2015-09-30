package k_nearest_neighbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class KNN_Algorithm {
	private ArrayList<DigitImage> trainingSet = new ArrayList<DigitImage>();
	private ArrayList<DigitImage> testSet = new ArrayList<DigitImage>();

	public KNN_Algorithm(ArrayList<DigitImage> trainingSet, ArrayList<DigitImage> testSet) {
		this.trainingSet = trainingSet;
		this.testSet = testSet;
	}

	public void calculate() {
		// Statistics
		int total = 0;
		int correct = 0;
		// Ask for user input
		System.out.println("Please input k value:");
		Scanner input = new Scanner(System.in);
		int kValue = input.nextInt();

		System.out.println("Calculating Classification Accuracy of K-Nearest Neighbour...");

		// Classify all data points in the test set
		for (final DigitImage testPoint: testSet) {
			// Sort array list according to the new test point
			Collections.sort(trainingSet, new Comparator<DigitImage>() {
				@Override
				public int compare(DigitImage o1, DigitImage o2) {
					return Double.compare(testPoint.distanceTo(o1),  testPoint.distanceTo(o2));
				}
			});

			// Neighbours cannot be larger than training set size
			if (kValue > trainingSet.size()) {
				kValue = trainingSet.size();
				System.out.println("K Neighbours cannot be larger than the training set size.");
				System.out.println("K Neighbour is now set to the training set size.");
			}

			// Neighbours <Class, Frequency>
			HashMap<Integer, Integer> neighbours = getKNearestNeighbours(kValue);

			for(int i = 0 ; i < kValue ; i ++){
				System.out.println(trainingSet.get(i).getDigitClass());
				System.out.println(testPoint.distanceTo(trainingSet.get(i)) + "\n");
			}

			// Find the majority class (for classification)
			int majorityClass = getMajorityClass(neighbours);

			// Special case where the highest frequency number of multiple neighbours are the same
			if (isEqualNeighbours(neighbours, majorityClass)) {
				// Choose closest distance from multiple neighbours with highest frequency
				int majority = getClosestNeighbourClass(neighbours, majorityClass);
				correct += checkCorrectClassification(testPoint.getDigitClass(), majority);
			} else {
				correct += checkCorrectClassification(testPoint.getDigitClass(), majorityClass);
			}

			total++;
//			return;
		}

		//Accuracy percentage
		double accuracy = (double)correct/(double)total;
		//Print out the statistics
		System.out.println("\nK Nearest Neighbour Results:");
		System.out.println("K value is " + kValue);
		System.out.println("Correct Instances: " + correct + " out of " + total);
		System.out.println("Incorrect Instances: " + (total-correct) + " out of " + total);
		System.out.println("Classification Accuracy: " + String.format("%.4f", accuracy*100) + " % (4 dp)");
	}
	
	public int checkCorrectClassification(int testClass, int majorityClass) {
		if (testClass == majorityClass){
			System.out.println("Correct classification: " + majorityClass +" of " + testClass);
			return 1;
		} else {
			System.out.println("Incorrect classification: " + majorityClass +" should be :" + testClass);
		}
		return 0;
	}

	public int getClosestNeighbourClass(HashMap<Integer, Integer> neighbours, int majorityClass) {
		HashSet<Integer> majorityNeighbours = getEqualMajoritySet(neighbours, majorityClass);
		for (int i = 0; i < trainingSet.size(); i++) {
			for (Integer majority: majorityNeighbours) {
				// Return class with closest distance (training set is sorted by distance)
				if (majority == trainingSet.get(i).getDigitClass()) {
					return majority;
				}
			}	
		}
		return -1;
	}

	public int getMajorityClass(HashMap<Integer, Integer> neighbours) {
		int majorityClass = Integer.MIN_VALUE;
		int majorityFrequency = 0;
		for (Entry<Integer, Integer> neighbour: neighbours.entrySet()) {
			// Set the Majority class
			if (neighbour.getValue() > majorityFrequency){
				majorityClass = neighbour.getKey();
				majorityFrequency = neighbour.getValue();
			}

			System.out.println("CLOSEST NEIGHBOURS: " + neighbour.getKey() + " VALUE: " + neighbour.getValue());
		}
		return majorityClass;
	}

	public HashMap<Integer, Integer> getKNearestNeighbours(int kValue) {
		// Map of neighbours <class, frequency>
		HashMap<Integer, Integer> neighbours = new HashMap<Integer, Integer>();

		for (int i = 0; i < kValue; i++) {
			if (neighbours.get(trainingSet.get(i).getDigitClass()) == null) {
				neighbours.put(trainingSet.get(i).getDigitClass(), 1);
			} else {
				neighbours.put(trainingSet.get(i).getDigitClass(), neighbours.get(trainingSet.get(i).getDigitClass()) + 1);
			}
		}
		return neighbours;
	}

	/**
	 * 
	 * @param neighbours HashMap<class, frequency> 
	 * @param majorityClass the neighbour's class with the highest frequency
	 * @return
	 */
	public boolean isEqualNeighbours(HashMap<Integer, Integer> neighbours, int majorityClass) {
		int majorityFrequency = neighbours.get(majorityClass);
		for (Entry<Integer, Integer> neighbour: neighbours.entrySet()) {
			if (neighbour.getKey() != majorityClass && neighbour.getValue() == majorityFrequency){
				return true;
			}
		}
		return false;
	}

	public HashSet<Integer> getEqualMajoritySet(HashMap<Integer, Integer> neighbours, int majorityClass) {
		HashSet<Integer> equalNeighboursList = new HashSet<Integer>();
		int majorityFrequency = neighbours.get(majorityClass);
		for (Entry<Integer, Integer> neighbour: neighbours.entrySet()) {
			if (neighbour.getValue() == majorityFrequency){
				equalNeighboursList.add(neighbour.getKey());
			}
		}
		return equalNeighboursList;
	}

}



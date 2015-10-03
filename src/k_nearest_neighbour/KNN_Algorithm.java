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
		// TODO: uncomment this for KNN
		//		System.out.println("Please input k value:");
		//		Scanner input = new Scanner(System.in);
		//		int kValue = input.nextInt();
		int kValue = 1;

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

	/**
	 * Classification:
	 * Checks whether the testClass is the same as the majority class.
	 * If they are the same it returns 1, otherwise returns 0.
	 *
	 * @param testClass the class from the test point
	 * @param majorityClass any neighbour class with the highest frequency
	 * @return 1 if correct, 0 if not
	 */
	public int checkCorrectClassification(int testClass, int majorityClass) {
		if (testClass == majorityClass){
			System.out.println("Correct classification: " + majorityClass +" of " + testClass);
			return 1;
		} else {
			System.out.println("Incorrect classification: " + majorityClass +" should be :" + testClass);
		}
		return 0;
	}

	/**
	 * Special case:
	 * Retrieves the neighbour with the closest distance if there are multiple majority neighbour classes.
	 * If the distances are the same, then it retrieves the class according to the closest equal class to
	 * the index 0 in the training set .
	 *
	 * @param neighbours HashMap<Class, Frequency>
	 * @param majorityClass any majority class with the highest number of frequency
	 * @return
	 */
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

	/**
	 * Retrieves any majority class with the highest frequency.
	 *
	 * @param neighbours HashMap<Class, Frequency>
	 * @return int, the majority class label
	 */
	public int getMajorityClass(HashMap<Integer, Integer> neighbours) {
		int majorityClass = Integer.MIN_VALUE;
		int majorityFrequency = 0;
		for (Entry<Integer, Integer> neighbour: neighbours.entrySet()) {
			// Set the Majority class
			if (neighbour.getValue() > majorityFrequency){
				majorityClass = neighbour.getKey();
				majorityFrequency = neighbour.getValue();
			}

			// System.out.println("CLOSEST NEIGHBOURS: " + neighbour.getKey() + " VALUE: " + neighbour.getValue());
		}
		return majorityClass;
	}

	/**
	 * Retrieves the K Nearest Neighbours in the form of <Class, Frequency>.
	 *
	 * @param kValue k
	 * @return HashMap<Class, Frequency>
	 */
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
	 * Special case:
	 * Checks to see if there are multiple distinct majority neighbour classes with the highest frequency.
	 *
	 * @param neighbours HashMap<class, frequency>
	 * @param majorityClass the neighbour's class with the highest frequency
	 * @return true if multiple majority classes, false otherwise
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

	/**
	 * Special case:
	 * Retrieves all the distinct classes of the neighbours with the highest frequency.
	 *
	 * @param neighbours HashMap of Neighbours <Class, Frequency>
	 * @param majorityClass Any majority class with the highest frequency
	 * @return
	 */
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



package k_nearest_neighbour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class KNN_Algorithm {
	private ArrayList<DigitImage> trainingSet = new ArrayList<DigitImage>();
	private ArrayList<DigitImage> testSet = new ArrayList<DigitImage>();

	public KNN_Algorithm(ArrayList<DigitImage> trainingSet, ArrayList<DigitImage> testSet) {
		this.trainingSet = trainingSet;
		this.testSet = testSet;
	}

	public void calculate(){
		// Statistics
		int total = 0;
		int correct = 0;
		// Ask for user input
		System.out.println("Please input k value:");
		Scanner input = new Scanner(System.in);
		int kValue = input.nextInt();

		System.out.println("Calculating Classification Accuracy of K-Nearest Neighbour...");
		System.out.println("May take a while, please wait...");
		// Classify all data points in the test set
		for (final DigitImage testPoint: testSet) {
			// Sort array list according to the new test point
			Collections.sort(trainingSet, new Comparator<DigitImage>() {
				@Override
				public int compare(DigitImage o1, DigitImage o2) {
					return Double.compare(testPoint.distanceTo(o1),  testPoint.distanceTo(o2));
				}
			});

			// Map of Neighbours <Class, Frequency>
			HashMap<Integer, Integer> neighbours = new HashMap<Integer, Integer>();

			// Neighbours cannot be larger than trainingSet size
			if (kValue > trainingSet.size()) {
				System.out.println("K Neighbours cannot be larger than the training set size.");
				System.out.println("K Neighbour is now set to the training set size.");
				kValue = trainingSet.size();
			}

			// Retrieve the K Nearest Neighbours
			for (int i = 0; i < kValue; i++){
				// Calculate the majority vote (for classification)
				if (neighbours.get(trainingSet.get(i).getDigitClass()) == null){
					neighbours.put(trainingSet.get(i).getDigitClass(), 1);
				} else {
					neighbours.put(trainingSet.get(i).getDigitClass(), neighbours.get(trainingSet.get(i).getDigitClass()) + 1);
				}
			}

			// Classify
			int majorityClass = -1;
			int classification = -1;
			boolean conflicted = false;
			//Classification of majority class
			for (Entry<Integer, Integer> k: neighbours.entrySet()) {
				if (k.getValue() == majorityClass) {
					conflicted = true;
				}
				//Set the majority class
				if (k.getValue() > majorityClass) {
					majorityClass = k.getValue();
					classification = k.getKey();
				}
			}

			if (!conflicted) {
				// Classification Accuracy
				if (testPoint.getDigitClass() == classification){
					System.out.println("Correct classification: " + classification +" of " + testPoint.getDigitClass());
					correct++;
				} else {
					System.out.println("Incorrect classification: " + classification +" should be :" + testPoint.getDigitClass());
				}
			} else {
				if (testPoint.getDigitClass() == trainingSet.get(0).getDigitClass()) {
					correct++;
					System.out.println("Correct classification: " + trainingSet.get(0).getDigitClass() +" of " + testPoint.getDigitClass());
				} else {
					System.out.println("Incorrect classification: " + trainingSet.get(0).getDigitClass() +" should be :" + testPoint.getDigitClass());
				}
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

}



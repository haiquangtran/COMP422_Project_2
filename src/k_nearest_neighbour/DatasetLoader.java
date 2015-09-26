package k_nearest_neighbour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class DigitReader {
	private String trainingName;
	private String testSet;
	private ArrayList<DigitImage> trainingSet;

	public DigitReader(String trainingSet, String testSet){
		this.trainingName = trainingSet;
		this.testSet = testSet;
		//Load Training set
		loadTrainingSet();
		//load Test set
		//		loadTestSet();
	}

	//	public void loadTestSet(){
	//		//Read in test set
	//		File file = new File(testSet);
	//		//Statistics
	//		int total = 0;
	//		int correct = 0;
	//		//Ask for user input
	//		System.out.println("Please input k value:");
	//		Scanner input = new Scanner(System.in);
	//		int kValue = input.nextInt();
	//		//Read Test file
	//		try {
	//			Scanner scan = new Scanner(new FileReader(file));
	//			while (scan.hasNext()){
	//				//Make a new unidentified flower - Select test set data point
	//				final DigitImage unknown = new DigitImage(scan.nextDouble(),
	//						scan.nextDouble(), scan.nextDouble(), scan.nextDouble(), scan.next());
	//
	//				//Sort array list according to the new test point
	//				Collections.sort(trainingSet, new Comparator<DigitImage>() {
	//					@Override
	//					public int compare(DigitImage o1, DigitImage o2) {
	//						return Double.compare(unknown.distanceTo(o1),  unknown.distanceTo(o2));
	//					}
	//				});
	//				//K neighbours - String = type of the class , Integer = tally of them/count
	//				HashMap<String, Integer> neighbours = new HashMap<String, Integer>();
	//
	//				//Check k neighbours to see whos closest and record the statistics
	//				if (kValue > trainingSet.size()){kValue = trainingSet.size();}
	//				for (int i=0; i < kValue; i++){
	//					//put all the k neighbours in map
	//					if (neighbours.get(trainingSet.get(i).getDigitClass()) == null){
	//						neighbours.put(trainingSet.get(i).getDigitClass(), 1);
	//					} else {
	//						neighbours.put(trainingSet.get(i).getDigitClass(), neighbours.get(trainingSet.get(i).getDigitClass()) + 1);
	//					}
	//				}
	//
	//				//For classification
	//				int max = Integer.MIN_VALUE;
	//				String classification = null;
	//				//Classification of majority class
	//				for (Map.Entry<String, Integer> k: neighbours.entrySet())
	//				{
	//					//Set the majority class
	//					if (k.getValue() > max){
	//						max = k.getValue();
	//						classification = k.getKey();
	//					}
	//				}
	//
	//
	//				//Check classification is correct
	//				if (unknown.getDigitClass().equals(classification)){
	//					correct++;
	//				} else {
	//					System.out.println("Incorrect classification: " + classification +" should be :" + unknown.getDigitClass());
	//				}
	//
	//				//total in data
	//				total++;
	//			}
	//
	//			scan.close();
	//			//Accuracy percentage
	//			double accuracy = (double)correct/(double)total;
	//			//Print out the statistics
	//			System.out.println("k value is " + kValue);
	//			System.out.println("Correct " + correct + " out of " + total);
	//			System.out.println("Classification accuracy " + String.format("%.4f", accuracy*100) + " % (4 dp)");
	//		} catch (Exception e){
	//			e.printStackTrace();
	//		}
	//	}

	public void loadTrainingSet() {
		//Create training set
		trainingSet = new ArrayList<DigitImage>();
		//Read in training set
		File file = new File(trainingName);

		try {
			Scanner scan = new Scanner(new FileReader(file));
			//Read in data
			while (scan.hasNextLine()){
				double[] digits = new double[DigitImage.NUM_PIXELS];
				// Pixels in image of the digit
				for (int i = 0; i < DigitImage.NUM_PIXELS; i++) {
					if (scan.hasNextDouble()) {
						digits[i] = scan.nextDouble();
					}
				}
				// Digit class
				int digitClass = 0;
				if (scan.hasNextDouble()) {
					digitClass = (int) scan.nextDouble();
				}

				DigitImage digitImage = new DigitImage(digits, digitClass);
				trainingSet.add(digitImage);
			}

			System.out.println(trainingSet.size());
			scan.close();

		} catch (Exception e){
			e.printStackTrace();
		}
	}


}
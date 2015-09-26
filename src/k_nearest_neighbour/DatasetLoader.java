package k_nearest_neighbour;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DatasetLoader {
	private ArrayList<DigitImage> trainingSet = new ArrayList<DigitImage>();
	private ArrayList<DigitImage> testSet = new ArrayList<DigitImage>();

	public DatasetLoader(String fileName){
		int trainingInstances = 500;
		// Load training and test set
		loadDatasets(fileName, trainingInstances);
	}

	public void loadDatasets(String fileName, int numOfTrainingInstances) {
		//Read in training set
		File file = new File(fileName);

		try {
			int lineCount = 0;
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

				// Partition the data into training and test set
				if (lineCount < numOfTrainingInstances) {
					// Load training set
					trainingSet.add(digitImage);
				} else {
					// Load test set
					testSet.add(digitImage);
				}

				scan.nextLine();
				lineCount++;
			}
			scan.close();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<DigitImage> getTrainingSet () {
		return trainingSet;
	}

	public ArrayList<DigitImage> getTestSet () {
		return testSet;
	}

}
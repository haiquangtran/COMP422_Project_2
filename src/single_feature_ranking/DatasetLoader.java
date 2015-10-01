package single_feature_ranking;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DatasetLoader {
	private ArrayList<Feature> trainingSet = new ArrayList<Feature>();
	private ArrayList<Feature> testSet = new ArrayList<Feature>();

	public DatasetLoader(String fileName, int trainingSize){
		// Load training and test set
		loadDatasets(fileName, trainingSize);
	}

	public void loadDatasets(String fileName, int numOfTrainingInstances) {
		//Read in training set
		File file = new File(fileName);

		try {
			int lineCount = 0;
			Scanner scan = new Scanner(new FileReader(file));

			//Read in data
			while (scan.hasNextLine()){

				scan.nextLine();
				lineCount++;
			}
			scan.close();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<Feature> getTrainingSet () {
		return trainingSet;
	}

	public ArrayList<Feature> getTestSet () {
		return testSet;
	}

}
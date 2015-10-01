package single_feature_ranking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DatasetLoader {
	private ArrayList<Feature> trainingSet = new ArrayList<Feature>();
	private ArrayList<Feature> testSet = new ArrayList<Feature>();

	public DatasetLoader(String fileName, int trainingSize){
		// Load training and test set
		loadDatasets(fileName, trainingSize);
	}

	public void loadDatasets(String fileName, int numOfTrainingInstances) {
		// Read in training set
		File file = new File(fileName);
		String line;
		int lineCount = 0;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] contents = line.split(",");
				int classIndex = contents.length-1;
				double[] features = new double[classIndex];
				// class
				int featureClass = Integer.parseInt(contents[classIndex]);
				// features
				for (int i = 0; i < contents.length-1; i++) {
					features[i] = Double.parseDouble(contents[i]);
				}
				// create a feature
				Feature feature = new Feature(features, featureClass);

				// create datasets
				if (lineCount < numOfTrainingInstances) {
					trainingSet.add(feature);
				} else {
					testSet.add(feature);
				}

				lineCount++;
			}

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
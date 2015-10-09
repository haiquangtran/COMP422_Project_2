package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DatasetFileCreater {
	private String trainingSetFileName;
	private String testSetFileName;
	private ArrayList<String> wholeDataset = new ArrayList<String>();

	/**
	 *
	 * @param inputFileName
	 * @param outputTrainingSetName
	 * @param outputTestSetName
	 * @param trainingSetPercentage how many percentage of instances you want in the training set. (0.0 represents 0% to 1.0 represents 100%)
	 */
	public DatasetFileCreater(String inputFileName, String outputTrainingSetName, String outputTestSetName, double trainingSetPercentage){
		this.trainingSetFileName = outputTrainingSetName;
		this.testSetFileName = outputTestSetName;

		// Create training and test set files
		loadFileIntoArray(inputFileName);
		createDatasets(trainingSetFileName, testSetFileName, trainingSetPercentage);
	}

	private void writeHeaderInfo(PrintWriter writer, int numOfFeatures) {
		String featureName = "feature";
		for (int i = 1; i <= numOfFeatures; i++) {
			writer.print(featureName + "-" + i + " ,");
		}
		writer.print("class \n");
	}

	public void loadFileIntoArray(String fileName) {
		// Read in training set
		File file = new File(fileName);
		String line;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] contents = line.split(",");
				int classIndex = contents.length-1;
				String featureClass = "class-" + contents[classIndex];

				// features
				StringBuilder features = new StringBuilder();
				for (int i = 0; i < contents.length-1; i++) {
					features.append(contents[i] + ", ");
				}
				// class
				features.append(featureClass + "\n");

				wholeDataset.add(features.toString());
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public String getTrainingSetFileName() {
		return trainingSetFileName;
	}

	public String getTestSetFileName() {
		return testSetFileName;
	}

	/**
	 * Randomizes the ordering and creates a training dataset and a test dataset
	 * in a format acceptable for weka (CSV file).
	 */
	private void createDatasets(String trainingSetFileName, String testSetFileName, double trainingSetPercentage) {
		// Add random seed to randomize the ordering of training set and test set
		Random random = new Random(1);
		// Randomize the array
		Collections.shuffle(wholeDataset, random);
		int featureSize = wholeDataset.get(0).split(",").length-1;
		int numOfTrainingInstances = (int) (wholeDataset.size() * trainingSetPercentage);

		try {
			// Dataset Ouput files
			PrintWriter trainingWriter = new PrintWriter(trainingSetFileName);
			PrintWriter testWriter = new PrintWriter(testSetFileName);

			for (int i = 0; i < wholeDataset.size(); i++) {
				// Create headers
				if (i == 0) {
					writeHeaderInfo(trainingWriter, featureSize);
					writeHeaderInfo(testWriter, featureSize);
				}

				// Create datasets (csv files)
				if (i < numOfTrainingInstances) {
					trainingWriter.write(wholeDataset.get(i));
					trainingWriter.flush();
				} else {
					testWriter.write(wholeDataset.get(i));
					testWriter.flush();
				}
			}

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Returns number of features (excludes the class).
	 * @return
	 */
	public int getNumberOfFeatures() {
		return wholeDataset.get(0).split(",").length-1;
	}

}
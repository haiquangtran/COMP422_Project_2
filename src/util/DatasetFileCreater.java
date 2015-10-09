package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DatasetFileCreater {
	private String trainingSetFileName;
	private String testSetFileName;

	public DatasetFileCreater(String inputFileName, String outputTrainingSetName, String outputTestSetName, int trainingSize){
		this.trainingSetFileName = outputTrainingSetName;
		this.testSetFileName = outputTestSetName;

		// Create training and test set files
		createDatasetFiles(inputFileName, trainingSetFileName, testSetFileName, trainingSize);
	}

	private void writeHeaderInfo(PrintWriter writer, int numOfFeatures) {
		String featureName = "feature";
		for (int i = 1; i <= numOfFeatures; i++) {
			writer.print(featureName + "-" + i + " ,");
		}
		writer.print("class \n");
	}

	public void createDatasetFiles(String fileName, String trainingFileName, String testSetFileName, int numOfTrainingInstances) {
		// Read in training set
		File file = new File(fileName);
		String line;
		int lineCount = 0;

		try {
			// Dataset Ouput files
			PrintWriter trainingWriter = new PrintWriter(trainingFileName);
			PrintWriter testWriter = new PrintWriter(testSetFileName);
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				String[] contents = line.split(",");
				int classIndex = contents.length-1;
				int featureSize = contents.length-1;
				String featureClass = "class-" + contents[classIndex];
				// Create headers
				if (lineCount == 0) {
					writeHeaderInfo(trainingWriter, featureSize);
					writeHeaderInfo(testWriter, featureSize);
				}

				// features
				StringBuilder features = new StringBuilder();
				for (int i = 0; i < contents.length-1; i++) {
					features.append(contents[i] + ", ");
				}

				// class
				features.append(featureClass + "\n");

				// create datasets
				if (lineCount < numOfTrainingInstances) {
					trainingWriter.write(features.toString());
					trainingWriter.flush();
				} else {
					testWriter.write(features.toString());
					testWriter.flush();
				}

				lineCount++;
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
}
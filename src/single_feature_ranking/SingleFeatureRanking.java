package single_feature_ranking;


import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;

public abstract class SingleFeatureRanking {
	private ArrayList<Feature> features;
	private Instances trainingSet;
	private Instances testSet;
	private String trainingSetFileName;
	private String testSetFileName;

	public SingleFeatureRanking(String trainingSetFileName, String testSetFileName) {
		this.trainingSetFileName = trainingSetFileName;
		this.testSetFileName = testSetFileName;
		this.features = new ArrayList<Feature>();
		// Load datasets for weka
		loadDataSets();
	}

	private void loadDataSets() {
		//load CSV
		CSVLoader loaderTrain = new CSVLoader();
		CSVLoader loaderTest = new CSVLoader();

		try {
			loaderTrain.setSource(new File(this.getTrainingSetFileName()));
			loaderTest.setSource(new File(this.getTestSetFileName()));

			//Set training set from CSV file
			trainingSet = loaderTrain.getDataSet();
			trainingSet.setClassIndex(trainingSet.numAttributes()-1);
			//Set test set from CSV file
			testSet = loaderTest.getDataSet();
			testSet.setClassIndex(testSet.numAttributes()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Feature[] getTopFeatures(int num) {
		Feature[] topFeatures = new Feature[num];

		for (int i = 0; i < num; i++) {
			topFeatures[i] = this.getFeatures().get(i);
		}

		return topFeatures;
	}

	public String getTrainingSetFileName() {
		return trainingSetFileName;
	}

	public String getTestSetFileName() {
		return testSetFileName;
	}

	public Instances getTrainingSet() {
		return trainingSet;
	}

	public Instances getTestSet() {
		return testSet;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}
}

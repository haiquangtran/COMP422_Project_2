package questions;


import feature_selection.ChiSquaredRanking;
import feature_selection.Feature;
import feature_selection.InformationGainRanking;
import feature_selection.NaiveBayesWrapper;
import feature_selection.SingleFeatureRanking;
import naive_bayes.NaiveBayesAlgorithm;
import util.DatasetFileCreater;
import util.FileLoader;
import weka.attributeSelection.ChiSquaredAttributeEval;

public class Question_6 {

	/**
	 * Choose two single feature ranking algorithms (e.g. Information Gain and Chi-square) and
	 * apply them to Wisconsin Breast Cancer and Sonar data sets. Choose a classifier (e.g na¨ıve
	 * Bayes) and a number n representing the desired number of selected features (e.g. 5). Do the
	 * following:
	 * 1. Compare the performance of the classifier using all the features vs n top ranked features
	 * from each method. Analyse your results.
	 * 2. Use a wrapper approach to select n features and then measure the performance of the
	 * classifier using the selected features. Compare the result with the filter (single-ranking)
	 * approach and analyse your findings.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		String wbcd = FileLoader.getFilePath("wbcd");
		String sonar = FileLoader.getFilePath("sonar");
		String fileType = ".data";

		// Determines the training data size (percentage of the split from whole dataset)
		double trainingSetPercent = 0.5;	//50%
		int kFoldNumber = 10;
		int topFeatureNumber = 5;

		// Create training set and test set csv files
		//		DatasetFileCreater dataLoader = new DatasetFileCreater(wbcd + fileType, wbcd+"_training.csv", wbcd+"_test.csv", trainingSetPercent);
		DatasetFileCreater dataLoader = new DatasetFileCreater(sonar + fileType, sonar+"_training.csv", sonar+"_test.csv", trainingSetPercent);

		// Load training set and test set csv files
		String trainingSet = dataLoader.getTrainingSetFileName();
		String testSet = dataLoader.getTestSetFileName();

		InformationGainRanking infoGain = new InformationGainRanking(trainingSet, testSet);
		ChiSquaredRanking chiSquared = new ChiSquaredRanking(trainingSet, testSet);

		// Get top n features
		int[] infoGainFeatures = infoGain.getTopFeatureIndices(topFeatureNumber);
		int[] chiFeatures = chiSquared.getTopFeatureIndices(topFeatureNumber);

		// Test on naive bayes
		System.out.println("================ Naive Bayes on All " + dataLoader.getNumberOfFeatures() + " Features ==============================");
		NaiveBayesAlgorithm naive = new NaiveBayesAlgorithm(kFoldNumber, trainingSet, testSet);
		System.out.println("================ Information Gain Ranked Top " + topFeatureNumber + " Features ======================");
		NaiveBayesAlgorithm info = new NaiveBayesAlgorithm(kFoldNumber, infoGainFeatures, trainingSet, testSet);
		System.out.println("================ Chi Squared Ranked Top " + topFeatureNumber + " Features ===========================");
		NaiveBayesAlgorithm chi = new NaiveBayesAlgorithm(kFoldNumber, chiFeatures, trainingSet, testSet);

		// Wrapper Approach
		NaiveBayesWrapper wrapper = new NaiveBayesWrapper(kFoldNumber, trainingSet, testSet);
	}

}

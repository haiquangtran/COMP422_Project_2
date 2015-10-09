package questions;


import naive_bayes.NaiveBayesAlgorithm;
import single_feature_ranking.ChiSquaredRanking;
import single_feature_ranking.Feature;
import single_feature_ranking.InformationGainRanking;
import single_feature_ranking.SingleFeatureRanking;
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

		// Max lines in files
		final int SONAR_SIZE = 208;
		final int WBCD_SIZE = 569;

		// Determines the training data size (percentage of the split from whole dataset)
		double trainingSetPercent = 0.8;

		//		int trainingSize = (int) (WBCD_SIZE * trainingSetPercent);
		int trainingSize = (int) (SONAR_SIZE * trainingSetPercent);

		// Create training set and test set csv files
		//		DatasetFileCreater dataLoader = new DatasetFileCreater(wbcd + fileType, wbcd+"_training.csv", wbcd+"_test.csv", trainingSize);
		DatasetFileCreater dataLoader = new DatasetFileCreater(sonar + fileType, sonar+"_training.csv", sonar+"_test.csv", trainingSize);

		// Load training set and test set csv files
		String trainingSet = dataLoader.getTrainingSetFileName();
		String testSet = dataLoader.getTestSetFileName();

		InformationGainRanking infoGain = new InformationGainRanking(trainingSet, testSet);
		ChiSquaredRanking chiSquared = new ChiSquaredRanking(trainingSet, testSet);

		// Get top n features
		Feature[] infoGainFeatures = infoGain.getTopFeatures(20);
		Feature[] chiFeatures = chiSquared.getTopFeatures(20);

		// Write to csv file for weka

		// Test on naive bayes
		NaiveBayesAlgorithm naive = new NaiveBayesAlgorithm(10, trainingSet, testSet);
	}

}

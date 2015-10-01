package questions;

import java.util.ArrayList;

import single_feature_ranking.DatasetLoader;
import single_feature_ranking.Feature;
import single_feature_ranking.PearsonFeatureRanking;
import single_feature_ranking.SingleFeatureRanking;
import util.FileLoader;

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
		String wbcd = FileLoader.getFilePath("wbcd.data");
		String sonar = FileLoader.getFilePath("sonar.data");

		int trainingSize = 500;

		// Load datasets
		DatasetLoader dataLoader = new DatasetLoader(wbcd, trainingSize);
		ArrayList<Feature> trainingSet = dataLoader.getTrainingSet();
		ArrayList<Feature> testSet = dataLoader.getTestSet();

		SingleFeatureRanking pearson = new PearsonFeatureRanking(trainingSet);
		Feature[] features = pearson.getTopFeatures(10);

		// Test
		for (int i = 0; i < features.length; i++) {
			System.out.println(features[i]);
		}
	}

}

package single_feature_ranking;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


public class InformationGainRanking extends SingleFeatureRanking {
	private HashMap<Attribute, Double> infoGainScores = new HashMap<Attribute, Double>();
	private Instances trainingSet;
	private Instances testSet;

	public InformationGainRanking(String trainingSetFileName, String testSetFileName) {
		super(trainingSetFileName, testSetFileName);
		sortFeatures();
	}

	public void classify(int numOfTopFeatures) {
		try {
			//Create a naive bayes classifier
			Classifier naiveBayes = (Classifier) new NaiveBayes();
			naiveBayes.buildClassifier(trainingSet);

			//Test the model
			Evaluation eval = new Evaluation(trainingSet);
			eval.crossValidateModel(naiveBayes, testSet, 10, new Random(1));

			//Print the result
			String result = eval.toSummaryString();
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sortFeatures() {
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

			// Information Gain
			InfoGainAttributeEval evaluation = new InfoGainAttributeEval();

			for (int i = 0; i < trainingSet.numAttributes(); i++) {
				Attribute t_attr = trainingSet.attribute(i);
				double infoGain  = evaluation.evaluateAttribute(i);
				infoGainScores.put(t_attr, infoGain);
			}

			// Sort
			InformationGainRanking.entriesSortedByValues(infoGainScores, -1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Provides a {@code SortedSet} of {@code Map.Entry} objects. The sorting is in ascending order if {@param order} > 0
	 * and descending order if {@param order} <= 0.
	 * @param map   The map to be sorted.
	 * @param order The sorting order (positive means ascending, non-positive means descending).
	 * @param <K>   Keys.
	 * @param <V>   Values need to be {@code Comparable}.
	 * @return      A sorted set of {@code Map.Entry} objects.
	 */
	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map, final int order) {
		SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<>(
				new Comparator<Map.Entry<K,V>>() {
					public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
						return (order > 0) ? compareToRetainDuplicates(e1.getValue(), e2.getValue()) : compareToRetainDuplicates(e2.getValue(), e1.getValue());
					}
				}
				);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	private static <V extends Comparable<? super V>> int compareToRetainDuplicates(V v1, V v2) {
		return (v1.compareTo(v2) == -1) ? -1 : 1;
	}

}
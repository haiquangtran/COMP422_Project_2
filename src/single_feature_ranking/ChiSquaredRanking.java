package single_feature_ranking;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import weka.attributeSelection.ChiSquaredAttributeEval;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


public class ChiSquaredRanking extends SingleFeatureRanking {
	private HashMap<Attribute, Double> chiScores = new HashMap<Attribute, Double>();
	private Instances trainingSet;
	private Instances testSet;

	public ChiSquaredRanking(String trainingSetFileName, String testSetFileName) {
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

			double[][] matrix = eval.confusionMatrix();

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

			// Chi Squared
			ChiSquaredAttributeEval evaluation = new ChiSquaredAttributeEval();
			evaluation.buildEvaluator(trainingSet);

			for (int i = 0; i < trainingSet.numAttributes(); i++) {
				Attribute t_attr = trainingSet.attribute(i);
				double chiSquared  = evaluation.evaluateAttribute(i);
				chiScores.put(t_attr, chiSquared);
			}

			// Sort
			SingleFeatureRanking.entriesSortedByValues(chiScores, -1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
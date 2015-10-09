package single_feature_ranking;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
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

	public ChiSquaredRanking(String trainingSetFileName, String testSetFileName) {
		super(trainingSetFileName, testSetFileName);
		sortFeatures();
	}

	public void classify(int numOfTopFeatures) {
		Instances trainingSet = this.getTrainingSet();
		Instances testSet = this.getTestSet();

		try {
			//Create a naive bayes classifier
			Classifier naiveBayes = (Classifier) new NaiveBayes();
			naiveBayes.buildClassifier(trainingSet);

			//Test the model
			Evaluation eval = new Evaluation(trainingSet);
			eval.crossValidateModel(naiveBayes, testSet, 10, new Random());

			//Print the result
			String result = eval.toSummaryString();
			System.out.println(result);

			double[][] matrix = eval.confusionMatrix();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sortFeatures() {
		Instances trainingSet = getTrainingSet();

		try {
			// Chi Squared
			ChiSquaredAttributeEval evaluation = new ChiSquaredAttributeEval();
			evaluation.buildEvaluator(trainingSet);

			for (int i = 0; i < trainingSet.numAttributes(); i++) {
				Attribute attr = trainingSet.attribute(i);
				double chiSquared  = evaluation.evaluateAttribute(i);
				this.getFeatures().add(new Feature(attr, i, chiSquared));
			}

			// sort features by descending order of score
			Collections.sort(getFeatures(), new Comparator<Feature>() {
				@Override
				public int compare(Feature o1, Feature o2) {
					return o2.compareTo(o1);
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
package single_feature_ranking;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Instances;
import weka.core.converters.CSVLoader;


public class PearsonFeatureRanking extends SingleFeatureRanking {

	public PearsonFeatureRanking(String trainingSetFileName,
			String testSetFileName) {
		super(trainingSetFileName, testSetFileName);
		// TODO Auto-generated constructor stub
	}

	public void calculate() {
		//Create features
		//load CSV
		CSVLoader loaderTrain = new CSVLoader();
		CSVLoader loaderTest = new CSVLoader();

		try {
			loaderTrain.setSource(new File(this.getTrainingSetFileName()));
			loaderTest.setSource(new File(this.getTestSetFileName()));

			//Set training set from CSV file
			Instances trainingSet = loaderTrain.getDataSet();
			trainingSet.setClassIndex(trainingSet.numAttributes()-1);
			//Set test set from CSV file
			Instances testSet = loaderTest.getDataSet();
			testSet.setClassIndex(testSet.numAttributes()-1);

			//Create a naive bayes classifier
			Classifier naiveBayes = (Classifier) new NaiveBayes();
			naiveBayes.buildClassifier(trainingSet);

			//Test the model
			Evaluation eval = new Evaluation(trainingSet);
			eval.evaluateModel(naiveBayes, testSet);
			//			eval.crossValidateModel(naiveBayes, testSet, 10, new Random(1));

			//Print the result
			String result = eval.toSummaryString();
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
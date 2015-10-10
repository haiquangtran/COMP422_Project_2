package feature_selection;

import java.io.File;
import java.util.Random;

import weka.attributeSelection.BestFirst;
import weka.attributeSelection.WrapperSubsetEval;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.CSVLoader;
import weka.filters.unsupervised.attribute.Remove;

public class NaiveBayesWrapper {
	private String trainingFileName;

	public NaiveBayesWrapper(int kFoldNumber, String trainingFileName) {
		this.trainingFileName = trainingFileName;
		// Wrapper approach
		naiveBayesWrapper(kFoldNumber);
	}

	private void naiveBayesWrapper(int kFoldNumber) {
		//load CSV
		CSVLoader loaderTrain = new CSVLoader();

		try {
			loaderTrain.setSource(new File(trainingFileName));

			//Set training set from CSV file
			Instances trainingSet = loaderTrain.getDataSet();
			trainingSet.setClassIndex(trainingSet.numAttributes()-1);

			//Create a naive bayes classifier
			Classifier naiveBayes = (Classifier) new NaiveBayes();

			WrapperSubsetEval wrapper = new WrapperSubsetEval();
			wrapper.setClassifier(naiveBayes);
			wrapper.setFolds(kFoldNumber);
			wrapper.setEvaluationMeasure(new SelectedTag(WrapperSubsetEval.EVAL_ACCURACY, WrapperSubsetEval.TAGS_EVALUATION));
			wrapper.buildEvaluator(trainingSet);

			BestFirst bfs = new BestFirst();
			bfs.setDirection(new SelectedTag(1, BestFirst.TAGS_SELECTION));
			bfs.search(wrapper, trainingSet);

			//Test the model
//			Evaluation eval = new Evaluation(trainingSet);
//			eval.crossValidateModel(naiveBayes, trainingSet, kFoldNumber, new Random(1));
//
//			//Print the result
//			String result = eval.toSummaryString();
//			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

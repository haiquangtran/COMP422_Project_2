package feature_selection;

import java.io.File;
import java.util.Random;

import naive_bayes.NaiveBayesAlgorithm;
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
	private String testFileName;

	public NaiveBayesWrapper(int kFoldNumber, String trainingFileName, String testFileName) {
		this.trainingFileName = trainingFileName;
		this.testFileName = testFileName;
		// Wrapper approach
		naiveBayesWrapper(kFoldNumber);
	}

	private void naiveBayesWrapper(int kFoldNumber) {
		//load CSV
		CSVLoader loaderTrain = new CSVLoader();
		CSVLoader loaderTest = new CSVLoader();

		try {
			loaderTrain.setSource(new File(trainingFileName));
			loaderTest.setSource(new File(testFileName));

			//Set training set from CSV file
			Instances trainingSet = loaderTrain.getDataSet();
			trainingSet.setClassIndex(trainingSet.numAttributes()-1);
			//Set test set from CSV file
			Instances testSet = loaderTest.getDataSet();
			testSet.setClassIndex(testSet.numAttributes()-1);

			WrapperSubsetEval wrapper = new WrapperSubsetEval();
			wrapper.setClassifier((Classifier) new NaiveBayes());
			wrapper.setFolds(kFoldNumber);
			wrapper.setEvaluationMeasure(new SelectedTag(WrapperSubsetEval.EVAL_ACCURACY, WrapperSubsetEval.TAGS_EVALUATION));
			wrapper.buildEvaluator(trainingSet);

			BestFirst bfs = new BestFirst();
			bfs.setDirection(new SelectedTag(1, BestFirst.TAGS_SELECTION));
			int[] selectedFeatures = bfs.search(wrapper, trainingSet);
			// Need bigger array for the class attribute
			int[] selectedFeaturesIndices = new int[selectedFeatures.length + 1];

			for (int i = 0; i < selectedFeatures.length; i++) {
				selectedFeaturesIndices[i] = selectedFeatures[i];
			}

			System.out.println("================ Wrapper Approach Using " +  selectedFeatures.length + " Features ===========================");
			NaiveBayesAlgorithm naive = new NaiveBayesAlgorithm(kFoldNumber, selectedFeaturesIndices, trainingFileName, testFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package questions;

import feature_construction.GeneticProgramming;
import genetic_programming.GenerateRegressionDataset;
import genetic_programming.SymbolicRegression;

import java.awt.BorderLayout;
import java.io.File;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;

import decision_tree.DecisionTreeC4_5;
import naive_bayes.NaiveBayesAlgorithm;
import util.DatasetFileCreater;
import util.FileLoader;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;



public class Question_5 {

	/**
	 * First apply the naive Bayes and Decision Tree (C4.5) classifiers to the Balance Scale and
	 * Wine Recognition datasets and measure the classification performance using 10-fold cross validation.
	 * Then develop a GP-based feature construction algorithm to construct features for
	 * the two problems. Feed the constructed features to these classifiers and report changes in
	 * their performance. Consider the following points:
	 * • Design an appropriate fitness function.
	 * • Use the best individual (out of one or several runs) to transform the data. Include the
	 * program (the best individual) in your report (in the form of Lisp code, tree, ...).
	 * • Report and analyse changes in performance.
	 *
	 * @param args
	 */
	public Question_5(String dataset) {
		final String FILE_TYPE = ".data";
		final String CONFIG_TYPE = ".conf";

		// Settings
		int kFoldNumber = 10;
		// Determines the training data size (percentage of the split from whole dataset)
		double trainingSetPercent = 0.5; //%

		if (! (dataset.equalsIgnoreCase("wine") || dataset.equalsIgnoreCase("balance"))) {
			System.out.println("q5: Invalid argument for name. args[2] should be wine or balance.");
			return;
		}

		String outputTrainingFile = FileLoader.getFilePath("construct_features_training.csv");
		String outputTestFile = FileLoader.getFilePath("construct_features_test.csv");

		// Config files
		String configFile = FileLoader.getFilePath(dataset + CONFIG_TYPE);

		// Create training set and test set csv files
		DatasetFileCreater dataLoader = new DatasetFileCreater(FileLoader.getFilePath(dataset) + FILE_TYPE, FileLoader.getFilePath(dataset)+"_training.csv", FileLoader.getFilePath(dataset)+"_test.csv", trainingSetPercent);

		// Load training set and test set csv files
		String trainingSet = dataLoader.getTrainingSetFileName();
		String testSet = dataLoader.getTestSetFileName();

		// Evaluate on naive and c4.5 (should set trainingSet to be 100% of the file)
		//		evaluation(kFoldNumber, trainingSet, trainingSet);

		constructFeatures(configFile, outputTrainingFile, outputTestFile, trainingSet, testSet);
		evaluation(kFoldNumber, outputTrainingFile, outputTestFile);
	}

	private void constructFeatures(String configFilePath, String outputTrainingFileName, String outputTestFileName, String trainingSet, String testSet) {
		try {
			// Solve symbolic regression problem using Genetic Programming
			GPConfiguration config = new GPConfiguration();
			GeneticProgramming constructFeatures = new GeneticProgramming(config);
			String[] mainArgs = {configFilePath};

			try {
				// Find the best program
				IGPProgram bestSolution = constructFeatures.main(mainArgs);
				// Feed the training and test data to the program
				constructFeatures.constructFeatures(bestSolution, trainingSet, outputTrainingFileName);
				constructFeatures.constructFeatures(bestSolution, testSet, outputTestFileName);

				// Evaluate how good the feature is
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void evaluation(int kFoldNumber, String trainingSet, String testSet) {
		// Cross validation on the training set (no need for test set)
		System.out.println("================ Naive Bayes ==============================");
		NaiveBayesAlgorithm naive = new NaiveBayesAlgorithm(kFoldNumber, trainingSet, testSet);
		System.out.println("================ C4.5 ==============================");
		DecisionTreeC4_5 c4 = new DecisionTreeC4_5(kFoldNumber, trainingSet, testSet);
	}
}

package questions;

import java.awt.BorderLayout;
import java.io.File;

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
	 * Wine Recognition datasets and measure the classification performance using 10-fold crossvalidation.
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
	public static void main(String[] args) {

	}

}

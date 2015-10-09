package naive_bayes;

import java.awt.BorderLayout;
import java.io.File;
import java.util.Random;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.CSVLoader;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;

public class NaiveBayesAlgorithm {
	private String trainingFileName;
	private String testFileName;

	public NaiveBayesAlgorithm(int kFoldNumber, String trainingFileName, String testFileName) {
		this.trainingFileName = trainingFileName;
		this.testFileName = testFileName;
		//Use features extracted on naive bayes
		runNaiveBayes(kFoldNumber);
	}

	private void runNaiveBayes(int kFoldNumber) {

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

			//Create a naive bayes classifier
			Classifier naiveBayes = (Classifier) new NaiveBayes();
			naiveBayes.buildClassifier(trainingSet);

			//Test the model
			Evaluation eval = new Evaluation(trainingSet);
			//			eval.evaluateModel(naiveBayes, testSet);
			eval.crossValidateModel(naiveBayes, testSet, kFoldNumber, new Random(1));

			//Print the result
			String result = eval.toSummaryString();
			System.out.println(result);

			//Generate ROC curve
			//			generateROC(eval);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Generates and displays a ROC curve from a dataset. Uses a default
	 * NaiveBayes to generate the ROC data.
	 *
	 */
	private static void generateROC(Evaluation eval) throws Exception {
		// generate curve
		ThresholdCurve tc = new ThresholdCurve();
		int classIndex = 0;
		Instances result = tc.getCurve(eval.predictions(), classIndex);

		// plot curve
		ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
		vmc.setROCString("(Area under ROC = " +
				Utils.doubleToString(tc.getROCArea(result), 4) + ")");
		vmc.setName(result.relationName());
		PlotData2D tempd = new PlotData2D(result);
		tempd.setPlotName(result.relationName());
		tempd.addInstanceNumberAttribute();

		// specify which points are connected
		boolean[] cp = new boolean[result.numInstances()];
		for (int n = 1; n < cp.length; n++)
			cp[n] = true;
		tempd.setConnectPoints(cp);

		// add plot
		vmc.addPlot(tempd);

		// display curve
		String plotName = vmc.getName();
		final javax.swing.JFrame jf =
				new javax.swing.JFrame("Weka Classifier Visualize: " + plotName);
		jf.setSize(500,400);
		jf.getContentPane().setLayout(new BorderLayout());
		jf.getContentPane().add(vmc, BorderLayout.CENTER);
		jf.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				jf.dispose();
			}
		});
		jf.setVisible(true);
	}
}

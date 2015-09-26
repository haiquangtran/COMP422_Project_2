package genetic_programming;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateRegressionDataset {

	public GenerateRegressionDataset(String fileName, int numOfexamples, int minValue, int maxValue) {
		// Generate data set to solve symbolic regression problem
		generateDataSet(fileName, numOfexamples, minValue, maxValue);
	}

	public void generateDataSet(String fileName, int numOfExamples, int minValue, int maxValue) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(fileName, "UTF-8");

			// How many lines in the file
			for (int i = 0; i < numOfExamples; i++) {
				double input = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
				double outputClass = regressionProblem(input);

				// Generate data
				writer.println(input + " " + outputClass);
			}

			writer.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double regressionProblem(double x) {
		if (x > 0) {
			return (1/x) + Math.sin(x);
		} else {
			return ((2*x) + (Math.pow(x,2)) + 3.0);
		}
	}

}

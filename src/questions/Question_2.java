package questions;

import util.FileLoader;
import k_nearest_neighbour.DatasetLoader;

public class Question_2 {

	/**
	 * This question concerns applying neural networks to nine digit-recognition tasks. Each task
	 * involves a collection of binary images (pictures) of printed digits (0, 1,..., 9). The tasks are
	 * classification problems of increasing di"culty, as shown in Table 1. In all of these recognition
	 * problems, the goal is to automatically recognize which of the 10 classes (digits 0, 1, 2, ...,
	 * 9) each pattern (digit example) belongs to. Except for the first task which contains clean
	 * images (no noise), all data examples in the other eight task have been corrupted by noise.
	 * The noise is generated by randomly flipping a pixel (from 0 to 1 or from 1 to 0). The noise
	 * ratio determines what proportion of pixels in an image have been flipped.
	 *
	 * Figure 1: Example patterns (digits) from each task. The nine rows of examples correspond to
	 * the nine tasks. In this question, you need to do at least four tasks including tasks 4 and 6 and two or more
	 * of your own choice.
	 *
	 * • For each task, develop a neural network classifier with an appropriate architecture which
	 * does the best possible job of correctly recognising unseen digits. Consider the pattern
	 * file format, network architecture, learning parameters, termination criteria, etc.
	 *
	 * • Use the nearest neighbour method to perform your tasks. Consider the pattern file format.
	 *
	 * • Present the results of the two methods in an appropriate form. Note that the results
	 * should be an average (or mean and standard deviation) of multiple runs (such as 10 runs).
	 *
	 * • Compare the two methods and results and draw your conclusions.
	 */
	public static void main(String[] args) {
		//first argument is training set, second argument is test set
		//Training set
		String line = FileLoader.getFilePath("digits00");
		//Test set
		DatasetLoader reader = new DatasetLoader(line);
	}

}

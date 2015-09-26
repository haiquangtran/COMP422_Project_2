package k_nearest_neighbour;

import java.util.ArrayList;

public class DigitImage {
	// Each image is a binary image containing 7x7 pixels
	public static final int NUM_PIXELS = 49;
	private double[] digits = new double[NUM_PIXELS];
	private int digitClass;

	public DigitImage(double[] digits, int digitClass) {
		this.digits = digits;
		this.digitClass = digitClass;
	}

	/**
	 * Uses general n-dimensional Euclidean distance for KNN distance measure.
	 *
	 * @param other
	 * @return
	 */
	public double distanceTo(DigitImage other){
		double total = 0;

		// Euclidean distance
		for (int i = 0; i < digits.length; i++) {
			double current = this.digits[i];
			double neighbour = other.digits[i];
			total += (Math.pow(current-neighbour, 2) / Math.pow(current-neighbour, 2));
		}

		return  Math.sqrt(total);
	}

	public int getDigitClass(){
		return digitClass;
	}

}
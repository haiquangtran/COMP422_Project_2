package single_feature_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import k_nearest_neighbour.DigitImage;

public class PearsonCorrelation extends SingleFeatureRanking {

	public PearsonCorrelation(ArrayList<Feature> features) {
		super(features);
	}

	public double pearsonCorrelation() {
		// r in [-1, 1]
		double x = 0;
		double y = 0;
		double totalProduct = 0;
		int n = this.getFeatures().size();
		double r = 0;

		// Find totals for xy, x and y
		for (int i = 0; i < n; i++) {
			x += this.getFeatures().get(i).getX();
			y += this.getFeatures().get(i).getY();
			totalProduct += x * y;
		}

		// TODO: Check this equation
		r = n * (totalProduct) - (x*y) / Math.sqrt((n*Math.pow(x, 2)-Math.pow(x, 2))*(n*Math.pow(y, 2)-Math.pow(y, 2)));

		return r;
	}

	private void sortFeatures() {

//		Collections.sort(this.getFeatures(), new Comparator<Feature>() {
//			@Override
//			public int compare(Feature feature1, Feature feature2) {
//				return Double.compare(,  testPoint.distanceTo(o2));
//			}
//		});
	}

	private Feature[] getTopFeatures(int n) {
		Feature[] topFeatures = new Feature[n];

		return topFeatures;
	}

}
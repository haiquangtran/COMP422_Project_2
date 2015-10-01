package single_feature_ranking;

public class Feature {
	private double[] features;
	private int featureClass;
	// Testing purposes
	private double pearsonScore;

	public Feature(double[] features, int featureClass) {
		this.features = features;
		this.featureClass = featureClass;
	}

	public double comparePearsonCorrelationTo(Feature other) {
		double x = 0;
		double y = 0;
		double totalProduct = 0;
		int n = features.length;
		double r = 0;

		// Find totals for xy, x and y
		for (int i = 0; i < n; i++) {
			x += features[i];
			y += other.getFeatures()[i];
			totalProduct += x * y;
		}
		// TODO: Check this equation
		r = n * (totalProduct) - (x*y) / Math.sqrt((n*Math.pow(x, 2)-Math.pow(x, 2))*(n*Math.pow(y, 2)-Math.pow(y, 2)));
		this.pearsonScore = r;

		// r in [-1, 1]
		return r;
	}

	/**
	 * For testing purposes.
	 *
	 * @return
	 */
	public double getPearsonScore() {
		return pearsonScore;
	}

	public double[] getFeatures() {
		return features;
	}

	public int getFeatureClass() {
		return featureClass;
	}

	@Override
	public String toString() {
		StringBuilder content = new StringBuilder();

		content.append("Features: ");
		for (int i = 0; i < features.length; i++) {
			content.append(features[i] + " ");
		}
		content.append("class: " + featureClass);

		return content.toString();
	}
}

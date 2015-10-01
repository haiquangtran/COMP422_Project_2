package single_feature_ranking;

public class Feature {
	private double[] features;
	private int featureClass;

	public Feature(int featureSize, int featureClass) {
		this.features = new double[featureSize];
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
		// r in [-1, 1]
		return r;
	}

	public double[] getFeatures() {
		return features;
	}

}

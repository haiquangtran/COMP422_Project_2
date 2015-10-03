package single_feature_ranking;

public class Feature {
	private double[] features;
	private int featureClass;

	public Feature(double[] features, int featureClass) {
		this.features = features;
		this.featureClass = featureClass;
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

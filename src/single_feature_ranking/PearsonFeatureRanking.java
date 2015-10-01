package single_feature_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PearsonFeatureRanking extends SingleFeatureRanking {

	public PearsonFeatureRanking(ArrayList<Feature> features) {
		super(features);
	}

	private void sortFeatures() {
		Collections.sort(this.getFeatures(), new Comparator<Feature>() {
			@Override
			public int compare(Feature feature1, Feature feature2) {
				return Double.compare(feature2.comparePearsonCorrelationTo(feature2),  feature1.comparePearsonCorrelationTo(feature2));
			}
		});
	}

	private Feature[] getTopFeatures(int n) {
		Feature[] topFeatures = new Feature[n];

		for (int i = 0; i < n; i++) {
			topFeatures[i] = this.getFeatures().get(i);
		}

		return topFeatures;
	}

}
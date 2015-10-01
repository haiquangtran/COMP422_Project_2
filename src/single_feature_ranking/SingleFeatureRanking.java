package single_feature_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class SingleFeatureRanking {
	private ArrayList<Feature> features;

	public SingleFeatureRanking(ArrayList<Feature> features) {
		this.features = features;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

	public Feature[] getTopFeatures(int n) {
		Feature[] topFeatures = new Feature[n];

		for (int i = 0; i < n; i++) {
			topFeatures[i] = this.getFeatures().get(i);
		}

		return topFeatures;
	}

}

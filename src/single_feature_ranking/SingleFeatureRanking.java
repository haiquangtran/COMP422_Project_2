package single_feature_ranking;

import java.util.ArrayList;

public abstract class SingleFeatureRanking {
	private ArrayList<Feature> features;

	public SingleFeatureRanking(ArrayList<Feature> features) {
		this.features = features;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

}

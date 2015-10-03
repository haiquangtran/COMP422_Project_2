package single_feature_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class SingleFeatureRanking {
	private String trainingSetFileName;
	private String testSetFileName;

	public SingleFeatureRanking(String trainingSetFileName, String testSetFileName) {
		this.trainingSetFileName = trainingSetFileName;
		this.testSetFileName = testSetFileName;
	}

	public Feature[] getTopFeatures(int n) {
		return null;
	}

	public String getTrainingSetFileName() {
		return trainingSetFileName;
	}

	public String getTestSetFileName() {
		return testSetFileName;
	}

}

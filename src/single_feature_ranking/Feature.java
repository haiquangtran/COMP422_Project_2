package single_feature_ranking;

import weka.core.Attribute;

public class Feature {
	private Attribute attribute;
	private double score;

	public Feature(Attribute attribute, double score) {
		this.attribute = attribute;
		this.score = score;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public double getScore() {
		return score;
	}

	public int compareTo(Feature other) {
		Double score1 = new Double(this.score);
		Double score2 = new Double(other.getScore());
		// Compare by score
		return score1.compareTo(score2);
	}

}

package feature_selection;

import weka.core.Attribute;

public class Feature {
	private Attribute attribute;
	private int attributeIndex;
	private double score;

	public Feature(Attribute attribute, int attributeIndex, double score) {
		this.attribute = attribute;
		this.attributeIndex = attributeIndex;
		this.score = score;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public double getScore() {
		return score;
	}

	public int getAttributeIndex(){
		return attributeIndex;
	}

	public int compareTo(Feature other) {
		Double score1 = new Double(this.score);
		Double score2 = new Double(other.getScore());
		// Compare by score
		return score1.compareTo(score2);
	}

}

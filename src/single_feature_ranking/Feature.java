package single_feature_ranking;

public class Feature {
	private int x;
	private int y;
	private double score;

	public Feature(int x, int y, double score) {
		this.x = x;
		this.y = y;
		this.score = score;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getScore() {
		return score;
	}

}

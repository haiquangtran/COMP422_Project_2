package feature_construction;

public class EntropyClass {
	// Frequnecy of expected class (classified)
	private double frequency = 0.0;
	// Frequency of actual class
	private double totalFrequency = 0.0;

	public void addToFrequency() {
		frequency++;
	}

	public void addToNumberOfTotalClasses() {
		totalFrequency++;
	}

	/**
	 * Shannons Entropy
	 *
	 * @param feature
	 * @return
	 */
	public double calculateEntropy(int classType) {
		double entropy = 0.0;
		for (int i = 0; i < totalFrequency; i++) {
			// prob of class occurring
			double probability = (double)(frequency/totalFrequency);
			entropy -= probability * (Math.log(probability) / Math.log(2));
		}

		return entropy;
	}

	public int getTotalFrequency() {
		return (int) totalFrequency;
	}

}

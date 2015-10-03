package single_feature_ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class SingleFeatureRanking {
	private String trainingSetFileName;
	private String testSetFileName;

	public SingleFeatureRanking(String trainingSetFileName, String testSetFileName) {
		this.trainingSetFileName = trainingSetFileName;
		this.testSetFileName = testSetFileName;
	}

	public String getTrainingSetFileName() {
		return trainingSetFileName;
	}

	public String getTestSetFileName() {
		return testSetFileName;
	}

	/**
	 * Provides a {@code SortedSet} of {@code Map.Entry} objects. The sorting is in ascending order if {@param order} > 0
	 * and descending order if {@param order} <= 0.
	 * @param map   The map to be sorted.
	 * @param order The sorting order (positive means ascending, non-positive means descending).
	 * @param <K>   Keys.
	 * @param <V>   Values need to be {@code Comparable}.
	 * @return      A sorted set of {@code Map.Entry} objects.
	 */
	static <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map, final int order) {
		SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<>(
				new Comparator<Map.Entry<K,V>>() {
					public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
						return (order > 0) ? compareToRetainDuplicates(e1.getValue(), e2.getValue()) : compareToRetainDuplicates(e2.getValue(), e1.getValue());
					}
				}
				);
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	private static <V extends Comparable<? super V>> int compareToRetainDuplicates(V v1, V v2) {
		return (v1.compareTo(v2) == -1) ? -1 : 1;
	}

}

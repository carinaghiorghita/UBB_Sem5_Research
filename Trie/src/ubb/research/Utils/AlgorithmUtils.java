package ubb.research.Utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.IntStream;

public class AlgorithmUtils {
    /**
     * get Levenshtein Edit Distance between two words
     */
    public static int led(String word1, String word2) {
        char[] wordArr1 = (' ' + word1).toCharArray();
        char[] wordArr2 = (' ' + word2).toCharArray();
        int[][] matrix = new int[wordArr1.length][wordArr2.length];
        IntStream.range(1, matrix.length).forEach(i -> matrix[i][0] = i);
        IntStream.range(1, matrix[0].length).forEach(j -> matrix[0][j] = j);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i - 1][j - 1];
                if (wordArr1[i] != wordArr2[j]) {
                    matrix[i][j] = min(matrix[i][j], matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }
        return matrix[word1.length()][word2.length()];
    }

    public static <T> T min(Collection<T> ts, Comparator<? super T> comparator) {
        if (ts.size() == 0) {
            return null;
        }
        Iterator<T> iterator = ts.iterator();
        T min = iterator.next();
        while (iterator.hasNext()) {
            T t = iterator.next();
            if (comparator.compare(t, min) < 0) {
                min = t;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(T... ts) {
        return min((T) Arrays.asList(ts));
    }
}

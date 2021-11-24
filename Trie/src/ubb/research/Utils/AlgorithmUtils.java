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
    public static int getLED(String word1, String word2){
        if (word1.isEmpty()) {
            return word2.length();
        }

        if (word2.isEmpty()) {
            return word1.length();
        }

        int substitution = getLED(word1.substring(1), word2.substring(1))
                + costOfSubstitution(word1.charAt(0), word2.charAt(0));
        int insertion = getLED(word1, word2.substring(1)) + 1;
        int deletion = getLED(word1.substring(1), word2) + 1;

        return Math.min(Math.min(substitution, insertion), deletion);
    }

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
}

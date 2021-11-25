package ubb.research.Utils;

import ubb.research.Model.Node;

import java.util.HashSet;
import java.util.Set;

public class TrieUtils {
    public static Set<String> traverse(Node node, String word){
        if(node.getChildren().isEmpty()) {
            if (node.isEndOfWord()) {
                var list = new HashSet<String>();
                list.add(word);
                return list;
            } else return new HashSet<>();
        }

        var results = new HashSet<String>();
        if(node.isEndOfWord())
            results.add(word);

        for(Node child : node.getChildren()){
            results.addAll(traverse(child, word + Character.toLowerCase(child.getValue())));
        }

        return results;
    }

    public static class SortString implements java.util.Comparator<String> {

        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }
}

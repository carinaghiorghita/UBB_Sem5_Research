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

        for(Node child : node.getChildren()){
            results.addAll(traverse(child, word + Character.toLowerCase(child.getValue())));
        }

        return results;
    }

}

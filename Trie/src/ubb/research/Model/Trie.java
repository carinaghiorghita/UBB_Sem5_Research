package ubb.research.Model;

import java.util.*;

public class Trie {
    private Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String key) {
        var length = key.length();
        var currentNode = root;
        var currentIndex = 0;

        while (currentIndex < length) {
            var currentCharacter = key.charAt(currentIndex);

            var children = currentNode.getChildren();
            var isFound = false;

            for (Node node : children)
                if (Character.toLowerCase(node.getValue()) == Character.toLowerCase(currentCharacter)) {
                    isFound = true;
                    currentNode = node;
                    break;
                }
            if (!isFound) {
                var newNode = new Node(currentCharacter);
                currentNode.getChildren().add(newNode);
                currentNode = newNode;
            }

            currentIndex++;
        }

        currentNode.setEndOfWord(true);
    }

    public boolean search(String key) {
        if (key.length() == 0)
            return true;

        var length = key.length();
        var currentNode = root;
        var currentIndex = 0;

        while (currentIndex < length) {
            var currentCharacter = key.charAt(currentIndex);

            var children = currentNode.getChildren();
            var isFound = false;
            for (Node node : children)
                if (Character.toLowerCase(node.getValue()) == Character.toLowerCase(currentCharacter)) {
                    isFound = true;
                    currentNode = node;
                    break;
                }
            if (!isFound)
                return false;

            currentIndex++;
        }

        return currentNode.isEndOfWord();
    }

    public Set<String> getByPrefix(String prefix) {
        prefix = prefix.toLowerCase(Locale.ROOT);

        Set<String> wordsWithPrefix = new HashSet<>();

        var length = prefix.length();
        var currentIndex = 0;
        var currentNode = root;

        while(currentIndex<length){
            var currentCharacter = prefix.charAt(currentIndex);
            var children = currentNode.getChildren();
            var isFound = false;

            for(Node node : children)
                if(Character.toLowerCase(node.getValue()) == Character.toLowerCase(currentCharacter)){
                    isFound = true;
                    currentNode = node;
                    break;
                }
            if(!isFound){
                return wordsWithPrefix;
            }

            currentIndex++;
        }

        if(currentNode.isEndOfWord())
            wordsWithPrefix.add(prefix);

        //use DFS to get the words
        wordsWithPrefix = traverse(currentNode, prefix);
        return wordsWithPrefix;
    }

    private Set<String> traverse(Node node, String word){
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

    public Node getRoot() {
        return root;
    }
}

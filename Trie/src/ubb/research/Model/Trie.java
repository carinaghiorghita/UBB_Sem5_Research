package ubb.research.Model;

import ubb.research.Utils.TrieUtils;

import java.util.*;

import static ubb.research.Utils.TrieUtils.traverse;

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
            var currentCharacter = Character.toLowerCase(key.charAt(currentIndex));

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

    public List<String> getByPrefix(String prefix) {
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
                return new ArrayList<>(wordsWithPrefix);
            }

            currentIndex++;
        }

        if(currentNode.isEndOfWord())
            wordsWithPrefix.add(prefix);

        //use DFS to get the words
        wordsWithPrefix.addAll(traverse(currentNode, prefix));
        ArrayList<String> wordList = new ArrayList<>(wordsWithPrefix);
        wordList.sort(new TrieUtils.SortString());
        return wordList;
    }

    public Set<String> getAllWords(){
        return traverse(root,"");
    }

    public Node getRoot() {
        return root;
    }
}

package ubb.research.Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private char value;
    private boolean isEndOfWord;
    private List<Node> children;

    public Node(){
        this.value = Character.MIN_VALUE;
        this.isEndOfWord = false;
        this.children = new ArrayList<>();
    }

    public Node(char value){
        this.value = value;
        this.isEndOfWord = false;
        this.children = new ArrayList<>();
    }

    public char getValue() {
        return value;
    }

    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setEndOfWord(boolean endOfWord) {
        isEndOfWord = endOfWord;
    }
}

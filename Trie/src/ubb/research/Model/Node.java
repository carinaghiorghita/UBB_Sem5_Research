package ubb.research.Model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private char value;
    private boolean isEndOfWord;
    private List<Node> children;

    public Node(){
        value = Character.MIN_VALUE;
        isEndOfWord = false;
        children = new ArrayList<>();
    }
}

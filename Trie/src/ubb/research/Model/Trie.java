package ubb.research.Model;

public class Trie {
    private Node root;

    public Trie () {
        this.root = new Node();
    }

    public void insert(String key) {
        var length = key.length();
        var currentNode = root;
        var currentIndex = 0;

        while (currentIndex<length){
            var currentChar = key.charAt(currentIndex);

            var children = currentNode.getChildren();
            var isFound = false;

            for(Node node : children)
                if(node.getValue() == currentChar){
                    isFound = true;
                    currentNode = node;
                    break;
                }
            if(!isFound){
                var newNode = new Node(currentChar);
                currentNode.getChildren().add(newNode);
                currentNode = newNode;
            }

            currentIndex++;
        }

        currentNode.setEndOfWord(true);
    }

    public boolean search(String key) {
        if(key.length() == 0)
            return true;

        var length = key.length();
        var currentNode = root;
        var currentIndex = 0;

        while (currentIndex < length) {
            var currentChar = key.charAt(currentIndex);

            var children = currentNode.getChildren();
            var isFound = false;
            for(Node node : children)
                if(node.getValue() == currentChar){
                    isFound = true;
                    currentNode = node;
                    break;
                }
            if(!isFound)
                return false;

            currentIndex++;
        }

        return currentNode.isEndOfWord();
    }



    public Node getRoot() {
        return root;
    }
}

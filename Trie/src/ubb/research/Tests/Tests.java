package ubb.research.Tests;

import ubb.research.Model.Node;
import ubb.research.Model.Trie;

public class Tests {

    public Tests() {
    }

    public void run(){
        testInsert();
        testSearch();
        testGetByPrefix();
    }

    private void testInsert() {
        System.out.println("Test Insert");

        Trie trie = new Trie();

        trie.insert("their");
        trie.insert("there");
        trie.insert("the");
        trie.insert("bye");

        Node currentNode = trie.getRoot();
        assert currentNode.getChildren().size() == 2;
        assert !currentNode.isEndOfWord();
        assert currentNode.getValue() == Character.MIN_VALUE;

        Node firstChild = currentNode.getChildren().get(0);
        do {
            assert firstChild.getChildren().size() == 1;
            assert !firstChild.isEndOfWord();
            firstChild = firstChild.getChildren().get(0);
        } while (firstChild.getValue() != 'e');

        assert firstChild.getChildren().size() == 2;
        assert firstChild.isEndOfWord();

        Node secondChild = currentNode.getChildren().get(1);
        do {
            assert secondChild.getChildren().size() == 1;
            assert !secondChild.isEndOfWord();
            secondChild = secondChild.getChildren().get(0);
        } while (secondChild.getValue() != 'e');
        assert secondChild.isEndOfWord();

        System.out.println("Finish Test Insert\n");
    }

    public void testSearch(){
        System.out.println("Test Search");

        Trie trie = new Trie();

        trie.insert("their");
        trie.insert("there");
        trie.insert("bye");

        assert trie.search("there");
        assert !trie.search("the");
        assert !trie.search("apple");
        assert !trie.search("thereisanapple");
        assert trie.search("");

        System.out.println("Finish Test Search\n");
    }

    private void testGetByPrefix(){
        System.out.println("Test Get by Prefix");

        Trie trie = new Trie();

        trie.insert("chin");
        trie.insert("check");
        trie.insert("cheque");
        trie.insert("good");

        assert trie.getByPrefix("c").size() == 3;
        assert trie.getByPrefix("ch").size() == 3;
        assert trie.getByPrefix("che").size() == 2;
        assert trie.getByPrefix("chin").size() == 1;
        assert trie.getByPrefix("cha").size() == 0;
        assert trie.getByPrefix("a").size() == 0;
        assert trie.getByPrefix("").size() == 4;

        System.out.println("Finish Test Get by Prefix\n");
    }
}

package ubb.research;

import ubb.research.Model.Trie;
import ubb.research.Tests.Tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static void runTests(){
        Tests tests = new Tests();
        tests.run();
    }

    private static Trie loadTrieFromFile(String filename) {
        Trie trie = new Trie();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            while (line != null) {
                trie.insert(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trie;
    }

    public static void main(String[] args) {
        runTests();

        Trie trie = loadTrieFromFile("src/ubb/research/Resources/keys.txt");
    }
}

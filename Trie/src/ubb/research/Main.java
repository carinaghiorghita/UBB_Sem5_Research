package ubb.research;

import ubb.research.Algorithms.SpellCheck;
import ubb.research.Model.Trie;
import ubb.research.Tests.Tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
                trie.insert(line.strip());
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return trie;
    }

    public static void main(String[] args) {
        //runTests();

        //System.out.println("Start algorithm for 3000 words:");
        //float startTime = System.nanoTime();
        Trie trie = loadTrieFromFile("src/ubb/research/Resources/3000_words.txt");

        SpellCheck spellCheck = new SpellCheck(trie);

        Scanner scanner = new Scanner(System.in);
        String phrase;

        do{
            System.out.println("Enter phrase or \"exit\":");
            phrase = scanner.nextLine();
            if(phrase.equals("exit")) return;

            System.out.println("1. Pick suggestions manually");
            System.out.println("2. Automatically get closest suggestion");
            int option = new Scanner(System.in).nextInt();

            switch (option) {
                case 1 -> System.out.println(spellCheck.autocorrect(phrase, true));
                case 2 -> System.out.println(spellCheck.autocorrect(phrase, false));
                default -> System.out.println("Invalid option");
            }
//            float endTime = System.nanoTime();
//            float totalTime = (endTime-startTime)/1000000000;
//            System.out.println("Finish algorithm; Execution took "+totalTime+" seconds");
        }
        while (true);
    }
}

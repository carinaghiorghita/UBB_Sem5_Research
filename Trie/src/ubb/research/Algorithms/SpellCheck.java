package ubb.research.Algorithms;

import ubb.research.Model.Trie;
import ubb.research.Utils.AlgorithmUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ubb.research.Utils.AlgorithmUtils.getLED;

public class SpellCheck {
    private Trie dictionary;

    public SpellCheck(Trie dictionary) {
        this.dictionary = dictionary;
    }

    public String autocorrect(String phrase, boolean manualChoice) {
        String[] words = phrase.split(" ");
        List<String> autocorrectedPhrase = new ArrayList<>();

        for (String word : words) {
            word = autocomplete(word, manualChoice);
            word = addWhitespace(word, manualChoice);
            word = getByLED(word, 2, manualChoice);
            autocorrectedPhrase.add(word);
        }

        return String.join(" ", autocorrectedPhrase);
    }

    public String autocomplete(String word, boolean manualChoice) {
        List<String> suggestedWords = new ArrayList<>(dictionary.getByPrefix(word));
        word = word.toLowerCase(Locale.ROOT);

        if(manualChoice) {
            System.out.println("Autocomplete: " + word);

            for (var i = 0; i < suggestedWords.size(); ++i) {
                System.out.println((i + 1) + ". " + suggestedWords.get(i));
            }
            if(!suggestedWords.contains(word))
                System.out.println("0. " + word);
            System.out.println("Your choice for autocompleting this word:");

            int option = new Scanner(System.in).nextInt();

            if (option == 0)
                return word;
            return suggestedWords.get(option - 1);
        }
        suggestedWords.add(word);
        return suggestedWords.get(0);
    }

    public String addWhitespace(String word, boolean manualChoice) {
        List<String> suggestedWords = new ArrayList<>();

        for (var i = 1; i < word.length() - 1; ++i) {
            String firstSubstring = word.substring(0, i);
            String secondSubstring = word.substring(i);
            if (dictionary.search(firstSubstring) && dictionary.search(secondSubstring))
                suggestedWords.add(firstSubstring + " " + secondSubstring);
        }

        if(manualChoice) {
            System.out.println("Add whitespace: "+word);

            for (var i = 0; i < suggestedWords.size(); ++i) {
                System.out.println((i + 1) + ". " + suggestedWords.get(i));
            }

            if(!suggestedWords.contains(word))
                System.out.println("0. " + word);
            System.out.println("Your choice for adding whitespace to this word:");

            int option = new Scanner(System.in).nextInt();

            if (option == 0)
                return word;
            return suggestedWords.get(option - 1);
        }
        suggestedWords.add(word);
        return suggestedWords.get(0);
    }

    public String getByLED(String word, int maxLED, boolean manualChoice) {
        if (maxLED == 0)
            return word;

        List<AbstractMap.SimpleEntry<String, Integer>> suggestions = new ArrayList<>();
        Set<String> wordsInDictionary = dictionary.getAllWords();

        if(wordsInDictionary.contains(word.toLowerCase(Locale.ROOT)))
            suggestions.add(new AbstractMap.SimpleEntry<>(word,0));

        for(String wordInDictionary : wordsInDictionary){
            int LED = getLED(word,wordInDictionary);
            if(LED <= maxLED)
                suggestions.add(new AbstractMap.SimpleEntry<>(wordInDictionary, LED));
        }

        suggestions.sort(new AlgorithmUtils.SortMap());

        if(manualChoice) {
            System.out.println("Suggest word using Levenshtein Edit Distance (max "+maxLED+"): "+word);

            for (var i = 0; i < suggestions.size(); ++i) {
                System.out.println((i + 1) + ". " + suggestions.get(i).getKey());
            }

            if(!suggestions.contains(word))
                System.out.println("0. " + word);
            System.out.println("Your choice after getting suggestions based on Levenshtein Edit Distance:");

            int option = new Scanner(System.in).nextInt();

            if (option == 0)
                return word;
            return suggestions.get(option - 1).getKey();
        }
        suggestions.add(new AbstractMap.SimpleEntry<>(word,0));
        return suggestions.get(0).getKey();
    }
}

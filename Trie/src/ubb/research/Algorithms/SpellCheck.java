package ubb.research.Algorithms;

import ubb.research.Model.Trie;

import java.util.*;
import java.util.stream.Collectors;

public class SpellCheck {
    private Trie dictionary;

    public SpellCheck(Trie dictionary){
        this.dictionary = dictionary;
    }

    public String autocorrect(String phrase){
        String[] words = phrase.split(" ");
        List<String> autocorrectedPhrase = new ArrayList<>();

        for(String word : words){
            String newWord = autocomplete(word);
            //word = addWhiteSpace(word);
            //word = getByLED(word);
            autocorrectedPhrase.add(newWord);
        }

        return String.join(" ", autocorrectedPhrase);
    }

    public String autocomplete(String word){
        System.out.println("Autocomplete: "+word);
        List<String> suggestedWords = new ArrayList<>(dictionary.getByPrefix(word));

        for(var i=0;i<suggestedWords.size();++i){
            System.out.println((i+1)+". "+suggestedWords.get(i));
        }
        System.out.println("0. "+word);
        System.out.println("Your choice for autocompleting this word:");

        int option = new Scanner(System.in).nextInt();

        if(option == 0)
            return word;
        return suggestedWords.get(option-1);
    }
}

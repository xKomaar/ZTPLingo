package pl.ztplingo.decorator;

import pl.ztplingo.settings.Language;
import pl.ztplingo.model.Word;

import java.util.ArrayList;
import java.util.Collections;

public class WordDecorator extends PhraseDecorator {

    public WordDecorator(Word word) {
        this.setWrappedPhrase(word);
    }

    public void shuffle(Language language) {
        this.getWrappedPhrase().shuffle(language);
        ArrayList<String> shuffled = this.getWrappedPhrase().getShuffledAnswer();
        if(language == Language.ENGLISH_TO_POLISH) {
            shuffled = divideIntoSyllables(getPolish());
        } else {
            shuffled = divideIntoSyllables(getEnglish());
        }
        Collections.shuffle(shuffled);
        setShuffledAnswer(shuffled);
    }

    private ArrayList<String> divideIntoSyllables(String word) {
        int j=0, i;
        ArrayList<String> result = new ArrayList<>();
        for(i=0; i<word.length(); i++) {
            if(word.charAt(i) == 'a' || word.charAt(i) == 'o' || word.charAt(i) == 'i'
                    || word.charAt(i) == 'u' || word.charAt(i) == 'y' || word.charAt(i) == 'e'
                    || word.charAt(i) == 'ą' || word.charAt(i) == 'ę' || word.charAt(i) == 'ó') {
                result.add(word.substring(j,i+1));
                j=i+1;
            }
        }
        if(j != word.length()) {
            result.set(result.size()-1, result.get(result.size()-1) + word.substring(j));
        }
        if(result.size() == 1) {
            result.remove(0);
            result.add(word.substring(0,word.length()/2));
            result.add(word.substring(word.length()/2));
        }
        return result;
    }

}

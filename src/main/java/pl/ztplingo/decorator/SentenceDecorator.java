package pl.ztplingo.decorator;

import pl.ztplingo.LanguageSetting;
import pl.ztplingo.LanguageState;
import pl.ztplingo.model.Sentence;

import java.util.ArrayList;
import java.util.Collections;

public class SentenceDecorator extends PhraseDecorator {

    public SentenceDecorator(Sentence sentence) {
        this.setWrappedPhrase(sentence);
    }

    public void shuffle() {
        this.getWrappedPhrase().shuffle();
        ArrayList<String> shuffled = this.getWrappedPhrase().getShuffled();
        if(LanguageSetting.language == LanguageState.ENGLISH_TO_POLISH) {
            shuffled = divideIntoWords(getEnglish());
        } else {
            shuffled = divideIntoWords(getPolish());
        }
        Collections.shuffle(shuffled);
        setShuffled(shuffled);
    }

    private ArrayList<String> divideIntoWords(String sentence) {
        int j=0, i;
        ArrayList<String> result = new ArrayList<>();
        for(i=0; i<sentence.length(); i++) {
            if(sentence.charAt(i) == ' ') {
                result.add(sentence.substring(j,i+1));
                j=i+1;
            }
        }
        result.add(sentence.substring(j));
        return result;
    }
}

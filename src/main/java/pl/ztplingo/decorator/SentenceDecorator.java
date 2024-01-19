package pl.ztplingo.decorator;

import pl.ztplingo.settings.Language;
import pl.ztplingo.model.Sentence;

import java.util.ArrayList;
import java.util.Collections;

public class SentenceDecorator extends PhraseDecorator {

    public SentenceDecorator(Sentence sentence) {
        this.setWrappedPhrase(sentence);
    }

    public void shuffle(Language language) {
        this.getWrappedPhrase().shuffle(language);
        ArrayList<String> shuffled = this.getWrappedPhrase().getShuffledAnswer();
        if(language == Language.ENGLISH_TO_POLISH) {
            shuffled = divideIntoWords(getPolish());
        } else {
            shuffled = divideIntoWords(getEnglish());
        }
        Collections.shuffle(shuffled);
        setShuffledAnswer(shuffled);
    }

    private ArrayList<String> divideIntoWords(String sentence) {
        int j=0, i;
        ArrayList<String> result = new ArrayList<>();
        for(i=0; i<sentence.length(); i++) {
            if(sentence.charAt(i) == ' ') {
                result.add(sentence.substring(j,i));
                j=i+1;
            }
        }
        result.add(sentence.substring(j));
        return result;
    }
}

package pl.ztplingo.decorator;

import pl.ztplingo.model.Phrase;
import pl.ztplingo.model.User;

import java.util.ArrayList;

public abstract class PhraseDecorator implements Phrase {
    private Phrase wrappedPhrase;

    public String getPolish() {
        return wrappedPhrase.getPolish();
    }
    
    public String getEnglish() {
        return wrappedPhrase.getEnglish();
    }
    
    public ArrayList<String> getShuffledAnswer() {
        return wrappedPhrase.getShuffledAnswer();
    }

    public void setShuffledAnswer(ArrayList<String> shuffled) {
        wrappedPhrase.setShuffledAnswer(shuffled);
    }
    
    public Integer getId() {
        return wrappedPhrase.getId();
    }
    
    public User getUser() {
        return wrappedPhrase.getUser();
    }
    
    public void setUser(User user) {
        wrappedPhrase.setUser(user);
    }

    public Phrase getWrappedPhrase() {
        return wrappedPhrase;
    }

    public void setWrappedPhrase(Phrase phrase) {
        this.wrappedPhrase = phrase;
    }
}

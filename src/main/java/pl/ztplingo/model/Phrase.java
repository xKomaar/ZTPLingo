package pl.ztplingo.model;

import pl.ztplingo.LanguageState;

import java.util.ArrayList;

public interface Phrase {
    String getPolish();
    String getEnglish();
    ArrayList<String> getShuffledAnswer();
    void shuffle(LanguageState language);
    Integer getId();
    User getUser();
    void setUser(User user);
    void setShuffledAnswer(ArrayList<String> shuffled);
}

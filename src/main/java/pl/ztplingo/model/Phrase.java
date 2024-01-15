package pl.ztplingo.model;

import pl.ztplingo.LanguageState;

import java.util.ArrayList;

public interface Phrase {
    String getPolish();
    String getEnglish();
    ArrayList<String> getShuffled();
    void shuffle(LanguageState language);
    Integer getId();
    User getUser();
    void setUser(User user);
    void setShuffled(ArrayList<String> shuffled);
}

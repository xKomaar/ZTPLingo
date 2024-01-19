package pl.ztplingo.model;

import pl.ztplingo.settings.Language;

import java.util.ArrayList;

public interface Phrase {
    String getPolish();
    String getEnglish();
    ArrayList<String> getShuffledAnswer();
    void shuffle(Language language);
    Integer getId();
    User getUser();
    void setUser(User user);
    void setShuffledAnswer(ArrayList<String> shuffled);
}

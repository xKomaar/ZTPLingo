package pl.ztplingo.model;

import java.util.ArrayList;

public interface Phrase {
    String getPolish();
    String getEnglish();
    ArrayList<String> getShuffled();
    void shuffle();
    Integer getId();
    User getUser();
    void setUser(User user);
    void setShuffled(ArrayList<String> shuffled);
}

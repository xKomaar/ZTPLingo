package pl.ztplingo.model;

import pl.ztplingo.LanguageState;

import java.util.ArrayList;

public interface Phrase {
    public String getPolish();
    public String getEnglish();
    public ArrayList<String> getScrambled();
    public void scramble(LanguageState language);
    public Integer getId();
    public User getUser();
    public void setUser(User user);
}

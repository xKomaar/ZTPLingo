package pl.ztplingo.model;

import jakarta.persistence.*;
import pl.ztplingo.LanguageState;

import java.util.ArrayList;


@Entity
@Table(name="words")
public class Word implements Phrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="polish")
    private String polish;

    @Column(name="english")
    private String english;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Transient
    private ArrayList<String> scrambled;


    public Word(String polish, String english) {
        this.polish = polish;
        this.english = english;
        this.scrambled = null;
    }

    public Word() {

    }


    @Override
    public String getPolish() {
        return polish;
    }

    @Override
    public String getEnglish() {
        return english;
    }

    @Override
    public ArrayList<String> getScrambled() {
        return scrambled;
    }

    @Override
    public void scramble(LanguageState language) {
        if(scrambled == null) {
            scrambled = new ArrayList<>();
        } else {
            scrambled.clear();
        }
        if(language == LanguageState.ENGLISH_TO_POLISH) {
            scrambled = divideIntoSyllables(english);
        } else {
            scrambled = divideIntoSyllables(polish);
        }
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
        return result;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

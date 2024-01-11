package pl.ztplingo.model;

import jakarta.persistence.*;
import pl.ztplingo.LanguageState;

import java.util.ArrayList;

@Entity
@Table(name="sentences")
public class Sentence implements Phrase {

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

    public Sentence(String polish, String english) {
        this.polish = polish;
        this.english = english;
    }

    public Sentence() {

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
            scrambled = divideIntoWords(english);
        } else {
            scrambled = divideIntoWords(polish);
        }
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

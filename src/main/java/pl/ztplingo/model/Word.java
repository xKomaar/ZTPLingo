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
    private ArrayList<String> shuffledAnswer;


    public Word(String polish, String english) {
        this.polish = polish;
        this.english = english;
        this.shuffledAnswer = null;
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
    public ArrayList<String> getShuffledAnswer() {
        return shuffledAnswer;
    }

    @Override
    public void setShuffledAnswer(ArrayList<String> shuffledAnswer) {
        this.shuffledAnswer = shuffledAnswer;
    }

    @Override
    public void shuffle(LanguageState language) {
        if(shuffledAnswer == null) {
            shuffledAnswer = new ArrayList<>();
        } else {
            shuffledAnswer.clear();
        }
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

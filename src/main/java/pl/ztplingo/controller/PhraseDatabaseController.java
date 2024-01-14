package pl.ztplingo.controller;

import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;

import javax.swing.*;
import java.util.List;

public class PhraseDatabaseController {
    private PhraseDatabaseView phraseDatabaseView;
    private DatabaseProxy databaseProxy;
    private JFrame appFrame;
    private User loggedUser;

    public void run(JFrame appFrame, User loggedUser) {
        databaseProxy = new DatabaseProxy();
        this.loggedUser = loggedUser;
        this.appFrame = appFrame;
        phraseDatabaseView = new PhraseDatabaseView(this);
        appFrame.getContentPane().add(phraseDatabaseView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public void addWord(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Word word = new Word(english, polish);
            databaseProxy.saveWord(word);
            phraseDatabaseView.printMenu();
        }
    }

    public void addSentence(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Sentence sentence = new Sentence(english, polish);
            databaseProxy.saveSentence(sentence);
            phraseDatabaseView.printMenu();
        }
    }

    public void deleteWord(Word word) {
        if(word != null) {
            databaseProxy.deleteWord(word);
            phraseDatabaseView.printMenu();
        }
    }

    public void deleteSentence(Sentence sentence) {
        if(sentence != null) {
            databaseProxy.deleteSentence(sentence);
            phraseDatabaseView.printMenu();
        }
    }

    public List<Word> getWords() {
        List<Word> words = databaseProxy.getWordsByUser(loggedUser);
        return words;
    }

    public List<Sentence> getSentences() {
        List<Sentence> sentences = databaseProxy.getSentencesByUser(loggedUser);
        return sentences;
    }
}

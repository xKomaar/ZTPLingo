package pl.ztplingo.controller;

import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;
import pl.ztplingo.view.PhraseDatabaseView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PhraseDatabaseController {
    private PhraseDatabaseView phraseDatabaseView;
    private MainController mainController;
    private DatabaseProxy databaseProxy;
    private JFrame appFrame;

    public void run(JFrame appFrame, MainController mainController) {
        databaseProxy = new DatabaseProxy();
        this.mainController = mainController;
        this.appFrame = appFrame;
        phraseDatabaseView = new PhraseDatabaseView(this);
        appFrame.getContentPane().add(phraseDatabaseView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public void addWord(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Word word = new Word(english, polish);
            word.setUser(mainController.getLoggedUser());
            databaseProxy.saveWord(word);
        }
        phraseDatabaseView.updateList();
    }

    public void addSentence(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Sentence sentence = new Sentence(english, polish);
            sentence.setUser(mainController.getLoggedUser());
            databaseProxy.saveSentence(sentence);
        }
        phraseDatabaseView.updateList();
    }

    public void deleteWordByIndexOnList(int index) {
        Word word = databaseProxy.getWordsByUser(mainController.getLoggedUser()).get(index);
        if(word != null) {
            databaseProxy.deleteWord(word);
        }
        phraseDatabaseView.updateList();
    }

    public void deleteSentenceByIndexOnList(int index) {
        Sentence sentence = databaseProxy.getSentencesByUser(mainController.getLoggedUser()).get(index);
        if(sentence != null) {
            databaseProxy.deleteSentence(sentence);
        }
        phraseDatabaseView.updateList();
    }

    public List<String> getWordsToString() {
        List<Word> words = databaseProxy.getWordsByUser(mainController.getLoggedUser());
        List<String> wordsToString = new ArrayList<>();
        for(Word word : words) {
            wordsToString.add(word.getEnglish() + " | " + word.getPolish());
        }
        return wordsToString;
    }

    public List<String> getSentencesToString() {
        List<Sentence> sentences = databaseProxy.getSentencesByUser(mainController.getLoggedUser());
        List<String> sentencesToString = new ArrayList<>();
        for(Sentence sentence : sentences) {
            sentencesToString.add(sentence.getEnglish() + " | " + sentence.getPolish());
        }
        return sentencesToString;
    }

    public void returnToMainController() {
        appFrame.getContentPane().removeAll();
        mainController.run(appFrame);
    }
}

package pl.ztplingo.controller;

import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.Sentence;
import pl.ztplingo.model.Word;
import pl.ztplingo.view.PhraseDatabaseView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PhraseDatabaseController {
    private PhraseDatabaseView phraseDatabaseView;
    private MenuController menuController;
    private DatabaseProxy databaseProxy;
    private JFrame appFrame;

    public void run(JFrame appFrame, MenuController menuController) {
        databaseProxy = new DatabaseProxy();
        this.menuController = menuController;
        this.appFrame = appFrame;
        phraseDatabaseView = new PhraseDatabaseView(this);
        appFrame.getContentPane().add(phraseDatabaseView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public void addWord(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Word word = new Word(english, polish);
            word.setUser(menuController.getLoggedUser());
            Integer result = databaseProxy.saveWord(word);
            if(result == -1) {
                phraseDatabaseView.showIncorrectPolishWordError();
            } else if(result == -2) {
                phraseDatabaseView.showIncorrectEnglishWordError();
            }
        }
        phraseDatabaseView.updateList();
    }

    public void addSentence(String english, String polish) {
        if(english != null && english.length() != 0 && polish != null && polish.length() != 0) {
            Sentence sentence = new Sentence(english, polish);
            sentence.setUser(menuController.getLoggedUser());
            Integer result = databaseProxy.saveSentence(sentence);
            if(result == -1) {
                phraseDatabaseView.showIncorrectPolishSentenceError();
            } else if(result == -2) {
                phraseDatabaseView.showIncorrectEnglishSentenceError();
            }
        }
        phraseDatabaseView.updateList();
    }

    public void deleteWordByIndexOnList(int index) {
        Word word = databaseProxy.getWordsByUser(menuController.getLoggedUser()).get(index);
        if(word != null) {
            databaseProxy.deleteWord(word);
        }
        phraseDatabaseView.updateList();
    }

    public void deleteSentenceByIndexOnList(int index) {
        Sentence sentence = databaseProxy.getSentencesByUser(menuController.getLoggedUser()).get(index);
        if(sentence != null) {
            databaseProxy.deleteSentence(sentence);
        }
        phraseDatabaseView.updateList();
    }

    public List<String> getWordsToString() {
        List<Word> words = databaseProxy.getWordsByUser(menuController.getLoggedUser());
        List<String> wordsToString = new ArrayList<>();
        for(Word word : words) {
            wordsToString.add(word.getPolish() + " --> " + word.getEnglish());
        }
        return wordsToString;
    }

    public List<String> getSentencesToString() {
        List<Sentence> sentences = databaseProxy.getSentencesByUser(menuController.getLoggedUser());
        List<String> sentencesToString = new ArrayList<>();
        for(Sentence sentence : sentences) {
            sentencesToString.add(sentence.getPolish() + " --> " + sentence.getEnglish());
        }
        return sentencesToString;
    }

    public void returnToMainController() {
        appFrame.getContentPane().removeAll();
        menuController.run(appFrame);
    }
}

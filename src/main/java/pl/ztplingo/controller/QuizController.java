package pl.ztplingo.controller;

import pl.ztplingo.Difficulty;
import pl.ztplingo.LanguageState;
import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.iterator.QuestionListIterator;
import pl.ztplingo.model.QuestionList;
import pl.ztplingo.model.QuizSession;
import pl.ztplingo.model.User;
import pl.ztplingo.snapshot.QuizSessionSnapshot;
import pl.ztplingo.view.MainView;
import pl.ztplingo.view.QuizView;

import javax.swing.*;

public class QuizController {
    private QuizView quizView;
    private MainController mainController;
    private JFrame appFrame;
    private QuizSession quizSession;
    private QuizSessionSnapshot quizSessionSnapshot;
    private DatabaseProxy databaseProxy;

    public void run(JFrame appFrame, MainController mainController) {
        this.databaseProxy = new DatabaseProxy();
        this.appFrame = appFrame;
        this.mainController = mainController;
        quizView = new QuizView(this);
        quizView.showSettingsPopup();
    }

    public void initQuizSession(LanguageState language, Difficulty difficulty, int questionQuantity) {
        int userPhraseQuantity = databaseProxy.getWordsByUser(mainController.getLoggedUser()).size() +
                databaseProxy.getSentencesByUser(mainController.getLoggedUser()).size();
        if(questionQuantity > userPhraseQuantity) {
            quizView.showQuestionQuantityError();
            appFrame.getContentPane().removeAll();
            mainController.run(appFrame);
        } else {
            this.quizSession = new QuizSession(language, difficulty, generateQuestionList(questionQuantity));
            appFrame.getContentPane().add(quizView);
            appFrame.getContentPane().revalidate();
            appFrame.getContentPane().repaint();
        }
    }

    public QuestionList generateQuestionList(int quantity) {
        return new QuestionList(databaseProxy.getWordsByUser(mainController.getLoggedUser()),
                databaseProxy.getSentencesByUser(mainController.getLoggedUser()), quantity);
    }

    public void startQuiz() {

    }

    public void invalidateQuizSessionWithSnapshot() {
        quizSessionSnapshot = quizSession.createSnapshot();

        appFrame.getContentPane().removeAll();
        mainController.run(appFrame);
    }

    public void invalidateQuizSession() {
        appFrame.getContentPane().removeAll();
        mainController.run(appFrame);
    }

    public void runFromSnapshot(JFrame appFrame, MainController mainController) {
        this.appFrame = appFrame;
        this.mainController = mainController;
        quizView = new QuizView(this);
        if(quizSessionSnapshot != null) {
            quizSessionSnapshot.restore(this.quizSession);
        } else {
            quizView.showNoSnapshotError();
            invalidateQuizSession();
        }
    }
}

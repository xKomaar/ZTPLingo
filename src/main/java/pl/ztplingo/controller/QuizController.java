package pl.ztplingo.controller;

import pl.ztplingo.settings.Difficulty;
import pl.ztplingo.settings.ExerciseMode;
import pl.ztplingo.settings.Language;
import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.QuestionList;
import pl.ztplingo.model.QuizSession;
import pl.ztplingo.model.User;
import pl.ztplingo.model.Word;
import pl.ztplingo.snapshot.QuizSessionSnapshot;
import pl.ztplingo.strategy.KeyboardInputStrategy;
import pl.ztplingo.strategy.SentenceByWordInputStrategy;
import pl.ztplingo.strategy.WordBySyllablesInputStrategy;
import pl.ztplingo.view.QuizView;

import javax.swing.*;

public class QuizController {
    private QuizView quizView;
    private MenuController menuController;
    private JFrame appFrame;
    private QuizSession quizSession;
    private QuizSessionSnapshot quizSessionSnapshot;
    private DatabaseProxy databaseProxy;
    private boolean ranFromSnaphot;

    public void run(JFrame appFrame, MenuController menuController, ExerciseMode exerciseMode) {
        this.databaseProxy = new DatabaseProxy();
        this.appFrame = appFrame;
        this.menuController = menuController;
        this.ranFromSnaphot = false;
        quizView = new QuizView(this);
        quizView.showSettingsPopup(exerciseMode);
    }

    public void runFromSnapshot(JFrame appFrame, MenuController menuController) {
        this.databaseProxy = new DatabaseProxy();
        this.appFrame = appFrame;
        this.menuController = menuController;
        this.ranFromSnaphot = true;
        quizView = new QuizView(this);

        if(quizSessionSnapshot != null) {
            quizSessionSnapshot.restore(this.quizSession);
            appFrame.getContentPane().add(quizView);
            appFrame.getContentPane().revalidate();
            appFrame.getContentPane().repaint();
            setAnswerInputStrategy();
            quizView.printQuestionAndAnswerInput(quizSession);
        } else {
            quizView.showNoSnapshotError();
            invalidateQuizSession();
        }
    }

    public void initQuizSession(Language language, Difficulty difficulty, ExerciseMode exerciseMode, int questionQuantity) {
        int userPhraseQuantity = databaseProxy.getWordsByUser(menuController.getLoggedUser()).size() +
                databaseProxy.getSentencesByUser(menuController.getLoggedUser()).size();
        if(questionQuantity > userPhraseQuantity) {
            quizView.showQuestionQuantityError();
            appFrame.getContentPane().removeAll();
            menuController.run(appFrame);
        } else {
            this.quizSession = new QuizSession(language, difficulty, generateQuestionList(questionQuantity), exerciseMode);
            appFrame.getContentPane().add(quizView);
            appFrame.getContentPane().revalidate();
            appFrame.getContentPane().repaint();
            printNextQuestion();
        }
    }

    public QuestionList generateQuestionList(int quantity) {
        return new QuestionList(databaseProxy.getWordsByUser(menuController.getLoggedUser()),
                databaseProxy.getSentencesByUser(menuController.getLoggedUser()), quantity);
    }

    public void printNextQuestion() {
        if(!quizSession.isFinished()) {
            quizSession.loadNextPhrase();

            setAnswerInputStrategy();

            quizView.printQuestionAndAnswerInput(quizSession);

        } else {
            if(ranFromSnaphot) {
                quizSessionSnapshot = null;
                ranFromSnaphot = false;
            }
            if(quizSession.getExerciseMode() == ExerciseMode.TEST) {
                addPointsToLoggedUser(quizSession.getCurrentPoints());
                invalidateQuizSession();
                quizView.showTestFinishedPopup(quizSession.getCurrentPoints(), quizSession.getQuestionQuantity());
            } else {
                invalidateQuizSession();
                quizView.showLearningSessionFinishedPopup();
            }
        }
    }

    public void checkAnswerAndGoNext(String answer) {
        boolean isCorrect = quizSession.checkAnswer(answer);
        if(isCorrect) {
            printNextQuestion();
            quizView.showCorrectAnswerPopup();
        } else {
            if(quizSession.getExerciseMode() == ExerciseMode.TEST) {
                printNextQuestion();
                quizView.showIncorrectAnswerPopup();
            } else {
                quizView.printQuestionAndAnswerInput(quizSession);
                quizView.showIncorrectAnswerTryAgainPopup();
            }
        }
    }

    public void invalidateQuizSessionWithSnapshot() {
        quizSessionSnapshot = quizSession.createSnapshot();

        appFrame.getContentPane().removeAll();
        menuController.run(appFrame);
    }

    public void invalidateQuizSession() {
        appFrame.getContentPane().removeAll();
        menuController.run(appFrame);
    }

    private void addPointsToLoggedUser(int points) {
        DatabaseProxy databaseProxy = new DatabaseProxy();
        User loggedUser = menuController.getLoggedUser();
        loggedUser.setPoints(loggedUser.getPoints() + points);
        databaseProxy.updateUser(loggedUser);
    }

    private void setAnswerInputStrategy() {
        if(quizSession.getDifficulty() == Difficulty.HARD) {
            quizView.setAnswerInputStrategy(new KeyboardInputStrategy());
        } else if (quizSession.getCurrentPhrase().getWrappedPhrase() instanceof Word) {
            quizView.setAnswerInputStrategy(new WordBySyllablesInputStrategy());
        } else {
            quizView.setAnswerInputStrategy(new SentenceByWordInputStrategy());
        }
    }
}

package pl.ztplingo.controller;

import pl.ztplingo.model.User;
import pl.ztplingo.view.MainView;

import javax.swing.*;

public class MainController {
    private MainView mainView;
    private QuizController quizController;
    private PhraseDatabaseController phraseDatabaseController;

    private JFrame appFrame;
    private User loggedUser;

    public void run(JFrame appFrame) {
        this.appFrame = appFrame;
        mainView = new MainView(this);
        phraseDatabaseController = new PhraseDatabaseController();
        quizController = new QuizController();
        appFrame.getContentPane().add(mainView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User user) {
        loggedUser = user;
    }

    public void redirectToPhraseDatabaseController() {
        appFrame.getContentPane().removeAll();
        phraseDatabaseController.run(appFrame, this);
    }

    public void redirectToQuizController() {
        appFrame.getContentPane().removeAll();
        quizController.run(appFrame, this);
    }

    public void redirectToQuizControllerWithSnapshot() {
        appFrame.getContentPane().removeAll();
        quizController.runFromSnapshot(appFrame, this);
    }
}

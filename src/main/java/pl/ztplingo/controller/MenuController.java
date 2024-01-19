package pl.ztplingo.controller;

import pl.ztplingo.model.User;
import pl.ztplingo.settings.ExerciseMode;
import pl.ztplingo.view.MenuView;

import javax.swing.*;

public class MenuController {
    private MenuView menuView;
    private final QuizController quizController = new QuizController();
    private PhraseDatabaseController phraseDatabaseController;

    private JFrame appFrame;
    private User loggedUser;

    public void run(JFrame appFrame) {
        this.appFrame = appFrame;
        menuView = new MenuView(this);
        phraseDatabaseController = new PhraseDatabaseController();
        appFrame.getContentPane().add(menuView);
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

    public void redirectToQuizController(ExerciseMode exerciseMode) {
        appFrame.getContentPane().removeAll();
        quizController.run(appFrame, this, exerciseMode);
    }

    public void redirectToQuizControllerWithSnapshot() {
        appFrame.getContentPane().removeAll();
        quizController.runFromSnapshot(appFrame, this);
    }
}

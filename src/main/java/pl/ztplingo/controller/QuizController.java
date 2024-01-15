package pl.ztplingo.controller;

import pl.ztplingo.model.User;
import pl.ztplingo.view.MainView;
import pl.ztplingo.view.QuizView;

import javax.swing.*;

public class QuizController {
    private QuizView quizView;
    private JFrame appFrame;
    private User loggedUser;

    public void run(JFrame appFrame) {
        this.appFrame = appFrame;
        quizView = new QuizView(this);
        appFrame.getContentPane().add(quizView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }
}

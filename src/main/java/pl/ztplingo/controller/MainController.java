package pl.ztplingo.controller;

import pl.ztplingo.model.User;
import pl.ztplingo.view.MainView;

import javax.swing.*;

public class MainController {
    private MainView mainView;
    private JFrame appFrame;
    private User loggedUser;

    public void run(JFrame appFrame, User loggedUser) {
        this.loggedUser = loggedUser;
        this.appFrame = appFrame;
        mainView = new MainView(this);
        appFrame.getContentPane().add(mainView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}

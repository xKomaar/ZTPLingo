package pl.ztplingo.controller;

import pl.ztplingo.view.StartView;

import javax.swing.*;

public class StartController {
    private LoginRegisterController loginRegisterController;
    private StartView startView;
    private JFrame appFrame;

    public void run(JFrame appFrame) {
        this.appFrame = appFrame;
        startView = new StartView(this);
        this.loginRegisterController = new LoginRegisterController();
        appFrame.getContentPane().add(startView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public void redirectToLogin() {
        appFrame.getContentPane().removeAll();
        loginRegisterController.run(appFrame, true);
    }

    public void redirectToRegister() {
        appFrame.getContentPane().removeAll();
        loginRegisterController.run(appFrame, false);
    }
}

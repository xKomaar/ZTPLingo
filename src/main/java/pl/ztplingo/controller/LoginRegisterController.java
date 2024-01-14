package pl.ztplingo.controller;

import pl.ztplingo.PasswordEncoder;
import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.User;
import pl.ztplingo.view.LoginRegisterView;

import javax.swing.*;

public class LoginRegisterController {
    private LoginRegisterView loginRegisterView;
    private MainController mainController;
    private DatabaseProxy databaseProxy;
    private JFrame appFrame;

    public void run(JFrame appFrame, boolean isLoginForm) {
        this.appFrame = appFrame;
        loginRegisterView = new LoginRegisterView(this, isLoginForm);
        mainController = new MainController();
        databaseProxy = new DatabaseProxy();
        appFrame.getContentPane().add(loginRegisterView);
        appFrame.getContentPane().revalidate();
        appFrame.getContentPane().repaint();
    }

    public void performLogin(String username, String password) {
        String encodedPassword = PasswordEncoder.encode(password);
        User user = databaseProxy.getUserByUsername(username);
        if(user != null) {
            if(user.getPassword().equals(encodedPassword)) {
                appFrame.getContentPane().removeAll();
                mainController.run(appFrame, user);
            }
        }
    }

    public void performRegistration(String username, String password) {
        String encodedPassword = PasswordEncoder.encode(password);
        User user = databaseProxy.getUserByUsername(username);
        if(user == null) {
            User userToSave = new User(username, encodedPassword);
            databaseProxy.saveUser(userToSave);
            loginRegisterView.switchForm();
        }
    }
}
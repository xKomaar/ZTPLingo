package pl.ztplingo.controller;

import pl.ztplingo.model.PasswordEncoder;
import pl.ztplingo.database.DatabaseProxy;
import pl.ztplingo.model.User;
import pl.ztplingo.view.LoginRegisterView;

import javax.swing.*;

public class LoginRegisterController {
    private LoginRegisterView loginRegisterView;
    private MenuController menuController;
    private DatabaseProxy databaseProxy;
    private JFrame appFrame;

    public void run(JFrame appFrame, boolean isLoginForm) {
        this.appFrame = appFrame;
        loginRegisterView = new LoginRegisterView(this, isLoginForm);
        this.menuController = new MenuController();
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
                menuController.setLoggedUser(user);
                menuController.run(appFrame);
            } else {
                loginRegisterView.showIncorrectPasswordError();
            }
        } else {
            loginRegisterView.showIncorrectUsernameError();
        }
    }

    public void performRegistration(String username, String password) {
        String encodedPassword = PasswordEncoder.encode(password);
        User userToSave = new User(username, encodedPassword);
        Integer result = databaseProxy.saveUser(userToSave);
        if(result > 0) {
            loginRegisterView.switchForm();
        } else if(result == -1) {
            loginRegisterView.showUsernameTooShortError();
        } else if(result == -2) {
            loginRegisterView.showUserAlreadyExistsError();
        }
    }
}

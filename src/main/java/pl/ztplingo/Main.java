package pl.ztplingo;


import pl.ztplingo.controller.StartController;
import pl.ztplingo.database.DatabaseConnection;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection.getInstance();

        StartController startController = new StartController();
        JFrame appFrame = new JFrame();
        appFrame.setTitle("ZTPLingo");
        appFrame.setSize(1040, 800);
        appFrame.setLocationRelativeTo(null);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setVisible(true);
        startController.run(appFrame);
    }
}
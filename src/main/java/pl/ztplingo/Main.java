package pl.ztplingo;


import pl.ztplingo.controller.StartController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
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
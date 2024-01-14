package pl.ztplingo;


import pl.ztplingo.view.StartView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Settings.language = LanguageState.ENGLISH_TO_POLISH;
        Settings.difficulty = Difficulty.HARD;
        SwingUtilities.invokeLater(() -> {
            new StartView();
        });
    }
}
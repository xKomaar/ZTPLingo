package pl.ztplingo.strategy;


import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;

public interface AnswerInputStrategy {
    void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession);
    String getInputedAnswer();
}

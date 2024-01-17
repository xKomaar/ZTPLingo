package pl.ztplingo.strategy;

import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;

public class KeyboardInputStrategy implements AnswerInputStrategy {

    private JTextField answerInput;

    public KeyboardInputStrategy() {
        answerInput = new JTextField(40);
    }

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        questionLabel.setText(quizSession.getCurrentQuestion());

        gbc.gridy=1;
        JTextField answerInput = new JTextField(40);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(new Font("Monospaced", Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(answerInput, 1);

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public String getInputedAnswer() {
        return answerInput.getText();
    }
}

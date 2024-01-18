package pl.ztplingo.strategy;

import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;

public class KeyboardInputStrategy implements AnswerInputStrategy {

    private JTextField answerInput;
    private JPanel quizPanel;

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        this.quizPanel = panel;

        questionLabel.setText(quizSession.getCurrentQuestion());

        gbc.gridy=1;
        answerInput = new JTextField(40);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(new Font("Monospaced", Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        quizPanel.add(answerInput, gbc);

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    @Override
    public String getInputedAnswer() {
        return answerInput.getText();
    }

    @Override
    public void clean() {
        quizPanel.remove(answerInput);
        quizPanel.revalidate();
        quizPanel.repaint();
    }
}

package pl.ztplingo.strategy;

import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordBySyllablesInputStrategy implements AnswerInputStrategy {

    private java.util.List<String> syllables;
    private JPanel syllablesPanel;
    private List<JButton> syllableButtons;
    private Stack<JButton> selectedButtons;
    private JTextField answerInput;
    private JButton backButton;
    private JPanel quizPanel;

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        this.quizPanel = panel;

        questionLabel.setText(quizSession.getCurrentQuestion());

        selectedButtons = new Stack<>();

        syllables = quizSession.getShuffledAnswer();

        gbc.gridy=1;
        syllableButtons = new ArrayList<>();
        syllablesPanel = new JPanel();
        syllablesPanel.setLayout(new FlowLayout());
        syllablesPanel.setBackground(new Color(0, 0, 0,0));
        quizPanel.add(syllablesPanel, gbc);

        for (String syllable : syllables) {
            JButton button = new JButton(syllable);
            button.addActionListener(new SyllableButtonListener());
            syllableButtons.add(button);
            syllablesPanel.add(button);
        }

        gbc.gridy=2;
        answerInput = new JTextField(40);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(new Font("Monospaced", Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        answerInput.setEditable(false);
        quizPanel.add(answerInput, gbc);

        gbc.gridy=3;
        backButton = new JButton("Cofnij");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new BackButtonListener());
        quizPanel.add(backButton, gbc);

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    @Override
    public String getInputedAnswer() {
        return answerInput.getText();
    }

    private class SyllableButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedsyllable = button.getText();
            answerInput.setText(answerInput.getText() + selectedsyllable);
            button.setEnabled(false);
            selectedButtons.push(button);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedButtons.isEmpty()) {
                JButton lastSelectedButton = selectedButtons.pop();
                String currentText = answerInput.getText();
                int lastIndexOf = currentText.lastIndexOf(lastSelectedButton.getText());

                if (lastIndexOf != -1) {
                    answerInput.setText(currentText.substring(0, lastIndexOf));
                    lastSelectedButton.setEnabled(true);
                }
            }
        }
    }

    @Override
    public void clean() {
        syllablesPanel.removeAll();
        quizPanel.remove(answerInput);
        quizPanel.remove(backButton);
        quizPanel.revalidate();
        quizPanel.repaint();
    }
}

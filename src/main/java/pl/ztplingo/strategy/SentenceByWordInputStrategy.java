package pl.ztplingo.strategy;

import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SentenceByWordInputStrategy implements AnswerInputStrategy {

    private List<String> words;
    private JPanel wordsPanel;
    private List<JButton> wordButtons;
    private Stack<JButton> selectedButtons;
    private JTextField answerInput;
    private JButton backButton;
    private JPanel quizPanel;

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        this.quizPanel = panel;

        questionLabel.setText(quizSession.getCurrentQuestion());

        selectedButtons = new Stack<>();

        words = quizSession.getShuffledAnswer();

        gbc.gridy=1;
        wordButtons = new ArrayList<>();
        wordsPanel = new JPanel();
        wordsPanel.setLayout(new FlowLayout());
        wordsPanel.setBackground(new Color(0, 0, 0,0));
        quizPanel.add(wordsPanel, gbc);

        for (String word : words) {
            JButton button = new JButton(word);
            button.addActionListener(new WordButtonListener());
            wordButtons.add(button);
            wordsPanel.add(button);
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
        backButton.addActionListener(new SentenceByWordInputStrategy.BackButtonListener());
        quizPanel.add(backButton, gbc);

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    @Override
    public String getInputedAnswer() {
        return answerInput.getText();
    }

    private class WordButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedWord = button.getText();
            if(answerInput.getText().isEmpty()) {
                answerInput.setText(selectedWord);
            } else {
                answerInput.setText(answerInput.getText() + " " + selectedWord);
            }

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
                int lastIndex;
                if(selectedButtons.size() > 1) {
                    lastIndex = currentText.lastIndexOf(" " + lastSelectedButton.getText());
                } else {
                    lastIndex = currentText.lastIndexOf(lastSelectedButton.getText());
                }
                if (lastIndex != -1) {
                    answerInput.setText(currentText.substring(0, lastIndex));
                    lastSelectedButton.setEnabled(true);
                }
            }
        }
        private void enableAllButtons() {
            for (JButton button : wordButtons) {
                button.setEnabled(true);
                break;
            }
        }
    }

    @Override
    public void clean() {
        wordsPanel.removeAll();
        quizPanel.remove(backButton);
        quizPanel.remove(answerInput);
        quizPanel.revalidate();
        quizPanel.repaint();
    }
}

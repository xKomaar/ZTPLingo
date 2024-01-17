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
    private Stack<String> selectedWords;
    private JTextField answerInput;
    private JButton backButton;

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        questionLabel.setText(quizSession.getCurrentQuestion());

        selectedWords = new Stack<>();

        words = quizSession.getShuffledAnswer();

        gbc.gridy=1;
        wordButtons = new ArrayList<>();
        wordsPanel = new JPanel();
        wordsPanel.setLayout(new FlowLayout());
        wordsPanel.setBackground(new Color(0, 0, 0,0));
        panel.add(wordsPanel, gbc);

        wordsPanel.removeAll();
        for (String word : words) {
            JButton button = new JButton(word);
            button.addActionListener(new SentenceByWordInputStrategy.WordButtonListener());
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
        panel.add(answerInput, gbc);


        gbc.gridy=3;
        backButton = new JButton("Cofnij");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new SentenceByWordInputStrategy.BackButtonListener());
        panel.add(backButton, gbc);

        panel.revalidate();
        panel.repaint();
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
            selectedWords.push(selectedWord);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedWords.isEmpty()) {
                String lastSelectedWord = selectedWords.pop();
                String currentText = answerInput.getText();
                int lastIndexOf = currentText.lastIndexOf(" " + lastSelectedWord);

                if (lastIndexOf != -1) {
                    answerInput.setText(currentText.substring(0, lastIndexOf));
                    enableButton(lastSelectedWord);
                }
            }
        }
        private void enableButton(String word) {
            for (JButton button : wordButtons) {
                if (button.getText().equals(word)) {
                    button.setEnabled(true);
                    break;
                }
            }
        }
    }
}

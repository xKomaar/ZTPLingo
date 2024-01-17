package pl.ztplingo.strategy;

import pl.ztplingo.model.QuizSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class WordBySyllablesInputStrategy implements AnswerInputStrategy {

    private java.util.List<String> syllables;
    private JPanel syllablesPanel;
    private List<JButton> syllableButtons;
    private Stack<String> selectedSyllables;
    private JTextField answerInput;
    private JButton backButton;

    @Override
    public void printQuestionAndAnswerInput(JLabel questionLabel, JPanel panel, GridBagConstraints gbc, QuizSession quizSession) {

        questionLabel.setText(quizSession.getCurrentQuestion());

        selectedSyllables = new Stack<>();

        syllables = quizSession.getShuffledAnswer();

        gbc.gridy=1;
        syllableButtons = new ArrayList<>();
        syllablesPanel = new JPanel();
        syllablesPanel.setLayout(new FlowLayout());
        syllablesPanel.setBackground(new Color(0, 0, 0,0));
        panel.add(syllablesPanel, gbc);

        gbc.gridy=2;
        answerInput = new JTextField(40);
        answerInput.setCaretPosition(0);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(new Font("Monospaced", Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setEditable(false);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        answerInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                answerInput.setFocusable(false);
            }
        });

        gbc.gridy=3;
        panel.add(answerInput, gbc);
        backButton = new JButton("Cofnij");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new WordBySyllablesInputStrategy.BackButtonListener());
        panel.add(backButton, gbc);

        syllablesPanel.removeAll();
        for (String syllable : syllables) {
            JButton button = new JButton(syllable);
            button.addActionListener(new WordBySyllablesInputStrategy.SyllableButtonListener());
            syllableButtons.add(button);
            syllablesPanel.add(button);
        }
        panel.revalidate();
        panel.repaint();
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
            selectedSyllables.push(selectedsyllable);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedSyllables.isEmpty()) {
                String lastSelectedsyllable = selectedSyllables.pop();
                String currentText = answerInput.getText();
                int lastIndexOf = currentText.lastIndexOf(" " + lastSelectedsyllable);

                if (lastIndexOf != -1) {
                    answerInput.setText(currentText.substring(0, lastIndexOf));
                    enableButton(lastSelectedsyllable);
                }
            }
        }
        private void enableButton(String syllable) {
            for (JButton button : syllableButtons) {
                if (button.getText().equals(syllable)) {
                    button.setEnabled(true);
                    break;
                }
            }
        }
    }
}

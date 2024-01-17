package pl.ztplingo.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class QuizView extends JPanel {

    private List<String> syllables;
    private List<JButton> syllableButtons;
    private Stack<String> selectedSyllables;
    private JTextField answerInput;
    private JButton submitButton;
    private JButton backButton;
    private Image backgroundImage;
    private JLabel phraseLabel;
    private JPanel syllablesPanel;

    public QuizView() {
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("blackboard.jpg")).getImage();
        selectedSyllables = new Stack<>();
        setBorder(new EmptyBorder(20, 10, 20, 10));
        syllables = new ArrayList<>();  // tutaj ma zostać wysłana lista sylab

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(0,0,0,0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        phraseLabel = new JLabel("ALA ma kota");
        phraseLabel.setFont(setFont(Font.BOLD, 36));
        phraseLabel.setForeground(Color.WHITE);
        phraseLabel.setBackground(Color.RED);
        formPanel.add(phraseLabel, gbc);

        syllableButtons = new ArrayList<>();
        syllablesPanel = new JPanel();
        syllablesPanel.setLayout(new FlowLayout());
        syllablesPanel.setBackground(new Color(0, 0, 0,0));
        gbc.gridy++;
        formPanel.add(syllablesPanel, gbc);

        answerInput = new JTextField(40);
        answerInput.setCaretPosition(0);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(setFont(Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setEditable(false);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        answerInput.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                answerInput.setFocusable(false);
            }
        });
        gbc.gridy++;
        formPanel.add(answerInput, gbc);

        submitButton = new JButton("Zatwierdź odpowiedź");
        submitButton.setFont(setFont(Font.BOLD, 18));
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.BLUE);
        submitButton.addActionListener(new SubmitButtonListener());
        gbc.gridy++;
        formPanel.add(submitButton, gbc);

        backButton = new JButton("Cofnij");
        backButton.setFont(setFont(Font.BOLD, 18));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new BackButtonListener());
        gbc.gridy++;
        formPanel.add(backButton, gbc);

        backButton = new JButton("Wróc do menu");
        backButton.setFont(setFont(Font.BOLD, 18));
        backButton.setForeground(Color.white);
        backButton.setBackground(Color.RED);
        backButton.addActionListener(new BackButtonListener());
        gbc.gridy++;
        formPanel.add(backButton, gbc);

        add(formPanel, BorderLayout.CENTER);
        resetButtons();
    }

    private class SyllableButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String selectedSyllable = button.getText();
            answerInput.setText(answerInput.getText() + " " + selectedSyllable);
            System.out.println(selectedSyllable);
            button.setEnabled(false);
            selectedSyllables.push(selectedSyllable);
        }
    }

    private class BackButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!selectedSyllables.isEmpty()) {
                String lastSelectedSyllable = selectedSyllables.pop();
                String currentText = answerInput.getText();
                int lastIndexOf = currentText.lastIndexOf(" " + lastSelectedSyllable);

                if (lastIndexOf != -1) {
                    answerInput.setText(currentText.substring(0, lastIndexOf));
                    enableButton(lastSelectedSyllable);
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


    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userAnswer = answerInput.getText().trim();

            String expectedAnswer = String.join(" ", syllables);

            if (userAnswer.equals(expectedAnswer)) {
                JOptionPane.showMessageDialog(QuizView.this, "Poprawna odpowiedź!");
            } else {
                JOptionPane.showMessageDialog(QuizView.this, "Błędna odpowiedź. Spróbuj ponownie.");
            }

            answerInput.setText("");
            resetButtons();
        }
    }

    private void resetButtons() {
        syllablesPanel.removeAll();
        for (String syllable : syllables) {
            JButton button = new JButton(syllable);
            button.addActionListener(new SyllableButtonListener());
            syllableButtons.add(button);
            syllablesPanel.add(button);
        }
        revalidate();
        repaint();
    }

    private Font setFont(int style, int size) {
        return new Font(Font.SANS_SERIF, style, size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

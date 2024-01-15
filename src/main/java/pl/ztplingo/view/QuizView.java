package pl.ztplingo.view;

import pl.ztplingo.controller.QuizController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class QuizView extends JPanel {
    private QuizController quizController;
    private JTextField answerInput;
    private JButton submitButton;
    private JButton cancelLearningButton;
    private Image backgroundImage;
    private JLabel phraseLabel;

    public QuizView(QuizController quizController) {
        this.quizController = quizController;
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("blackboard.jpg")).getImage();
        setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(200, 300, 200, 300),
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0))));
        JPanel quizPanel = new JPanel();
        quizPanel.setLayout(new GridBagLayout());
        quizPanel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        phraseLabel = new JLabel();
        phraseLabel.setFont(setFont(Font.BOLD, 36));
        phraseLabel.setForeground(Color.WHITE);
        quizPanel.add(phraseLabel, gbc);

        gbc.gridy++;
        answerInput = new JTextField(40);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(setFont(Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        quizPanel.add(answerInput, gbc);

        gbc.gridy++;
        submitButton = new JButton("Zatwierdź odpowiedź");
        submitButton.setFont(setFont(Font.BOLD, 18));
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.BLUE);
        // submitButton.addActionListener(e -> performQuizAction());
        quizPanel.add(submitButton, gbc);

        gbc.gridy++;
        cancelLearningButton = new JButton("Przerwij naukę");
        cancelLearningButton.setFont(setFont(Font.BOLD, 18));
        cancelLearningButton.setForeground(Color.WHITE);
        cancelLearningButton.setBackground(Color.RED);
        // cancelLearningButton.addActionListener(e -> whatdoYouWant());
        quizPanel.add(cancelLearningButton, gbc);

        add(quizPanel, BorderLayout.CENTER);
    }

    private Font setFont(int style, int size) {
        return new Font("Monospaced", style, size);
    }

    public void setPhrase(String newPhrase) {
        phraseLabel.setText(newPhrase);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

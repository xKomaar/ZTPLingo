package pl.ztplingo.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class QuizView extends JPanel {
    private JTextField answerInput;
    private JButton submitButton;
    private JButton cancelLearningButton;
    private Image backgroundImage;
    private JLabel phraseLabel;

    public QuizView() {
        this.setLayout(new BorderLayout());
        this.backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("blackboard.jpg")).getImage();
        setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(200, 300, 200, 300),
                BorderFactory.createLineBorder(new Color(0,0,0,0))));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(0, 0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        phraseLabel = new JLabel();
        phraseLabel.setFont(setFont(Font.BOLD, 36));
        phraseLabel.setForeground(Color.WHITE);
        formPanel.add(phraseLabel, gbc);

        gbc.gridy++;
        answerInput = new JTextField(40);
        answerInput.setPreferredSize(new Dimension(0, 40));
        answerInput.setFont(setFont(Font.PLAIN, 16));
        answerInput.setBackground(Color.WHITE);
        answerInput.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(answerInput, gbc);

        gbc.gridy++;
        submitButton = new JButton("Zatwierdź odpowiedź");
        submitButton.setFont(setFont(Font.BOLD, 18));
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.BLUE);
        // submitButton.addActionListener(e -> performQuizAction());
        formPanel.add(submitButton, gbc);

        gbc.gridy++;
        cancelLearningButton = new JButton("Przerwij naukę");
        cancelLearningButton.setFont(setFont(Font.BOLD, 18));
        cancelLearningButton.setForeground(Color.WHITE);
        cancelLearningButton.setBackground(Color.RED);
        // cancelLearningButton.addActionListener(e -> whatdoYouWant());
        formPanel.add(cancelLearningButton, gbc);

        add(formPanel, BorderLayout.CENTER);
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

package pl.ztplingo.view;

import pl.ztplingo.Difficulty;
import pl.ztplingo.LanguageState;
import pl.ztplingo.controller.QuizController;
import pl.ztplingo.model.QuizSession;
import pl.ztplingo.strategy.AnswerInputStrategy;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.util.Objects;

public class QuizView extends JPanel {
    private AnswerInputStrategy answerInputStrategy;
    private QuizController quizController;
    private JButton submitButton;
    private JButton cancelLearningButton;
    private Image backgroundImage;
    private JLabel questionLabel;
    private JPanel quizPanel;
    private GridBagConstraints gbc;

    public QuizView(QuizController quizController) {
        this.quizController = quizController;
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("blackboard.jpg")).getImage();
        setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100),
                BorderFactory.createLineBorder(new Color(0, 0, 0, 0))));
        quizPanel = new JPanel();
        quizPanel.setLayout(new GridBagLayout());
        quizPanel.setBackground(new Color(0, 0, 0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        questionLabel = new JLabel();
        questionLabel.setFont(setFont(Font.BOLD, 36));
        questionLabel.setForeground(Color.WHITE);
        quizPanel.add(questionLabel, gbc);

        // ANSWER INPUT STRATEGY - gbc 1 lub gbc 1,2

        gbc.gridy=4;
        submitButton = new JButton("Zatwierdź odpowiedź");
        submitButton.setFont(setFont(Font.BOLD, 18));
        submitButton.setForeground(Color.white);
        submitButton.setBackground(Color.BLUE);
        submitButton.addActionListener(e -> quizController.checkAnswerAndGoNext(answerInputStrategy.getInputedAnswer()));
        quizPanel.add(submitButton, gbc);

        gbc.gridy=5;
        cancelLearningButton = new JButton("Przerwij sesję");
        cancelLearningButton.setFont(setFont(Font.BOLD, 18));
        cancelLearningButton.setForeground(Color.WHITE);
        cancelLearningButton.setBackground(Color.RED);
        cancelLearningButton.addActionListener(e -> quizController.invalidateQuizSessionWithSnapshot());
        quizPanel.add(cancelLearningButton, gbc);

        gbc.gridy=6;
        cancelLearningButton = new JButton("Anuluj sesję");
        cancelLearningButton.setFont(setFont(Font.BOLD, 18));
        cancelLearningButton.setForeground(Color.WHITE);
        cancelLearningButton.setBackground(Color.RED);
        cancelLearningButton.addActionListener(e -> quizController.invalidateQuizSession());
        quizPanel.add(cancelLearningButton, gbc);

        add(quizPanel, BorderLayout.CENTER);
    }

    public void showSettingsPopup() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        String[] difficulties = {"Łatwy", "Trudny"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        panel.add(new JLabel("Poziom trudności:"));
        panel.add(difficultyComboBox);

        String[] languages = {"Polski -> Angielski", "Angielski -> Polski"};
        JComboBox<String> languageComboBox = new JComboBox<>(languages);
        panel.add(new JLabel("Tryb językowy:"));
        panel.add(languageComboBox);

        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 1, 30, 1);
        JSpinner spinner = new JSpinner(spinnerModel);
        panel.add(new JLabel("Ilość pytań"));
        panel.add(spinner);

        int result = JOptionPane.showConfirmDialog(null, panel, "Ustawienia", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Difficulty difficulty;
            LanguageState language;
            int questionQuantity;

            if(Objects.equals((String) difficultyComboBox.getSelectedItem(), "Łatwy")) {
                difficulty = Difficulty.EASY;
            } else {
                difficulty = Difficulty.HARD;
            }

            if(Objects.equals((String) languageComboBox.getSelectedItem(), "Polski -> Angielski")) {
                language = LanguageState.POLISH_TO_ENGLISH;
            } else {
                language = LanguageState.ENGLISH_TO_POLISH;
            }

            questionQuantity = (Integer)spinner.getValue();

            quizController.initQuizSession(language, difficulty, questionQuantity);
        } else {
            quizController.invalidateQuizSession();
        }
    }

    public void showQuestionQuantityError() {
        JOptionPane.showMessageDialog(this, "Liczba pytań nie może być większa niż liczba fraz w bazie", "Za mało fraz w bazie", JOptionPane.ERROR_MESSAGE);
    }

    public void showNoSnapshotError() {
        JOptionPane.showMessageDialog(this, "Aby zapisać jakąś sesję użyj przysisku \"Przerwij sesję\" podczas jej wykonywania", "Brak Zapisanej Sesji", JOptionPane.ERROR_MESSAGE);
    }

    private Font setFont(int style, int size) {
        return new Font("Monospaced", style, size);
    }

    public void printQuestionAndAnswerInput(QuizSession quizSession) {
        answerInputStrategy.printQuestionAndAnswerInput(questionLabel, quizPanel, gbc, quizSession);
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void setAnswerInputStrategy(AnswerInputStrategy answerInputStrategy) {
        this.answerInputStrategy = answerInputStrategy;
    }
}

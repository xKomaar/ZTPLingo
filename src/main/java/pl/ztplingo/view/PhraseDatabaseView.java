package pl.ztplingo.view;

import pl.ztplingo.controller.PhraseDatabaseController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PhraseDatabaseView extends JPanel {
    private PhraseDatabaseController phraseDatabaseController;
    private JList<String> phraseList;
    private JTextField polishTextField, englishTextField;
    private JButton showWordsButton, showSentencesButton, removeButton, addWordButton, backButton;

    private boolean showWords = true;

    public PhraseDatabaseView(PhraseDatabaseController phraseDatabaseController) {
        this.phraseDatabaseController = phraseDatabaseController;
        Color orange = new Color(245, 131, 81);
        Color darkblue = new Color(18, 42, 52);
        setBackground(darkblue);
        setVisible(true);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        buttonPanel.setBackground(orange);
        showWordsButton = new JButton("Baza słów");
        showWordsButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        showWordsButton.setBackground(darkblue);
        showWordsButton.setForeground(Color.WHITE);
        showWordsButton.addActionListener(e -> {
            showWords = true;
            updateList();
        });
        buttonPanel.add(showWordsButton);

        showSentencesButton = new JButton("Baza zdań");
        showSentencesButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        showSentencesButton.setForeground(Color.WHITE);
        showSentencesButton.setBackground(darkblue);
        showSentencesButton.addActionListener(e -> {
            showWords = false;
            updateList();
        });
        buttonPanel.add(showSentencesButton);

        removeButton = new JButton("Usuń");
        removeButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        removeButton.setForeground(Color.WHITE);
        removeButton.setBackground(Color.RED);
        removeButton.addActionListener(e -> removePhrase());
        buttonPanel.add(removeButton);

        backButton = new JButton("Wróć do menu");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(darkblue);
        backButton.addActionListener(e -> phraseDatabaseController.returnToMainController());
        buttonPanel.add(backButton);

        phraseList = new JList<>();
        phraseList.setFont(new Font("Monospaced", Font.PLAIN, 16));

        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(phraseList);
        scrollPane.setPreferredSize(new Dimension(0, 400));
        mainPanel.add(scrollPane);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        inputPanel.setBackground(orange);

        JLabel polish = new JLabel("Polski:");
        polish.setFont(new Font("Monospaced", Font.BOLD, 18));
        inputPanel.add(polish);

        polishTextField = new JTextField(20);
        polishTextField.setFont(new Font("Monospaced", Font.PLAIN, 18));
        polishTextField.setPreferredSize(new Dimension(0, 40));
        inputPanel.add(polishTextField);

        JLabel english = new JLabel("Angielski:");
        english.setFont(new Font("Monospaced", Font.BOLD, 18));
        inputPanel.add(english);

        englishTextField = new JTextField(20);
        englishTextField.setFont(new Font("Monospaced", Font.PLAIN, 18));
        englishTextField.setPreferredSize(new Dimension(0, 40));
        inputPanel.add(englishTextField);

        addWordButton = new JButton("Dodaj");
        addWordButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        addWordButton.setBackground(darkblue);
        addWordButton.setForeground(Color.WHITE);
        addWordButton.addActionListener(e -> addPhrase());
        inputPanel.add(addWordButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        setBorder(new EmptyBorder(100, 10, 10, 10));
        add(mainPanel);

        updateList();
    }

    public void updateList() {
        phraseList.setModel(new DefaultListModel<>());
        if (showWords) {
            DefaultListModel<String> wordListModel = new DefaultListModel<>();
            wordListModel.addAll(phraseDatabaseController.getWordsToString());
            phraseList.setModel(wordListModel);
        } else {
            DefaultListModel<String> sentenceListModel = new DefaultListModel<>();
            sentenceListModel.addAll(phraseDatabaseController.getSentencesToString());
            phraseList.setModel(sentenceListModel);
        }
    }

    private void removePhrase() {
        int selectedIndex = phraseList.getSelectedIndex();
        if (selectedIndex != -1) {
            if (showWords) {
                phraseDatabaseController.deleteWordByIndexOnList(selectedIndex);
            } else {
                phraseDatabaseController.deleteSentenceByIndexOnList(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz frazę do usunięcia.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addPhrase() {
        String polish = polishTextField.getText();
        String english = englishTextField.getText();

        if (!polish.isEmpty() && !english.isEmpty()) {
            if (showWords) {
                phraseDatabaseController.addWord(english, polish);
            } else {
                phraseDatabaseController.addSentence(english, polish);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wprowadź obie wartości.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        polishTextField.setText("");
        englishTextField.setText("");
    }
}

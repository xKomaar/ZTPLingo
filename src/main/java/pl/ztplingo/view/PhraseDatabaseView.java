package pl.ztplingo.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PhraseDatabaseView extends JPanel {
    private DefaultListModel<String> wordListModel;
    private DefaultListModel<String> sentenceListModel;
    private JList<String> phraseList;
    private JTextField polishTextField, englishTextField;
    private JButton showWordsButton, showSentencesButton, removeButton, addWordButton, backButton;
    private Image backgroundImage;

    private boolean showWords = true;

    public PhraseDatabaseView() {
        this.backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("blackboard.jpg")).getImage();
        wordListModel = new DefaultListModel<>();
        sentenceListModel = new DefaultListModel<>();
        Color orange = new Color(245, 131, 81);
        Color darkblue = new Color(18, 42, 52);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        buttonPanel.setBackground(orange);
        showWordsButton = new JButton("Baza słów");
        showWordsButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        showWordsButton.setBackground(darkblue);
        showWordsButton.setForeground(Color.WHITE);
        buttonPanel.add(showWordsButton);

        showSentencesButton = new JButton("Baza zdań");
        showSentencesButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        showSentencesButton.setForeground(Color.WHITE);
        showSentencesButton.setBackground(darkblue);
        buttonPanel.add(showSentencesButton);

        removeButton = new JButton("Usuń");
        removeButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        removeButton.setForeground(Color.WHITE);
        removeButton.setBackground(Color.RED);
        buttonPanel.add(removeButton);

        backButton = new JButton("Wróć do menu");
        backButton.setFont(new Font("Monospaced", Font.BOLD, 18));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(darkblue);
        buttonPanel.add(backButton);

        phraseList = new JList<>(wordListModel);
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
        inputPanel.add(addWordButton);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        showWordsButton.addActionListener(e -> {
            showWords = true;
            updateList();
        });

        showSentencesButton.addActionListener(e -> {
            showWords = false;
            updateList();
        });

        removeButton.addActionListener(e -> removePhrase());

        addWordButton.addActionListener(e -> addWord());

        backButton.addActionListener(e -> {
        });

        setBorder(new EmptyBorder(100, 10, 10, 10));
        add(mainPanel);

        wordListModel.addElement("dom : house");
        wordListModel.addElement("kot : cat");
        wordListModel.addElement("pies : dog");

        sentenceListModel.addElement("zdanie1 : translation1");
        sentenceListModel.addElement("zdanie2 : translation2");
        sentenceListModel.addElement("zdanie3 | translation3");
    }

    private void updateList() {
        phraseList.setModel(new DefaultListModel<>());
        if (showWords) {
            phraseList.setModel(wordListModel);
        } else {
            phraseList.setModel(sentenceListModel);
        }
    }

    private void removePhrase() {
        int selectedIndex = phraseList.getSelectedIndex();
        if (selectedIndex != -1) {
            if (showWords) {
                wordListModel.remove(selectedIndex);
            } else {
                sentenceListModel.remove(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz frazę do usunięcia.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addWord() {
        String polishWord = polishTextField.getText();
        String englishWord = englishTextField.getText();

        if (!polishWord.isEmpty() && !englishWord.isEmpty()) {
            String phrase = polishWord + " : " + englishWord;
            if (showWords) {
                wordListModel.addElement(phrase);
            } else {
                sentenceListModel.addElement(phrase);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Wprowadź obie wartości.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        polishTextField.setText("");
        englishTextField.setText("");
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

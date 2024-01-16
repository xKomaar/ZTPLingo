package pl.ztplingo.view;

import pl.ztplingo.Difficulty;
import pl.ztplingo.LanguageState;
import pl.ztplingo.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JPanel {
    private JPanel menuPanel;
    private MainController mainController;
    private String username;
    private Integer points;
    private JButton testButton;
    private JButton sessionButton;
    private JButton phrasesButton;
    private JButton continueButton;
    private JButton exitButton;
    private Image backgroundImage;
    private Color orange;
    private Font buttonFont;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        username = mainController.getLoggedUser().getUsername();
        points = mainController.getLoggedUser().getPoints();
        setLayout(new BorderLayout());
        setVisible(true);
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("main.jpg")).getImage();
        orange = new Color(245, 131, 81);
        buttonFont = new Font("Monospaced", Font.BOLD, 24);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel greetingLabel = new JLabel("Cześć, " + username);
        greetingLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        greetingLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(greetingLabel, gbc);

        JLabel pointsLabel = new JLabel("Twoje punkty: " + points);
        pointsLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        pointsLabel.setForeground(Color.BLUE);
        gbc.gridy = 1;
        add(pointsLabel, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 2, 0, 10));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));

        testButton = new JButton("WYKONAJ TEST");
        testButton.addActionListener(e -> mainController.redirectToQuizController());
        createButtonStyle(testButton);
        buttonPanel.add(testButton);

        sessionButton = new JButton("ZRÓB SESJE NAUKI");
        sessionButton.addActionListener(e -> mainController.redirectToQuizController());
        createButtonStyle(sessionButton);
        buttonPanel.add(sessionButton);

        phrasesButton = new JButton("BAZA FRAZ");
        phrasesButton.addActionListener(e -> mainController.redirectToPhraseDatabaseController());
        createButtonStyle(phrasesButton);
        buttonPanel.add(phrasesButton);

        continueButton = new JButton("OSTATNIA PRZERWANA");
        continueButton.addActionListener(e -> mainController.redirectToQuizControllerWithSnapshot());
        createButtonStyle(continueButton);
        buttonPanel.add(continueButton);

        exitButton = new JButton("WYJDŹ");
        exitButton.addActionListener(e -> System.exit(0));
        createButtonStyle(exitButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(buttonPanel, gbc);
    }

    public void createButtonStyle(JButton button) {
        button.setBackground(orange);
        button.setFont(buttonFont);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(orange);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(orange);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
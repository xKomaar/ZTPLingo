package pl.ztplingo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JPanel {
    private static final String[] options = {"WYKONAJ TEST", "ZRÓB SESJĘ NAUKI", "BAZA FRAZ", "WYJDŹ"};
    private JPanel menuPanel;

    public MainView() {
        menuPanel = createMenuPanel();
        this.setLayout(new BorderLayout());
        this.add(menuPanel);
        this.setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("main.jpg")).getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String username= "Alicja";
        int points= 130;
        JLabel greetingLabel = new JLabel("Cześć, " + username);
        greetingLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        greetingLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(greetingLabel, gbc);

        JLabel pointsLabel = new JLabel("Twoje punkty: " + points);
        pointsLabel.setFont(new Font("Monospaced", Font.BOLD, 24));
        pointsLabel.setForeground(Color.BLUE);
        gbc.gridy = 1;
        panel.add(pointsLabel, gbc);

        JPanel buttonPanel = new JPanel();
        Font buttonFont = new Font("Monospaced", Font.BOLD, 24);
        buttonPanel.setLayout(new GridLayout(options.length, 2, 0, 10));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));
        for (int i = 0; i < options.length; i++) {
            JButton button = new JButton(options[i]);
            button.addActionListener(new MenuButtonListener(i));
            Color orange = new Color(245, 131, 81);
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
            buttonPanel.add(button);
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private class MenuButtonListener implements ActionListener {
        private final int optionIndex;

        public MenuButtonListener(int optionIndex) {
            this.optionIndex = optionIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            handleOption(optionIndex);
        }
    }

    private void handleOption(int selectedOption) {
        switch (selectedOption) {
            case 0:
                showPopup();
                break;
            case 1:
                showPopup();
                switchToPanel(new QuizView());
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private void showPopup() {
        JPanel panel = new JPanel(new GridLayout(4, 1));
        String[] difficulties = {"Łatwy", "Trudny"};
        JComboBox<String> difficultyComboBox = new JComboBox<>(difficulties);
        panel.add(new JLabel("Wybierz poziom trudności:"));
        panel.add(difficultyComboBox);

        String[] languages = {"Polski -> Angielski", "Angielski -> Polski"};
        JComboBox<String> languageComboBox = new JComboBox<>(languages);
        panel.add(new JLabel("Wybierz tryb językowy:"));
        panel.add(languageComboBox);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Ustawienia", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String difficulty = (String) difficultyComboBox.getSelectedItem();
            String language = (String) languageComboBox.getSelectedItem();
        }
    }

    private void switchToPanel(JPanel newPanel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(newPanel);
        frame.revalidate();
        frame.repaint();
    }
}

package pl.ztplingo.view;

import pl.ztplingo.controller.MainController;
import pl.ztplingo.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JPanel {
    private static final String[] options = {"WYKONAJ TEST", "ZRÓB SESJĘ NAUKI", "BAZA FRAZ", "WYJDŹ"};
    private JPanel menuPanel;
    private MainController mainController;
    private String username;
    private Integer points;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        username = mainController.getLoggedUser().getUsername();
        points = mainController.getLoggedUser().getPoints();
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
            button.setFocusable(false);
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
                break;
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
}

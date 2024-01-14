package pl.ztplingo.view;

import pl.ztplingo.controller.StartController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartView extends JPanel {
    private StartController startController;
    private Image backgroundImage;

    public StartView(StartController startController) {
        this.startController = startController;
        setLayout(new GridBagLayout());
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("main.jpg")).getImage();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel titleLabel = new JLabel("ZTPLingo");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 38));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);
        JPanel buttonPanel = new JPanel();
        Font buttonFont = new Font("Monospaced", Font.BOLD, 24);
        buttonPanel.setLayout(new GridLayout(2, 2, 0, 10));

        //przycisk logowania
        JButton loginButton = new JButton("Zaloguj się");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.redirectToLogin();
            }
        });
        loginButton.setFocusable(false);
        Color orange= new Color(245, 131, 81);
        loginButton.setBackground(orange);
        loginButton.setFont(buttonFont);
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(orange);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                loginButton.setBackground(orange);
            }
        });
        buttonPanel.add(loginButton);

        //przycisk rejestracji
        JButton registerButton = new JButton("Zarejestruj się");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startController.redirectToRegister();
            }
        });
        registerButton.setFocusable(false);
        registerButton.setBackground(orange);
        registerButton.setFont(buttonFont);
        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(orange);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                registerButton.setBackground(orange);
            }
        });
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        add(buttonPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

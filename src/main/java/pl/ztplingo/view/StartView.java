package pl.ztplingo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartView extends JFrame {
    private static final String[] options = {"ZALOGUJ", "ZAREJESTRUJ"};
    private JPanel menuPanel;

    public StartView() {
        setTitle("ZTPLingo");
        setSize(1040, 800);
        setLocationRelativeTo(null);
        menuPanel = createMenuPanel();
        add(menuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
        JLabel titleLabel = new JLabel("ZTPLingo");
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 38));
        titleLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        JPanel buttonPanel = new JPanel();
        Font buttonFont = new Font("Monospaced", Font.BOLD, 24);
        buttonPanel.setLayout(new GridLayout(options.length, 2, 0, 10));
        for (int i = 0; i < options.length; i++) {
            final int optionIndex = i;
            JButton button = new JButton(options[i]);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchToPanel(new LoginView(optionIndex == 0));
                }
            });
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
        gbc.gridy = 1;
        gbc.insets = new Insets(50, 0, 0, 0);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private void switchToPanel(JPanel newPanel) {
        getContentPane().removeAll();
        getContentPane().add(newPanel);
        revalidate();
        repaint();
    }
}

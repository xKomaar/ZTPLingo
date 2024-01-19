package pl.ztplingo.view;

import pl.ztplingo.controller.LoginRegisterController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton actionButton;
    private JLabel switchLabel;
    private JLabel usernameLabel;
    private JLabel confirmPasswordLabel;
    private Image backgroundImage;
    private boolean isLoginForm;

    private LoginRegisterController loginRegisterController;

    public LoginRegisterView(LoginRegisterController loginRegisterController, boolean isLoginForm) {
        this.loginRegisterController = loginRegisterController;
        this.isLoginForm = isLoginForm;
        setLayout(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("main.jpg")).getImage();
        setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(200, 300, 200, 300),
                BorderFactory.createLineBorder(Color.RED)));
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        formPanel.setBackground(new Color(245, 131, 81));
        formPanel.setOpaque(true);

        usernameLabel = new JLabel("Nazwa użytkownika:");
        usernameLabel.setFont(setFont(Font.BOLD, 18));
        formPanel.add(usernameLabel, gbc);

        gbc.gridy++;
        usernameField = new JTextField(20);
        usernameField.setPreferredSize(new Dimension(0, 40));
        usernameField.setFont(setFont(Font.PLAIN, 16));
        usernameField.setBackground(Color.WHITE);
        formPanel.add(usernameField, gbc);

        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Hasło:");
        passwordLabel.setFont(setFont(Font.BOLD, 18));
        formPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(0, 40));
        passwordField.setFont(setFont(Font.PLAIN, 16));
        passwordField.setBackground(Color.WHITE);
        formPanel.add(passwordField, gbc);

        gbc.gridy++;
        confirmPasswordLabel = new JLabel("Potwierdź hasło:");
        confirmPasswordLabel.setFont(setFont(Font.BOLD, 18));
        confirmPasswordLabel.setVisible(!isLoginForm);
        formPanel.add(confirmPasswordLabel, gbc);

        gbc.gridy++;
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setPreferredSize(new Dimension(0, 40));
        confirmPasswordField.setFont(setFont(Font.PLAIN, 16));
        confirmPasswordField.setVisible(!isLoginForm);
        formPanel.add(confirmPasswordField, gbc);

        gbc.gridy++;
        actionButton = new JButton(isLoginForm ? "Zaloguj" : "Zarejestruj");
        actionButton.setFont(setFont(Font.BOLD, 18));
        actionButton.setBackground(Color.BLUE);
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLoginOrRegistration();
            }
        });
        formPanel.add(actionButton, gbc);

        gbc.gridy++;
        gbc.insets = new Insets(5, 5, 10, 5);
        switchLabel = new JLabel(isLoginForm ? "Nie masz jeszcze konta? Zarejestruj się" : "Masz już konto? Zaloguj się");
        switchLabel.setFont(setFont(Font.BOLD, 16));
        switchLabel.setForeground(Color.WHITE);
        switchLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switchForm();
            }
        });
        formPanel.add(switchLabel, gbc);
        add(formPanel, BorderLayout.CENTER);
    }

    private Font setFont(int style, int size) {
        return new Font("Monospaced", style, size);
    }

    private void performLogin() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        loginRegisterController.performLogin(username, password);
    }

    private void performRegistration() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        char[] confirmPasswordChars = confirmPasswordField.getPassword();
        String confirmPassword = new String(confirmPasswordChars);

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Hasła się różnią!", "Błąd rejestracji", JOptionPane.ERROR_MESSAGE);
            return;
        }

        loginRegisterController.performRegistration(username, password);
    }

    private void performLoginOrRegistration() {
        if (isLoginForm) {
            performLogin();
        } else {
            performRegistration();
        }
    }

    public void switchForm() {
        isLoginForm = !isLoginForm;
        switchLabel.setText(isLoginForm ? "Nie masz jeszcze konta? Zarejestruj się" : "Masz już konto? Zaloguj się");
        actionButton.setText(isLoginForm ? "Zaloguj" : "Zarejestruj");
        confirmPasswordLabel.setVisible(!isLoginForm);
        confirmPasswordField.setVisible(!isLoginForm);
        revalidate();
        repaint();
    }

    public void showUserAlreadyExistsError() {
        JOptionPane.showMessageDialog(this, "Użytkownik o takiej nazwie już istnieje!", "Błąd rejestracji", JOptionPane.ERROR_MESSAGE);
    }

    public void showIncorrectUsernameError() {
        JOptionPane.showMessageDialog(this, "Niepoprawna nazwa użytkownika!", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
    }

    public void showIncorrectPasswordError() {
        JOptionPane.showMessageDialog(this, "Niepoprawne hasło!", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
    }

    public void showUsernameTooShortError() {
        JOptionPane.showMessageDialog(this, "Nazwa użytkownika powinna składać się z conajmniej 3 liter!", "Błąd rejestracji", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

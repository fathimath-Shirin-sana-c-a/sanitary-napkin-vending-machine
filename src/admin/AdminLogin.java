package admin;

import javax.swing.*;
import java.awt.*;
import ui.Theme;

public class AdminLogin {
    JFrame frame;
    JPasswordField passwordField;

    public AdminLogin() {

        frame = new JFrame("Admin Login");
        frame.setSize(350, 180);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Theme.applyFrame(frame);


        JLabel label = new JLabel("Enter Admin Password:", SwingConstants.CENTER);
        Theme.applyLabel(label);
        frame.add(label);


        passwordField = new JPasswordField();
        passwordField.setFont(Theme.FONT_LABEL);
        passwordField.setForeground(Theme.FG);
        passwordField.setBackground(Theme.BG);
        frame.add(passwordField);


        JButton loginBtn = Theme.createButton("Login");
        loginBtn.addActionListener(e -> {
            String entered = new String(passwordField.getPassword());
            if (entered.equals("admin123")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                new AdminPanel();   // go to Admin Panel
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect password. Try again.");
            }
        });
        frame.add(loginBtn);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
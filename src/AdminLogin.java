import javax.swing.*;

public class AdminLogin {
    JFrame frame;
    JPasswordField passwordField;

    public AdminLogin() {
        frame = new JFrame("Admin Login");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(3, 1));

        JLabel label = new JLabel("Enter Admin Password:", SwingConstants.CENTER);
        frame.add(label);

        passwordField = new JPasswordField();
        frame.add(passwordField);

        JButton loginBtn = new JButton("Login");
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
}
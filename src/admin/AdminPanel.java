package admin;

import javax.swing.*;
import java.awt.*;
import ui.Theme; // import the Theme class

public class AdminPanel {
    JFrame frame;

    public AdminPanel() {

        frame = new JFrame("Admin Panel");
        frame.setSize(350, 250);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3, 1, 10, 10));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Theme.applyFrame(frame);


        JButton refillBtn = Theme.createButton("Refill Items");
        refillBtn.addActionListener(e -> {
            new RefillScreen();
            frame.dispose();
        });

        JButton reportBtn = Theme.createButton("View Reports");
        reportBtn.addActionListener(e -> {
            new ReportScreen();
            frame.dispose();
        });

        JButton logoutBtn = Theme.createButton("Logout");
        logoutBtn.addActionListener(e -> {
            new user.UserPanel(); // back to home
            frame.dispose();
        });


        frame.add(refillBtn);
        frame.add(reportBtn);
        frame.add(logoutBtn);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}
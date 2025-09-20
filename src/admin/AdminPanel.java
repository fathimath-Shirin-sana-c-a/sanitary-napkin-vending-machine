package admin;

import javax.swing.*;
import java.awt.*;

public class AdminPanel {
    JFrame frame;

    public AdminPanel() {
        frame = new JFrame("Admin Panel");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JButton refillBtn = new JButton("Refill Items");
        refillBtn.addActionListener(e -> {
            new RefillScreen();
            frame.dispose();
        });
        frame.add(refillBtn);

        JButton reportBtn = new JButton("View Reports");
        reportBtn.addActionListener(e -> {
            new ReportScreen();
            frame.dispose();
        });
        frame.add(reportBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            new user.UserPanel(); // back to home
            frame.dispose();
        });
        frame.add(logoutBtn);

        frame.setVisible(true);
    }
}
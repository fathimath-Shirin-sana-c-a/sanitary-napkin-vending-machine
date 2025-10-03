package user;

import db.DBHelper;
import admin.AdminLogin;
import javax.swing.*;
import java.awt.*;

public class UserPanel {
    JFrame frame;
    JLabel title;
    JLabel totalLabel;
    int total = 0;

    public UserPanel() {
        frame = new JFrame("Sanitary Napkin Vending Machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        title = new JLabel("Welcome! Insert Cash to Buy Napkin (₹5)", SwingConstants.CENTER);
        frame.add(title, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btn1 = new JButton("₹1");
        JButton btn2 = new JButton("₹2");
        JButton btn5 = new JButton("₹5");
        JButton btn10 = new JButton("₹10");
        JButton btn20 = new JButton("₹20");
        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn5);
        buttonPanel.add(btn10);
        buttonPanel.add(btn20);

        // Total Label
        totalLabel = new JLabel("Total Inserted: ₹0", SwingConstants.CENTER);

        // Action Listeners for buttons
        btn1.addActionListener(e -> updateTotal(1));
        btn2.addActionListener(e -> updateTotal(2));
        btn5.addActionListener(e -> updateTotal(5));
        btn10.addActionListener(e -> updateTotal(10));
        btn20.addActionListener(e -> updateTotal(20));

        // Get Napkin Button
        JButton getNapkinBtn = new JButton("Get Napkin");
        getNapkinBtn.addActionListener(e -> getNapkin());

        // Admin Login Button
        JButton adminBtn = new JButton("Admin Login");
        adminBtn.addActionListener(e -> new AdminLogin());

        // Adding components to frame
        frame.add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(totalLabel);
        bottomPanel.add(getNapkinBtn);
        bottomPanel.add(adminBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateTotal(int amount) {
        total += amount;
        totalLabel.setText("Total Inserted: ₹" + total);
    }

    private void getNapkin() {
        int stock = DBHelper.getNapkinStock();
        if (stock > 0 && total >= 5) {
            JOptionPane.showMessageDialog(frame, "Napkin Dispensed!");
            DBHelper.updateNapkinStock(stock - 1);
            total -= 5;
            totalLabel.setText("Total Inserted: ₹" + total);
        } else if (stock <= 0) {
            JOptionPane.showMessageDialog(frame, "Out of stock! Please contact admin.");
        } else {
            JOptionPane.showMessageDialog(frame, "Insert at least ₹5 to get a napkin.");
        }
    }

    public static void main(String[] args) {
        new UserPanel();
    }
}


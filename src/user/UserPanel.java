package user;

import javax.swing.*;
import db.DBHelper;

public class UserPanel {
    JFrame frame;
    JLabel title, totalLabel;

    public UserPanel() {
        frame = new JFrame("Sanitary Napkin Vending Machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(4, 1));

        // ✅ Welcome Message
        title = new JLabel("Welcome! Insert Cash to Buy Napkin (₹5)", SwingConstants.CENTER);
        frame.add(title);

        // ✅ Buttons for inserting money
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

        frame.add(buttonPanel);

        // ✅ Label showing total inserted
        totalLabel = new JLabel("Total Inserted: ₹0", SwingConstants.CENTER);
        frame.add(totalLabel);

        final int[] total = {0};

        btn1.addActionListener(e -> { total[0] += 1; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn2.addActionListener(e -> { total[0] += 2; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn5.addActionListener(e -> { total[0] += 5; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn10.addActionListener(e -> { total[0] += 10; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn20.addActionListener(e -> { total[0] += 20; totalLabel.setText("Total Inserted: ₹" + total[0]); });

        // ✅ Get Napkin button
        JButton getNapkinBtn = new JButton("Get Napkin");
        frame.add(getNapkinBtn);

        getNapkinBtn.addActionListener(e -> {
            int stock = DBHelper.getNapkinStock();
            if (stock > 0 && total[0] >= 5) {
                JOptionPane.showMessageDialog(frame, "Napkin Dispensed!");
                DBHelper.updateNapkinStock(stock - 1);   // reduce stock
                DBHelper.insertTransaction(1, 5);       // log transaction
                total[0] -= 5;                          // deduct from user balance
                totalLabel.setText("Total Inserted: ₹" + total[0]);
            } else if (stock <= 0) {
                JOptionPane.showMessageDialog(frame, "Out of stock! Please contact admin.");
            } else {
                JOptionPane.showMessageDialog(frame, "Insert at least ₹5 to get a napkin.");
            }
        });

        // ✅ Admin Login button
        JButton adminBtn = new JButton("Admin Login");
        adminBtn.addActionListener(e -> new admin.AdminLogin());
        frame.add(adminBtn);

        frame.setVisible(true);
    }
}
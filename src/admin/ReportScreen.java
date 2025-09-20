package admin;

import javax.swing.*;
import java.awt.*;

public class ReportScreen {
    JFrame frame;

    // Sample data (will be from DB in Day 4)
    private static int todayNapkins = 3;
    private static int todayAmount = 15;
    private static int totalNapkins = 25;
    private static int totalAmount = 125;

    public ReportScreen() {
        frame = new JFrame("Reports");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        JLabel todayLabel = new JLabel("Today's Report:");
        JLabel napkinsToday = new JLabel("Napkins Sold: " + todayNapkins);
        JLabel amountToday = new JLabel("Amount Collected: ₹" + todayAmount);

        JLabel allTimeLabel = new JLabel("All-Time Report:");
        JLabel napkinsTotal = new JLabel("Total Napkins Sold: " + totalNapkins);
        JLabel amountTotal = new JLabel("Total Amount Collected: ₹" + totalAmount);

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.addActionListener(e -> {
            new AdminPanel();
            frame.dispose();
        });

        frame.add(todayLabel);
        frame.add(napkinsToday);
        frame.add(amountToday);
        frame.add(allTimeLabel);
        frame.add(napkinsTotal);
        frame.add(amountTotal);
        frame.add(backBtn);

        frame.setVisible(true);
    }
}
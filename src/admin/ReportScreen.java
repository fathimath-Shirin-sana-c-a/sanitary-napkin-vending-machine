package admin;

import db.DBHelper;
import javax.swing.*;
import java.awt.*;

public class ReportScreen {
    JFrame frame;

    public ReportScreen() {
        frame = new JFrame("Reports");
        frame.setSize(420, 260);
        frame.setLayout(new GridLayout(8, 1));

        JLabel todayLabel      = new JLabel("Today's Report:");
        JLabel napkinsToday    = new JLabel("Napkins Sold: " + DBHelper.getTodaySales());
        JLabel amountToday     = new JLabel("Amount Collected: ₹" + DBHelper.getTodayRevenue());

        JLabel allTimeLabel    = new JLabel("All-Time Report:");
        JLabel napkinsTotal    = new JLabel("Total Napkins Sold: " + DBHelper.getTotalSales());
        JLabel amountTotal     = new JLabel("Total Amount Collected: ₹" + DBHelper.getTotalRevenue());

        JLabel lastNapkinRefill = new JLabel("Last Napkin Refill: " + DBHelper.getLastNapkinRefill());
        JLabel lastCoinRefill   = new JLabel("Last Coin Refill: " + DBHelper.getLastCoinRefill());

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> {
            napkinsToday.setText("Napkins Sold: " + DBHelper.getTodaySales());
            amountToday.setText("Amount Collected: ₹" + DBHelper.getTodayRevenue());
            napkinsTotal.setText("Total Napkins Sold: " + DBHelper.getTotalSales());
            amountTotal.setText("Total Amount Collected: ₹" + DBHelper.getTotalRevenue());
            lastNapkinRefill.setText("Last Napkin Refill: " + DBHelper.getLastNapkinRefill());
            lastCoinRefill.setText("Last Coin Refill: " + DBHelper.getLastCoinRefill());
        });

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.addActionListener(e -> { new AdminPanel(); frame.dispose(); });

        frame.add(todayLabel);
        frame.add(napkinsToday);
        frame.add(amountToday);
        frame.add(allTimeLabel);
        frame.add(napkinsTotal);
        frame.add(amountTotal);
        frame.add(lastNapkinRefill);
        frame.add(lastCoinRefill);
        frame.add(refreshBtn);
        frame.add(backBtn);

        frame.setVisible(true);
    }
}
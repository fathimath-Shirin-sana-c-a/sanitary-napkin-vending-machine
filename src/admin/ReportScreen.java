package admin;

import db.DBHelper;
import ui.Theme;

import javax.swing.*;
import java.awt.*;

public class ReportScreen {
    JFrame frame;

    public ReportScreen() {

        frame = new JFrame("Reports");
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(6, 2, 10, 10));
        Theme.applyFrame(frame);


        JLabel todayLabel      = new JLabel("Today's Report:");
        todayLabel.setFont(Theme.FONT_TITLE);
        todayLabel.setForeground(Theme.FG);

        JLabel napkinsToday    = new JLabel("Napkins Sold: " + DBHelper.getTodaySales());
        Theme.applyLabel(napkinsToday);

        JLabel amountToday     = new JLabel("Amount Collected: ₹" + DBHelper.getTodayRevenue());
        Theme.applyLabel(amountToday);

        JLabel allTimeLabel    = new JLabel("All-Time Report:");
        allTimeLabel.setFont(Theme.FONT_TITLE);
        allTimeLabel.setForeground(Theme.FG);

        JLabel napkinsTotal    = new JLabel("Total Napkins Sold: " + DBHelper.getTotalSales());
        Theme.applyLabel(napkinsTotal);

        JLabel amountTotal     = new JLabel("Total Amount Collected: ₹" + DBHelper.getTotalRevenue());
        Theme.applyLabel(amountTotal);

        JLabel lastNapkinRefill = new JLabel("Last Napkin Refill: " + DBHelper.getLastNapkinRefill());
        Theme.applyLabel(lastNapkinRefill);

        JLabel lastCoinRefill   = new JLabel("Last Coin Refill: " + DBHelper.getLastCoinRefill());
        Theme.applyLabel(lastCoinRefill);


        JButton refreshBtn = Theme.createButton("Refresh");
        refreshBtn.addActionListener(e -> {
            napkinsToday.setText("Napkins Sold: " + DBHelper.getTodaySales());
            amountToday.setText("Amount Collected: ₹" + DBHelper.getTodayRevenue());
            napkinsTotal.setText("Total Napkins Sold: " + DBHelper.getTotalSales());
            amountTotal.setText("Total Amount Collected: ₹" + DBHelper.getTotalRevenue());
            lastNapkinRefill.setText("Last Napkin Refill: " + DBHelper.getLastNapkinRefill());
            lastCoinRefill.setText("Last Coin Refill: " + DBHelper.getLastCoinRefill());
        });

        JButton backBtn = Theme.createButton("Back to Admin Panel");
        backBtn.addActionListener(e -> { new AdminPanel(); frame.dispose(); });


        frame.add(todayLabel);
        frame.add(allTimeLabel);
        frame.add(napkinsToday);
        frame.add(napkinsTotal);
        frame.add(amountToday);
        frame.add(amountTotal);
        frame.add(lastNapkinRefill);
        frame.add(lastCoinRefill);
        frame.add(refreshBtn);
        frame.add(backBtn);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ReportScreen();
    }
}
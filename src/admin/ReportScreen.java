package admin;

import javax.swing.*;
import java.awt.*;

import db.DBHelper;

public class ReportScreen {
    JFrame frame;

    public ReportScreen() {
        frame = new JFrame("Reports");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(5, 1));

        // ✅ Labels for today's report
        JLabel todaySales = new JLabel("Today's Sales: " + DBHelper.getTodaySales(), SwingConstants.CENTER);
        JLabel todayRevenue = new JLabel("Today's Revenue: ₹" + DBHelper.getTodayRevenue(), SwingConstants.CENTER);

        // ✅ Labels for all-time report
        JLabel totalSales = new JLabel("All-time Sales: " + DBHelper.getTotalSales(), SwingConstants.CENTER);
        JLabel totalRevenue = new JLabel("All-time Revenue: ₹" + DBHelper.getTotalRevenue(), SwingConstants.CENTER);

        // ✅ Close button
        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(e -> frame.dispose());

        // Add all components
        frame.add(todaySales);
        frame.add(todayRevenue);
        frame.add(totalSales);
        frame.add(totalRevenue);
        frame.add(closeBtn);

        frame.setVisible(true);
    }
}
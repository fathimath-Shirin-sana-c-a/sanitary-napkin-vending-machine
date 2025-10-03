package admin;

import db.DBHelper;
import javax.swing.*;
import java.awt.*;

public class RefillScreen {
    JFrame frame;

    public RefillScreen() {
        frame = new JFrame("Refill Items");
        frame.setSize(480, 420);
        frame.setLayout(new GridLayout(7, 2, 8, 8));

        // ===== Napkins =====
        JLabel napkinLabel = new JLabel("Napkin Stock: " + DBHelper.getNapkinStock());
        JButton addNapkins = new JButton("Add Napkins");
        addNapkins.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter number of napkins to add:");
            if (input != null && input.matches("\\d+")) {
                int added = Integer.parseInt(input);
                DBHelper.updateNapkinStock(DBHelper.getNapkinStock() + added);
                DBHelper.logNapkinRefill();
                napkinLabel.setText("Napkin Stock: " + DBHelper.getNapkinStock());
                JOptionPane.showMessageDialog(frame, "Added " + added + " napkins.");
            }
        });

        // ===== Coins helper =====
        // creates a row (label + button) for a denomination
        JPanel[] holders = new JPanel[0]; // not used, just to keep code simple

        JLabel oneLbl = new JLabel("₹1 Coins: "   + DBHelper.getCoinQuantity(1));
        JButton oneBtn = new JButton("Add ₹1");
        oneBtn.addActionListener(e -> addCoins(1, oneLbl));

        JLabel twoLbl = new JLabel("₹2 Coins: "   + DBHelper.getCoinQuantity(2));
        JButton twoBtn = new JButton("Add ₹2");
        twoBtn.addActionListener(e -> addCoins(2, twoLbl));

        JLabel fiveLbl = new JLabel("₹5 Coins: "  + DBHelper.getCoinQuantity(5));
        JButton fiveBtn = new JButton("Add ₹5");
        fiveBtn.addActionListener(e -> addCoins(5, fiveLbl));

        JLabel tenLbl = new JLabel("₹10 Coins: "  + DBHelper.getCoinQuantity(10));
        JButton tenBtn = new JButton("Add ₹10");
        tenBtn.addActionListener(e -> addCoins(10, tenLbl));

        JLabel twentyLbl = new JLabel("₹20 Coins: " + DBHelper.getCoinQuantity(20));
        JButton twentyBtn = new JButton("Add ₹20");
        twentyBtn.addActionListener(e -> addCoins(20, twentyLbl));

        // add components to grid (7 rows × 2 cols)
        frame.add(napkinLabel); frame.add(addNapkins);
        frame.add(oneLbl);      frame.add(oneBtn);
        frame.add(twoLbl);      frame.add(twoBtn);
        frame.add(fiveLbl);     frame.add(fiveBtn);
        frame.add(tenLbl);      frame.add(tenBtn);
        frame.add(twentyLbl);   frame.add(twentyBtn);

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.addActionListener(e -> { new AdminPanel(); frame.dispose(); });
        frame.add(backBtn);
        frame.add(new JLabel("")); // filler cell

        frame.setVisible(true);
    }

    private void addCoins(int denom, JLabel labelToUpdate) {
        String qtyStr = JOptionPane.showInputDialog(null,
                "Enter quantity to add for ₹" + denom + " coins:");
        if (qtyStr != null && qtyStr.matches("\\d+")) {
            int qty = Integer.parseInt(qtyStr);
            DBHelper.incrementCoinQuantity(denom, qty);
            DBHelper.logCoinRefill();
            labelToUpdate.setText("₹" + denom + " Coins: " + DBHelper.getCoinQuantity(denom));
            JOptionPane.showMessageDialog(null, "Refilled " + qty + " coins of ₹" + denom);
        }
    }
}
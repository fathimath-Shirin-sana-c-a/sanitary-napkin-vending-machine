package admin;
import db.DBHelper;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;



public class RefillScreen {
    JFrame frame;

    public RefillScreen() {
        frame = new JFrame("Refill Screen");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(0, 1));

        // ✅ Napkin Refill
        JLabel napkinLabel = new JLabel("Napkin Stock: " + DBHelper.getNapkinStock());
        JButton addNapkins = new JButton("Add Napkins");
        addNapkins.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter number of napkins to add:");
            if (input != null) {
                int added = Integer.parseInt(input);
                int newStock = DBHelper.getNapkinStock() + added;
                DBHelper.updateNapkinStock(newStock);
                DBHelper.logNapkinRefill();
                napkinLabel.setText("Napkin Stock: " + newStock);
            }
        });
        frame.add(napkinLabel);
        frame.add(addNapkins);

        // ✅ Coin Refill
        JButton coinRefillBtn = new JButton("Refill Coins");
        coinRefillBtn.addActionListener(e -> {
            String denomStr = JOptionPane.showInputDialog("Enter denomination (1,2,5,10,20):");
            String qtyStr = JOptionPane.showInputDialog("Enter quantity to add:");
            if (denomStr != null && qtyStr != null) {
                int denom = Integer.parseInt(denomStr);
                int qty = Integer.parseInt(qtyStr);
                int newQty = DBHelper.getCoinStock(denom) + qty;
                DBHelper.updateCoinStock(denom, newQty);
                DBHelper.logCoinRefill();
                JOptionPane.showMessageDialog(frame,
                        "Refilled " + qty + " coins of ₹" + denom + "\nNew stock: " + newQty);
            }
        });
        frame.add(coinRefillBtn);

        frame.setVisible(true);
    }
}
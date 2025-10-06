package admin;

import db.DBHelper;
import ui.Theme;

import javax.swing.*;
import java.awt.*;

public class RefillScreen {
    JFrame frame;

    public RefillScreen() {

        frame = new JFrame("Refill Items");
        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(7, 2, 10, 10));
        Theme.applyFrame(frame);


        JLabel napkinLabel = new JLabel("Napkin Stock: " + DBHelper.getNapkinStock());
        Theme.applyLabel(napkinLabel);

        JButton addNapkins = Theme.createButton("Add Napkins");
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

        //  Coins section
        JLabel oneLbl = new JLabel("₹1 Coins: " + DBHelper.getCoinQuantity(1));
        Theme.applyLabel(oneLbl);
        JButton oneBtn = Theme.createButton("Add ₹1");
        oneBtn.addActionListener(e -> addCoins(1, oneLbl));

        JLabel twoLbl = new JLabel("₹2 Coins: " + DBHelper.getCoinQuantity(2));
        Theme.applyLabel(twoLbl);
        JButton twoBtn = Theme.createButton("Add ₹2");
        twoBtn.addActionListener(e -> addCoins(2, twoLbl));

        JLabel fiveLbl = new JLabel("₹5 Coins: " + DBHelper.getCoinQuantity(5));
        Theme.applyLabel(fiveLbl);
        JButton fiveBtn = Theme.createButton("Add ₹5");
        fiveBtn.addActionListener(e -> addCoins(5, fiveLbl));

        JLabel tenLbl = new JLabel("₹10 Coins: " + DBHelper.getCoinQuantity(10));
        Theme.applyLabel(tenLbl);
        JButton tenBtn = Theme.createButton("Add ₹10");
        tenBtn.addActionListener(e -> addCoins(10, tenLbl));

        JLabel twentyLbl = new JLabel("₹20 Coins: " + DBHelper.getCoinQuantity(20));
        Theme.applyLabel(twentyLbl);
        JButton twentyBtn = Theme.createButton("Add ₹20");
        twentyBtn.addActionListener(e -> addCoins(20, twentyLbl));


        frame.add(napkinLabel); frame.add(addNapkins);
        frame.add(oneLbl); frame.add(oneBtn);
        frame.add(twoLbl); frame.add(twoBtn);
        frame.add(fiveLbl); frame.add(fiveBtn);
        frame.add(tenLbl); frame.add(tenBtn);
        frame.add(twentyLbl); frame.add(twentyBtn);


        JButton backBtn = Theme.createButton("Back to Admin Panel");
        backBtn.addActionListener(e -> { new AdminPanel(); frame.dispose(); });
        frame.add(backBtn);
        frame.add(new JLabel(""));

        frame.setVisible(true);
    }

    private void addCoins(int denom, JLabel labelToUpdate) {
        String qtyStr = JOptionPane.showInputDialog(frame,
                "Enter quantity to add for ₹" + denom + " coins:");
        if (qtyStr != null && qtyStr.matches("\\d+")) {
            int qty = Integer.parseInt(qtyStr);
            DBHelper.incrementCoinQuantity(denom, qty);
            DBHelper.logCoinRefill();
            labelToUpdate.setText("₹" + denom + " Coins: " + DBHelper.getCoinQuantity(denom));
            JOptionPane.showMessageDialog(frame, "Refilled " + qty + " coins of ₹" + denom);
        }
    }

    public static void main(String[] args) {
        new RefillScreen();
    }
}
package user;

import db.DBHelper;
import admin.AdminLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserPanel {
    JFrame frame;
    JLabel title, totalLabel;
    int total = 0;

    //  Theme colors
    private final Color BG = new Color(15, 15, 15);  // soft black
    private final Color FG = Color.WHITE;
    private final Color BTN_BG = Color.WHITE;
    private final Color BTN_FG = Color.BLACK;
    private final Color BTN_HOVER = new Color(220, 220, 220);

    //  Fonts
    private final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 40);
    private final Font FONT_LABEL = new Font("Inter", Font.PLAIN, 16);
    private final Font FONT_BUTTON = new Font("Alta", Font.BOLD, 15);

    public UserPanel() {
        //  Frame Setup
        frame = new JFrame("Sanitary Napkin Vending Machine");
        frame.setSize(520, 340);
        frame.setLayout(new BorderLayout(15, 15));
        frame.getContentPane().setBackground(BG);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        title = new JLabel("Welcome! Insert Cash to Buy Napkin (₹5)", SwingConstants.CENTER);
        title.setFont(FONT_TITLE);
        title.setForeground(FG);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        frame.add(title, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(BG);

        JPanel coinPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        coinPanel.setBackground(BG);
        JButton btn1 = createSquareButton("₹1");
        JButton btn2 = createSquareButton("₹2");
        JButton btn5 = createSquareButton("₹5");
        JButton btn10 = createSquareButton("₹10");
        JButton btn20 = createSquareButton("₹20");
        coinPanel.add(btn1); coinPanel.add(btn2); coinPanel.add(btn5); coinPanel.add(btn10); coinPanel.add(btn20);

        totalLabel = new JLabel("Total Inserted: ₹0", SwingConstants.CENTER);
        totalLabel.setFont(FONT_LABEL);
        totalLabel.setForeground(FG);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(coinPanel);
        centerPanel.add(totalLabel);
        centerPanel.add(Box.createVerticalGlue());
        frame.add(centerPanel, BorderLayout.CENTER);


        btn1.addActionListener(e -> updateTotalAndDB(1));
        btn2.addActionListener(e -> updateTotalAndDB(2));
        btn5.addActionListener(e -> updateTotalAndDB(5));
        btn10.addActionListener(e -> updateTotalAndDB(10));
        btn20.addActionListener(e -> updateTotalAndDB(20));


        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 10));
        bottomPanel.setBackground(BG);
        OvalButton getNapkinBtn = new OvalButton("Get Napkin");
        OvalButton adminBtn = new OvalButton("Admin Login");
        bottomPanel.add(getNapkinBtn);
        bottomPanel.add(adminBtn);

        JPanel wrapper = new JPanel();
        wrapper.setBackground(BG);
        wrapper.add(bottomPanel);
        frame.add(wrapper, BorderLayout.SOUTH);


        getNapkinBtn.addActionListener(e -> getNapkin());
        adminBtn.addActionListener(e -> new AdminLogin());

        frame.setVisible(true);
    }

    private void updateTotalAndDB(int amount) {
        total += amount;
        totalLabel.setText("Total Inserted: ₹" + total);
        DBHelper.insertCoin(amount);
    }

    private void getNapkin() {
        int stock = DBHelper.getNapkinStock();
        if (stock > 0 && total >= 5) {
            int change = total - 5;
            if (canReturnChange(change)) {
                showMessage("Napkin Dispensed! Returning ₹" + change + " as change.");
                DBHelper.updateNapkinStock(stock - 1);
                DBHelper.insertTransaction(1, 5);
                total = 0;
                totalLabel.setText("Total Inserted: ₹" + total);
            } else if (change == 0) {
                showMessage("Napkin Dispensed!");
                DBHelper.updateNapkinStock(stock - 1);
                DBHelper.insertTransaction(1, 5);
                total = 0;
                totalLabel.setText("Total Inserted: ₹" + total);
            } else {
                showMessage("Please insert exact amount. Cannot return ₹" + change + " change.");
            }
        } else if (stock <= 0) {
            showMessage("Out of stock! Please contact admin.");
        } else {
            showMessage("Insert at least ₹5 to get a napkin.");
        }
    }

    private void showMessage(String msg) {
        UIManager.put("OptionPane.background", BG);
        UIManager.put("Panel.background", BG);
        UIManager.put("OptionPane.messageForeground", FG);
        UIManager.put("Button.background", BTN_BG);
        UIManager.put("Button.foreground", BTN_FG);
        UIManager.put("Button.font", FONT_BUTTON);
        JOptionPane.showMessageDialog(frame, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean canReturnChange(int change) {
        int[] denoms = {20, 10, 5, 2, 1};
        for (int d : denoms) {
            while (change >= d && DBHelper.getCoinQuantity(d) > 0) {
                if (DBHelper.deductCoin(d, 1)) {
                    change -= d;
                }
            }
        }
        return change == 0;
    }

    //  Oval Button (rounded corners)
    class OvalButton extends JButton {
        public OvalButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBackground(BTN_BG);
            setForeground(BTN_FG);
            setFont(FONT_BUTTON);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addHoverEffect(this);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            super.paintComponent(g2);
        }

        @Override
        protected void paintBorder(Graphics g) {
            g.setColor(getBackground());
            g.drawRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
        }
    }

    //  Square coin button
    private JButton createSquareButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(FONT_BUTTON);
        btn.setBackground(BTN_BG);
        btn.setForeground(BTN_FG);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addHoverEffect(btn);
        return btn;
    }

    //  Hover animation
    private void addHoverEffect(JButton btn) {
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(BTN_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                btn.setBackground(BTN_BG);
            }
        });
    }

    public static void main(String[] args) {
        new UserPanel();
    }
}
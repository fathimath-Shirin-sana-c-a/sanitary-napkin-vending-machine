import javax.swing.*;

public class UserPanel {
    JFrame frame;
    JLabel title;

    public UserPanel() {
        frame = new JFrame("Sanitary Napkin Vending Machine");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.GridLayout(3, 1));

        title = new JLabel("Welcome! Insert Cash to Buy Napkin (₹5)", SwingConstants.CENTER);
        frame.add(title);

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
        JLabel totalLabel = new JLabel("Total Inserted: ₹0", SwingConstants.CENTER);
        frame.add(totalLabel);

        final int[] total = {0};

        btn1.addActionListener(e -> { total[0] += 1; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn2.addActionListener(e -> { total[0] += 2; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn5.addActionListener(e -> { total[0] += 5; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn10.addActionListener(e -> { total[0] += 10; totalLabel.setText("Total Inserted: ₹" + total[0]); });
        btn20.addActionListener(e -> { total[0] += 20; totalLabel.setText("Total Inserted: ₹" + total[0]); });




        frame.setVisible(true);
    }
}
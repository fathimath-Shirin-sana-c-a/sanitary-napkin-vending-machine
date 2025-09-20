package admin;
import db.DBHelper;
...


public class RefillScreen {
    JFrame frame;
    private static int napkinStock = 10;
    private static int coins1 = 10, coins2 = 10, coins5 = 10, coins10 = 10, coins20 = 10;

    public RefillScreen() {
        frame = new JFrame("Refill Items");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(8, 2));
        JLabel napkinLabel = new JLabel("Napkin Stock: " + DBHelper.getNapkinStock());
        JButton addNapkins = new JButton("Add Napkins");
        addNapkins.addActionListener(e -> {
            String input = JOptionPane.showInputDialog("Enter number of napkins to add:");
            if (input != null) {
                int added = Integer.parseInt(input);
                int newStock = DBHelper.getNapkinStock() + added;
                DBHelper.updateNapkinStock(newStock);
                napkinLabel.setText("Napkin Stock: " + newStock);
            }
        });
        
        JLabel oneLabel = new JLabel("₹1 Coins: " + coins1);
        JButton addOne = new JButton("Add ₹1");
        addOne.addActionListener(e -> {
            coins1 += 10;
            oneLabel.setText("₹1 Coins: " + coins1);
        });

        JLabel twoLabel = new JLabel("₹2 Coins: " + coins2);
        JButton addTwo = new JButton("Add ₹2");
        addTwo.addActionListener(e -> {
            coins2 += 10;
            twoLabel.setText("₹2 Coins: " + coins2);
        });

        JLabel fiveLabel = new JLabel("₹5 Coins: " + coins5);
        JButton addFive = new JButton("Add ₹5");
        addFive.addActionListener(e -> {
            coins5 += 10;
            fiveLabel.setText("₹5 Coins: " + coins5);
        });

        frame.add(napkinLabel); frame.add(addNapkins);
        frame.add(oneLabel); frame.add(addOne);
        frame.add(twoLabel); frame.add(addTwo);
        frame.add(fiveLabel); frame.add(addFive);

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.addActionListener(e -> {
            new AdminPanel();
            frame.dispose();
        });

        frame.add(backBtn);
        frame.setVisible(true);
    }
}
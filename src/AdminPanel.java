import javax.swing.*;
import java.awt.*;

public class AdminPanel {
    JFrame frame;

    public AdminPanel() {
        frame = new JFrame("Admin Panel");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JButton refillBtn = new JButton("Refill Items");
        refillBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Refill screen not ready yet."));
        frame.add(refillBtn);

        JButton reportBtn = new JButton("View Reports");
        reportBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Report screen not ready yet."));
        frame.add(reportBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Logged out!");
            new UserPanel(); // back to home
            frame.dispose();
        });
        frame.add(logoutBtn);

        frame.setVisible(true);
    }
}
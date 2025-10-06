package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

    public class Theme {

        // Colors
        public static final Color BG = new Color(15, 15, 15);   // soft black
        public static final Color FG = Color.WHITE;
        public static final Color BTN_BG = Color.WHITE;
        public static final Color BTN_FG = Color.BLACK;
        public static final Color BTN_HOVER = new Color(220, 220, 220);

        //  Fonts (you can change globally here)
        public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 32);
        public static final Font FONT_LABEL = new Font("Inter", Font.PLAIN, 16);
        public static final Font FONT_BUTTON = new Font("Inter", Font.BOLD, 15);

        // 🪄 Frame background
        public static void applyFrame(JFrame frame) {
            frame.getContentPane().setBackground(BG);
        }

        // 🪄 Label
        public static void applyLabel(JLabel label) {
            label.setForeground(FG);
            label.setFont(FONT_LABEL);
        }

        //  Buttons
        public static JButton createButton(String text) {
            JButton btn = new JButton(text);
            btn.setBackground(BTN_BG);
            btn.setForeground(BTN_FG);
            btn.setFont(FONT_BUTTON);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            addHover(btn);
            return btn;
        }

        //  Hover effect
        private static void addHover(JButton btn) {
            btn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { btn.setBackground(BTN_HOVER); }
                public void mouseExited(MouseEvent e) { btn.setBackground(BTN_BG); }
            });
        }

        //  Option pane (JOptionPane)
        public static void applyDialogTheme() {
            UIManager.put("OptionPane.background", BG);
            UIManager.put("Panel.background", BG);
            UIManager.put("OptionPane.messageForeground", FG);
            UIManager.put("Button.background", BTN_BG);
            UIManager.put("Button.foreground", BTN_FG);
            UIManager.put("Button.font", FONT_BUTTON);
        }
    }


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeTrackerUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            showTimeTrackerDialog(null);
        });
    }

    public static void showTimeTrackerDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Time Tracker", true);
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5)); // 0 rows, 2 columns
        dialog.add(panel);

        JLabel instructionLabel = new JLabel("ONLY INPUT IN HH:MM FORMAT!!");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(instructionLabel);
        panel.add(new JPanel()); // Empty panel for spacing

        JTextField[] loginFields = new JTextField[7];
        JTextField[] logoutFields = new JTextField[7];

        for (int day = 0; day < 7; day++) {
            panel.add(new JLabel("Day " + (day + 1) + " Login:"));
            loginFields[day] = createFormattedTextField();
            panel.add(loginFields[day]);

            panel.add(new JLabel("Logout:"));
            logoutFields[day] = createFormattedTextField();
            panel.add(logoutFields[day]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(e -> {
            @SuppressWarnings("unused")
            double weeklyHours = TimeTracker.weekHoursTotal(loginFields, logoutFields);
      });
        panel.add(calculateButton);

        dialog.pack();
        dialog.setVisible(true);
    }

    private static JTextField createFormattedTextField() {
        JTextField textField = new JTextField();
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String text = textField.getText();
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == ':' || (text.isEmpty() && (c == 'N' || c == 'A')) ||
                        (text.equalsIgnoreCase("N") && c == 'A'))) {
                    e.consume(); // Ignore the input
                    JOptionPane.showMessageDialog(null, "Please enter only numbers or \":\", or \"NA\" if absent.");
                }
            }
        });
        return textField;
    }
}

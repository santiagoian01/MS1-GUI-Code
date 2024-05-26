import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dashboard {
    public static void openDashboard(String eid) {
        JFrame dashboardFrame = new JFrame();
        dashboardFrame.setTitle("Dashboard");
        dashboardFrame.setSize(600, 300);
        dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dashboardFrame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout());
        dashboardFrame.add(mainPanel, BorderLayout.CENTER);

        // Date and Time Panel
        JPanel dateTimePanel = new JPanel(new GridLayout(2, 1));
        mainPanel.add(dateTimePanel, BorderLayout.CENTER);

        JLabel dateLabel = new JLabel();
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        dateTimePanel.add(dateLabel);

        JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        dateTimePanel.add(timeLabel);

        // Update date and time label every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                Date now = new Date();
                dateLabel.setText(dateFormat.format(now));
                timeLabel.setText(timeFormat.format(now));
            }
        });
        timer.start();

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for profile button
        JButton profileButton = new JButton("Employee Profile");
        profileButton.setPreferredSize(new Dimension(150, 50)); // Set preferred button size
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.openProfile(eid);
            }
        });
        profilePanel.add(profileButton);
        buttonPanel.add(profilePanel, BorderLayout.NORTH);

        JPanel timeTrackerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel for time tracker button
        JButton timetrackerButton = new JButton("Time Tracker");
        timetrackerButton.setPreferredSize(new Dimension(150, 50)); // Set preferred button size
        timetrackerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeTrackerUI.main(null); // Call to the time tracker UI
            }
        });
        timeTrackerPanel.add(timetrackerButton);
        buttonPanel.add(timeTrackerPanel, BorderLayout.SOUTH);

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome, " + getEmployeeName(eid) + "!");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 26)); // Set font and size
        dashboardFrame.add(welcomeLabel, BorderLayout.NORTH);

        // Center the frame on screen
        dashboardFrame.setLocationRelativeTo(null);
        dashboardFrame.setVisible(true);
    }

    private static String getEmployeeName(String eid) {
        String csvFile = "lib\\Employee_Database.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equals(eid)) {
                    return data[3] + " " + data[2];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Unknown Employee";
    }
}

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard {
    public static void openDashboard(String eid) {
        JPanel dashboardPanel = new JPanel();
        JFrame dashboardFrame = new JFrame();
        dashboardFrame.setTitle("Dashboard");
        dashboardFrame.setSize(450, 200);
        dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dashboardFrame.setVisible(true);

        dashboardPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome " + eid + "!");
        welcomeLabel.setBounds(10, 15, 400, 25);
        dashboardPanel.add(welcomeLabel);

        JButton profileButton = new JButton();
        profileButton.setBounds(10, 80, 200, 25);
        profileButton.setText("Employee Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Profile.openProfile(eid);
            }
        });

        JButton timetrackerButton = new JButton();
        timetrackerButton.setBounds(220, 80, 200, 25);
        timetrackerButton.setText("Time Tracker");
        timetrackerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeTrackerUI.main(null); // Pang call sa timetracker UI
            }
        });

        dashboardFrame.add(dashboardPanel);
        dashboardPanel.add(profileButton);
        dashboardPanel.add(timetrackerButton);
    }

}

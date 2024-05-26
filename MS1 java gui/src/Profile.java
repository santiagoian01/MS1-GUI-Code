import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Profile {
    public static void openProfile(String eid) {
        JFrame profileFrame = new JFrame();
        profileFrame.setTitle("Employee Profile");
        profileFrame.setSize(500, 600);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profileFrame.add(profilePanel);

        JLabel profileLabel = new JLabel("EMPLOYEE ID: " + eid);
        profileLabel.setBounds(10, 15, 200, 25);
        profilePanel.add(profileLabel);

        // Read employee profile information
        String[] profileInfo = getEmployeeProfile(eid);

        // Labels for profile information
        String[] labels = {"Last Name", "First Name", "Birthday", "Address", "Phone Number",
                            "SSS #", "Philhealth #", "TIN #", "Pag-ibig #", "Status", "Position",
                            "Immediate Supervisor", "Basic Salary", "Rice Subsidy", "Phone Allowance",
                            "Clothing Allowance", "Gross Semi-monthly Rate", "Hourly Rate"};
        int[] indices = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

        int yCoordinate = 45;

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i] + ": " + profileInfo[indices[i]]);
            label.setBounds(10, yCoordinate, 400, 20);
            profilePanel.add(label);
            yCoordinate += 30; // Increment y-coordinate
        }

        profileFrame.setLocationRelativeTo(null);
        profileFrame.setVisible(true);
    }

    private static String[] getEmployeeProfile(String eid) {
        String[] profileInfo = new String[21]; // Adjusted for the provided indices
        String csvFile = "lib\\Employee_Database.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] profileRecord = line.split(cvsSplitBy);
                if (profileRecord[0].equals(eid)) {
                    System.arraycopy(profileRecord, 1, profileInfo, 1, Math.min(profileRecord.length - 1, profileInfo.length - 1));
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return profileInfo;
    }
}

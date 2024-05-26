import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.*;

public class GUI implements ActionListener {

    private static JLabel eidLabel;
    private static JTextField eidTextField;
    private static JLabel passLabel;
    private static JPasswordField passTextField;
    private static JButton logButton;
    private static JButton backButton;
    private static JLabel successloginJLabel;
    private static JFrame frame;
    private boolean isHRLogin = false;

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.showInitialChoice();
    }

    public void showInitialChoice() {
        if (frame != null) {
            frame.dispose();
        }
        
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("MotorPH Portal");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton empChoiceButton = new JButton("Log in as Employee");
        empChoiceButton.setFont(new Font("Arial", Font.PLAIN, 16));
        empChoiceButton.addActionListener(e -> showLoginScreen(false));
        panel.add(empChoiceButton);

        JButton hrChoiceButton = new JButton("Log in as HR");
        hrChoiceButton.setFont(new Font("Arial", Font.PLAIN, 16));
        hrChoiceButton.addActionListener(e -> showLoginScreen(true));
        panel.add(hrChoiceButton);
    }

    public void showLoginScreen(boolean isHR) {
        isHRLogin = isHR;
        frame.dispose();
        
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle(isHR ? "HR Login" : "Employee Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // EID Label and textbox
        eidLabel = new JLabel("ID");
        panel.add(eidLabel);

        eidTextField = new JTextField(20);
        panel.add(eidTextField);

        // Password Label
        passLabel = new JLabel("Password");
        panel.add(passLabel);
        
        // Password display (****)
        passTextField = new JPasswordField(20);
        panel.add(passTextField);

        // Log in Button
        logButton = new JButton("Log In");
        logButton.addActionListener(this);
        panel.add(logButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> showInitialChoice());
        panel.add(backButton);

        successloginJLabel = new JLabel("");
        successloginJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(successloginJLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logButton) {
            String user = eidTextField.getText();
            char[] charpassword = passTextField.getPassword();
            String password = new String(charpassword); // Converts char to string

            boolean authenticated = authenticate(user, password, isHRLogin);
            if (authenticated) {
                frame.dispose();
                if (isHRLogin) {
                    successloginJLabel.setText("HR Log in Success");
                    HRDashboard.openHRDashboard(user);
                } else {
                    successloginJLabel.setText("Employee Log in Success");
                    Dashboard.openDashboard(user); 
                }
            } else {
                successloginJLabel.setText("Invalid ID or password!");
            }
        }
    }

    private boolean authenticate(String username, String password, boolean isHR) {
        String csvFile = isHR ? "lib/HR Details.csv" : "lib/Employee_Database.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] userRecord = line.split(cvsSplitBy);
                String storedUsername = userRecord[0];
                String storedPassword = userRecord[1];
                if (storedUsername.equals(username) && storedPassword.equals(password)) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false; // Invalid Auth
    }
}

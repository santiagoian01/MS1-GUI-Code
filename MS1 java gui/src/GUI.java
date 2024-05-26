import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        JButton empChoiceButton = new JButton("Log in as Employee");
        empChoiceButton.setBounds(50, 50, 200, 25);
        empChoiceButton.addActionListener(e -> showLoginScreen(false));
        panel.add(empChoiceButton);

        JButton hrChoiceButton = new JButton("Log in as HR");
        hrChoiceButton.setBounds(50, 100, 200, 25);
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
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        // EID Label and textbox
        eidLabel = new JLabel("ID");
        eidLabel.setBounds(10, 20, 80, 25);
        panel.add(eidLabel);

        eidTextField = new JTextField(20);
        eidTextField.setBounds(100, 20, 165, 25);
        panel.add(eidTextField);

        // password Label
        passLabel = new JLabel("PASSWORD");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);
        
        // password display (****)
        passTextField = new JPasswordField(20);
        passTextField.setBounds(100, 50, 165, 25);
        panel.add(passTextField);

        // Log in Button
        logButton = new JButton();
        logButton.setBounds(10, 80, 100, 25);
        logButton.setText("LOG IN");
        logButton.addActionListener(this);
        panel.add(logButton);

        // Back Button
        backButton = new JButton();
        backButton.setBounds(120, 80, 100, 25);
        backButton.setText("BACK");
        backButton.addActionListener(e -> showInitialChoice());
        panel.add(backButton);

        successloginJLabel = new JLabel("");
        successloginJLabel.setBounds(10, 110, 300, 25);
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
                if (isHRLogin) {
                    successloginJLabel.setText("HR Log in Success");
                    HRDashboard.openHRDashboard(user);
                } else {
                    successloginJLabel.setText("Employee Log in Success");
                    Dashboard.openDashboard(user); 
                }
            } else {
                successloginJLabel.setText("Invalid password!");
            }
        }
    }

    private boolean authenticate(String username, String password, boolean isHR) {
        String csvFile = isHR ? "MS1 java gui\\lib\\HR Details.csv" : "MS1 java gui\\lib\\Employee information - Employee Details.csv";
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

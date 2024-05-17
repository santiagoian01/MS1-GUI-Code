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



//Pa view ng lib-> username and password.csv to test yung username and passwords
public class GUI implements ActionListener {

    private static JLabel eidLabel;
    private static JTextField eidTextField;
    private static JLabel passLabel;
    private static JPasswordField passTextField;
    private static JButton logButton;
    private static JLabel successloginJLabel;
    
    public static void main(String[] args){

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setTitle("MotorPH Employee Portal");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        // EID Label and textbox
        eidLabel = new JLabel("EMPLOYEE ID");
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

        //Log in Button
        logButton = new JButton();
        logButton.setBounds(10, 80, 80, 25);
        logButton.setText("LOG IN");
        logButton.addActionListener(new GUI());
        panel.add(logButton);

        successloginJLabel = new JLabel("");
        successloginJLabel.setBounds(10, 110, 300, 25);
        panel.add(successloginJLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = eidTextField.getText();
        char[] charpassword = passTextField.getPassword();
        String password = new String(charpassword); // Converts char to string

        boolean authenticated = authenticate(user, password);
        if (authenticated) {
            successloginJLabel.setText("Log in Success"); 
            Dashboard.openDashboard(user); // Pass EID to openDashboard
        } else {
            successloginJLabel.setText("Invalid password!");
            // Invalid Auth
        }
    }

    private boolean authenticate(String username, String password) {
        String csvFile = "lib\\username and password.csv";
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
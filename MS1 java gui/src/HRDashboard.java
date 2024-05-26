import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class HRDashboard {
    public static void openHRDashboard(String eid) {
        JPanel dashboardPanel = new JPanel();
        JFrame dashboardFrame = new JFrame();
        dashboardFrame.setTitle("HR Dashboard");
        dashboardFrame.setSize(800, 600);
        dashboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dashboardFrame.setVisible(true);

        dashboardPanel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome " + eid + " to HR Dashboard!");
        welcomeLabel.setBounds(10, 15, 400, 25);
        dashboardPanel.add(welcomeLabel);

        JButton viewEmployeesButton = new JButton();
        viewEmployeesButton.setBounds(10, 50, 200, 25);
        viewEmployeesButton.setText("View All Employees");
        viewEmployeesButton.addActionListener(e -> displayAllEmployees());
        dashboardPanel.add(viewEmployeesButton);

        JButton addEmployeeButton = new JButton();
        addEmployeeButton.setBounds(220, 50, 200, 25);
        addEmployeeButton.setText("Add Employee");
        addEmployeeButton.addActionListener(e -> {
            addEmployee();
        });
        dashboardPanel.add(addEmployeeButton);

        JButton deleteEmployeeButton = new JButton();
        deleteEmployeeButton.setBounds(430, 50, 200, 25);
        deleteEmployeeButton.setText("Delete Employee");
        deleteEmployeeButton.addActionListener(e -> {
            deleteEmployee();// Code to delete an employee record
        });
        dashboardPanel.add(deleteEmployeeButton);

        dashboardFrame.add(dashboardPanel);
    }

    private static void displayAllEmployees() {
        SwingUtilities.invokeLater(() -> {
            JFrame employeesFrame = new JFrame("All Employees");
            JPanel employeesPanel = new JPanel(new BorderLayout());
            employeesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
            String[] columnNames = {"Employee ID", "Last Name", "First Name", "Birthday", "Address", "Phone Number",
                    "SSS#", "Philhealth#", "TIN#", "PagIbig#", "Status", "Position", "Immediate Supervisor",
                    "Basic Salary", "Rice Subsidy", "Phone Allowance", "Clothing Allowance",
                    "Gross Semi-Monthly Rate", "Hourly Rate"};
    
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);
    
            String csvFile = "MS1 java gui\\lib\\Employee information - Employee Details.csv";
            String line;
            String cvsSplitBy = ",";
    
            try {
                BufferedReader br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    String[] employeeData = line.split(cvsSplitBy);
                    // Skip adding the second column (password)
                    Object[] rowData = new Object[employeeData.length - 1];
                    for (int i = 0, j = 0; i < employeeData.length; i++) {
                        if (i != 1) { // Skip the second column
                            rowData[j++] = employeeData[i];
                        }
                    }
                    model.addRow(rowData);
                }
                br.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
    
            JScrollPane scrollPane = new JScrollPane(table);
            employeesPanel.add(scrollPane, BorderLayout.CENTER);
    
            employeesFrame.getContentPane().add(employeesPanel);
            employeesFrame.setSize(800, 600);
            employeesFrame.setVisible(true);
        });
    }
    
        private static void addEmployee() {
        JFrame addEmployeeFrame = new JFrame("Add Employee");
        JPanel addEmployeePanel = new JPanel(new GridLayout(0, 2));
        addEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel employeeIDLabel = new JLabel("Employee ID:");
        JTextField employeeIDField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField();
        JLabel birthdayLabel = new JLabel("Birthday:");
        JTextField birthdayField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        JTextField phoneNumberField = new JTextField();
        JLabel sssLabel = new JLabel("SSS#:");
        JTextField sssField = new JTextField();
        JLabel philhealthLabel = new JLabel("Philhealth#:");
        JTextField philhealthField = new JTextField();
        JLabel tinLabel = new JLabel("TIN#:");
        JTextField tinField = new JTextField();
        JLabel pagIbigLabel = new JLabel("PagIbig#:");
        JTextField pagIbigField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        JTextField statusField = new JTextField();
        JLabel positionLabel = new JLabel("Position:");
        JTextField positionField = new JTextField();
        JLabel immediateSupervisorLabel = new JLabel("Immediate Supervisor:");
        JTextField immediateSupervisorField = new JTextField();
        JLabel basicSalaryLabel = new JLabel("Basic Salary:");
        JTextField basicSalaryField = new JTextField();
        JLabel riceSubsidyLabel = new JLabel("Rice Subsidy:");
        JTextField riceSubsidyField = new JTextField();
        JLabel phoneAllowanceLabel = new JLabel("Phone Allowance:");
        JTextField phoneAllowanceField = new JTextField();
        JLabel clothingAllowanceLabel = new JLabel("Clothing Allowance:");
        JTextField clothingAllowanceField = new JTextField();
        JLabel grossSemiMonthlyRateLabel = new JLabel("Gross Semi-Monthly Rate:");
        JTextField grossSemiMonthlyRateField = new JTextField();
        JLabel hourlyRateLabel = new JLabel("Hourly Rate:");
        JTextField hourlyRateField = new JTextField();

        addEmployeePanel.add(employeeIDLabel);
        addEmployeePanel.add(employeeIDField);
        addEmployeePanel.add(passwordLabel);
        addEmployeePanel.add(passwordField);
        addEmployeePanel.add(lastNameLabel);
        addEmployeePanel.add(lastNameField);
        addEmployeePanel.add(firstNameLabel);
        addEmployeePanel.add(firstNameField);
        addEmployeePanel.add(birthdayLabel);
        addEmployeePanel.add(birthdayField);
        addEmployeePanel.add(addressLabel);
        addEmployeePanel.add(addressField);
        addEmployeePanel.add(phoneNumberLabel);
        addEmployeePanel.add(phoneNumberField);
        addEmployeePanel.add(sssLabel);
        addEmployeePanel.add(sssField);
        addEmployeePanel.add(philhealthLabel);
        addEmployeePanel.add(philhealthField);
        addEmployeePanel.add(tinLabel);
        addEmployeePanel.add(tinField);
        addEmployeePanel.add(pagIbigLabel);
        addEmployeePanel.add(pagIbigField);
        addEmployeePanel.add(statusLabel);
        addEmployeePanel.add(statusField);
        addEmployeePanel.add(positionLabel);
        addEmployeePanel.add(positionField);
        addEmployeePanel.add(immediateSupervisorLabel);
        addEmployeePanel.add(immediateSupervisorField);
        addEmployeePanel.add(basicSalaryLabel);
        addEmployeePanel.add(basicSalaryField);
        addEmployeePanel.add(riceSubsidyLabel);
        addEmployeePanel.add(riceSubsidyField);
        addEmployeePanel.add(phoneAllowanceLabel);
        addEmployeePanel.add(phoneAllowanceField);
        addEmployeePanel.add(clothingAllowanceLabel);
        addEmployeePanel.add(clothingAllowanceField);
        addEmployeePanel.add(grossSemiMonthlyRateLabel);
        addEmployeePanel.add(grossSemiMonthlyRateField);
        addEmployeePanel.add(hourlyRateLabel);
        addEmployeePanel.add(hourlyRateField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the employee record
                String employeeID = employeeIDField.getText();
                String password = new String(passwordField.getPassword());
                String lastName = lastNameField.getText();
                String firstName = firstNameField.getText();
                String birthday = birthdayField.getText();
                String address = addressField.getText();
                String phoneNumber = phoneNumberField.getText();
                String sss = sssField.getText();
                String philhealth = philhealthField.getText();
                String tin = tinField.getText();
                String pagIbig = pagIbigField.getText();
                String status = statusField.getText();
                String position = positionField.getText();
                String immediateSupervisor = immediateSupervisorField.getText();
                String basicSalary = basicSalaryField.getText();
                String riceSubsidy = riceSubsidyField.getText();
                String phoneAllowance = phoneAllowanceField.getText();
                String clothingAllowance = clothingAllowanceField.getText();
                String grossSemiMonthlyRate = grossSemiMonthlyRateField.getText();
                String hourlyRate = hourlyRateField.getText();

                // Save the employee record to CSV file
                saveEmployeeRecordToCSV(employeeID, password, lastName, firstName, birthday, address, phoneNumber,
                        sss, philhealth, tin, pagIbig, status, position, immediateSupervisor, basicSalary,
                        riceSubsidy, phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
                // You can add further logic such as validation and feedback to the user here
                addEmployeeFrame.dispose();
            }
        });
        addEmployeePanel.add(addButton);

        addEmployeeFrame.getContentPane().add(addEmployeePanel);
        addEmployeeFrame.setSize(400, 600);
        addEmployeeFrame.setVisible(true);
    }

    private static void saveEmployeeRecordToCSV(String... employeeData) {
        String csvFile = "MS1 java gui\\lib\\Employee information - Employee Details.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile, true))) {
            StringBuilder sb = new StringBuilder();
            for (String data : employeeData) {
                sb.append(data).append(",");
            }
            // Remove the last comma and append a newline
            sb.deleteCharAt(sb.length() - 1).append("\n");
            writer.write(sb.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static void deleteEmployee() {
       JFrame deleteEmployeeFrame = new JFrame("Delete Employee");
        deleteEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel deleteEmployeePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Delete Employee");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        deleteEmployeePanel.add(titleLabel, constraints);

        constraints.gridy++;
        JLabel employeeIDLabel = new JLabel("Employee ID:");
        deleteEmployeePanel.add(employeeIDLabel, constraints);

        constraints.gridx++;
        JTextField employeeIDField = new JTextField(15);
        deleteEmployeePanel.add(employeeIDField, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeID = employeeIDField.getText();
                if (!employeeID.isEmpty()) {
                    deleteEmployeeRecord(employeeID);
                    JOptionPane.showMessageDialog(deleteEmployeeFrame, "Employee record deleted successfully!");
                    deleteEmployeeFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(deleteEmployeeFrame, "Please enter the Employee ID!");
                }
            }
        });
        deleteEmployeePanel.add(deleteButton, constraints);

        deleteEmployeeFrame.getContentPane().add(deleteEmployeePanel);
        deleteEmployeeFrame.pack();
        deleteEmployeeFrame.setLocationRelativeTo(null); // Center the frame on the screen
        deleteEmployeeFrame.setVisible(true);
    }

    private static void deleteEmployeeRecord(String employeeID) {
        String csvFile = "MS1 java gui\\lib\\Employee information - Employee Details.csv";
        String tempFile = "MS1 java gui\\lib\\temp.csv";
        File oldFile = new File(csvFile);
        File newFile = new File(tempFile);
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {
            while ((line = br.readLine()) != null) {
                String[] employeeData = line.split(cvsSplitBy);
                if (!employeeData[0].equals(employeeID)) {
                    pw.println(line);
                    pw.flush();
                }
            }
            br.close();
            pw.close();
            oldFile.delete();
            newFile.renameTo(new File(csvFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

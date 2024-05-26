import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalaryCalculator {

    private static final String CSV_FILE_PATH = "lib\\Employee_Database.csv";

    public static double calculateWeeklySalary(String employeeId, double weeklyHoursWorked) {
        double hourlyRate = getHourlyRate(employeeId);
        if (hourlyRate == -1) {
            // Handle the case where the employee ID is not found
            return -1; // or throw an exception, display an error message, etc.
        }
        return hourlyRate * weeklyHoursWorked;
    }

    private static double getHourlyRate(String employeeId) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(employeeId)) {
                    // Assuming hourly rate is in the last column
                    return Double.parseDouble(parts[parts.length - 1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the IO exception appropriately
        }
        return -1; // Return -1 if employee ID not found
    }
}

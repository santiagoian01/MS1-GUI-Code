import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class TimeTracker {

    public static double weekHoursTotal(JTextField[] loginFields, JTextField[] logoutFields) {
        double weeklyHoursWorked = 0;
        int daysWorked = 0; // Track the number of days worked
    
        for (int day = 0; day < 5; day++) { // Iterate over 5 days only
            String timeInString = loginFields[day].getText().trim();
            String timeOutString = logoutFields[day].getText().trim();
    
            // Check for "NA" and skip calculation for absent days
            if (timeInString.equalsIgnoreCase("NA") || timeOutString.equalsIgnoreCase("NA")) {
                continue;
            }
    
            double dailyHoursWorked = calcHoursWorked(timeInString, timeOutString);
            weeklyHoursWorked += dailyHoursWorked;
            daysWorked++; // Increment the number of days worked
        }
    
        // Display total hours worked and the number of days worked
        JOptionPane.showMessageDialog(null, "You worked a total of " + weeklyHoursWorked + " hours over " + daysWorked + " days this week.");
    
        // Display message based on weekly hours
        if (daysWorked < 5) {
            JOptionPane.showMessageDialog(null, "You didn't work a full 5-day week.");
        } else if (weeklyHoursWorked >= 40) {
            JOptionPane.showMessageDialog(null, "Congratulations! You completed your work week.");
        } else {
            JOptionPane.showMessageDialog(null, "You are lacking hours to achieve the 40-hour work week.");
        }
    
        return weeklyHoursWorked;
    }
    

    private static double calcHoursWorked(String timeInString, String timeOutString) {
        // Converter
        int timeInMinutes = timeConverter(timeInString);
        int timeOutMinutes = timeConverter(timeOutString);

        // Shift Start and shift end
        int shiftStart = 7 * 60; // 7:00 AM in minutes
        int shiftEnd = 16 * 60;  // 4:00 PM in minutes

        // Lunch break
        int lunchStart = 12 * 60; // 12:00 PM in minutes
        int lunchEnd = 13 * 60;   // 1:00 PM in minutes

        // If the employee logs in before the shift starts, adjust the login time to the shift start
        if (timeInMinutes < shiftStart) {
            timeInMinutes = shiftStart;
        }

        // If the employee logs out after the shift ends, adjust the logout time to the shift end
        if (timeOutMinutes > shiftEnd) {
            timeOutMinutes = shiftEnd;
        }

        // Deduct lunch break if applicable
        if (timeOutMinutes >= lunchStart && timeInMinutes <= lunchEnd) {
            timeOutMinutes -= 60; // Deduct 1 hour
        }

        // Calculate hours worked
        double hoursWorked = (timeOutMinutes - timeInMinutes) / 60.0;

        return hoursWorked;
    }

    // Converter time string to minutes
    private static int timeConverter(String timeString) {
        String[] timeParts = timeString.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        return hours * 60 + minutes;
    }
}

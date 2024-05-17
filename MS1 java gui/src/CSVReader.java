import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    private String filePath;

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public Map<String, Double> readHourlyRates() throws IOException {
        Map<String, Double> hourlyRates = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String employeeId = parts[0].trim();
                    double hourlyRate = Double.parseDouble(parts[parts.length - 1].trim()); // Assuming hourly rate is in the last column
                    hourlyRates.put(employeeId, hourlyRate);
                }
            }
        }
        return hourlyRates;
    }
}

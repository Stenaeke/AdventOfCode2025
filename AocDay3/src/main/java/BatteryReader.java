import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BatteryReader {

    public List<String> parseInput(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }

    public int getHighestVoltage(List<String> batteries) {
        int highestVoltage = 0;

        for (String battery : batteries) {
            int highestValue = 0;
            int secondHighestValue = 0;

            for (int i = 0; i < battery.length() - 1; i++) {
                int value = Integer.parseInt(String.valueOf(battery.charAt(i)));
                if  (value > highestValue) {
                    highestValue = value;
                }
            }
            for (int i = battery.indexOf(String.valueOf(highestValue)) + 1; i < battery.length(); i++) {
                int value = Integer.parseInt(String.valueOf(battery.charAt(i)));
                if (value > secondHighestValue) {
                    secondHighestValue = value;
                }
            }
            int sumForBattery = Integer.parseInt(Integer.toString(highestValue) + Integer.toString(secondHighestValue));
            System.out.println("sumForBattery: " + sumForBattery);
            highestVoltage += sumForBattery;
        }

        return highestVoltage;
    }
}

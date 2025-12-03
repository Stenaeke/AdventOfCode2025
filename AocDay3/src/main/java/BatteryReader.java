import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BatteryReader {

    public List<String> parseInput(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
    }

    public long getHighestVoltage(List<String> batteries, int numJoltages) {
        long highestVoltage = 0;

        for (String battery : batteries) {
            int skips = battery.length() - numJoltages;

            StringBuilder resultingNumber = new StringBuilder();

            for (char c : battery.toCharArray()) {
                int currentDigit = Character.getNumericValue(c);
                while (!resultingNumber.isEmpty() && skips > 0) {
                    int lastDigit = Character.getNumericValue(resultingNumber.charAt(resultingNumber.length() - 1));

                    if (currentDigit > lastDigit) {
                        resultingNumber.deleteCharAt(resultingNumber.length() - 1);
                        skips--;
                    } else {
                        break;
                    }
                }

                resultingNumber.append(c);
            }

            resultingNumber.setLength(numJoltages);

            long sumForBatteryLong = Long.parseLong(resultingNumber.toString());
            System.out.println("Result: " + resultingNumber);
            highestVoltage += sumForBatteryLong;
        }
        return highestVoltage;
    }
}

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class MainDay3 {

    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("AocDay3/src/main/java/battery_input.txt");
        BatteryReader batteryReader = new BatteryReader();
        List<String> batteries = batteryReader.parseInput(filePath);
        int result = batteryReader.getHighestVoltage(batteries);
        System.out.println("result: " + result);
    }
}

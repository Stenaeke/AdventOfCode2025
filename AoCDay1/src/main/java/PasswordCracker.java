import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PasswordCracker {

    public List<String> readContent(Path filepath) throws IOException {
        return Files.readAllLines(filepath);
    }

    public int executeOperationsCountZeros(int startNumber, List<String> operation) {
        int zeroCounter = 0;
        for (String line : operation) {
            int amount = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'L') {
                amount = -amount;
            }

            startNumber = Math.floorMod(startNumber + amount, 100);
            if (startNumber == 0) {
                zeroCounter++;
            }
        }

        return zeroCounter;
    }

    public int executeOperationsCountRotations(int startNumber, List<String> operation) {
        int startSector;
        int targetSector;
        int rotationCounter = 0;

        for (String line : operation) {
            int amount = Integer.parseInt(line.substring(1));
            if (line.charAt(0) == 'L') {
                amount = -amount;
            }

            int currentPos = startNumber;
            int targetPos = startNumber + amount;

            if (amount >= 0) {
                startSector = Math.floorDiv(currentPos, 100);
                targetSector = Math.floorDiv(targetPos, 100);
            } else {
                startSector = Math.floorDiv(currentPos - 1, 100);
                targetSector = Math.floorDiv(targetPos - 1, 100);
            }

            rotationCounter += Math.abs(targetSector - startSector);
            startNumber = targetPos;
        }

        return rotationCounter;
    }
}
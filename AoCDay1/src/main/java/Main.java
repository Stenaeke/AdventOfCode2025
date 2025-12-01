import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {
        int startNumber = 50;

        Path filepath = Path.of("src/main/java/operations.txt");
        PasswordCracker cracker = new PasswordCracker();
        int password = cracker.executeOperationsCountRotations(startNumber, cracker.readContent(filepath));
        System.out.println("The password is: " + password);
    }
}

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {


    public static void main(String[] args) throws IOException {
        IdParser parser = new IdParser();
        Path filePath = Path.of("src/main/java/test.txt");
        Map<Long,Long > idRanges = parser.parseId(filePath);
        Long badIdsPart1 = parser.findBadIds(idRanges);
        System.out.println("Bad ids part 1: " + badIdsPart1);
    }
}

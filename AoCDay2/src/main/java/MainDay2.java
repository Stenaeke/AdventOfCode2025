import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class MainDay2 {


    public static void main(String[] args) throws IOException {
        IdParser parser = new IdParser();
        Path filePath = Path.of("AoCDay2/src/main/java/id_ranges.txt");
        Map<Long,Long > idRanges = parser.parseId(filePath);
        Long badIdsPart1 = parser.findBadIds(idRanges);
        System.out.println("Bad ids part 2: " + badIdsPart1);
    }
}

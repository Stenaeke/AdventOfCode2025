import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdParser {

    public Map<Long, Long> parseId(Path filePath) throws IOException {
        String content = Files.readString(filePath);
        content = content.replace("\n","");
        content = content.replace("\r","");
        Map<Long, Long> idRanges = new HashMap<>();
        String[] contentList = content.split(",");
        for (String s : contentList) {
            Long val1 = Long.parseLong(s.substring(0,s.indexOf("-")));
            Long val2 = Long.parseLong(s.substring(s.indexOf("-")+1));

            idRanges.put(val1, val2);
        }
        return idRanges;

    }

    public Long findBadIds(Map<Long, Long> idRanges) {
        long badIdSum = 0L;

        for (Long startNumber : idRanges.keySet()) {

            for (Long iterator = startNumber; iterator < idRanges.get(startNumber) + 1; iterator++ ) {
                String iteratorAsString = Long.toString(iterator);
                if (checkPattern(iteratorAsString)) {
                    System.out.println(iteratorAsString);
                    badIdSum += iterator;
                }
            }
        }
        return badIdSum;
    }

    //Part 1 solution.
    public Long checkEvenPattern(String value) {
        if (value.substring(0, Math.floorDiv(value.length(),2)).equals(value.substring(Math.floorDiv(value.length(),2)))) {
            System.out.println(value);
            return Long.parseLong(value);
        }
        return 0L;
    }

    public Boolean checkPattern(String value) {
        return value.matches(("(.+)\\1+"));
    }
}

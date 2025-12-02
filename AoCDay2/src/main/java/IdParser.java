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
        if (value.length() == 1) {
            return false;
        }
        if(value.length()>=3) {
            if(checkDoublePattern(value))
                return true;
        }
        if(value.length()>=5) {
            if(checkTriplePattern(value))
                return true;
        }
        String regex = (value.substring(0, Math.ceilDiv(value.length(),2)));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value.substring(Math.ceilDiv(value.length(),2)));
        return matcher.find(0);
    }

    public Boolean checkDoublePattern(String value) {
        String regex = (value.substring(0,2));
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i + 1< value.length(); i = i+2) {
            if (!pattern.matcher(value.substring(i, i+2)).find() || !value.endsWith(regex)) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkTriplePattern(String value) {
        String regex = (value.substring(0,3));
        Pattern pattern = Pattern.compile(regex);
        for (int i = 0; i + 2 < value.length(); i = i+3) {
            if (!pattern.matcher(value.substring(i, i+3)).find() || !value.endsWith(regex)) {
                return false;
            }
        }
        return true;
    }
}

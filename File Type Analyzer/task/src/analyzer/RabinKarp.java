package analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class RabinKarp implements Runnable {

    private final Map<Long, Patterns> PATTERNS_MAP;
    private final String FILE_NAME;
    private final int LONGEST_PATTERN;
    private final int SHORTEST_PATTERN;
    private Patterns pattern;

    static final int A = 135_919;
    static final long M = 11_009_000_000_071L;

    public RabinKarp(Map<Long, Patterns> PATTERNS_MAP, String FILE_NAME, int LONGEST_PATTERN, int SHORTEST_PATTERN) {
        this.PATTERNS_MAP = PATTERNS_MAP;
        this.FILE_NAME = FILE_NAME;
        this.LONGEST_PATTERN = LONGEST_PATTERN;
        this.SHORTEST_PATTERN = SHORTEST_PATTERN;
    }

    private void doJob(int length, String line) {
        if (length > line.length()) {
            return;
        }
        long pow = 1;
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += line.charAt(line.length() - length + i) * pow;
            hash %= M;

            if (i != length - 1) {
                pow = pow * A % M;
            }
        }

        if (PATTERNS_MAP.containsKey(hash)) {
            if (pattern != null && PATTERNS_MAP.get(hash).compareTo(pattern) < 0 ||
                    pattern == null) {
                pattern = PATTERNS_MAP.get(hash);
            }
        }

        for (int i = line.length(); i > length; i--) {
            hash = (hash - line.charAt(i - 1) * pow % M + M) * A % M;
            hash = (hash + line.charAt(i - length - 1)) % M;
            if (PATTERNS_MAP.containsKey(hash)) {
                if (pattern != null && PATTERNS_MAP.get(hash).compareTo(pattern) < 0 ||
                        pattern == null) {
                    pattern = PATTERNS_MAP.get(hash);
                }
            }
        }
    }

    @Override
    public void run() {
        String text = null;
        try {
            text = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int length = SHORTEST_PATTERN; length <= LONGEST_PATTERN; length++) {
            doJob(length, text);
        }
        if (pattern != null) {
            System.out.println(FILE_NAME + ": " + pattern.getFILE_TYPE());
        } else {
            System.out.println(FILE_NAME + ": " + FileTypes.UNKNOWN.getType());
        }
    }

}

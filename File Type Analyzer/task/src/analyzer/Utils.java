package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Utils {

    private static int longestPattern = 0;
    private static int shortestPattern = Integer.MAX_VALUE;
    private static final int A = 135_919;
    private static final long M = 11_009_000_000_071L;


    public static ArrayList<Patterns> getPatterns(String patternsDB) throws FileNotFoundException {

        File db = new File(patternsDB);
        Scanner scanner = new Scanner(db);
        String[] patternDescription;
        ArrayList<Patterns> patterns = new ArrayList<>();

        while (scanner.hasNextLine()) {
            patternDescription = scanner.nextLine().split(";");
            patterns.add(new Patterns(patternDescription[0], patternDescription[1].replaceAll("\"", ""),
                    patternDescription[2].replaceAll("\"", "")));
        }

        scanner.close();

//        patterns.sort(Patterns::compareTo);

        return patterns;
    }

    public static long getHash(String line) {

        int length = line.length();
        long pow = 1;
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += line.charAt(line.length() - length + i) * pow;
            hash %= M;

            if (i != length - 1) {
                pow = pow * A % M;
            }
        }
        return hash;
    }

    public static Map<Long, Patterns> getPatternsHashes(String patternsDB) throws FileNotFoundException {

        File db = new File(patternsDB);
        Scanner scanner = new Scanner(db);
        String[] patternDescription;
        Map<Long, Patterns> patterns = new HashMap<>();
        Patterns currentPattern;

        int currentLength;

        while (scanner.hasNextLine()) {
            patternDescription = scanner.nextLine().split(";");
            currentPattern = new Patterns(patternDescription[0], patternDescription[1].replaceAll("\"", ""),
                    patternDescription[2].replaceAll("\"", ""));
            currentLength = currentPattern.getPATTERN().length();
            if (currentLength > longestPattern) {
                longestPattern = currentLength;
            }
            if (currentLength < shortestPattern) {
                shortestPattern = currentLength;
            }
            long hash = getHash(currentPattern.getPATTERN());
            patterns.put(hash, currentPattern);
        }
        scanner.close();
        return patterns;
    }

    public static int getLongestPattern() {
        return longestPattern;
    }

    public static int getShortestPattern() {
        return shortestPattern;
    }

}

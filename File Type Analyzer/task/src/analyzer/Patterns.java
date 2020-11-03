package analyzer;

public class Patterns implements Comparable<Patterns> {

    private final String PRIORITY;
    private final String PATTERN;
    private final String FILE_TYPE;

    public Patterns(String priority, String pattern, String FILE_TYPE) {
        this.PRIORITY = priority;
        this.PATTERN = pattern;
        this.FILE_TYPE = FILE_TYPE;
    }

    public String getPATTERN() {
        return PATTERN;
    }

    public String getFILE_TYPE() {
        return FILE_TYPE;
    }

    @Override
    public int compareTo(Patterns pattern) {
        if (this.PRIORITY.compareTo(pattern.PRIORITY) > 0) {
            return -1;
        } else if (this.PRIORITY.compareTo(pattern.PRIORITY) < 0) {
            return 1;
        }
        return 0;
    }
}

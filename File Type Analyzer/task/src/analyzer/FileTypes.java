package analyzer;

import java.util.regex.Pattern;

enum FileTypes {

    PDF_PATTERN("%PDF-", "PDF document"),
    UNKNOWN(null, "Unknown file type");

    String pattern;
    String type;

    FileTypes(String pattern, String type) {
        this.pattern = pattern;
        this.type = type;
    }

    public String getPattern() {
        return pattern;
    }

    public String getType() {
        return type;
    }
}

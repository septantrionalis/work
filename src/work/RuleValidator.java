package work;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleValidator {

    // Regular expression definitions based on the ABNF rules provided
    private static final String tchar = "[!#$%&'*+\\-.^_`|~0-9A-Za-z]";
    private static final String token = tchar + "+";
    private static final String name = "[A-Za-z0-9]+"; // Assuming CHAR includes alphanumeric
    private static final String headerReference = "header\\." + token;
    private static final String queryReference = "query\\." + name;
    private static final String pathReference = "path\\." + name;
    private static final String bodyReference = "body(?:#(\\/(?:[^~\\/]|~[01])*)?)?";
    private static final String jsonPointer = "(\\/[^~\\/]|~[01])*"; // simplified json-pointer
    private static final String source = "(" + headerReference + "|" + queryReference + "|" + pathReference + "|" + bodyReference + ")";
    
    private static final String expression = "\\$url|\\$method|\\$statusCode|\\$request\\." + source + "|\\$response\\." + source;

    public static void main(String[] args) {
        String testString = "$request.query.param"; // Example test string
        if (isValidRule(testString)) {
            System.out.println("Valid ABNF expression.");
        } else {
            System.out.println("Invalid ABNF expression.");
        }
    }

    public static boolean isValidRule(String input) {
        Pattern pattern = Pattern.compile("^(" + expression + ")$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}

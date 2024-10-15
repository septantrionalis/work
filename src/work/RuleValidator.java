package work;

import java.util.regex.Pattern;

public class RuleValidator {
    
    // Regular expressions based on the ABNF syntax provided
    private static final String UNESCAPED = "[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\u10FFFF]";
    private static final String ESCAPED = "~(0|1)";
    private static final String REFERENCE_TOKEN = "(" + UNESCAPED + "|(" + ESCAPED + "))*";
    private static final String JSON_POINTER = "((" + REFERENCE_TOKEN + ")?(/" + REFERENCE_TOKEN + ")*)?";
    private static final String NAME = "[a-zA-Z0-9]+";
    private static final String TOKEN = "[" + "!#$%&'*+\\-.^_`|~0-9A-Za-z]+";
    
    private static final String HEADER_REFERENCE = "header\\." + TOKEN;
    private static final String QUERY_REFERENCE = "query\\." + NAME;
    private static final String PATH_REFERENCE = "path\\." + NAME;
    private static final String BODY_REFERENCE = "body(" + JSON_POINTER + ")?";
    
    private static final String SOURCE = "(" + HEADER_REFERENCE + "|" + QUERY_REFERENCE + "|" + PATH_REFERENCE + "|" + BODY_REFERENCE + ")";
    
    private static final String EXPRESSION = "(\\$url|\\$method|\\$statusCode|\\$request\\." + SOURCE + "|\\$response\\." + SOURCE + ")";
    
    // Pattern compiled for performance
    private static final Pattern VALID_RULE_PATTERN = Pattern.compile("^" + EXPRESSION + "$");
    
    /**
     * Validates if the given rule string is valid according to the defined syntax.
     *
     * @param rule the rule string to validate
     * @return true if the rule is valid, false otherwise
     */
    public boolean isValidRule(String rule) {
        return VALID_RULE_PATTERN.matcher(rule).matches();
    }

    public static void main(String[] args) {
        RuleValidator validator = new RuleValidator();

        // Test cases including the new rule
        String[] testRules = {
            "$url",
            "$method",
            "$statusCode",
            "$request.header.X-Custom-Header",
            "$response.query.userId",
            "$request.body#/data/item",
            "$response.path.user.name",
            "$response.header.content-type",
            "$request.body#/callbackUrl", // New rule added for testing
            "$invalidRule" // invalid rule for testing
        };

        for (String rule : testRules) {
            System.out.println("Rule: " + rule + " is valid: " + validator.isValidRule(rule));
        }
    }
}

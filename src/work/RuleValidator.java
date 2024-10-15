package work;

import java.util.regex.Pattern;

public class RuleValidator {

    // Regular expression to match the valid rule according to the given augmented BNF syntax
    private static final String RULE_REGEX = 
            "^\\$url$|" +                      // matches "$url"
            "^\\$method$|" +                   // matches "$method"
            "^\\$statusCode$|" +               // matches "$statusCode"
            "^\\$request\\.[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*$|" +  // matches "$request.source"
            "^\\$response\\.[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*$|" + // matches "$response.source"
            "^\\$header$|" +                    // matches "$header"
            "^\\$header\\.[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*$|" +   // matches "$header.reference"
            "^\\$query$|" +                     // matches "$query"
            "^\\$query\\.[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*$|" +    // matches "$query.reference"
            "^\\$path$|" +                      // matches "$path"
            "^\\$path\\.[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*$|" +     // matches "$path.reference"
            "^\\$body$|" +                      // matches "$body"
            "^\\$body#(%[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*|\\\\[\\u0000-\\uFFFF]|[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*)" + // matches "$body#<unescaped/escaped>"
            "(/(%[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*|\\\\[\\u0000-\\uFFFF]|[\\u0000-\\u002E\\u0030-\\u007D\\u007F-\\uFFFF]*))*$|" + 
            "^\\~(0|1)$|" + // matches "~0" or "~1"
            "^(?=.{1,})[!#$%&'*+\\-\\.\\^_`|~0-9A-Za-z]*$"; // matches 1*tchar

    private static final Pattern pattern = Pattern.compile(RULE_REGEX);

    public static boolean isValidRule(String input) {
        return pattern.matcher(input).matches();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
            "$url",
            "$method",
            "$statusCode",
            "$request.source",
            "$response.source",
            "$header", // valid
            "$header.reference", // valid
            "$query", // valid
            "$query.reference", // valid
            "$path", // valid
            "$path.reference", // valid
            "$body", // valid
            "$body#foo", // valid unescaped token
            "$body#foo/bar", // valid unescaped token
            "$body#foo/bar/baz", // valid unescaped token with multiple segments
            "$body#1/2/3", // valid unescaped token
            "$body#name", // valid unescaped token
            "$body#escaped\\n", // valid escaped token
            "$body#escaped\\t", // valid escaped token
            "$body#escaped/another", // valid mixed escaped and unescaped
            "$body#invalid*pointer", // invalid (contains *)
            "$body#", // invalid (trailing # without pointer)
            "$body#foo/bar/baz/123", // valid
            "$body#foo/bar//baz", // invalid (double slash)
            "$body#escaped/invalid*token", // invalid (contains *)
            "$body#escaped/invalid\\*token", // valid escaped asterisk
            "$body#%20", // valid encoded space
            "$body#%2E", // valid encoded dot
            "$body#%3A", // valid encoded colon
            "$body#%7D", // valid encoded closing brace
            "$body#%FF", // valid encoded character
            "$body#escaped/invalid%pointer", // invalid (invalid percent encoding)
            "$request.invalid-source", // invalid
            "$response.123source", // invalid
            "$header.invalid-ref", // valid
            "$query.2invalid", // invalid
            "$path.validPath", // valid
            "$body.ref_123", // valid
            "~0", // valid
            "~1", // valid
            "~2", // invalid (not 0 or 1)
            "~", // invalid (just ~ without number)
            "valid!", // valid tchar sequence
            "test$123", // valid tchar sequence
            "$body#~1valid", // valid
            "$body#~0valid", // valid
            "just characters", // valid CHAR
            "", // invalid (empty string, needs at least one tchar)
            "$body#*invalid", // invalid (contains *)
            "abcde", // valid tchar sequence
            "A1#~1valid", // valid
        };

        for (String testCase : testCases) {
            System.out.println("Is \"" + testCase + "\" a valid rule? " + isValidRule(testCase));
        }
    }
}

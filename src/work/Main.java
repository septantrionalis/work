package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * The Main class implements an application that reads lines from the standard
 * input and prints them to the standard output.
 */
public class Main {

    public static final String YES = "YES";
    public static final String NO = "NO";
    
    public static final String SMILEY_FACE = ":)";
    public static final String FROWNEY_FACE = ":(";
    

    /**
     * 
     * @throws IOException
     */
    private void run()  {
//        System.out.println("-------");
//        System.out.println(isBalanced(""));
//        System.out.println(isBalanced("((()))"));
//        System.out.println(isBalanced("123"));
//        System.out.println(isBalanced("i am sick today (:()"));
        
        String line = "(asdf(asdf)))))";
        if (isBalanced(line)) {
            System.out.println(YES);
        } else {
            System.out.println(NO);              
        }
    }        


    /**
     * 
     * @param input
     * @return
     */
    private boolean isBalanced(String input) {
        // Check for null case
        if (input == null) {
            return false;
        }        
        
        // An empty string ""
        if (input.isEmpty()) {
            return true;
        }
        
        // A smiley face ":)" or a frowny face ":(" 
        if (input.contains(SMILEY_FACE) || input.contains(FROWNEY_FACE)) {
            return true;
        }

        int parentheses = 0;
        // An open parenthesis '(', followed by a message with balanced
        // parentheses, followed by a close parenthesis ')'.
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isValidCharacter(c)) {
                return false;
            }            
            
            if (c == '(') {
                parentheses++;
                
            } else if (c == ')') {
                parentheses--;
            }                
            
        }

        if (parentheses != 0) {
            return false;
        }

        return true;
    }

    /**
     * 
     * @param c
     * @return
     */
    private boolean isValidCharacter(char c) {
        return Character.isAlphabetic(c) || c == ':' || c == ' ' | c == '(' || c == ')';

    }

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        new Main().run();
    }

}

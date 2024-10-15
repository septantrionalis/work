package work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test2 {

    public Test2() {
        
    }
    
    public static void main(String[] args) throws IOException {

        String str = "assa1";
        StringBuilder builder = new StringBuilder(str);
        
        System.out.println(builder.reverse());
        
        if (builder.reverse().toString().equals(str)) {
            System.out.println("is palindrome");
        } else {
            System.out.println("is NOT palindrome");            
        }     
        
        StringBuffer buffer = new StringBuffer();
        for (int index = str.length() - 1; index >= 0; index--) {
            char c = str.charAt(index);
            buffer.append(c);
        }
        System.out.println(buffer.toString());
       
        
     }
    
    
    
}

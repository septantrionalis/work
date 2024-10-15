package work;

public class Test {

    public Test() {
        
        for (int count = 1; count <= 100; count++) {

            if (((count % 3) == 0) && ((count % 5) == 0)) {
                System.out.println("fizzbuzz");                
            } else if ((count % 3) == 0) {
                System.out.println("fizz");                
            } else  if ((count % 5) == 0) {
                System.out.println("buzz");                
            } else {
                System.out.println(count);                
            }

        }
    }
    
    public static void main(String[] args) {

        new Test();
    }

}

package work.inheritance;

import java.io.IOException;

public class BaseClass implements InterfaceClassA {

    
    public void run() {
        
        this.startVehicle();
        
    }
    
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        new BaseClass().run();
    }

    
}

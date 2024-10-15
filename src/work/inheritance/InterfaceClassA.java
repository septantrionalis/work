package work.inheritance;

public interface InterfaceClassA {
    
    default public void startVehicle() {
        System.out.println("Vehicle is starting");
    }
    
}

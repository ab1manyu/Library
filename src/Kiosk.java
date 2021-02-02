import java.util.Scanner;
public class Kiosk {
    public void run() {
        boolean running = true;
        System.out.println("Library Kiosk running.");
        while(running){
            Scanner s = new Scanner(System.in);  // Create a Scanner object
            String[] input = s.nextLine().split(",");  // Read user input



            switch (input[0]){

                case "Q":
                    System.out.println("Kiosk session ended.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }



        }
    }

}

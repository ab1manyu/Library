import java.util.Scanner;
import java.util.StringTokenizer;

public class Kiosk {
    public void run() {
        boolean running = true;
        Library library = new Library();
        System.out.println("Library Kiosk running.");
        while(running){
            Scanner s = new Scanner(System.in);  // Create a Scanner object
            String sline = s.nextLine();  // Read user input

            StringTokenizer input = new StringTokenizer(sline, ",");

            switch (input.nextToken()){
                case "A":
                    if(input.countTokens()==2){
                        try {

                        } catch (Exception e){

                        }
                    }
                    break;
                case "R":

                    break;
                case "PA":
                    library.print();
                    break;
                case "PD":
                    library.printByDate();
                    break;
                case "PN":
                    library.printByNumber();
                    break;



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

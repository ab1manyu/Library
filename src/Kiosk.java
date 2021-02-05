import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * A class that drives the entire program
 * and takes in all the command line arguments
 * from the client
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */

public class Kiosk {
    public static final int ADD_BOOK_ARGUMENTS = 2;

    /**
     * Runs the program by taking in the user's
     * command line arguments/requests and responding
     * with the appropriate results.
     *
     */

    public void run() {
        boolean running = true;
        Library library = new Library();
        System.out.println("Library Kiosk running.");
        while(running){
            Scanner s = new Scanner(System.in); // Create a Scanner object
            String sline = s.nextLine(); // Read user input
            try {
                StringTokenizer input = new StringTokenizer(sline, ",");

                switch (input.nextToken()) {
                    case "A":
                        if (input.countTokens() == ADD_BOOK_ARGUMENTS) {
                            try {
                                String nameOfBook = input.nextToken();
                                String strDate = input.nextToken();

                                Date date = new Date(strDate);
                                if (date.isValid()) {
                                    Book book = new Book(nameOfBook, date);
                                    library.add(book);
                                    System.out.println(nameOfBook + " added to the library.");
                                } else {
                                    System.out.println("Invalid Date!");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
                            }
                        } else
                            System.out.println("Invalid Command!");

                        break;
                    case "R":
                        if (input.countTokens() == 1) {
                            String serialNumber = input.nextToken();
                            try {
                                library.remove(library.findBook(serialNumber));
                                System.out.println("Book#" + serialNumber + " removed.");
                            } catch (Exception e) {
                                System.out.println("Unable to remove, the library does not have this book.");
                            }
                        }
                        break;
                    case "O":
                        if (input.countTokens() == 1) {
                            String serialNumber = input.nextToken();
                            try {
                                Book target = library.findBook(serialNumber);
                                if (target != null) {
                                    if (library.checkOut(target))
                                        System.out.println("You’ve checked out Book#" + serialNumber + ". Enjoy!");
                                    else
                                        System.out.println("Book#" + serialNumber + " is not available.");
                                } else
                                    System.out.println("Book#" + serialNumber + " is not available.");

                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
                            }
                        }
                        break;
                    case "I":
                        if (input.countTokens() == 1) {
                            String serialNumber = input.nextToken();
                            try {
                                Book target = library.findBook(serialNumber);
                                if (target != null) {
                                    if (library.returns(target))
                                        System.out.println("Book#"+ serialNumber +" return has completed. Thanks!");
                                    else
                                        System.out.println("Unable to return Book#" + serialNumber);
                                } else
                                    System.out.println("Unable to return Book#" + serialNumber);

                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
                            }
                        }
                        break;

                    case "PA":
                        if (library.getNumBooks() > 0) {
                            System.out.println("**List of books in the library.");
                            library.print();
                            System.out.println("**End of list.");
                        }else
                            System.out.println("Bookshelf is empty!");
                        break;
                    case "PD":
                        if (library.getNumBooks() > 0) {
                            System.out.println("**List of books in the library.");
                            library.printByDate();
                            System.out.println("**End of list.");
                        }else
                            System.out.println("Bookshelf is empty!");
                        break;
                    case "PN":
                        if (library.getNumBooks() > 0) {
                            System.out.println("**List of books in the library.");
                            library.printByNumber();
                            System.out.println("**End of list.");
                        }else
                            System.out.println("Bookshelf is empty!");
                        break;

                    case "Q":
                        System.out.println("Kiosk session ended.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid command!");
                        break;
                }
            }catch (Exception e){
                continue;
            }
        }
    }

}
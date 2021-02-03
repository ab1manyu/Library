/**
 * A class that stores information about a single book.
 * This information includes a serial number, the name of
 the book, check out status, and the date its been published.
 * @author Abimanyu Ananthu, Ashish Shenoy
 */

public class Book {

    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    private static String serialNumber = "10001";


    /**
     * Constructor that creates an instance of the book class.
     * Upon instantiation, checkedOut and serialNumber are
     * automatically assigned their respective values
     *
     * @param name - name of the book
     * @param datePublished - Date object showing the books publish date
     */

    public Book(String name, Date datePublished){
        this.number = Book.serialNumber;
        this.name = name;
        this.checkedOut = false;
        this.datePublished = datePublished;

        int intSerialNumber = Integer.parseInt(serialNumber);
        intSerialNumber = intSerialNumber + 1;
        Book.serialNumber = Integer.toString(intSerialNumber);

    }

    /**
     * Compares two books' serial number and returns either true
     * or false depending on the result.
     *
     * @param obj
     * @return true if the books have the same serial number
     *         false otherwise
     */

    @Override
    public boolean equals(Object obj){
        Book book = (Book) obj;
        return this.number.equals(book.number);
    }

    /**
     * Prints out all the information about the book in
     * the required format
     *
     * @return String containing the books information
     */

    @Override
    public String toString() {
        String status;
        if(this.checkedOut){
            status = "is checked out";
        }else{
            status = "is available";
        }
        return "Book#" + this.number + "::" + this.name + "::" + this.datePublished.toString() + "::" + status;
    }

    /**
     * Compares the dates between 2 books
     *
     * @param book - book
     * @return true if the book we are calling the method on
     * is earlier that the param book, false otherwise
     */

    public boolean compareDates(Book book){
        Date book1Date = this.datePublished;
        Date book2Date = book.datePublished;

        if(book1Date.getYear() < book2Date.getYear()){
            return true;
        }else{
            if(book1Date.getYear() == book2Date.getYear()){
                if(book1Date.getMonth() < book2Date.getMonth()){
                    return true;
                }else{
                    if (book1Date.getMonth() == book2Date.getMonth()){
                        return book1Date.getDay() < book2Date.getDay();
                    }else{
                        return false;
                    }
                }
            }else{
                return false;
            }
        }
    }

    /**
     * Toggles the checkedOut field of a book.
     * Invoked when client requests to
     * check out or return a book.
     */

    public void toggleStatus(){
        this.checkedOut = !this.checkedOut;
    }

    /**
     * Returns the value of the checkedOut instance variable
     *
     * @return true if the book is currently checked out,
     *         false otherwise
     */

    public boolean getStatus(){
        return this.checkedOut;
    }

    /**
     * Returns the serial Number of a book as an integer
     *
     * @return integer value that is greater than or
     *         equal to 10001
     */

    public int getSerialNumber(){
        return Integer.parseInt(this.number);
    }


    public static void main(String[] args) {
        Book b1 = new Book("Hi", new Date());
        System.out.println(b1);
        Book b2 = new Book("Bye", new Date());
        System.out.println(b2);

    }
}

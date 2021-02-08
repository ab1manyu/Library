/**
 * The Library class stores a list of Books in an array and provides functions to add,
 * remove, checkout, and return.
 * Other functions include printing list in order by date / serial number. Also there are
 * helper methods that are referenced throughout the Kiosk class.
 * @author  Abimanyu Ananthu, Ashish Shenoy
 */
public class Library {
    public Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    /**
     * Creates a Library and starts off with memory to store 4 books.
     */
    public Library() {
        this.books = new Book[4];
        this.numBooks = 0;
    }

    /**
     * A helper method to check to see and find a book in the library.
     * @param book the Book we are looking for.
     * @return index of the book if found, otherwise return -1 if the book is not in the library.
     */
    private int find(Book book) {
        Book[] currentBag = this.books;
        for(int i = 0; i < currentBag.length; i++){
            if(book.equals(currentBag[i])){
                return i;
            }
        }

        return -1;
    }

    /**
     * A getter method to return the number of books currently within the library.
     * @return number of books in the library
     */
    public int getNumBooks(){
        return  numBooks;
    }

    /**
     * A helper method to grow the library by 4 indexes once the library is full.
     * A library always starts with space to store 4 books and expands as more books are added to the library.
     */
    private void grow() {
        Book[] oldBag = this.books;
        int newLength = oldBag.length + 4;
        Book[] newBag = new Book[newLength];
        for(int i = 0; i < oldBag.length; i++){
            newBag[i] = oldBag[i];
        }
        this.books = newBag;
    }

    /**
     * Method to add a specified book to the library.
     * @param book the Book we are adding to the library.
     */
    public void add(Book book) {
        Book[] currentBag = this.books;
        boolean placed = false;
        for (int i = 0; i < currentBag.length; i++){
            if(currentBag[i] == null){
                currentBag[i] = book;
                numBooks++;
                placed = true;
                break;
            }
        }
        if(!placed){
            this.grow();
            this.add(book);
        }
    }

    /**
     * Method to remove a specified book to the library.
     * @param book the Book we are removing to the library.
     * @return returns true if the book is removed, otherwise false in the case the book is not able to be
     * removed because it is not in the library.
     */
    public boolean remove(Book book) {
        Book[] currentBag = this.books;
        int result = this.find(book);
        if(result == -1){
            return false;
        }
        currentBag[result] = null;
        numBooks--;
        return true;
    }

    /**
     * Method to check out a specified book to the library.
     * @param book the Book we are checking out to the library.
     * @return returns true if the book is has not been checked out, otherwise false in the case it has
     * already been checked out because you cannot check out a checked out book.
     */
    public boolean checkOut(Book book) {
        boolean checkedOut = book.getStatus();
        if(checkedOut){
            return false;
        }

        book.toggleStatus();
        return true;

    }

    /**
     * Method to return a specified book to the library.
     * @param book the Book we are returning back to the library.
     * @return returns true if the book is has been checked out, otherwise false in the case it has
     * not been checked out because you cannot check out a book that has been returned.
     */
    public boolean returns(Book book) {
        boolean checkedOut = book.getStatus();
        if(checkedOut){
            book.toggleStatus();
            return true;
        }
        return false;
    }

    /**
     * Helper method to find a specified book within the library.
     * This method is used to identify a book that has to be removed, checked out, or returned.
     * @param serialNumber the serial number of the book is the way to identify the book.
     * @return returns the book if its serial number is found within the library, otherwise returns null.
     */
    public Book findBook(String serialNumber) {
        Book[] currentBag = this.books;
        for (Book book : currentBag) {
            if (book != null && (Integer.parseInt(serialNumber) == book.getSerialNumber())) {
                return book;
            }
        }

        return null;
    }

    /**
     * Method to print all the books in the library.
     */
    public void print() {
        Book[] currentBag = this.books;
        for (Book book : currentBag) {
            if (book != null)
                System.out.println(book);
        }
    }

    /**
     * Method to print all the books in the library, in ascending date order.
     */
    public void printByDate() {
        this.sortByDate();
        this.print();
    }

    /**
     * Method to print all the books in the library, in ascending serial number order.
     */
    public void printByNumber() {
        this.sortByNumber();
        this.print();
    }

    /**
     * Helper method to sort the library in ascending date order, using selection sort.
     */
    private void sortByDate(){
        int arrLength = this.books.length;
        for (int i = 0; i < arrLength-1; i++) {
            int currentMin = i;
            for (int j = i+1; j < arrLength; j++) {
                if (this.books[j] != null && this.books[currentMin] != null) {
                    if (this.books[j].compareDates(this.books[currentMin])){
                        currentMin = j;
                    }

                }
            }
            Book temp = this.books[currentMin];
            this.books[currentMin] = this.books[i];
            this.books[i] = temp;
        }
    }

    /**
     * Helper method to sort the library in ascending serial number order, using selection sort.
     */
    private void sortByNumber() {
        int arrLength = this.books.length;
        for (int i = 0; i < arrLength-1; i++) {
            int min = i;
            for (int j = i+1; j < arrLength; j++) {
                if (this.books[j] != null && this.books[min] != null) {
                    if (this.books[j].getSerialNumber() < this.books[min].getSerialNumber()){
                        min = j;
                    }
                }
            }
            Book temp = this.books[min];
            this.books[min] = this.books[i];
            this.books[i] = temp;
        }
    }
}

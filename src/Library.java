public class Library {
    public Book[] books; // array-based implementation of the bag data structure
    private int numBooks; // the number of books currently in the bag

    //default constructor to create an empty bag
    public Library() {
        this.books = new Book[4];
        this.numBooks = 0;
    }

    // helper method to find a book in the bag
    private int find(Book book) {
        Book[] currentBag = this.books;
        for(int i = 0; i < currentBag.length; i++){
            if(book.equals(currentBag[i])){
                return i;
            }
        }

        return -1;
    }

    // helper method to grow the capacity by 4
    private void grow() {
        Book[] oldBag = this.books;
        int newLength = oldBag.length + 4;
        Book[] newBag = new Book[newLength];
        for(int i = 0; i < oldBag.length; i++){
            newBag[i] = oldBag[i];
        }
        this.books = newBag;
    }

    public void add(Book book) {
        Book[] currentBag = this.books;
        boolean placed = false;
        for (int i = 0; i < currentBag.length; i++){
            if(currentBag[i] == null){
                currentBag[i] = book;
                placed = true;
                break;
            }
        }
        if(!placed){
            this.grow();
            this.add(book);
        }
    }

    public boolean remove(Book book) {
        Book[] currentBag = this.books;
        int result = this.find(book);
        if(result == -1){
            return false;
        }
        currentBag[result] = null;
        return true;

    }
    public boolean checkOut(Book book) {
        boolean checkedOut = book.getStatus();
        if(checkedOut){
            return false;
        }

        book.toggleStatus();
        return true;

    }
    public boolean returns(Book book) {
        boolean checkedOut = book.getStatus();
        if(checkedOut){
            book.toggleStatus();
            return true;
        }
        return false;
    }

    //print the list of books in the bag
    public void print() {
        Book[] currentBag = this.books;
        for (int i = 0; i < currentBag.length; i++){
            if(currentBag[i]!=null)
                System.out.println(currentBag[i]);
        }
    }

    //print the list of books by datePublished (ascending)
    public void printByDate() {
        this.sortByDate();
        this.print();
    }

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

    //print the list of books by number (ascending)
    public void printByNumber() {
        this.sortByNumber();
        this.print();
    }

    //printing by serial number
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

    public static void main(String[] args) {
        Library l = new Library();

        Book b1 = new Book("homies in the cotton field", true, new Date("09/29/2020"));
        Book b2 = new Book("someone in the cotton field", false, new Date("09/28/2020"));
        Book b3 = new Book("titans in the cotton field", true, new Date("09/27/2020"));
        Book b4 = new Book("roxy in the cotton field", false, new Date("09/26/2020"));

        l.add(b1);
        l.add(b2);
        l.add(b3);
        l.add(b4);

        l.printByDate();
        System.out.println();
        l.printByNumber();

    }
}

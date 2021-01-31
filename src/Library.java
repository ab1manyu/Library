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
            System.out.println(currentBag[i]);
        }
    }
    public void printByDate() {

    } //print the list of books by datePublished (ascending)
    public void printByNumber() {

    } //print the list of books by number (ascending)

    public static void main(String[] args) {
        Library l = new Library();
        for (int i = 0; i < 9; i++){
           l.add(new Book());

        }
        for (int i = 0; i < l.books.length; i++){
            System.out.println(l.books[i]);
        }
    }
}

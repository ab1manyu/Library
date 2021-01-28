public class Book {

    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;

    private static String serialNumber = "10001";

    public Book(String name, boolean checkedOut, Date datePublished){
        this.number = Book.serialNumber;
        this.name = name;
        this.checkedOut = checkedOut;
        this.datePublished = datePublished;

        int intSerialNumber = Integer.parseInt(serialNumber);
        intSerialNumber = intSerialNumber + 1;
        Book.serialNumber = Integer.toString(intSerialNumber);

    }

    @Override
    public boolean equals(Object obj){
        Book book = (Book) obj;
        return this.number.equals(book.number);
    }

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

    public static void main(String[] args) {
        Book b1 = new Book("Hi", true, new Date());
        System.out.println(b1);
        Book b2 = new Book("Bye", true, new Date());
        System.out.println(b2);

    }
}

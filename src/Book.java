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

    public Book(){
        this.number = Book.serialNumber;
        this.name = "test";
        this.checkedOut = true;
        this.datePublished = new Date();

        int intSerialNumber = Integer.parseInt(serialNumber);
        intSerialNumber = intSerialNumber + 1;
        Book.serialNumber = Integer.toString(intSerialNumber);

    }

    @Override
    public boolean equals(Object obj){
        Book book = (Book) obj;
        return this.number.equals(book.number);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = new Date(datePublished);
    }

    public void setSerialNumber(int serialNumber){
        this.number =  serialNumber+"";
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

    public void toggleStatus(){
        this.checkedOut = !this.checkedOut;
    }

    public boolean getStatus(){
        return this.checkedOut;
    }

    public int getSerialNumber(){
        return Integer.parseInt(this.number);
    }


    public static void main(String[] args) {
        Book b1 = new Book("Hi", true, new Date());
        System.out.println(b1);
        Book b2 = new Book("Bye", true, new Date());
        System.out.println(b2);

    }
}

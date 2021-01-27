import java.util.Calendar;
import java.util.StringTokenizer;

public class Date {
    private int year;
    private int month;
    private int day;

    //taking mm/dd/yyyy and create a Date object{ }
    public Date(String date) {
        int i = 0; //Keeps track of the iterations
        StringTokenizer st = new StringTokenizer(date, "/");
        String[] arr = new String[3];
        while(st.hasMoreTokens()){
            arr[i] = st.nextToken();
            i++;
        }
        this.month = Integer.parseInt(arr[0]);
        this.day = Integer.parseInt(arr[1]);
        this.year = Integer.parseInt(arr[2]);
    }
    //return today’s date
    public Date() {
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH) + 1;
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isValid() {
        return true;
    }

    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    //check if year is a leap year
    public boolean isLeapYear(int year){
        return false;
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Date d = new Date();
        System.out.println(d);

    }
}

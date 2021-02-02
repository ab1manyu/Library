import java.util.Calendar;
import java.util.StringTokenizer;

public class Date {
    private int year;
    private int month;
    private int day;

    //constants
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARYLEAP = 29;
    public static final int MONTHEND = 31;
    public static final int OLDBOOK = 1900;

    //taking mm/dd/yyyy and create a Date object {}
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
        Calendar rightNow = Calendar.getInstance();
        this.year = rightNow.get(Calendar.YEAR);
        this.month = rightNow.get(Calendar.MONTH) + 1;
        this.day = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isValid() {
        boolean lessThanCurrDate = !this.greaterThanCurrDate();
        boolean dayValidator = this.dayValidator();
        boolean publishedAfter1990 =  this.year > OLDBOOK;

        return lessThanCurrDate && dayValidator && publishedAfter1990;
    }

    //checks if year is a leap year
    private boolean isLeapYear(){
        if(this.year % QUADRENNIAL == 0){
            if(this.year % CENTENNIAL == 0){
                return this.year % QUATERCENTENNIAL == 0;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    //checks if the mm/dd is valid
    private boolean dayValidator(){
        int month = this.month;
        int day = this.day;
        boolean day31 = false;

        if(day>MONTHEND || day<1)
            return false;

        switch(month){
            case Calendar.JANUARY+1:
            case Calendar.MARCH+1:
            case Calendar.MAY+1:
            case Calendar.OCTOBER+1:
            case Calendar.JULY+1:
            case Calendar.AUGUST+1:
            case Calendar.DECEMBER+1:
                day31=true;
                break;

            case Calendar.FEBRUARY+1:
                if(this.day>=MONTHEND-1) //in the case of FEB the day cannot be 30 or greater
                    return false;
                if(this.isLeapYear() && this.day==FEBRUARYLEAP)
                    return true;
                return this.day < FEBRUARYLEAP;
            case Calendar.APRIL+1:
            case Calendar.JUNE+1:
            case Calendar.SEPTEMBER+1:
            case Calendar.NOVEMBER+1:
                break;
            //case that the month is just not one of the above (1-12)
            default: return false;
        }

        return day31 || this.day != MONTHEND;
    }

    //checks if the date of book is set past the current date
    private boolean greaterThanCurrDate(){
        Date currDate = new Date();
        if(this.year > currDate.year){
            return true;
        }else{
            if(this.year == currDate.year){
                if(this.month > currDate.month){
                    return true;
                }else{
                    if (this.month == currDate.month){
                        return this.day > currDate.day;
                    }else{
                        return false;
                    }
                }
            }else{
                return false;
            }
        }
    }

    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    public static void main(String[] args) {
        Date date = new Date();
        Date anotherDate = new Date("02/1/2021");

        System.out.println("The date "+anotherDate.toString()+" is: "+anotherDate.isValid());
    }
}

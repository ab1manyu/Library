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
        Calendar rightNow = Calendar.getInstance();
        this.year = rightNow.get(Calendar.YEAR);
        this.month = rightNow.get(Calendar.MONTH) + 1;
        this.day = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    public boolean isValid() {
        boolean isLeapYear = this.isLeapYear();
        return true;
    }

    public String toString(){
        return this.month + "/" + this.day + "/" + this.year;
    }

    //check if year is a leap year
    private boolean isLeapYear(){
        if(this.year % 4 == 0){
            if(this.year % 100 == 0){
                return this.year % 400 == 0;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

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

    //checks if the mm/dd is valid
    private boolean dayValidator(){
        int month = this.month;
        int day = this.day;
        boolean day31 = false;

        if(day>31 || day<1)
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
                if(this.day>=30) //check if day is greater than 30
                    return false;
                if(this.isLeapYear() && this.day==29)
                    return true;
                return this.day < 29;

            case Calendar.APRIL+1:
            case Calendar.JUNE+1:
            case Calendar.SEPTEMBER+1:
            case Calendar.NOVEMBER+1:
                break;
            //case that the month is just not one of the above (1-12)
            default: return false;
        }

        return day31 || this.day != 31;
    }

    public static void main(String[] args) {
        Date date = new Date("13/29/2020");
        System.out.println(date.toString());
        System.out.println(date.dayValidator());
    }
}

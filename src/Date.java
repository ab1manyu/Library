import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that stores the date based off an input string.
 *
 * We break down the string and save the year, the month, and
 * the day within the respective instance variables.
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public class Date {
    private int year;
    private int month;
    private int day;

    //constants
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARY_LEAP = 29;
    public static final int MONTH_END = 31;
    public static final int OLD_BOOK = 1900;

    /**
     * Creates a Date object based off the given string.
     * Using StringTokenizer, it assigns the proper values
     * to the instance variables month, day, and year
     *
     * @param date - a string in the form mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     * Creates a Date object that represents today's date
     * with the help of the Java Calender library.
     * In the event the user doesn't specify a string,
     * this constructor will run
     */
    public Date() {
        Calendar rightNow = Calendar.getInstance();
        this.year = rightNow.get(Calendar.YEAR);
        this.month = rightNow.get(Calendar.MONTH) + 1;
        this.day = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the year from the date object
     *
     * @return integer value representing the year of the date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the month from the date object
     *
     * @return integer value representing the month of the date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the day from the date object
     *
     * @return integer value representing the day of the date
     */
    public int getDay() {
        return this.day;
    }
    /**
     * Checks if the date is valid and greater than
     * 1900 while taking into account of leap years
     *
     * @return true if the date is valid and greater than
     * year 1900, false otherwise
     */
    public boolean isValid() {
        boolean lessThanCurrDate = !this.greaterThanCurrDate();
        boolean dayValidator = this.dayValidator();
        boolean publishedAfter1900 = this.year > OLD_BOOK;

        return lessThanCurrDate && dayValidator && publishedAfter1900;
    }

    /**
     * Checks whether or not the date's year is a leap year
     *
     * @return true if the date is in a leap year, false otherwise
     */
    private boolean isLeapYear() {
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                return this.year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the day is valid based off the given month,
     * taking into account of leap years as well.
     *
     * @return true if the day is within the range of the given month,
     * false otherwise.
     */
    private boolean dayValidator() {
        int month = this.month;
        int day = this.day;
        boolean day31 = false;

        if (day > MONTH_END || day < 1)
            return false;

        switch (month) {
            case Calendar.JANUARY + 1:
            case Calendar.MARCH + 1:
            case Calendar.MAY + 1:
            case Calendar.OCTOBER + 1:
            case Calendar.JULY + 1:
            case Calendar.AUGUST + 1:
            case Calendar.DECEMBER + 1:
                day31 = true;
                break;

            case Calendar.FEBRUARY + 1:
                if (this.day >= MONTH_END - 1) //in the case of FEB the day cannot be 30 or greater
                    return false;
                if (this.isLeapYear() && this.day == FEBRUARY_LEAP)
                    return true;
                return this.day < FEBRUARY_LEAP;

            case Calendar.APRIL + 1:
            case Calendar.JUNE + 1:
            case Calendar.SEPTEMBER + 1:
            case Calendar.NOVEMBER + 1:
                break;
            //case that the month is just not one of the above (1-12)
            default:
                return false;
        }

        return day31 || this.day != MONTH_END;
    }

    /**
     * Helper method for isValid().
     * Checks whether the date is ahead of the current Date.
     *
     * @return true if the date is not greater than the current date,
     *         false otherwise.
     */
    private boolean greaterThanCurrDate() {
        Date currDate = new Date();
        if (this.year > currDate.year) {
            return true;
        } else {
            if (this.year == currDate.year) {
                if (this.month > currDate.month) {
                    return true;
                } else {
                    if (this.month == currDate.month) {
                        return this.day > currDate.day;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Returns a string of the date in the format mm/dd/yyyy
     *
     * @return String representing the date object
     */
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Testbed main to exercise the isValid() method in this class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Testing the isValid Method

        // Test Case #1, checking invalid month.
        System.out.println("Running Test Case#1");
        Date tCase1 = new Date("13/1/2000");
        if(!tCase1.isValid()) System.out.println("Test Case#1, checking a date with invalid month. Passed");
        else System.out.println("Test Case#1, checking a date with invalid month. Failed");

        // Test Case #2, checking 02/29 on a non-leap year.
        System.out.println("Running Test Case#2");
        Date tCase2 = new Date("2/29/2021");
        if(!tCase2.isValid()) System.out.println("Test Case#2, checking a date with invalid month. Passed");
        else System.out.println("Test Case#2, checking a date with invalid month. Failed");

        // Test Case #3, testing day=31 on a 30-day month.
        System.out.println("Running Test Case#3");
        Date tCase3 = new Date("4/31/2009");
        if(!tCase3.isValid()) System.out.println("Test Case#3, checking a date with invalid month. Passed");
        else System.out.println("Test Case#3, checking a date with invalid month. Failed");

        // Test Case #4, checking a date before 1900.
        System.out.println("Running Test Case#4");
        Date tCase4 = new Date("3/31/1800");
        if(!tCase4.isValid()) System.out.println("Test Case#4, checking a date before 1900. Passed");
        else System.out.println("Test Case#4, checking a date before 1900.  Failed");

        // Test Case #5, checking invalid day.
        System.out.println("Running Test Case#5");
        Date tCase5 = new Date("4/32/2009");
        if(!tCase5.isValid()) System.out.println("Test Case#5, checking a date with invalid day. Passed");
        else System.out.println("Test Case#5, checking a date with invalid day. Failed");

        // Test Case#6, checking a date in the future.
        System.out.println("Running Test Case#6");
        Date tCase6 = new Date("4/31/2109");
        if(!tCase6.isValid()) System.out.println("Test Case#6, checking a date in the future. Passed");
        else System.out.println("Test Case#6, checking a date in the future. Failed");

        // Test Case#7, checking a correct date.
        System.out.println("Running Test Case#7");
        Date tCase7 = new Date("4/30/2019");
        if(tCase7.isValid()) System.out.println("Test Case#7, checking a normal date. Passed");
        else System.out.println("Test Case#7, checking a normal date. Failed");

    }
}

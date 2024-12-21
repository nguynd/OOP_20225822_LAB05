package lab01;
import java.util.Scanner;

public class DaysOfMonth {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfDaysInMonth = 0;
        String monthName = "unknown";
        
        System.out.println("Enter Month (full name, abbreviation, or number): ");
        String month = scanner.next();
        
        System.out.println("Enter a year: ");
        int year = scanner.nextInt();
        
        // convert Month to Number
        int month_number = convertMonthToInt(month);
        
        switch(month_number) 
        {
            case 1:
                monthName = "January";
                numberOfDaysInMonth = 31;
                break;
            case 2:
                monthName = "February";
                numberOfDaysInMonth = (isLeapYear(year)) ? 29 : 28;
                break;
            case 3:
                monthName = "March";
                numberOfDaysInMonth = 31;
                break;
            case 4:
                monthName = "April";
                numberOfDaysInMonth = 30;
                break;
            case 5:
                monthName = "May";
                numberOfDaysInMonth = 31;
                break;
            case 6:
                monthName = "June";
                numberOfDaysInMonth = 30;
                break;
            case 7:
                monthName = "July";
                numberOfDaysInMonth = 31;
                break;
            case 8:
                monthName = "August";
                numberOfDaysInMonth = 31;
                break;
            case 9:
                monthName = "September";
                numberOfDaysInMonth = 30;
                break;
            case 10:
                monthName = "October";
                numberOfDaysInMonth = 31;
                break;
            case 11:
                monthName = "November";
                numberOfDaysInMonth = 30;
                break;
            case 12:
                monthName = "December";
                numberOfDaysInMonth = 31;
                break;
            default:
            	System.out.println("Invalid");
            	System.exit(1);
        }
        
        System.out.println(monthName + " " + year + " has " + numberOfDaysInMonth + " days");
        
        scanner.close();
    }
    
    private static int convertMonthToInt(String monthInput) {
    	monthInput = monthInput.toLowerCase();
    	switch(monthInput) {
    	case "january":
    	case "jan.":
    	case "jan":
    	case "1":
    		return 1;
    	case "february":
    	case "feb.":
    	case "feb":
    	case "2":
    		return 2;
    	case "march":
    	case "mar.":
    	case "mar":
    	case "3":
    		return 3;
    	case "april":
    	case "apr.":
    	case "apr":
    	case "4":
    		return 4;
    	case "may":
    	case "may.":
    	case "5":
    		return 5;
    	case "june":
    	case "jun.":
    	case "jun":
    	case "6":
    		return 6;
    	case "july":
    	case "jul.":
    	case "jul":
    	case "7":
    		return 7;
    	case "august":
    	case "aug.":
    	case "aug":
    	case "8":
    		return 8;
    	case "september":
    	case "sept.":
    	case "sep":
    	case "9":
    		return 9;
    	case "october":
    	case "oct.":
    	case "oct":
    	case "10":
    		return 10;
    	case "november":
    	case "nov.":
    	case "nov":
    	case "11":
    		return 11;
    	case "december":
    	case "dec.":
    	case "dec":
    	case "12":
    		return 12;
    	default:
    		return -1;
    	}
    }
    
    private static boolean isLeapYear(int year) {
    	return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    	
    }
    }
        
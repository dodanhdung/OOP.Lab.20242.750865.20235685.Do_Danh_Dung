package x;
import java.util.Scanner;
import java.util.HashMap;
public class DaysInMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> DayOfMonth = new HashMap<>();
        DayOfMonth.put("January", 31);
        DayOfMonth.put("Jan.", 31);
        DayOfMonth.put("Jan", 31);
        DayOfMonth.put("1", 31);
        DayOfMonth.put("February", 28);
        DayOfMonth.put("Feb.", 28);
        DayOfMonth.put("Feb", 28);
        DayOfMonth.put("2", 28);
        DayOfMonth.put("March", 31);
        DayOfMonth.put("Mar.", 31);
        DayOfMonth.put("Mar", 31);
        DayOfMonth.put("3", 31);
        DayOfMonth.put("April", 30);
        DayOfMonth.put("Apr.", 30);
        DayOfMonth.put("Apr", 30);
        DayOfMonth.put("4", 30);
        DayOfMonth.put("May", 31);
        DayOfMonth.put("May.", 31);
        DayOfMonth.put("May", 31);
        DayOfMonth.put("5", 31);
        DayOfMonth.put("June", 30);
        DayOfMonth.put("Jun.", 30);
        DayOfMonth.put("Jun", 30);
        DayOfMonth.put("6", 30);
        DayOfMonth.put("July", 31);
        DayOfMonth.put("Jul.", 31);
        DayOfMonth.put("Jul", 31);
        DayOfMonth.put("7", 31);
        DayOfMonth.put("August", 31);
        DayOfMonth.put("Aug.", 31);
        DayOfMonth.put("Aug", 31);
        DayOfMonth.put("8", 31);
        DayOfMonth.put("September", 30);
        DayOfMonth.put("Sept.", 30);
        DayOfMonth.put("Sep", 30);
        DayOfMonth.put("9", 30);
        DayOfMonth.put("October", 31);
        DayOfMonth.put("Oct.", 31);
        DayOfMonth.put("Oct", 31);
        DayOfMonth.put("10", 31);
        DayOfMonth.put("November", 30);
        DayOfMonth.put("Nov.", 30);
        DayOfMonth.put("Nov", 30);
        DayOfMonth.put("11", 30);
        DayOfMonth.put("December", 31);
        DayOfMonth.put("Dec.", 31);
        DayOfMonth.put("Dec", 31);
        DayOfMonth.put("12", 31);
        while (true) {
            System.out.print("Month: ");
            String month = scanner.nextLine().trim();
            System.out.print("Year: ");
            int year = scanner.nextInt();
            if (year < 0) {
                System.out.println("Eror");
                continue;
            }
            boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            int days = DayOfMonth.get(month);
            if (month.equals("February") || month.equals("Feb.") || month.equals("Feb") || month.equals("2")) {
                days = isLeapYear ? 29 : 28;
            }
            System.out.println(days);
            break;
        }
        scanner.close();
    }
}


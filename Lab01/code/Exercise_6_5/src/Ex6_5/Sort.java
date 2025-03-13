package Ex6_5;
import java.util.Arrays;
import java.util.Scanner;

public class Sort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr;
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        arr = new int[n];
        System.out.println("Enter " + n + " numbers:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }        
        Arrays.sort(arr);
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        double avg = (double) sum / arr.length;
        System.out.println("Result: " + Arrays.toString(arr));
        System.out.println("Avg value: " + avg);
        scanner.close();
    }
}


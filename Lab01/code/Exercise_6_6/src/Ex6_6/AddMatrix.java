package Ex6_6;
import java.util.Scanner;
public class AddMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m, n;
        int[][] A, B, C;
        System.out.print("Enter m: ");
        m = scanner.nextInt();
        System.out.print("Enter n: ");
        n = scanner.nextInt();
        A = new int[m][n];
        B = new int[m][n];
        C = new int[m][n];
        System.out.println("Enter A:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter B:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        System.out.println("Result:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
            	System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
        scanner.close();
    }    
}




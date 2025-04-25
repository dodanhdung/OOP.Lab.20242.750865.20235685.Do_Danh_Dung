import java.util.Scanner;
public class InputFromKeyboard {
	public static void main(String args[]) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What's ur name?");
		String strName = keyboard.nextLine();
		System.out.println("How old are u?");
		int iAge = keyboard.nextInt();
		System.out.println("How tall are u (m)?");
		double dHeight = keyboard.nextDouble();
		System.out.println("Mrs/Ms. " + strName + ", " + iAge + "years old." + "Ur height is " + dHeight + ".");
	}
}

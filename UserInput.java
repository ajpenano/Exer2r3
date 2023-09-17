import java.util.Scanner;

public class UserInput {
	Scanner scanner = new Scanner(System.in);
	public int inputRows() {
		int rows = 0;
		System.out.print("Input number of rows greater than 0: ");
		
		do {
			if (!scanner.hasNextInt()) {
                System.out.print("You have entered an invalid string, input a number greater than 0 for the rows: ");
            } else {
                rows = scanner.nextInt();
                if (rows <= 0) {
                    System.out.print("Please enter a number greater than 0 only: ");
                } 
            }
			scanner.nextLine();
		} while (rows <= 0);
		return rows;
	}
	
	public int inputColumns() {
		int columns = 0;
		System.out.print("Input number of columns greater than 0: ");
		
		do {
			if (!scanner.hasNextInt()) {
                System.out.print("You have entered an invalid string, input a number greater than 0 for the columns: ");
            } else {
                columns = scanner.nextInt();
                if (columns <= 0) {
                    System.out.print("Please enter a number greater than 0 only: ");
                }
            }
            scanner.nextLine();
		} while (columns <= 0);
		return columns;
	}
	
	public int inputKeyLength() {
		int keyLength = 0;
		System.out.print("Enter desired length of each key in the cells (1-5): ");
		do {
			if (!scanner.hasNextInt()) {
                System.out.print("You have entered an invalid string, input a number between 1 to 5: ");
            }
			else {
                keyLength = scanner.nextInt();
                if ((keyLength <= 0) || (keyLength > 5)) {
                    System.out.print("Please enter a number between 1 to 5 only: ");
                }
            }
            scanner.nextLine();
		} while ((keyLength <= 0) || (keyLength > 5));
		return keyLength;
	}
	
	public int inputValueLength() {
		int valueLength = 0;
		System.out.print("Enter desired length of each value in the cells (1-5): ");
		do {
			if (!scanner.hasNextInt()) {
                System.out.print("You have entered an invalid string, input a number between 1 to 5: ");
            }
			else {
                valueLength = scanner.nextInt();
                if ((valueLength <= 0) || (valueLength > 5)) {
                    System.out.print("Please enter a number between 1 to 5 only: ");
                }
            }
            scanner.nextLine();
		} while ((valueLength <= 0) || (valueLength > 5));
		return valueLength;
	}
}
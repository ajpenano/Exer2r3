import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class InvalidFileContents {
	void generateNewTable(String text) {
		Scanner scanner = new Scanner(System.in);
		
		Reset rt = new Reset();
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		ArrayList<Integer> dim = new ArrayList<Integer>();
		
		String option = "a";
		
		System.out.println(" but there are problems in the contents of the file. "
				+ "\nMake sure the keys and values are delimited by colons (:) and key:value pairs are separated by spaces.");
		System.out.print("Generate a new table in the same file? (Yes = y or No = n): ");
		while (!option.equals("y") && !option.equals("n")) {
			option = scanner.nextLine();
			if (option.equals("y")) {
				dim = rt.resetDim();
				map = malCreator.createRandomMap(3, 3, dim);
				rt.saveNewTable(text, map, dim);
				System.out.println("Run the program again to access the menu. Exiting the program...");
				System.exit(0);
			} else if (option.equals("n")) {
				System.out.println("Try editing the file and try again. Exiting the program...");
				scanner.close();
				System.exit(0);
			} else {
				System.out.print("Invalid key entered, choose between \"y\" or \"n\" only: ");
			}
		}
	}
}

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Reset {
	
	public ArrayList<Integer> resetDim() {
		ArrayList<Integer> dim = new ArrayList<Integer>();
		UserInput ui = new UserInput();
		int rows = ui.inputRows();
		int columns = ui.inputColumns();
		//creates arraylist of columns per row for a square table
		for (int i = 0; i < rows; i++) {
			dim.add(columns);
		}
		return dim;
	}
	
	public LinkedHashMap<String, ArrayList<String>> resetTable(ArrayList<Integer> dim) {
		UserInput ui = new UserInput();
		int keyLength = ui.inputKeyLength();
		int valueLength = ui.inputValueLength();
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		LinkedHashMap<String, ArrayList<String>> map = malCreator.createRandomMap(keyLength, valueLength, dim); 
		return map;
	}
	
	public void saveNewTable(String text, LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim) {
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		ArrayList<String> keyArrayList = malCreator.createKeyArrayList(map); 
		ArrayList<String> valueArrayList = malCreator.createValueArrayList(map);
		
		try {	
			PrintWriter pw = new PrintWriter(new FileWriter(text));
			int counter = 0;
			String textEntry = "";
			for (int i = 0; i < dim.size(); i++) {
				for (int j = 0; j < dim.get(i); j++) {
					textEntry = textEntry + keyArrayList.get(counter) + ":" + valueArrayList.get(counter) + " ";
					counter++;	
				}
				textEntry = textEntry + "\r\n";
			}		
			pw.write(textEntry);
			pw.close();
			System.out.println("Text file " + text + " is now updated with new key:value pairs.\n\nNEW TABLE");
			counter = 0;
			for (int i = 0; i < dim.size(); i++) {
				for (int j = 0; j < dim.get(i); j++) {
					System.out.print(keyArrayList.get(counter) + ":" + valueArrayList.get(counter) + " ");
					counter++;	
				}
				System.out.println();
			}
			
		} catch (IOException e) {
	        System.err.println("Error writing to the file: " + text);
	        e.printStackTrace();
	 	}
	}
	
	ArrayList<Integer> dim;
	LinkedHashMap<String, ArrayList<String>> map;
	
	void confirmReset(String text, Scanner scanner) {
		Reset rt = new Reset();
		String option;
		do {
			System.out.print("You are to set the new dimensions, reset the values of the table, and save it to file. Continue? Yes(y)/No(n): ");
			option = scanner.nextLine();
			if (option.equals("y")) {
				this.dim = rt.resetDim();
				this.map = rt.resetTable(dim);
				rt.saveNewTable(text, map, dim);
            } else if (option.equals("n")) {
            	System.out.println("Dimensions and values were not reset and saved.");
            } else {
                System.out.println("You have entered an invalid string."); 
            }
		} while (!option.equals("y") && !option.equals("n"));
		
	}
}
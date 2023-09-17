import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Searcher {
	Scanner scanner = new Scanner(System.in);
	
	public void search(LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim) {
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		ArrayList<String> keyArrayList = malCreator.createKeyArrayList(map); 
		ArrayList<String> valueArrayList = malCreator.createValueArrayList(map);
		
		System.out.print("\nEnter the string to be searched: ");
		String find = scanner.nextLine();
		System.out.println("Search String: "+find);
		boolean match = false;
		int counter = 0;
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {
				String source = keyArrayList.get(counter);
				int index = 0;
				int occur = 0;
				while ((index = source.indexOf(find, index)) != -1) {
					index++;
					occur++;
				}
				if (occur == 1 ) {
					System.out.println("Found "+find+" on ("+i+", "+j+") key with "+occur+" instance.");
					match = true;
				}
				else if (occur > 1) {
					System.out.println("Found "+find+" on ("+i+", "+j+") key with "+occur+" instances.");
					match = true;
				}
				counter++;
			}	
		}
		counter = 0;
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {
				String source = valueArrayList.get(counter);
				int index = 0;
				int occur = 0;
				while ((index = source.indexOf(find, index)) != -1) {
					index++;
					occur++;
				}
				if (occur == 1 ) {
					System.out.println("Found "+find+" on ("+i+", "+j+") value with "+occur+" instance.");
					match = true;
				}
				else if (occur > 1) {
					System.out.println("Found "+find+" on ("+i+", "+j+") value with "+occur+" instances.");
					match = true;
				}
				counter++;
			}	
		}
		if (match == false) {
			System.out.println("No match found!");
		}
	}
}
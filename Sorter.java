import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Sorter {
	
	public LinkedHashMap<String, ArrayList<String>> sortTableAscending(String text, LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim, Scanner scanner) {
		//sort by key
		ArrayList<String> sortedKeys = new ArrayList<String>(map.keySet());
		Collections.sort(sortedKeys);
		
		//store into mapSortedByKey
		LinkedHashMap<String, ArrayList<String>> mapSortedByKey = new LinkedHashMap<String, ArrayList<String>>();
		for (String key : sortedKeys) {
			mapSortedByKey.put(key, new ArrayList<String>());
			mapSortedByKey.get(key).add(map.get(key).get(0));
			mapSortedByKey.get(key).add(map.get(key).get(1));
		}
		
		//generates a mapKey:KeyValue
		LinkedHashMap<String, String> mapKeyKeyValue = new LinkedHashMap<String, String>();
		for (Map.Entry<String, ArrayList<String>> pair : mapSortedByKey.entrySet()) {
			mapKeyKeyValue.put(pair.getKey(), pair.getValue().get(0));
		}
		
		//sort by KeyValue
		LinkedHashMap<String, String> mapSortedByKeyValue = mapKeyKeyValue
			.entrySet()		
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(LinkedHashMap::new,
				(col, e) -> col.put(e.getKey(), e.getValue()),
				HashMap::putAll);
		
		//store into mapSortedByKeyKeyValue
		LinkedHashMap<String, ArrayList<String>> mapSortedByKeyKeyValue = new LinkedHashMap<String, ArrayList<String>>();
		for (String key : mapSortedByKeyValue.keySet()) {
			mapSortedByKeyKeyValue.put(key, new ArrayList<String>());
			mapSortedByKeyKeyValue.get(key).add(mapSortedByKeyValue.get(key));
			mapSortedByKeyKeyValue.get(key).add(mapSortedByKey.get(key).get(1));
		}
		
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		//creates arraylist of sortedKeys
		ArrayList<String> sortedKeyArrayList = malCreator.createKeyArrayList(mapSortedByKeyKeyValue); 
		//creates arraylist of sorted values
		ArrayList<String> sortedValueArrayList = malCreator.createValueArrayList(mapSortedByKeyKeyValue);
		
		System.out.println("\nSORTED BY KEYVALUE");
		int counter = 0;
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {
				System.out.print(sortedKeyArrayList.get(counter) + sortedValueArrayList.get(counter) + " ");
				counter++;	
			}
			System.out.println();
		}
		
		String option = null;
		do {
			System.out.print("Do you want to save the sorted table? Yes(y)/No(n): ");
			option = scanner.nextLine();
			if (option.equals("y")) {
				try {	
					PrintWriter pw = new PrintWriter(new FileWriter(text));
					counter = 0;
					String textEntry = "";
					for (int i = 0; i < dim.size(); i++) {
						for (int j = 0; j < dim.get(i); j++) {
							textEntry = textEntry + sortedKeyArrayList.get(counter) + ":" + sortedValueArrayList.get(counter) + " ";
							counter++;	
						}
						textEntry = textEntry + "\r\n";
					}		
					pw.write(textEntry);
					pw.close();
					System.out.println("Text file " + text + " is now updated with new key:value pairs.\n\nSORTED TABLE");
					counter = 0;
					for (int i = 0; i < dim.size(); i++) {
						for (int j = 0; j < dim.get(i); j++) {
							System.out.print(sortedKeyArrayList.get(counter) + ":" + sortedValueArrayList.get(counter) + " ");
							counter++;	
						}
						System.out.println();
					}
					map.clear();
					map = new LinkedHashMap<String, ArrayList<String>>();
					map = mapSortedByKeyKeyValue;
					
				} catch (IOException e) {
		            System.err.println("Error writing to the file: " + text);
		            e.printStackTrace();
			 	}
				return map;
            } else if (option.equals("n")) {
            	System.out.println("Sorted table is not saved into the text file.\n\nORIGINAL UNSORTED TABLE"); 
            	Printer p = new Printer();
            	p.print(map, dim);
            } else {
                System.out.println("You have entered an invalid string."); 
            }
		} while (!option.equals("y") && !option.equals("n"));
	return map;
	}
}
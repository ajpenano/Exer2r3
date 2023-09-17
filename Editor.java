import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Editor {
	public LinkedHashMap<String, ArrayList<String>> edit(String text, LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim) {
		FRSTokenizer st = new FRSTokenizer();
		Reset rt = new Reset();
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		
		ArrayList<String> keyArrayList = malCreator.createKeyArrayList(map); 
		ArrayList<String> valueArrayList = malCreator.createValueArrayList(map);
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the key of the key:value pair to edit: ");
		String edit = scanner.nextLine();
			
		boolean match = false;
		int counter = 0;
		int index = 0;
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {
				if (keyArrayList.get(counter).equals(edit)) {
					System.out.println("Found the key "+edit+" in ("+i+", "+j+") cell.");
					System.out.println("Key:Value = "+keyArrayList.get(counter)+":"+valueArrayList.get(counter));
					match = true;
					index = counter;
					}
					counter++;
				}	
			}
		String option;
		if (match == true) {
			outerloop:
			do {
				System.out.print("Type \"k\" to edit the key, type \"v\" to edit the value or type \"x\" to exit the editor: ");
				option = scanner.nextLine();
				String replacement;
				if (option.equals("k")) {
					System.out.print("Enter the new key containing no space or \":\". Type \"x\" to exit editor: ");
					replacement = scanner.nextLine();
					if (replacement.equals("x")) {
						System.out.println("Exiting editor...");
						break outerloop;
					} 
					while (replacement.contains(" ") || replacement.contains(":")) {
						System.out.print("Enter the new key containing no space or \":\". Type \"x\" to exit editor: ");
						replacement = scanner.nextLine();
						if (replacement.equals("x")) {
							System.out.println("Exiting editor...");
							break outerloop;
						}
					}
					do {	
						int otherIndex = -1;
						counter = 0;
						for (int i = 0; i < dim.size(); i++) {
							for (int j = 0; j < dim.get(i); j++) {
								if (counter == index) {
									//do nothing;
								} else if (keyArrayList.get(counter).equals(replacement)) {
									otherIndex = counter;
								}
								counter++;
							}	
						}
						if (otherIndex == -1) {
							match = false;
						} else if (keyArrayList.get(otherIndex).equals(replacement)) {
							System.out.print("Key already exists. Enter a unique key containing no space or \":\". Type \"x\" to exit editor: ");
							replacement = scanner.nextLine();
							while (replacement.contains(" ") || replacement.contains(":")) {
								System.out.print("Enter the new key containing no space or \":\". Type \"x\" to exit editor: ");
								replacement = scanner.nextLine();
								if (replacement.equals("x")) {
									System.out.println("Exiting editor...");
									break outerloop;
								}
							}
							if (replacement.equals("x")) {
								System.out.println("Exiting editor...");
								break outerloop;
							} else {
								match = true;	
							}
						}
					} while (match == true);
					keyArrayList.set(index, replacement);
					map = st.createMapFromKeyAndValueArrayLists(keyArrayList, valueArrayList); 
					rt.saveNewTable(text, map, dim);
				} else if (option.equals("v")) {
					System.out.print("Enter the new value containing no space or \":\". Type \"x\" to exit editor: ");
					replacement = scanner.nextLine();
					while (replacement.contains(" ") || replacement.contains(":")) {
						System.out.print("Enter the new value containing no space or \":\". Type \"x\" to exit editor: ");
						replacement = scanner.nextLine();
						if (replacement.equals("x")) {
							System.out.println("Exiting editor...");
							break outerloop;
						}
					}
					if (replacement.equals("x")) {
						System.out.println("Exiting editor...");
						break;
					} else {
						valueArrayList.set(index, replacement);
						map = st.createMapFromKeyAndValueArrayLists(keyArrayList, valueArrayList); 
						rt.saveNewTable(text, map, dim);
					}
				} else if (option.equals("x")){
					System.out.println("Exiting editor...");
					break;
				} else {
					System.out.println("Invalid string entered.");
				}	
			} while (!option.equals("k") && !option.equals("v"));
		} else {
			System.out.println("Key not found!");
		}
		return map;
	}
}
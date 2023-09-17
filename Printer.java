import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Printer {
	public void print(LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim) {
		MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
		ArrayList<String> keyArrayList = malCreator.createKeyArrayList(map); 
		ArrayList<String> valueArrayList = malCreator.createValueArrayList(map);
		
		int counter = 0;
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {
				System.out.print(keyArrayList.get(counter) + ":" + valueArrayList.get(counter) + " ");
				counter++;	
			}
			System.out.println();
		}		
	}
}
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MapAndArrayListCreator {
	
	//stores generated random key-keyvalue-value pairs to map
	public LinkedHashMap<String, ArrayList<String>> createRandomMap(int keyLength, int valueLength, ArrayList<Integer> dim) {
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		StringGenerator sg = new StringGenerator();
		for (int i = 0; i < dim.size(); i++) {
			for (int j = 0; j < dim.get(i); j++) {				
				String key = sg.getString(keyLength);
				while (map.containsKey(key)) {
					key = sg.getString(keyLength); //generates new key if there is duplicate
				}
				String value = sg.getString(valueLength);
				map.put(key, new ArrayList<String>());
				map.get(key).add(key+value);
				map.get(key).add(value);
			}
		}
		return map;
	}
	
	//creates arraylist of keys
	public ArrayList<String> createKeyArrayList(LinkedHashMap<String, ArrayList<String>> map) {
		ArrayList<String> keyArrayList = new ArrayList<String>();
			for (String key : map.keySet()) {
				keyArrayList.add(key);
			}
		return keyArrayList;
	}
	
	//creates arraylist of values
	public ArrayList<String> createValueArrayList(LinkedHashMap<String, ArrayList<String>> map) {
		//flattens the arraylist values to create a single arraylist of value pairs arraylist 	
		ArrayList<String> valueArrayListPairs = new ArrayList<String>();
		for (ArrayList<String> innerList : map.values()) {
			for (String value : innerList) {
				valueArrayListPairs.add(value);
			}
		}
		//creates arraylist of values from value pairs arraylist
        ArrayList<String> valueArrayList = new ArrayList<String>();
        for (int i = 1; i < valueArrayListPairs.size(); i = i + 2) {
        	valueArrayList.add(valueArrayListPairs.get(i));
        }
		return valueArrayList;
	}
}
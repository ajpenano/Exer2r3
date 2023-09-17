import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RowAdder {
	public ArrayList<Integer> updateDim(ArrayList<Integer> dim) {
		UserInput ui = new UserInput();
		int columns = ui.inputColumns();
		dim.add(columns);
		return dim;
	}
	
	public LinkedHashMap<String, ArrayList<String>> addRow(String text, LinkedHashMap<String, ArrayList<String>> map, ArrayList<Integer> dim) {
		UserInput ui = new UserInput();
		int keyLength = ui.inputKeyLength();
		int valueLength = ui.inputValueLength();
		
		StringGenerator sg = new StringGenerator();
		for (int j = 0; j < dim.get(dim.size()-1); j++) {				
			String key = sg.getString(keyLength);
			while (map.containsKey(key)) {
				key = sg.getString(keyLength); //generates new key if there is duplicate
			}
			String value = sg.getString(valueLength);
			map.put(key, new ArrayList<String>());
			map.get(key).add(key+value);
			map.get(key).add(value);
		}
		
		Reset rt = new Reset();
		rt.saveNewTable(text, map, dim);
		return map;
	}
}
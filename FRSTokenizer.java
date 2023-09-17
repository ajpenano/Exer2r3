import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class FRSTokenizer {
	
	public ArrayList<String> createListOfLines(String text) {
		ArrayList<String> listOfLines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(text));
			System.out.println("File: " + text);
			System.out.print("File exists");
			String line = br.readLine();
			while (line != null) {
				listOfLines.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + text);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error reading the file: " + text);
			e.printStackTrace();
		} 
		return listOfLines;
	}
	
	public ArrayList<ArrayList<Object>> getKeyValueAndDimFromList(ArrayList<String> listOfLines) {
		int tokenCounterPerRow;
		ArrayList<Object> extractedKeyValueArrayList = new ArrayList<Object>();
		ArrayList<Object> dim = new ArrayList<Object>();
		ArrayList<ArrayList<Object>> keyValueDim = new ArrayList<ArrayList<Object>>();
		for (int i = 0; i < listOfLines.size(); i++) {
			tokenCounterPerRow = 0;
			StringTokenizer row = new StringTokenizer(listOfLines.get(i), " ");
			while (row.hasMoreTokens()) {
				extractedKeyValueArrayList.add(row.nextToken());
				tokenCounterPerRow++;
			}
			dim.add(tokenCounterPerRow);
		}
		keyValueDim.add(extractedKeyValueArrayList);
		keyValueDim.add(dim);
		return keyValueDim;
	}
	
	public ArrayList<String> getKeyArrayList(ArrayList<String> extractedKeyValueArrayList, String text) {
		ArrayList<String> keyArrayList = new ArrayList<String>();
		InvalidFileContents ifc = new InvalidFileContents();
		
		try {
			for (int i = 0; i < extractedKeyValueArrayList.size(); i++) {
				StringTokenizer cell = new StringTokenizer(extractedKeyValueArrayList.get(i), ":");
				while (cell.hasMoreTokens()) {
					keyArrayList.add(cell.nextToken());
					cell.nextToken();
				}
			}	
		} catch (NoSuchElementException e) {
			ifc.generateNewTable(text);
			
		} 
		return keyArrayList;
	}
	
	public ArrayList<String> getValueArrayList(ArrayList<String> extractedKeyValueArrayList) {
		ArrayList<String> valueArrayList = new ArrayList<String>();
		for (int i = 0; i < extractedKeyValueArrayList.size(); i++) {
			StringTokenizer cell = new StringTokenizer(extractedKeyValueArrayList.get(i), ":");
			while (cell.hasMoreTokens()) {
				cell.nextToken();
				valueArrayList.add(cell.nextToken());
			}
		}
		return valueArrayList;
	}
	
	public LinkedHashMap<String, ArrayList<String>> 
			createMapFromKeyAndValueArrayLists(ArrayList<String> keyArrayList, ArrayList<String> valueArrayList) {
		LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
		for (int i = 0; i < keyArrayList.size(); i++) {
			String key = keyArrayList.get(i);
			String value = valueArrayList.get(i);
			map.put(key, new ArrayList<String>());
			map.get(key).add(key+value);
			map.get(key).add(value);
		}
		return map;
	}
}
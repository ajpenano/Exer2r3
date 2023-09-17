import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ExistingOrNewFile {
	
	FRSTokenizer st = new FRSTokenizer();
	MapAndArrayListCreator malCreator = new MapAndArrayListCreator();
	InvalidFileContents ifc = new InvalidFileContents();
	
	LinkedHashMap<String, ArrayList<String>> map;
	ArrayList<Integer> dim = new ArrayList<Integer>();
	
	void checkExistingOrNewFile(boolean existence, String text, Printer p, Reset rt) {
		if (existence == true) {
			//creates arraylist of lines
			ArrayList<String> listOfLines = st.createListOfLines(text);
			
			//breaks all lines into arraylist of key:values and gets dim per row
			ArrayList<ArrayList<Object>> keyValueDim = new ArrayList<ArrayList<Object>>();
			keyValueDim = st.getKeyValueAndDimFromList(listOfLines);
			ArrayList<String> extractedKeyValueArrayList = new ArrayList<>();
			//object to String converter
			for (Object keyValue : keyValueDim.get(0)) {
				extractedKeyValueArrayList.add(keyValue.toString());
			}
			//object to Integer converter
			for (Object dimPerRow : keyValueDim.get(1)) {
				this.dim.add(((Integer) dimPerRow).intValue());
			}
				
			ArrayList<String> keyArrayList = st.getKeyArrayList(extractedKeyValueArrayList, text);
			ArrayList<String> valueArrayList = st.getValueArrayList(extractedKeyValueArrayList);
			
			this.map = st.createMapFromKeyAndValueArrayLists(keyArrayList, valueArrayList);
			if (map.size()!=dim.stream().mapToInt(Integer::intValue).sum()) {
				ifc.generateNewTable(text);
			} else {
				System.out.println(". Below are the contents:");
				p.print(map, dim);
			}
			
		} 
		
		else {
			System.out.println("File does not exist. We will instead generate a new one.");
			this.dim = rt.resetDim();			
			this.map = malCreator.createRandomMap(3, 3, dim); 

			System.out.println("NEW TABLE");
			System.out.println("(Saved in " + text + ")");

			p.print(map, dim);			
			
			ArrayList<String> keyArrayList = malCreator.createKeyArrayList(map); 
			ArrayList<String> valueArrayList = malCreator.createValueArrayList(map);
				
			try {
			    FileWriter fstream = new FileWriter(text);
			    BufferedWriter info = new BufferedWriter(fstream);
				int counter = 0;
				String textEntry = "";
				for (int i = 0; i < dim.size(); i++) {
					for (int j = 0; j < dim.get(i); j++) {
						textEntry = textEntry + keyArrayList.get(counter) + ":" + valueArrayList.get(counter) + " ";
						counter++;	
					}
					textEntry = textEntry + "\r\n";
				}		
				info.write(textEntry);
			    info.close();
			} catch (Exception e) {
			    System.out.println("A write error has occurred");
			}
			
		}
	}
}

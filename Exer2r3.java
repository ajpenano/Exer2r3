import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Exer2r3 {

	public static void main(String[] args) {
		
		FileValidator fv = new FileValidator();
		String text = fv.run(args);
		boolean existence = fv.existence;
		
		Searcher s = new Searcher();
		Editor e = new Editor();
		Printer p = new Printer();
		Reset rt = new Reset();
		Sorter sort = new Sorter();
		RowAdder ra = new RowAdder();
		
		ExistingOrNewFile eonf = new ExistingOrNewFile();
		eonf.checkExistingOrNewFile(existence, text, p, rt);
		
		LinkedHashMap<String, ArrayList<String>> map = eonf.map;
		ArrayList<Integer> dim = eonf.dim;
		
		String option;
		while(true) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nEnter the small letter representing the action you like:"
					+ "\nSearch(s), Edit(d), Print(p), Reset(r), Sort(o), Add Row(w), Exit(x)"
					+ "\nYour choice: ");
			option = scanner.nextLine();
		
			switch (option) {
			
			case "s":
				s.search(map, dim);
				break;
				
			case "d":
				map = e.edit(text, map, dim);		
				break;
				
			case "p":
				p.print(map, dim);
				break;
				
			case "r":				
				rt.confirmReset(text, scanner);
				dim = rt.dim;
				map = rt.map;
				break;
			
			case "o":
				map = sort.sortTableAscending(text, map, dim, scanner);
				break;
				
			case "w":
				dim = ra.updateDim(dim);
				map = ra.addRow(text, map, dim);
				break;
				
			case "x":
				System.out.println("Exiting the program...");
				scanner.close();
				System.exit(0);
				break;
				
			default:
				System.out.println("This is not a valid menu option. Please choose again.");
				break;
			}
		}
	}

}
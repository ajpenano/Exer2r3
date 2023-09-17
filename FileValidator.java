import java.io.File;
import java.io.IOException;

public class FileValidator {
	String text = "file.txt";
	boolean existence;
	
	String run(String[] args) {
		
		File currDir = new File(".");
		String currDirPath = currDir.getAbsolutePath();
		currDirPath = currDirPath.substring(0,currDirPath.length()-1);
		
		if (args.length == 0) {
			this.text = currDirPath + "file.txt";
			File file = new File(text);
			this.existence = file.exists();
		} else if (args[0].endsWith(".txt")) {
			this.text = currDirPath + args[0];
			File file = new File(text);
			this.existence = file.exists();
			if (existence == false) {
				try {
					validateStringFilenameUsingIO(text);
				} catch (IOException ioe) {
					//ioe.printStackTrace();
					System.out.println("Invalid file name. Try using file.txt instead.");
					System.exit(0);
				}	
			}
		} else {
			System.out.println("Invalid file name. Try using file.txt instead.");
			System.exit(0);
		}			
		return text;
	}
	
	public void validateStringFilenameUsingIO(String filename) throws IOException {
	    File file = new File(filename);
	    boolean created = false;
			try {
				created = file.createNewFile();
			} finally {
				if (created) {
					file.delete();
			}
		}
	}
	
}

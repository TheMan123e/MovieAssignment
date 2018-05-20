package s3722763.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Class: FileHandler
 * Description: This class handles the saving and loading of files
 * Author: Daniel Miskimmin	- 3722763
 */
public class FileHandler {
	private final String DELIMITER = ":";
	
	/*
	 * ALGORIGHM
	 * BEGIN
	 * 		IF file doesn't exist
	 * 			IF backup file doesn't exist
	 * 				CREATE new file with file name
	 * 				RETURN empty string
	 * 		LOAD file
	 * 		RETURN string created from loading file
	 * END
	 */
	public String load(String fileName) throws IOException{
		File file = new File(fileName);
		
		if (!file.exists()) {
			file = new File(fileName + "_backup");
			if (!file.exists()) {
				file = new File(fileName);
				file.createNewFile();
			}
			return "";
		}
		
		Scanner input = new Scanner(file);
		
		String result = "";
		input.useDelimiter("");
		while(input.hasNext()) {
			 result += input.next();
		}
		
		return result;
	}
	
	public void save(Object toSave, String fileName) {
		File newFile = new File(fileName);
		boolean createBackup = true;
		
		if (!newFile.exists()) {
			newFile = new File(fileName + "_backup");
			if(!newFile.exists()) {
				newFile = new File(fileName);
				try {
					newFile.createNewFile();
					createBackup = false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			PrintWriter out;
			
			File backupFile = new File(fileName + "_backup");
			if (!backupFile.exists()) {
				backupFile.createNewFile();
			}
			
			if (createBackup) {
				String content = load(newFile.getName());
				out = new PrintWriter(new FileOutputStream(backupFile.getName()));
				out.write(content);
				out.close();
			}
			
			out = new PrintWriter(new FileOutputStream(fileName));
			String a = toSave.toString();
			out.write(toSave.toString());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getDelimiter() {
		return DELIMITER;
	}
}

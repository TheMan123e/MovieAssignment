package s3722763.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

//TODO: Add support for backups
public class FileHandler {
	private final String DELIMITER = ":";
	public String load(String fileName) throws IOException{
		File file = new File(fileName);
		
		if (!file.exists()) {
			file.createNewFile();
			//TODO: Add exception
			//TODO: Check for backup
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

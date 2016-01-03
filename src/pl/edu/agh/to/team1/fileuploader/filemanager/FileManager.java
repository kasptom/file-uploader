package pl.edu.agh.to.team1.fileuploader.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * FileManager creates directories
 * for choosen user, for each of
 * tasks which sollution(s) he has sent
 */

public class FileManager {
	OutputStream outputStream = null;
	
	
	public void handleFile(InputStream stream, String fileName){
		try {
			File file =  new File("TMP/" + fileName);
			boolean append = true;
			file.getParentFile().mkdirs();
			if(!file.exists()){
				file.createNewFile();
				System.out.println("Created file: " + file.getAbsolutePath());
			}else{
				System.out.println("File: " + file.getAbsolutePath() + " already exists");
			}
			
			outputStream = new FileOutputStream(file, append);
			
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = stream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		//	outputStream.flush();
		//	outputStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

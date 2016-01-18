package pl.edu.agh.to.team1.fileuploader.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

public class TestUserToCompiler {
	
	public void testCompiler(){
		try{
			InputStream fileStream = new FileInputStream("TMP/HelloWorld!");
			byte encArr[] = IOUtils.toByteArray(fileStream);
			String base64FileString =  new String(Base64.getEncoder().encode(encArr));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	byte[] fileData; 
}

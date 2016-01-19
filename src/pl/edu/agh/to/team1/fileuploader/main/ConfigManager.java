package pl.edu.agh.to.team1.fileuploader.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
	
	
	public void setValue(String key, String newValue){
		//replaceSelected(String replaceWith, String type)
	    try {
	        // input the file content to the String "input"
	        BufferedReader file = new BufferedReader(new FileReader("serv-config/config"));
	        String line;String input = "";

	        while ((line = file.readLine()) != null) input += line + '\n';

	        file.close();

	        System.out.println(input); // check that it's inputted right

	        String lineWithKey = input.substring(input.indexOf(key), input.indexOf("\n", input.indexOf(key)));
	        System.out.println(lineWithKey);
	        
	        
	        input = input.replace(lineWithKey, key + " = " + newValue);

	        // check if the new input is right
	        System.out.println("----------------------------------"  + '\n' + input);

	        // write the new String with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream("serv-config/config");
	        fileOut.write(input.getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        if(e instanceof IndexOutOfBoundsException){
	        	System.out.println("specified key does not exist");
	        }else{
	        	e.printStackTrace();
	        }
	        
	    }
	}
	
	public String getValue(String key){
		BufferedReader file;
		String value = null;
		
		try {
			file = new BufferedReader(new FileReader("serv-config/config"));
			
			String line;String input = "";

		    while ((line = file.readLine()) != null){
		    	if(line.startsWith(key)){
		    		System.out.println(line);
		    		value = line.substring(line.indexOf('=')+1).trim();
		    		break;
		    	}
		    }

		     file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}

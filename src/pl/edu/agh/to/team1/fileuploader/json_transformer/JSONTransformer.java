package pl.edu.agh.to.team1.fileuploader.json_transformer;

import java.io.File;
import java.io.OutputStream;

public abstract class JSONTransformer {
	
	/*
	 * wraps information to JSON file
	 * to send it via socket
	 */
	public File wrap(){
		
		return null;
	}
	/*
	 * gets information from the file 
	 * received via socket
	 */
	public void unwrap(File jsonFile){
		return;
	}
	
	public void process(OutputStream stream){
		return;
	}
}

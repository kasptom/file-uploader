package pl.edu.agh.to.team1.fileuploader.model;

import java.io.File;
import java.util.List;

public class Student extends User {
	private List<File> files;
	
	private File chooseFile() {
		File tmp = new File("");
		return tmp;
	}
}

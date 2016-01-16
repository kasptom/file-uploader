package pl.edu.agh.to.team1.fileuploader.model;

import java.io.File;
import java.util.List;

public class Student extends User {
	@SuppressWarnings("unused")
	private List<File> files;
	
	@SuppressWarnings("unused")
	private File chooseFile() {
		File tmp = new File("");
		return tmp;
	}
}

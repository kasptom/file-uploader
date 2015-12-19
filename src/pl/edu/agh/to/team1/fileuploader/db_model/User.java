package pl.edu.agh.to.team1.fileuploader.db_model;

import java.util.Set;

public class User {
	private long id;
	
	private String name;
	private String surname;
	private String type;

	private Set<Solution> solutions;

	public User(){}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
	}

	
	
}

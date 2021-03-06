package pl.edu.agh.to.team1.fileuploader.db_model;

import java.util.Date;
import java.util.Set;

public class Task {
	private long id;
	
	private String name;
	private Date deadline;
	private int maxScore;
	private Set<Solution> solutions;
	
	public Task(){}
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public Set<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<Solution> solutions) {
		this.solutions = solutions;
	}
	
	public void addSolution(Solution solution) {
		this.addSolution(solution);
	}
	
}

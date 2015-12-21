package pl.edu.agh.to.team1.fileuploader.db_model;

import java.util.Date;

public class Solution {
	private long id;
	
	private String filePath;
	private Date addedDate;
	private Date expDate;
	private Date assesedDate;
	private double result;
	private String resultType;
	private User user;
	private Task task;
	
	public Solution(){}
		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getAssesedDate() {
		return assesedDate;
	}

	public void setAssesedDate(Date assesedDate) {
		this.assesedDate = assesedDate;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}

package pl.edu.agh.to.team1.fileuploader.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pl.edu.agh.to.team1.fileuploader.persistence.*;
import pl.edu.agh.to.team1.fileuploader.db_model.Solution;
import pl.edu.agh.to.team1.fileuploader.db_model.Task;
import pl.edu.agh.to.team1.fileuploader.db_model.User;

/*
 * DBManager operates on File Uploader's 
 * database
 */
public class DBManager {
	
	@SuppressWarnings("unused")
	private void saveFile(String filePath, User user, Task task) {
		
		HibernateUtils.getSession().close();
		
		Solution solution = new Solution();
		solution.setFilePath(filePath);
		solution.setUser(user);
		
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.persist(solution);
		user.addSolution(solution);
		session.update(user);
		task.addSolution(solution);
		session.update(task);
		
		transaction.commit();
		session.close();
	}
	
	@SuppressWarnings("unused")
	private void saveResultOfTask(Solution solution, double result, String resultType) {
		HibernateUtils.getSession().close();
		
		solution.setResult(result);
		solution.setResultType(resultType);
		
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(solution);
		
		transaction.commit();
		session.close();
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private List<Double> getResultOfTask(long taskId) {
		HibernateUtils.getSession().close();
		
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		
		List<Double> result = session.createQuery("select s.result from Solution s inner join Task t " 
				+ "on s.taskId = t.taskId where s.taskId is :taskId").setParameter("taskId", taskId).list();
		
		
		transaction.commit();
		session.close();
		
		return result;
	}
	
	
}

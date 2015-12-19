package pl.edu.agh.to.team1.fileuploader.persistence;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	
	static
	{
		Configuration configuration = new Configuration();
		configuration.configure(new File("hibernate.cfg.xml"));
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}

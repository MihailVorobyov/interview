package ru.vorobev.interview.homework_05.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.vorobev.interview.homework_05.entities.Student;
import ru.vorobev.interview.homework_05.services.SessionFactoryService;

public class Preparer {
	public static void prepare() {
		SessionFactory sessionFactory = SessionFactoryService.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		
		String[] sql = {"USE Students;\n",
			"DROP TABLE IF EXISTS students;\n",
			"CREATE TABLE students (\n" +
			"    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
			"    name VARCHAR(25) NOT NULL,\n" +
			"    mark INT,\n" +
			"    INDEX id_idx (id ASC) VISIBLE\n" +
			");"};
		
		Query query;
		for (String s : sql) {
			query = session.createNativeQuery(s);
			query.executeUpdate();
		}
		transaction.commit();
		session.close();
	}
}

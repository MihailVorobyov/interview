package ru.vorobev.interview.homework_05.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.vorobev.interview.homework_05.entities.Student;
import ru.vorobev.interview.homework_05.services.SessionFactoryService;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
	
	private SessionFactory sessionFactory;
	private Session session;
	
	public StudentDaoImpl() {
		this.sessionFactory = SessionFactoryService.getSessionFactory();
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void insert(Student student) {
		session = getCurrentSession();
		session.beginTransaction();
		getCurrentSession().save(student);
		session.getTransaction().commit();
	}
	
	@Override
	public void insert(List<Student> students) {
		session = getCurrentSession();
		session.beginTransaction();
		for(Student student : students) {
			getCurrentSession().save(student);
		}
		session.getTransaction().commit();
	}
	
	@Override
	public void deleteById(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Wrong id (" + id + ")");
		}
		Student student = getOneById(id);
		if (student == null) {
			return;
		}
		session = getCurrentSession();
		session.beginTransaction();
		getCurrentSession().delete(student);
		session.getTransaction().commit();
	}
	
	@Override
	public void delete(Student student) {
		if (student == null) {
			return;
		}
		session = getCurrentSession();
		session.beginTransaction();
		getCurrentSession().delete(student);
		session.getTransaction().commit();
	}
	
	@Override
	public void update(Student student) {
	
	}
	
	@Override
	public Student getOneById(Long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Wrong id (" + id + ")");
		}
		session = getCurrentSession();
		session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.getTransaction().commit();
		return student;
	}
	
	@Override
	public List<Student> getAll() {
		session = getCurrentSession();
		session.beginTransaction();
		List<Student> students = session.createQuery("FROM Student").getResultList();
		session.getTransaction().commit();
		return students;
	}
}

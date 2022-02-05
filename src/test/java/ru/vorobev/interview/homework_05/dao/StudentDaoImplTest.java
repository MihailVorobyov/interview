package ru.vorobev.interview.homework_05.dao;

import org.junit.jupiter.api.*;
import ru.vorobev.interview.homework_05.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDaoImplTest {
	
	private static StudentDaoImpl studentDao = new StudentDaoImpl();
	
	@BeforeAll
	static void prepareTable() {
		Preparer.prepare();
	}
	
	// get test
	
	@Test
	@Order(1)
	void shouldReturn0() {
		List<Student> students = studentDao.getAll();
		assertEquals(0, students.size());
		assertNull(studentDao.getOneById(1L));
	}
	
	// insert() test
	
	@Test
	@Order(2)
	void shouldInsert1000recordingsIntoTableStudents() {
		add1000Students();
		List<Student> students = studentDao.getAll();
		assertEquals(1000, students.size());
	}
	
	
	// getOneById() test
	
	@Test
	@Order(3)
	void shouldReturnStudent7Name() {
		long id = 7;
		String student7Name = "Студент " + (id - 1);
		Student student7 = studentDao.getOneById(id);
		assertEquals(student7Name, student7.getName());
	}
	
	// delete() test
	
	@Test
	@Order(4)
	void shouldDeleteStudentWithId1TableStudents() {
		long id = 7;
		Student student1 = studentDao.getOneById(id);
		studentDao.deleteById(id);
		Student student2 = studentDao.getOneById(id);
		assertNotNull(student1);
		assertNull(student2);
	}
	
	void add1000Students() {
		List<Student> students = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			students.add(new Student("Студент " + i, new Random().nextInt(5)));
		}
		studentDao.insert(students);
	}
}

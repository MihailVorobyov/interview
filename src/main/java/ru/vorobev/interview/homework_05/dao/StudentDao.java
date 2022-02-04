package ru.vorobev.interview.homework_05.dao;

// Создать DAO-слой с операциями добавления, удаления, изменения сущности, выборки записи по ID и всех записей.

import ru.vorobev.interview.homework_05.entities.Student;

import java.util.List;

public interface StudentDao {
	
	void insert(Student student);
	
	void insert(List<Student> students);
	
	void deleteById(Long id);
	
	void delete(Student student);
	
	void update(Student student);
	
	Student getOneById(Long id);
	
	List<Student> getAll();
	
}

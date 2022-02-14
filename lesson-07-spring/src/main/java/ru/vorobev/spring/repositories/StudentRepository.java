package ru.vorobev.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vorobev.spring.entities.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	@Override
	<S extends Student> S save(S entity);
	
	@Override
	Optional<Student> findById(Long aLong);
	
	@Override
	List<Student> findAll();
	
	@Override
	void deleteById(Long aLong);
	
	
}

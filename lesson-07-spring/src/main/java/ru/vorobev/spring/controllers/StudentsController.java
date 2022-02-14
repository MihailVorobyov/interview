package ru.vorobev.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.spring.entities.Student;
import ru.vorobev.spring.repositories.StudentRepository;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentsController {
	
	StudentRepository repository;
	
	@Autowired
	public void setRepository(StudentRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	private String getAllStudents(Model model) {
		List<Student> studentList = repository.findAll();
		model.addAttribute("studentList", studentList);
		Student student = new Student();
		model.addAttribute("newStudent", student);
		Student studentToDelete = new Student();
		model.addAttribute("studentToDelete", studentToDelete);
		
		return "students";
	}
	
	@PostMapping("/add")
	private String saveStudent(Model model, @ModelAttribute("newStudent") Student student) {
		repository.save(student);
		model.addAttribute("student", student);
		return "redirect:/students";
	}
	
	@PostMapping("/delete")
	private String deleteStudent(Model model, @RequestParam("idToDelete") Long id) {
		repository.deleteById(id);
		return "redirect:/students";
	}
	
	@GetMapping("/edit")
	private String edit(Model model, @RequestParam("idToEdit") Long id) {
		
		Student studentToEdit = repository.findById(id).orElse(null);
		if (studentToEdit != null) {
			model.addAttribute("studentToEdit", studentToEdit);
		}
		return "edit-student";
	}
	
	@PostMapping("/edit")
	private String update(Model model, @ModelAttribute("studentToEdit") Student studentToEdit) {
		System.out.println(studentToEdit);
		repository.save(studentToEdit);
		return "redirect:/students";
	}
}

package ru.vorobev.spring.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "students")
@Data
public class Student implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "full_name")
	@NotNull
	private String name;
	
	@Column(name = "age")
	@NotNull
	private int age;
	
}

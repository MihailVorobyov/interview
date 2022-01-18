package ru.vorobev.interview.homework_01.task_01;

public class Main {
	
	public static void main(String[] args) {
		Person person = Person.getBuilder()
			.withFirstName("first")
			.withLastName("last")
			.withMiddleName("mddle")
			.withAge(15)
			.withGender("male")
			.withCountry("country")
			.withAddress("address")
			.withPhone("phone 7898798")
			.build();
		System.out.println(person);
	}
}

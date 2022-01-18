package ru.vorobev.interview.homework_01.task_01;

import lombok.Data;

@Data
public class Person {
	private String firstName;
	private String lastName;
	private String middleName; 
	private String country;
	private String address; 
	private String phone;
	private int age = -1;
	private String gender;
	
	public static Builder getBuilder() {
		return new Builder();
	}
	
	static class Builder {
		private Person person;
		
		private Builder () {
			person = new Person();
		}
		
		public Builder withFirstName(String firstName) {
			this.person.setFirstName(firstName);
			return this;
		}
		
		public Builder withLastName(String lastName) {
			this.person.setLastName(lastName);
			return this;
		}
		
		public Builder withMiddleName(String middleName) {
			this.person.setMiddleName(middleName);
			return this;
		}
		
		public Builder withCountry(String country) {
			this.person.setCountry(country);
			return this;
		}
		
		public Builder withAddress(String address) {
			this.person.setAddress(address);
			return this;
		}
		
		public Builder withPhone(String phone) {
			this.person.setPhone(phone);
			return this;
		}
		
		public Builder withAge(int age) {
			this.person.setAge(age);
			return this;
		}
		
		public Builder withGender(String gender) {
			this.person.setGender(gender);
			return this;
		}
		
		public Person build() {
			if (this.person.firstName == null || this.person.lastName == null || this.person.middleName == null || this.person.country == null
				|| this.person.address == null || this.person.phone == null || this.person.age < 0 || this.person.gender == null) {
				throw new RuntimeException("Недопустимое значение параметра");
			}
			return person;
		}
		
	}
}

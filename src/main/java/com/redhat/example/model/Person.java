package com.redhat.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String firstName;
	String lastName;
	
	public Person() {}
	
	public Person(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@NotNull(message = "入力は必須です。")
	@Size(max = 20, message = "{max}文字以下で入力してください。")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull(message = "入力は必須です。") 
	@Size(max = 20, message = "{max}文字以下で入力してください。")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return "Person{" +
				"id=\"" + id + "\", " +
				"firstName=\"" + firstName + "\", " +
				"lastName=\"" + lastName + "\"}";
	}
}

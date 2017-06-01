package com.redhat.example.web;

import javax.enterprise.inject.Model;

import com.redhat.example.model.Person;

@Model
public class PersonForm extends Person {

	public Person toEntity() {
		return new Person(getId(), getFirstName(), getLastName());
	}
}

package com.redhat.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redhat.example.model.Person;

public class PersonDao extends AbstractDao<Person,String> {
	public PersonDao() {
		entityClass = Person.class;
	}
	
	@PersistenceContext(unitName="ExamplePU") EntityManager em;
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}

}

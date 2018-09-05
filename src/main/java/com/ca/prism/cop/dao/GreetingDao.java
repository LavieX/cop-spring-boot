package com.ca.prism.cop.dao;

import java.util.*;

import javax.persistence.*;
import javax.transaction.*;

import org.springframework.stereotype.*;

import com.ca.prism.cop.model.*;

@Repository
@Transactional
public class GreetingDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void create(Greeting greeting) {
        entityManager.persist(greeting);
    }

    public void update(Greeting greeting) {
        entityManager.merge(greeting);
    }

    public Greeting getGreetingById(long id) {
        return entityManager.find(Greeting.class, id);
    }

    public void delete(long id) {
        Greeting greeting = getGreetingById(id);
        if (greeting != null) {
            entityManager.remove(greeting);
        }
    }

	@SuppressWarnings("unchecked")
	public List<Greeting> getAll() {
		return entityManager.createQuery("Select g from " + Greeting.class.getSimpleName() + " g").getResultList();
	}
}
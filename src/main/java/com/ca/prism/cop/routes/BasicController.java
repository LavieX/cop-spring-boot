package com.ca.prism.cop.routes;

import static java.lang.String.*;

import java.util.*;
import java.util.stream.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.ca.prism.cop.dao.*;
import com.ca.prism.cop.errors.*;
import com.ca.prism.cop.model.*;

@RestController
public class BasicController {

	@Autowired
	GreetingDao dao;
	
	@GetMapping(path = "/greeting", produces = { "application/json" })
	public List<Greeting> getAllHello(@RequestParam(value = "evensOnly", defaultValue = "false", required=false) boolean evensOnly) {
		List<Greeting> g = dao.getAll();
		if(evensOnly) {
			g = g.stream()
					.filter(greeting -> greeting.getId() % 2 != 0)
					.collect(Collectors.toList());
		}
		return g;
	}
	
	@PostMapping(path = "/greeting", produces = { "application/json" })
	@ResponseStatus(code=HttpStatus.CREATED)
	public Greeting createHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting g = new Greeting(new Name(name), "A random number: " + new Random().nextInt());
		dao.create(g);
		return g;
	}

	@GetMapping(path = "/greeting/{id}", produces = { "application/json" })
	public Greeting getHello(@PathVariable long id) {
		Greeting g = dao.getGreetingById(id);
		if(g == null) {
			throw new ResourceNotFoundException(format("Resource with the ID %d was not found", id));
		}
		return g;
	}
}

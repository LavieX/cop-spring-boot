package com.ca.prism.cop.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.*;
import lombok.extern.java.*;

@Entity
@Table(name = "Greeting")
@Data
@Log
public class Greeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	private final Name name;
	
	@JsonIgnore
	private final String randomString;
	
	@JsonInclude
	@Transient
	public String getGreeting() {
		log.info("Returning \"Hello, " + name.getName() + "\"");
		return "Hello, " + name.getName();
	}
}

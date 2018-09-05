package com.ca.prism.cop.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Name")
@Data
public class Name {

	@Id
	@GeneratedValue
	private long id;

	private final String name;
}

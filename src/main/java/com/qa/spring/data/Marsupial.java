package com.qa.spring.data;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Tells spring this is a table in a database
public class Marsupial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String species;

	private String colour;

	public Marsupial() {
		super();
	}

	public String getName() {
		return name;
	}

	public Marsupial(Integer id, String name, String species, String colour) {
		super();
		this.id = id;
		this.name = name;
		this.species = species;
		this.colour = colour;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Marsupial [name=" + name + ", species=" + species + ", colour=" + colour + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(colour, id, name, species);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Marsupial other = (Marsupial) obj;
		return Objects.equals(colour, other.colour) && Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(species, other.species);
	}

}

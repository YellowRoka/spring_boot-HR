package hu.webuni.hr.roka.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Company {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String location;
	@ManyToMany
	private List <Employer> emplyores;
	
	public Company(){}

	public Company(int id, String name, String location, List<Employer> emplyores) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.emplyores = emplyores;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Employer> getEmplyores() {
		return emplyores;
	}

	public void setEmplyores( List<Employer> emplyores) {
		this.emplyores = emplyores;
	}
	
	
}

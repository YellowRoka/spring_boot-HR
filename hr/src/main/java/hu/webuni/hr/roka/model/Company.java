package hu.webuni.hr.roka.model;

import java.util.Map;

public class Company {
	private long id;
	private String name;
	private String location;
	private Map<Long, Employer> emplyores;
	
	public Company(){}

	public Company(int id, String name, String location, Map<Long, Employer> emplyores) {
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

	public Map<Long, Employer> getEmplyores() {
		return emplyores;
	}

	public void setEmplyores( Map<Long, Employer> emplyores) {
		this.emplyores = emplyores;
	}
	
	
}
